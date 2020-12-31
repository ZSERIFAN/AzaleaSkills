package by.thm.tasks;

import by.thm.AzaleaSkills;
import by.thm.api.SkillManager;
import by.thm.persistent.SQLMethods;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;

import java.sql.SQLException;

public class DataSavingTask implements Runnable {

    private BukkitTask task;
    private Player player;
    long minute = 1200L;

    public DataSavingTask(Player player) {
        this.player = player;
        this.task = Bukkit.getScheduler().runTaskTimer(AzaleaSkills.getInstance(), this, 0L, minute * 5);
    }

    public void run() {
        try {
            SkillManager.getSkilledPlayer(this.player).uploadData();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    public void clear() {
        if (task != null) {
            Bukkit.getScheduler().cancelTask(task.getTaskId());
            SkillManager.getSkilledPlayer(this.player).removeDataSavingTask();
            task = null;
            this.clear();
        }
    }
}
