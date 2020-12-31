package by.thm.listeners;

import by.thm.api.SkillManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import java.sql.SQLException;

public class PlayerLeaveListener implements Listener {

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent e) {
        Player p = e.getPlayer();
        try {
            if (SkillManager.getSkilledPlayer(p) == null) return;
            SkillManager.getSkilledPlayer(p).uploadData();
            SkillManager.getSkilledPlayer(p).getDataSavingTask().clear();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        SkillManager.removeSkilledPlayer(p);
        System.out.println("[AzaleaSkills] Removed player " + p.getName() + " from cached memory due to him leaving the server.");
    }
}
