package me.asta.randomTeleportPlugin;

import me.asta.randomTeleportPlugin.commands.RandomTPCommand;
import org.bukkit.plugin.java.JavaPlugin;

public final class RandomTeleportPlugin extends JavaPlugin {
    private static RandomTeleportPlugin plugin;

    @Override
    public void onEnable() {
        plugin = this;

        getCommand("rtp").setExecutor(new RandomTPCommand());

        getConfig().options().copyDefaults();
        saveDefaultConfig();
    }

    public static RandomTeleportPlugin getInstance() { return plugin; }
}