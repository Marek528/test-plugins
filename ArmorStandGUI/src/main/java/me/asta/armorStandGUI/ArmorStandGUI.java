package me.asta.armorStandGUI;

import me.asta.armorStandGUI.commands.ArmorStandCommand;
import me.asta.armorStandGUI.listeners.MenuHandler;
import org.bukkit.ChatColor;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;

public final class ArmorStandGUI extends JavaPlugin {
    private static ArmorStandGUI plugin;
    public HashMap<Player, ArmorStand> armorStands = new HashMap<>();

    @Override
    public void onEnable() {
        plugin = this;

        getConfig().options().copyDefaults();
        saveDefaultConfig();

        getCommand("menuAS").setExecutor(new ArmorStandCommand());
        getServer().getPluginManager().registerEvents(new MenuHandler(), this);
    }

    public static ArmorStandGUI getInstance() { return plugin; }

    public String getFromConfig(String s) {
        return ChatColor.translateAlternateColorCodes('&', getConfig().getString(s));
    }
}
