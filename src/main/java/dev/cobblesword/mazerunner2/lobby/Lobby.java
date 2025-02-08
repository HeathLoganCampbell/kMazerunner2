package dev.cobblesword.mazerunner2.lobby;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashSet;
import java.util.UUID;

public class Lobby implements Listener
{
    private HashSet<UUID> playeresWaiting = new HashSet<>();
    private boolean gameStarted = false;
    private int countdownTilGame = 10;

    public Lobby(JavaPlugin plugin)
    {
        Bukkit.getPluginManager().registerEvents(this, plugin);
        new BukkitRunnable() {
            @Override
            public void run()
            {
                update();
            }
        }.runTaskTimer(plugin, 0L, 20L);
    }

    public void update()
    {
        if(countdownTilGame <= 0)
        {
            gameStarted = true;
            playeresWaiting.clear();
            // Start game
            return;
        }

        if(countdownTilGame % 10 == 0 || countdownTilGame <= 9)
        {
            Bukkit.broadcastMessage(ChatColor.GREEN + "Game starting in " + countdownTilGame + " seconds");
        }

        countdownTilGame--;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event)
    {
        if(!gameStarted)
        {
            playeresWaiting.add(event.getPlayer().getUniqueId());
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event)
    {
        playeresWaiting.remove(event.getPlayer().getUniqueId());
    }

    @EventHandler
    public void onKick(PlayerKickEvent event)
    {
        playeresWaiting.remove(event.getPlayer().getUniqueId());
    }

    @EventHandler
    public void onDamage(EntityDamageEvent event)
    {
        if(gameStarted) return;
        event.setCancelled(true);
    }

    @EventHandler
    public void onFood(FoodLevelChangeEvent event)
    {
        if(gameStarted) return;
        event.setFoodLevel(20);
    }
}
