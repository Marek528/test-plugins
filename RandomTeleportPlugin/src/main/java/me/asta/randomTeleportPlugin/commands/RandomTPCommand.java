package me.asta.randomTeleportPlugin.commands;

import me.asta.randomTeleportPlugin.utils.TeleportUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class RandomTPCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (sender instanceof Player p) {
            if (args.length == 0) {
                Location rLoc = TeleportUtils.findSafeLocation(p);

                p.teleport(rLoc);
                p.sendMessage(ChatColor.BLUE + "Teleported to Random Location!!!");
                p.sendMessage(ChatColor.AQUA + "New Coordinates: " + ChatColor.LIGHT_PURPLE + rLoc.getX() + " " + rLoc.getY() + " " + rLoc.getZ());
            } else if (args.length == 1) {
                if (p.hasPermission("rtp.others")) {
                    Player target = Bukkit.getPlayer(args[0]);
                    Location rLoc = TeleportUtils.findSafeLocation(p);

                    if (target != null) {
                        target.teleport(rLoc);

                        target.sendMessage(ChatColor.GREEN + p.getDisplayName() + ChatColor.GOLD + " just Random Teleported you!");
                        target.sendMessage(ChatColor.AQUA + "New Coordinates: " + ChatColor.LIGHT_PURPLE + rLoc.getX() + " " + rLoc.getY() + " " + rLoc.getZ());

                        p.sendMessage(ChatColor.RED + "Player successfully teleported to: " + ChatColor.LIGHT_PURPLE + rLoc.getX() + " " + rLoc.getY() + " " + rLoc.getZ());
                    } else {
                        p.sendMessage(ChatColor.RED + "Couldn't find provided player!");
                    }
                } else {
                    p.sendMessage(ChatColor.RED + "You do not have permission to do that!");
                }
            }
        }

        return true;
    }
}