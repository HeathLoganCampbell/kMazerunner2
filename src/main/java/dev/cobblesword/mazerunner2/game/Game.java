package dev.cobblesword.mazerunner2.game;

import dev.cobblesword.mazerunner2.teams.GameTeam;
import dev.cobblesword.mazerunner2.teams.TeamSelector;
import dev.cobblesword.mazerunner2.world.GameWorld;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public class Game implements Listener
{
    private GameWorld gameWorld;
    private List<GameTeam> teams;

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
        this.teams = new ArrayList<>();
        this.teams.add(new GameTeam("Red", Color.RED));
        this.teams.add(new GameTeam("Blue", Color.BLUE));
        this.teams.add(new GameTeam("Green", Color.GREEN));
        TeamSelector teamSelector = new TeamSelector(this.teams);

        for (Player player : playerList)
        {
            GameTeam bestOption = teamSelector.getBestOption(player);
            bestOption.addPlayer(player);
            this.gameWorld.teleport(player);
        }
    }

    public void update()
    {

    }

    @EventHandler
    public void onChat(PlayerChatEvent event) {
        if(event.getMessage().contains("new")) {
            gameWorld.regenerateMaze();
        }
    }
}
