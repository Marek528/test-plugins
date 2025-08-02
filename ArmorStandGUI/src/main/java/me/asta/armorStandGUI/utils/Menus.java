package me.asta.armorStandGUI.utils;

import me.asta.armorStandGUI.ArmorStandGUI;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class Menus {
    private static final ArmorStandGUI plugin = ArmorStandGUI.getInstance();

    public static void openMainMenu(Player p) {
        Inventory mainMenu = Bukkit.createInventory(p, 36, plugin.getFromConfig("mainMenu-title"));

        ItemStack createBtn = new ItemStack(Material.ARMOR_STAND);
        ItemMeta createMeta = createBtn.getItemMeta();
        createMeta.setDisplayName(plugin.getFromConfig("create.create-name"));
        ArrayList<String> createLore = new ArrayList<>(List.of(plugin.getFromConfig("create.create-lore")));
        createMeta.setLore(createLore);
        createBtn.setItemMeta(createMeta);

        ItemStack closeBtn = new ItemStack(Material.BARRIER);
        ItemMeta closeMeta = closeBtn.getItemMeta();
        closeMeta.setDisplayName(plugin.getFromConfig("close.close-name"));
        closeBtn.setItemMeta(closeMeta);

        mainMenu.setItem(13, createBtn);
        mainMenu.setItem(31, closeBtn);
        p.openInventory(mainMenu);
    }

    public static void openCreateMenu(Player p) {
        Inventory createMenu = Bukkit.createInventory(p, 9, plugin.getFromConfig("createMenu-title"));
        ArmorStand stand = null;

        if (plugin.armorStands.containsKey(p)) {
            stand = plugin.armorStands.get(p);
        }

        ItemStack arms = new ItemStack(Material.ARMOR_STAND);
        ItemStack glow = new ItemStack(Material.BEACON);
        ItemStack armor = new ItemStack(Material.LEATHER_CHESTPLATE);
        ItemStack base = new ItemStack(Material.STONE_SLAB);
        ItemStack complete = new ItemStack(Material.GREEN_WOOL);
        ItemStack cancel = new ItemStack(Material.RED_WOOL);

        ItemMeta armsMeta = arms.getItemMeta();
        armsMeta.setDisplayName(plugin.getFromConfig("create-menu.arms-name"));
        ItemMeta glowMeta = glow.getItemMeta();
        glowMeta.setDisplayName(plugin.getFromConfig("create-menu.glow-name"));
        ItemMeta armorMeta = armor.getItemMeta();
        armorMeta.setDisplayName(plugin.getFromConfig("create-menu.armor-name"));
        ItemMeta baseMeta = base.getItemMeta();
        baseMeta.setDisplayName(plugin.getFromConfig("create-menu.base-name"));
        baseMeta.setEnchantmentGlintOverride(true);
        ItemMeta completeMeta = complete.getItemMeta();
        completeMeta.setDisplayName(plugin.getFromConfig("create-menu.complete-name"));
        ItemMeta cancelMeta = cancel.getItemMeta();
        cancelMeta.setDisplayName(plugin.getFromConfig("create-menu.cancel-name"));

        if (stand != null) {
            armsMeta.setEnchantmentGlintOverride(stand.hasArms());
            glowMeta.setEnchantmentGlintOverride(stand.isGlowing());
            baseMeta.setEnchantmentGlintOverride(stand.hasBasePlate());
        }

        arms.setItemMeta(armsMeta);
        glow.setItemMeta(glowMeta);
        armor.setItemMeta(armorMeta);
        base.setItemMeta(baseMeta);
        complete.setItemMeta(completeMeta);
        cancel.setItemMeta(cancelMeta);

        createMenu.setItem(0, arms);
        createMenu.setItem(1, glow);
        createMenu.setItem(2, armor);
        createMenu.setItem(3, base);
        createMenu.setItem(7, complete);
        createMenu.setItem(8, cancel);

        p.openInventory(createMenu);
    }

    public static void openConfirmMenu(Player p, Material option) {
        String title = switch (option) {
            case ARMOR_STAND -> "Arms";
            case BEACON -> "Glow";
            case STONE_SLAB -> "Base";
            default -> "";
        };

        Inventory confirmMenu = Bukkit.createInventory(p, 36, plugin.getFromConfig("confirmMenu-title") + " - " + title);

        ItemStack optionItem = new ItemStack(option);
        ItemMeta optionMeta = optionItem.getItemMeta();

        if (option == Material.ARMOR_STAND) {
            optionMeta.setDisplayName(plugin.getFromConfig("confirm-menu.arms-name"));
            optionItem.setItemMeta(optionMeta);
        } else if (option == Material.BEACON) {
            optionMeta.setDisplayName(plugin.getFromConfig("confirm-menu.glow-name"));
            optionItem.setItemMeta(optionMeta);
        } else if (option == Material.STONE_SLAB) {
            optionMeta.setDisplayName(plugin.getFromConfig("confirm-menu.base-name"));
            optionItem.setItemMeta(optionMeta);
        }

        ItemStack yesItem = new ItemStack(Material.GREEN_WOOL);
        ItemMeta yesMeta = yesItem.getItemMeta();
        yesMeta.setDisplayName(plugin.getFromConfig("confirm-menu.yes-dsName"));
        yesItem.setItemMeta(yesMeta);

        ItemStack noItem = new ItemStack(Material.RED_WOOL);
        ItemMeta noMeta = noItem.getItemMeta();
        noMeta.setDisplayName(plugin.getFromConfig("confirm-menu.no-dsName"));
        noItem.setItemMeta(noMeta);

        confirmMenu.setItem(13, optionItem);
        confirmMenu.setItem(21, yesItem);
        confirmMenu.setItem(23, noItem);

        p.openInventory(confirmMenu);
    }

    public static void openArmorMenu(Player p, ArmorStand as) {
        Inventory armorMenu = Bukkit.createInventory(p, 45, plugin.getFromConfig("armorMenu-title"));
        EntityEquipment eq = as.getEquipment();

        ItemStack head = (eq != null && eq.getHelmet() != null && eq.getHelmet().getType() != Material.AIR) ? eq.getHelmet() : new ItemStack(Material.GRAY_WOOL);
        ItemStack chest = (eq != null && eq.getChestplate() != null && eq.getChestplate().getType() != Material.AIR) ? eq.getChestplate() : new ItemStack(Material.GRAY_WOOL);
        ItemStack legs = (eq != null && eq.getLeggings() != null && eq.getLeggings().getType() != Material.AIR) ? eq.getLeggings() : new ItemStack(Material.GRAY_WOOL);
        ItemStack boots = (eq != null && eq.getBoots() != null && eq.getBoots().getType() != Material.AIR) ? eq.getBoots() : new ItemStack(Material.GRAY_WOOL);

        ItemMeta headMeta = head.getItemMeta();
        headMeta.setDisplayName(plugin.getFromConfig("armor-menu.helmet-name"));
        head.setItemMeta(headMeta);
        ItemMeta chestMeta = chest.getItemMeta();
        chestMeta.setDisplayName(plugin.getFromConfig("armor-menu.chest-name"));
        chest.setItemMeta(chestMeta);
        ItemMeta legsMeta = legs.getItemMeta();
        legsMeta.setDisplayName(plugin.getFromConfig("armor-menu.legs-name"));
        legs.setItemMeta(legsMeta);
        ItemMeta bootsMeta = boots.getItemMeta();
        bootsMeta.setDisplayName(plugin.getFromConfig("armor-menu.boots-name"));
        boots.setItemMeta(bootsMeta);

        ItemStack confirm = new ItemStack(Material.GREEN_WOOL);
        ItemMeta confirmMeta = confirm.getItemMeta();
        confirmMeta.setDisplayName(plugin.getFromConfig("armor-menu.confirm-name"));
        confirm.setItemMeta(confirmMeta);

        armorMenu.setItem(11, head);
        armorMenu.setItem(12, chest);
        armorMenu.setItem(14, legs);
        armorMenu.setItem(15, boots);
        armorMenu.setItem(40, confirm);

        p.openInventory(armorMenu);
    }

    public static void openArmorChoice(Player p, String armorToPick) {
        p.closeInventory();
        Inventory pickArmor = Bukkit.createInventory(p, 27, plugin.getFromConfig("armorChoice-title"));

        ItemStack none = new ItemStack(Material.GRAY_WOOL);
        ItemMeta noneMeta = none.getItemMeta();
        noneMeta.setDisplayName(plugin.getFromConfig("armor-choice.noArmor-name"));
        none.setItemMeta(noneMeta);

        ItemStack leather, golden, chain, iron, diamond, netherite;
        String suffix = null;

        if (armorToPick.equalsIgnoreCase(plugin.getFromConfig("armor-menu.helmet-name"))) {
            suffix = "HELMET";
        } else if (armorToPick.equalsIgnoreCase(plugin.getFromConfig("armor-menu.chest-name"))) {
            suffix = "CHESTPLATE";
        } else if (armorToPick.equalsIgnoreCase(plugin.getFromConfig("armor-menu.legs-name"))) {
            suffix = "LEGGINGS";
        } else if (armorToPick.equalsIgnoreCase(plugin.getFromConfig("armor-menu.boots-name"))) {
            suffix = "BOOTS";
        }

        if (suffix == null) {
            p.sendMessage("Name for armors in config are missing!");
            return;
        }

        leather = new ItemStack(Material.valueOf("LEATHER_" + suffix));
        golden = new ItemStack(Material.valueOf("GOLDEN_" + suffix));
        chain = new ItemStack(Material.valueOf("CHAINMAIL_" + suffix));
        iron = new ItemStack(Material.valueOf("IRON_" + suffix));
        diamond = new ItemStack(Material.valueOf("DIAMOND_" + suffix));
        netherite = new ItemStack(Material.valueOf("NETHERITE_" + suffix));

        pickArmor.setItem(10, none);
        pickArmor.setItem(11, leather);
        pickArmor.setItem(12, golden);
        pickArmor.setItem(13, chain);
        pickArmor.setItem(14, iron);
        pickArmor.setItem(15, diamond);
        pickArmor.setItem(16, netherite);

        p.openInventory(pickArmor);
    }
}