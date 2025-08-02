package me.asta.armorStandGUI.commands;

import me.asta.armorStandGUI.ArmorStandGUI;
import me.asta.armorStandGUI.utils.Menus;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ArmorStandCommand implements CommandExecutor {
    private static final ArmorStandGUI plugin = ArmorStandGUI.getInstance();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (sender instanceof Player p) {
            Menus.openMainMenu(p);
        }

        return true;
    }
}