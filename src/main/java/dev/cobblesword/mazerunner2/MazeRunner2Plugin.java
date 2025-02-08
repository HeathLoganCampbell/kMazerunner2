package dev.cobblesword.mazerunner2;

import dev.cobblesword.mazerunner2.lobby.Lobby;
import dev.cobblesword.mazerunner2.world.GameWorld;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class MazeRunner2Plugin extends JavaPlugin implements Listener
{
    private GameWorld gameWorld;
    @Override
    public void onEnable()
    {
        Lobby lobby = new Lobby(this);
        gameWorld = new GameWorld(1);
        Bukkit.getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void onChat(PlayerChatEvent event) {
        Player player = event.getPlayer();
        if(event.getMessage().contains("maze")) {
            gameWorld.teleport(player);
        }

        if(event.getMessage().contains("new")) {
            gameWorld.regenerateMaze();
        }
    }
}
