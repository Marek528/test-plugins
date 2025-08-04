package me.asta.banGUI.listeners;

import me.asta.banGUI.BanGUI;
import me.asta.banGUI.utils.BanMenusUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.time.Duration;

public class BanInventoryListener implements Listener {
    @EventHandler
    public void onPlayerHeadClick(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();

        if (e.getView().getTitle().equalsIgnoreCase(ChatColor.AQUA + "Player List")) {
            Player whoToBan = BanGUI.getInstance().getServer().getPlayerExact(ChatColor.stripColor(e.getCurrentItem().getItemMeta().getDisplayName()));

            if (whoToBan == null) {
                p.sendMessage("No such player found!");
                p.closeInventory();
                return;
            }

            BanMenusUtils.openConfirmMenu(p, whoToBan);

            e.setCancelled(true);

        } else if (e.getView().getTitle().equalsIgnoreCase( ChatColor.RED + "Confirm Ban")) {
            if (e.getCurrentItem().getType() == Material.WOODEN_AXE) {
                String playerToBanName = ChatColor.stripColor(e.getClickedInventory().getItem(4).getItemMeta().getDisplayName());
                Player playerToBan = BanGUI.getInstance().getServer().getPlayerExact(playerToBanName);

                playerToBan.ban("imagine getting banned", Duration.ofMinutes(5), p.getDisplayName(), true);
                Bukkit.broadcastMessage(ChatColor.DARK_RED + "Player " + ChatColor.BOLD + playerToBanName + ChatColor.DARK_RED + " was banned!");
                p.closeInventory();

            } else if (e.getCurrentItem().getType() == Material.BARRIER) {
                BanMenusUtils.openPlayerList(p);
            }

            e.setCancelled(true);
        }
    }
}
