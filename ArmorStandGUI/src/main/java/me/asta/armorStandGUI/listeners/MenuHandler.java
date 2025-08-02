package me.asta.armorStandGUI.listeners;

import me.asta.armorStandGUI.ArmorStandGUI;
import me.asta.armorStandGUI.utils.Menus;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public class MenuHandler implements Listener {
    private static final ArmorStandGUI plugin = ArmorStandGUI.getInstance();

    private final String MAIN_MENU = plugin.getFromConfig("mainMenu-title");
    private final String CREATE_MENU = plugin.getFromConfig("createMenu-title");
    private final String CONFIRM_MENU = plugin.getFromConfig("confirmMenu-title");
    private final String ARMOR_MENU = plugin.getFromConfig("armorMenu-title");
    private final String ARMOR_CHOICE_MENU = plugin.getFromConfig("armorChoice-title");

    @EventHandler
    public void onMenuClick(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();
        ArmorStand stand;

        ItemStack clickedItem = e.getCurrentItem();
        if (clickedItem == null || clickedItem.getType() == Material.AIR) return;

        if (e.getView().getTitle().equalsIgnoreCase(MAIN_MENU)) {
            e.setCancelled(true);

            switch (clickedItem.getType()) {
                case ARMOR_STAND:
                    p.closeInventory();
                    Menus.openCreateMenu(p);
                    break;
                case BARRIER:
                    p.closeInventory();
                    break;
            }

        } else if (e.getView().getTitle().equalsIgnoreCase(CREATE_MENU)) {
            e.setCancelled(true);

            if (!plugin.armorStands.containsKey(p)) {
                stand = (ArmorStand) p.getWorld().spawnEntity(p.getLocation(), EntityType.ARMOR_STAND);
                stand.setVisible(false);

                stand.setInvulnerable(true);
                stand.setCanPickupItems(false);
                stand.setPersistent(true);
                stand.setRemoveWhenFarAway(false);

                plugin.armorStands.put(p, stand);
            }

            stand = plugin.armorStands.get(p);

            switch (clickedItem.getType()) {
                case ARMOR_STAND:
                    Menus.openConfirmMenu(p, Material.ARMOR_STAND);
                    break;
                case BEACON:
                    Menus.openConfirmMenu(p, Material.BEACON);
                    break;
                case LEATHER_CHESTPLATE:
                    Menus.openArmorMenu(p, stand);
                    break;
                case STONE_SLAB:
                    Menus.openConfirmMenu(p, Material.STONE_SLAB);
                    break;
                case GREEN_WOOL:
                    p.sendMessage("Created Armor Stand");
                    stand.setVisible(true);
                    break;
                case RED_WOOL:
                    p.sendMessage("Deleted Armor Stand");
                    stand.remove();
                    break;
            }

            if (clickedItem.getType() == Material.GREEN_WOOL || clickedItem.getType() == Material.RED_WOOL) {
                plugin.armorStands.remove(p);
                p.closeInventory();
            }

        } else if (e.getView().getTitle().contains(CONFIRM_MENU)) {
            e.setCancelled(true);

            if (!plugin.armorStands.containsKey(p)) return;
            stand = plugin.armorStands.get(p);

            if (e.getClickedInventory().contains(Material.ARMOR_STAND)) {
                switch (clickedItem.getType()) {
                    case GREEN_WOOL:
                        stand.setArms(true);
                        break;
                    case RED_WOOL:
                        stand.setArms(false);
                        break;
                }
            } else if (e.getClickedInventory().contains(Material.BEACON)) {
                switch (clickedItem.getType()) {
                    case GREEN_WOOL:
                        stand.setGlowing(true);
                        break;
                    case RED_WOOL:
                        stand.setGlowing(false);
                        break;
                }
            } else if (e.getClickedInventory().contains(Material.STONE_SLAB)) {
                switch (clickedItem.getType()) {
                    case GREEN_WOOL:
                        stand.setBasePlate(true);
                        break;
                    case RED_WOOL:
                        stand.setBasePlate(false);
                        break;
                }
            }

            if (clickedItem.getType() == Material.GREEN_WOOL || clickedItem.getType() == Material.RED_WOOL) {
                p.closeInventory();
                Menus.openCreateMenu(p);
            }

        } else if (e.getView().getTitle().contains(ARMOR_MENU)) {
            e.setCancelled(true);

            if (!plugin.armorStands.containsKey(p)) return;

            if (clickedItem.getType() == Material.GREEN_WOOL) {
                p.closeInventory();
                Menus.openCreateMenu(p);
                return;
            }

            ArrayList<String> armorsName = new ArrayList<>();
            for (String key : plugin.getConfig().getConfigurationSection("armor-menu").getKeys(false)) {
                armorsName.add(plugin.getFromConfig("armor-menu." + key));
            }

            for (String armor : armorsName) {
                if (clickedItem.getItemMeta().getDisplayName().equalsIgnoreCase(armor)) {
                    Menus.openArmorChoice(p, armor);
                }
            }

        } else if (e.getView().getTitle().contains(ARMOR_CHOICE_MENU)) {
            e.setCancelled(true);

            if (!plugin.armorStands.containsKey(p)) return;
            stand = plugin.armorStands.get(p);

            if (clickedItem.getType() == Material.AIR) return;

            Material chosenArmor = Material.getMaterial(clickedItem.getType().toString());
            if (chosenArmor == null) return;

            if (chosenArmor == Material.GRAY_WOOL)
                chosenArmor = Material.AIR;

            EntityEquipment armorEq = stand.getEquipment();
            if (e.getClickedInventory().contains(Material.LEATHER_HELMET)) {
                armorEq.setHelmet(new ItemStack(chosenArmor));
            } else if (e.getClickedInventory().contains(Material.LEATHER_CHESTPLATE)) {
                armorEq.setChestplate(new ItemStack(chosenArmor));
            } else if (e.getClickedInventory().contains(Material.LEATHER_LEGGINGS)) {
                armorEq.setLeggings(new ItemStack(chosenArmor));
            } else if (e.getClickedInventory().contains(Material.LEATHER_BOOTS)) {
                armorEq.setBoots(new ItemStack(chosenArmor));
            } else {
                return;
            }

            p.closeInventory();
            Menus.openArmorMenu(p, stand);
        }
    }
}