package me.asta.teleportBowPlugin.listeners;

import me.asta.teleportBowPlugin.TeleportBowPlugin;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.inventory.ItemStack;

public class TeleportBowListener implements Listener {
    private static final TeleportBowPlugin plugin = TeleportBowPlugin.getInstance();
    private float yawDirection = 180f;

    @EventHandler
    public void onArrowHit (ProjectileHitEvent e) {
        if (e.getEntity().getShooter() instanceof Player p) {
            ItemStack itemInMainHand = p.getInventory().getItemInMainHand();

            if (!itemInMainHand.getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("tpBow-name")))) {
                return;
            }

            Location locToTeleport = e.getEntity().getLocation();
            locToTeleport.setYaw(yawDirection);
            locToTeleport.setPitch(0f);
            p.teleport(locToTeleport);

            e.getEntity().remove();
            if (plugin.getConfig().getBoolean("tpBow-teleportMsg-enable"))
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("tpBow-teleportMsg")));
            p.playSound(p, Sound.ENTITY_ENDERMAN_TELEPORT, 1f, 1f);
        }
    }

    @EventHandler
    public void onBowShoot(EntityShootBowEvent e) {
        if (e.getEntity() instanceof Player p) {
            if (e.getBow().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("tpBow-name")))) {
                yawDirection = p.getLocation().getYaw();
            }
        }
    }
}
