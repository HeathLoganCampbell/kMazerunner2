package dev.cobblesword.mazerunner2;

import dev.cobblesword.mazerunner2.game.Game;
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
    @Override
    public void onEnable()
    {
        Game game = new Game(this);
        Lobby lobby = new Lobby(this, game);
    }
}
