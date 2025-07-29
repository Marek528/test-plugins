package me.asta.spawnPlugin.commands;

import me.asta.spawnPlugin.SpawnPlugin;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetSpawnCommand implements CommandExecutor
{
    private final SpawnPlugin plugin;

    public SetSpawnCommand(SpawnPlugin plugin) { this.plugin = plugin; }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String l, String[] args)
    {
        if (sender instanceof Player p)
        {
            Location location = p.getLocation();

            plugin.getConfig().set("spawn", location);
            plugin.saveConfig();

            p.sendMessage("Spawn location set");
        }
        else
        {
            System.out.println("Not a player man");
        }

        return true;
    }
}
