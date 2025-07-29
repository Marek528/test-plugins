package me.asta.joinLeavePlugin.listeners;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class JoinLeaveListener implements Listener
{
    @EventHandler
    public void onJoin(PlayerJoinEvent e)
    {
        Player p = e.getPlayer();

        if (p.hasPlayedBefore())
        {
            e.setJoinMessage(ChatColor.YELLOW + "Welcome back to the server " + ChatColor.GOLD + "" + ChatColor.BOLD + p.getDisplayName() + ChatColor.YELLOW + "!");
        }
        else
        {
            e.setJoinMessage(ChatColor.RED + "WELCOME NEWCOMER " + ChatColor.YELLOW + p.getDisplayName() + ChatColor.RED + "! Hope you will enjoy your stay!");
        }
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent e)
    {
        Player p = e.getPlayer();
        e.setQuitMessage(ChatColor.MAGIC + p.getDisplayName() + ChatColor.LIGHT_PURPLE + " has left! Bye o/");
    }
}
