package me.asta.flyPlugin.commands;

import me.asta.flyPlugin.FlyPlugin;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class FlyCommand implements CommandExecutor {

    private final ArrayList<Player> flyingPlayers = new ArrayList<>();
    private final FlyPlugin plugin;

    public FlyCommand(FlyPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (sender instanceof Player p) {
            if (p.hasPermission("flyplugin.fly")) {
                if (args.length == 0) {
                    setFly(p);
                } else if (args.length == 1) {
                    if (p.hasPermission("flyplugin.others")) {
                        Player target = Bukkit.getPlayer(args[0]);
                        setFly(target);
                    } else {
                        p.sendMessage(ChatColor.RED + "You don't have permission to do this");
                    }
                }
            }
        }

        return true;
    }

    private void setFly(Player player) {
        if (flyingPlayers.contains(player)) {
            player.setAllowFlight(false);
            flyingPlayers.remove(player);
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("fly-messages.off-message")));
        } else {
            player.setAllowFlight(true);
            flyingPlayers.add(player);
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("fly-messages.on-message")));
        }
    }
}