package dev.cobblesword.mazerunner2.game;

import dev.cobblesword.mazerunner2.world.GameWorld;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public class Game implements Listener
{
    private GameWorld gameWorld;

    public Game(JavaPlugin plugin)
    {
        this.gameWorld = new GameWorld(1);
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    public void init()
    {
        this.gameWorld.generateWorld();
    }

    public void start(List<Player> playerList)
    {
        for (Player player : playerList)
        {
            this.gameWorld.teleport(player);
        }
    }

    @EventHandler
    public void onChat(PlayerChatEvent event) {
        if(event.getMessage().contains("new")) {
            gameWorld.regenerateMaze();
        }
    }
}
