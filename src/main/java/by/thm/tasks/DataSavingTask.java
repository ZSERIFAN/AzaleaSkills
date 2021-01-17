package by.thm.tasks;

import by.thm.AzaleaSkills;
import by.thm.api.SkillManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;

public class DataSavingTask implements Runnable {

    private BukkitTask task;
    private Player player;
    long minute = 1200L;

    public DataSavingTask(Player player) {
        this.player = player;
        this.task = Bukkit.getScheduler().runTaskTimer(AzaleaSkills.getInstance(), this, 0L, minute * 5);
    }

    public void run() {
        SkillManager.getSkilledPlayer(this.player).uploadData();
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
