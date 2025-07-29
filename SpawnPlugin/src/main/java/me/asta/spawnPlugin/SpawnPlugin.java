package me.asta.spawnPlugin;

import me.asta.spawnPlugin.commands.SetSpawnCommand;
import me.asta.spawnPlugin.commands.SpawnCommand;
import me.asta.spawnPlugin.listeners.SpawnListener;
import org.bukkit.plugin.java.JavaPlugin;

public final class SpawnPlugin extends JavaPlugin
{
    @Override
    public void onEnable()
    {
        // config.yml
        getConfig().options().copyDefaults();
        saveDefaultConfig();

        getCommand("setspawn").setExecutor(new SetSpawnCommand(this));
        getCommand("spawn").setExecutor(new SpawnCommand(this));

        getServer().getPluginManager().registerEvents(new SpawnListener(this), this);
    }
}