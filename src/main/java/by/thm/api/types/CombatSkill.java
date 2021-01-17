package by.thm.api.types;

import by.thm.api.SkillManager;
import by.thm.api.SkillType;
import by.thm.api.SkilledPlayer;
import de.tr7zw.nbtapi.NBTCompound;
import de.tr7zw.nbtapi.NBTEntity;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

public class CombatSkill implements Listener {

    @EventHandler (priority = EventPriority.HIGHEST)
    public void onEntityDeath(EntityDeathEvent e) {
        Player player = e.getEntity().getKiller();
        if (player == null) return;
        Entity entity = e.getEntity();
        NBTEntity comp = new NBTEntity(entity);
        if (!comp.hasKey("xpOnKill")) return;
        int xpToAdd = comp.getInteger("xpOnKill");
        SkilledPlayer skilledPlayer = SkillManager.getSkilledPlayer(player);
        if (skilledPlayer.getCurrentXp(SkillType.COMBAT) + xpToAdd >= skilledPlayer.getXpToNextLevel(SkillType.COMBAT)) {
            if (skilledPlayer.getLevel(SkillType.COMBAT) >= 50)
                skilledPlayer.setCurrentXp(SkillType.COMBAT, skilledPlayer.getCurrentXp(SkillType.COMBAT) + xpToAdd);
            else
                skilledPlayer.levelUp(SkillType.COMBAT);
        }
        else skilledPlayer.setCurrentXp(SkillType.COMBAT, skilledPlayer.getCurrentXp(SkillType.COMBAT) + xpToAdd);
        System.out.println("COMBAT XP: " + skilledPlayer.getCurrentXp(SkillType.COMBAT) + " / XP TO NEXT LEVEL: " + skilledPlayer.getXpToNextLevel(SkillType.COMBAT) + " (ADDED " + xpToAdd + " FOR KILLING " + e.getEntity().getType().toString().toUpperCase());
    }
}
