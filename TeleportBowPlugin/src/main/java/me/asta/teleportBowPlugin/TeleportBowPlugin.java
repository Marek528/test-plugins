package me.asta.teleportBowPlugin;

import me.asta.teleportBowPlugin.commands.GetBowCommand;
import me.asta.teleportBowPlugin.listeners.TeleportBowListener;
import org.bukkit.plugin.java.JavaPlugin;

public final class TeleportBowPlugin extends JavaPlugin {
    private static TeleportBowPlugin plugin;

    @Override
    public void onEnable() {
        plugin = this;

        getConfig().options().copyDefaults();
        saveDefaultConfig();

        getCommand("gettpbow").setExecutor(new GetBowCommand());

        getServer().getPluginManager().registerEvents(new TeleportBowListener(), this);
    }

    public static TeleportBowPlugin getInstance() { return plugin; }
}