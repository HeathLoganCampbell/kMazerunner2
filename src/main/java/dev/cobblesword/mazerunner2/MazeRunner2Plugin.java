package dev.cobblesword.mazerunner2;

import dev.cobblesword.mazerunner2.world.GameWorld;
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
        gameWorld = new GameWorld(1);
    }

    @EventHandler
    public void onChat(PlayerChatEvent event) {
        Player player = event.getPlayer();
        gameWorld.teleport(player);
    }
}
