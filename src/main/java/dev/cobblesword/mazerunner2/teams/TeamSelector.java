package dev.cobblesword.mazerunner2.teams;

import org.bukkit.entity.Player;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

public class TeamSelector
{
    private List<GameTeam> teams;

    public TeamSelector(List<GameTeam> teams)
    {
        this.teams = teams;
    }

    public GameTeam getBestOption(Player player)
    {
        GameTeam bestTeam = null;
        for (GameTeam team : teams)
        {
            if(bestTeam == null)
            {
                bestTeam = team;
                continue;
            }

            if(bestTeam.size() > team.size())
            {
                bestTeam = team;
            }
        }

        return bestTeam;
    }
}
