package me.asta.playerMenu.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class PlayerMenuCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        if (sender instanceof Player p) {

            Inventory inv = Bukkit.createInventory(p, 27, "Custom Menu");

            ItemStack feed = new ItemStack(Material.COOKED_CHICKEN);
            ItemStack death = new ItemStack(Material.TNT_MINECART);
            ItemStack sword = new ItemStack(Material.NETHERITE_SWORD);

            ItemMeta feedMeta = feed.getItemMeta();
            feedMeta.setDisplayName(ChatColor.GREEN + "Feed");
            feedMeta.setLore(List.of(ChatColor.DARK_AQUA + "No more hunger"));

            ItemMeta deathMeta = death.getItemMeta();
            deathMeta.setDisplayName(ChatColor.DARK_RED + "DEATH");
            deathMeta.setLore(List.of(ChatColor.DARK_AQUA + "Inevitable death"));

            ItemMeta swordMeta = sword.getItemMeta();
            swordMeta.setDisplayName(ChatColor.GREEN + "Give Sword");
            swordMeta.setLore(List.of(ChatColor.DARK_AQUA + "The strongest sword in the world"));

            swordMeta.addEnchant(Enchantment.SHARPNESS, 5, false);
            swordMeta.addEnchant(Enchantment.LOOTING, 3, false);
            swordMeta.addEnchant(Enchantment.UNBREAKING, 3, false);
            swordMeta.addEnchant(Enchantment.MENDING, 1, false);
            swordMeta.addEnchant(Enchantment.FIRE_ASPECT, 2, false);
            swordMeta.addEnchant(Enchantment.SWEEPING_EDGE, 3, false);

            feed.setItemMeta(feedMeta);
            death.setItemMeta(deathMeta);
            sword.setItemMeta(swordMeta);

            inv.setItem(9, feed);
            inv.setItem(13, death);
            inv.setItem(17, sword);

            p.openInventory(inv);
        }

        return true;
    }
}