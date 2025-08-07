package me.asta.randomTeleportPlugin.utils;

import me.asta.randomTeleportPlugin.RandomTeleportPlugin;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class TeleportUtils {
    private static ArrayList<Material> badBlocks = new ArrayList<>();

    static {
        // badBlocks.add(Material.GRASS_BLOCK); // test
        badBlocks.add(Material.LAVA);
        badBlocks.add(Material.FIRE);
        badBlocks.add(Material.CACTUS);
        badBlocks.add(Material.WATER);
    }

    public static Location generateRandomLocation(Player player) {
        int max = 10000;
        int min = -max;

        if (RandomTeleportPlugin.getInstance().getConfig().getBoolean("world-border")) {
            max = RandomTeleportPlugin.getInstance().getConfig().getInt("border");
            min = -max;
        }

        int x = (int) (Math.random() * (max - min)) + min;
        int y = 150;
        int z = (int) (Math.random() * (max - min)) + min;

        Location randomLocation = new Location(player.getWorld(), x, y, z);

        y = randomLocation.getWorld().getHighestBlockYAt(randomLocation) + 1;
        randomLocation.setY(y);

        return randomLocation;
    }

    public static Location findSafeLocation(Player player) {
        Location randomLocation = generateRandomLocation(player);

        while (!isSurfaceSafe(randomLocation)) {
            randomLocation = generateRandomLocation(player);
        }

        return randomLocation;
    }

    private static boolean isSurfaceSafe(Location loc) {
        int x = loc.getBlockX();
        int y = loc.getBlockY();
        int z = loc.getBlockZ();

        Block block = loc.getWorld().getBlockAt(x, y, z);
        Block below = loc.getWorld().getBlockAt(x, y - 1, z);
        Block above = loc.getWorld().getBlockAt(x, y + 1, z);

        return !(badBlocks.contains(below.getType()) || block.getType().isSolid() || above.getType().isSolid());
    }
}
