package kileo.utility.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuit implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onPlayerQuit(PlayerQuitEvent event) {

        removePlayerPowers(event);
    }

    private void removePlayerPowers(PlayerQuitEvent e) {

        Player player = e.getPlayer();

        player.setAllowFlight(false);
        player.setInvulnerable(false);
    }
}