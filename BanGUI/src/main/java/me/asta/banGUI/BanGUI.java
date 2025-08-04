package me.asta.banGUI;

import me.asta.banGUI.commands.BanGUICommand;
import me.asta.banGUI.listeners.BanInventoryListener;
import org.bukkit.plugin.java.JavaPlugin;

public final class BanGUI extends JavaPlugin {
    private static BanGUI plugin;

    @Override
    public void onEnable() {
        plugin = this;

        getCommand("bangui").setExecutor(new BanGUICommand());

        getServer().getPluginManager().registerEvents(new BanInventoryListener(), this);
    }

    public static BanGUI getInstance() { return plugin; }
}
