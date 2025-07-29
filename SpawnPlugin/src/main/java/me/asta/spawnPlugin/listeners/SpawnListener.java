package me.asta.spawnPlugin.listeners;

import me.asta.spawnPlugin.SpawnPlugin;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

public class SpawnListener implements Listener
{
    private final SpawnPlugin plugin;

    public SpawnListener(SpawnPlugin plugin) { this.plugin = plugin; }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e)
    {
        Player p = e.getPlayer();

        if (!p.hasPlayedBefore())
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
    }

    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent e)
    {
        Location location = plugin.getConfig().getLocation("spawn");

        if (location != null)
        {
            e.setRespawnLocation(location);
        }
    }
}
