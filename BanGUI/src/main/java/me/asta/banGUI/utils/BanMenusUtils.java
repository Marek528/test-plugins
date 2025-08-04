package me.asta.banGUI.utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class BanMenusUtils {
    public static void openPlayerList(Player p) {
        Inventory banGUI = Bukkit.createInventory(p, 45, ChatColor.AQUA + "Player List");

        ArrayList<Player> onlinePlayers = new ArrayList<>(p.getServer().getOnlinePlayers());

        for (Player player : onlinePlayers) {
            ItemStack playerHead = getItemStack(player);
            banGUI.addItem(playerHead);
        }

        p.openInventory(banGUI);
    }

    public static void openConfirmMenu(Player p, Player whoToBan) {
        Inventory confirmMenu = Bukkit.createInventory(p, 9, ChatColor.RED + "Confirm Ban");

        ItemStack ban = new ItemStack(Material.WOODEN_AXE);
        ItemMeta banMeta = ban.getItemMeta();
        banMeta.setDisplayName(ChatColor.DARK_RED + "BAN");
        ban.setItemMeta(banMeta);

        ItemStack player = new ItemStack(Material.PLAYER_HEAD);
        ItemMeta playerMeta = player.getItemMeta();
        playerMeta.setDisplayName(ChatColor.DARK_GREEN + whoToBan.getDisplayName());
        player.setItemMeta(playerMeta);

        ItemStack back = new ItemStack(Material.BARRIER);
        ItemMeta backMeta = back.getItemMeta();
        backMeta.setDisplayName(ChatColor.RED + "Back");
        back.setItemMeta(backMeta);


        confirmMenu.setItem(0, ban);
        confirmMenu.setItem(4, player);
        confirmMenu.setItem(8, back);

        p.openInventory(confirmMenu);
    }

    private static ItemStack getItemStack(Player player) {
        ItemStack playerHead = new ItemStack(Material.PLAYER_HEAD);

        ItemMeta playerHeadMeta = playerHead.getItemMeta();
        playerHeadMeta.setDisplayName(ChatColor.DARK_RED + player.getDisplayName());

        ArrayList<String> lore = new ArrayList<>();
        lore.add("Health: " + ChatColor.RED + player.getHealth());
        lore.add("EXP: " + ChatColor.AQUA + player.getExp());
        playerHeadMeta.setLore(lore);

        playerHead.setItemMeta(playerHeadMeta);
        return playerHead;
    }
}
