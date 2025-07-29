package me.asta.playerMenu;

import me.asta.playerMenu.commands.PlayerMenuCommand;
import me.asta.playerMenu.listeners.PlayerMenuListener;
import org.bukkit.plugin.java.JavaPlugin;

public final class PlayerMenu extends JavaPlugin {

    @Override
    public void onEnable() {

        getServer().getPluginManager().registerEvents(new PlayerMenuListener(), this);

        getCommand("menu").setExecutor(new PlayerMenuCommand());

    }
}
