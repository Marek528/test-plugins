package me.asta.spawnPlugin.commands;

import me.asta.spawnPlugin.SpawnPlugin;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SpawnCommand implements CommandExecutor
{
    private final SpawnPlugin plugin;

    public SpawnCommand(SpawnPlugin plugin)
    {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String l, String[] args)
    {
        if (sender instanceof Player p)
        {
            Location location = plugin.getConfig().getLocation("spawn");

            if (location != null)
            {
                p.teleport(location);
                p.sendMessage("You have been teleported to the spawn point!");
            }
            else
            {
                p.sendMessage("There is no spawn point set. Use /setspawn to set it");
            }
        }

        return true;
    }
}
