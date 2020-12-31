package by.thm.listeners;

import by.thm.Util;
import by.thm.persistent.SQLMethods;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {

    SQLMethods sql = new SQLMethods();

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        Util.initPlayer(p);
    }
}
