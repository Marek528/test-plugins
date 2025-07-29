package me.asta.playerMenu.listeners;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class PlayerMenuListener implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {

        if (e.getView().getTitle().equalsIgnoreCase("Custom Menu")) {

            if (e.isRightClick()) return;

            // can't move the item
            e.setCancelled(true);

            Player p = (Player) e.getWhoClicked();

            switch (e.getRawSlot()) {

                case 9:
                    p.setFoodLevel(40);
                    p.sendMessage("You're full now!");
                    break;

                case 13:
                    p.setHealth(0);
                    p.sendMessage(ChatColor.DARK_RED + "You DIED!");
                    break;

                case 17:
                    ItemStack swordToGive = new ItemStack(Material.NETHERITE_SWORD, 1);
                    ItemMeta swordToGiveMeta = swordToGive.getItemMeta();

                    swordToGiveMeta.addEnchant(Enchantment.SHARPNESS, 5, false);
                    swordToGiveMeta.addEnchant(Enchantment.LOOTING, 3, false);
                    swordToGiveMeta.addEnchant(Enchantment.UNBREAKING, 3, false);
                    swordToGiveMeta.addEnchant(Enchantment.MENDING, 1, false);
                    swordToGiveMeta.addEnchant(Enchantment.FIRE_ASPECT, 2, false);
                    swordToGiveMeta.addEnchant(Enchantment.SWEEPING_EDGE, 3, false);

                    swordToGive.setItemMeta(swordToGiveMeta);

                    p.getInventory().addItem(swordToGive);
                    p.sendMessage(ChatColor.GOLD + "Here's your sword warrior!");
                    break;
            }

        }

    }
}