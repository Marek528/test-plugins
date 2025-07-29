package me.asta.teleportBowPlugin.utils;

import me.asta.teleportBowPlugin.TeleportBowPlugin;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class TpBowUtil {
    private static final TeleportBowPlugin plugin = TeleportBowPlugin.getInstance();

    public static ItemStack createTpBow() {
        ItemStack tpBow = new ItemStack(Material.BOW, 1);
        ItemMeta tpBowMeta = tpBow.getItemMeta();

        tpBowMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("tpBow-name")));
        tpBowMeta.setLore(List.of(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("tpBow-description"))));
        tpBowMeta.addEnchant(Enchantment.INFINITY, 1, false);
        tpBowMeta.setUnbreakable(true);

        tpBow.setItemMeta(tpBowMeta);

        return tpBow;
    }
}