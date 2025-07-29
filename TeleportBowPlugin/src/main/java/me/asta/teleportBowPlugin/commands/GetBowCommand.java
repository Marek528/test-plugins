package me.asta.teleportBowPlugin.commands;

import me.asta.teleportBowPlugin.TeleportBowPlugin;
import me.asta.teleportBowPlugin.utils.TpBowUtil;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class GetBowCommand implements CommandExecutor {
    private static final TeleportBowPlugin plugin = TeleportBowPlugin.getInstance();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (sender instanceof Player p) {
            if (!p.hasPermission("tp-bow.get-bow")) {
                p.sendMessage(ChatColor.RED + "You do not have permission to use this command!");
                return true;
            }

            String bowName = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("tpBow-name"));

            for (ItemStack is : p.getInventory().getContents()) {
                if (is != null && is.getItemMeta().getDisplayName().equalsIgnoreCase(bowName)) {
                    p.sendMessage("You already own " + bowName);
                    return true;
                }
            }

            p.getInventory().addItem(TpBowUtil.createTpBow());
            p.getInventory().setItem(9, new ItemStack(Material.ARROW));
            p.sendMessage("You successfully obtained " + bowName);
        }

        return true;
    }
}
