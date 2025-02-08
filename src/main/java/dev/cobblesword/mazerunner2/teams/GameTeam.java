package dev.cobblesword.mazerunner2.teams;

import dev.cobblesword.mazerunner2.princess.Princess;
import net.minecraft.world.item.ItemStack;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class GameTeam
{
    private String name;
    private List<UUID> players;
    private Color color;
    private Princess princess;

    public GameTeam(String name, Color color)
    {
        this.name = name;
        this.color = color;
        this.players = new ArrayList<>();
    }

    public void addPlayer(Player player)
    {
        this.players.add(player.getUniqueId());
        player.sendMessage("welcome to " + name + " team");
    }

    public void removePlayer(Player player)
    {
        this.players.remove(player.getUniqueId());
    }

    public void broadcast(String message)
    {
        for (UUID playerId : this.players)
        {
            Player player = Bukkit.getPlayer(playerId);
            if(player == null) continue;
            player.sendMessage(message);
        }
    }

    public int size() {
        return this.players.size();
    }
}
