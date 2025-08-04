package me.asta.banGUI.commands;

import me.asta.banGUI.utils.BanMenusUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BanGUICommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (sender instanceof Player p) {
            BanMenusUtils.openPlayerList(p);
        }

        return true;
    }
}
