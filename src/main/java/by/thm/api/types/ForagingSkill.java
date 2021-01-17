package by.thm.api.types;

import by.thm.api.SkillManager;
import by.thm.api.SkillType;
import by.thm.api.SkilledBlock;
import by.thm.api.SkilledPlayer;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class ForagingSkill implements Listener {

    @EventHandler (priority = EventPriority.HIGHEST)
    public void onBreak(BlockBreakEvent e) {
        Player player = e.getPlayer();
        SkilledPlayer skilledPlayer = SkillManager.getSkilledPlayer(player);
        Block block = e.getBlock();
        if (!SkilledBlock.exists(block.getType())) return;
        SkilledBlock skilledBlock = SkilledBlock.getFromMaterial(block.getType());
        if (skilledBlock == null) return;
        if (!skilledBlock.getSkillType().equals(SkillType.FORAGING)) return;
        int xpToAdd = skilledBlock.getXp();
        if (skilledPlayer.getCurrentXp(SkillType.FORAGING) + xpToAdd >= skilledPlayer.getXpToNextLevel(SkillType.FORAGING)) {
            if (skilledPlayer.getLevel(SkillType.FORAGING) >= 50)
                skilledPlayer.setCurrentXp(SkillType.FORAGING, skilledPlayer.getCurrentXp(SkillType.FORAGING) + xpToAdd);
            else {
                skilledPlayer.setCurrentXp(SkillType.FORAGING, skilledPlayer.getCurrentXp(SkillType.FORAGING) + xpToAdd);
                skilledPlayer.levelUp(SkillType.FORAGING);
            }
        }
        else skilledPlayer.setCurrentXp(SkillType.FORAGING, skilledPlayer.getCurrentXp(SkillType.FORAGING) + xpToAdd);
        int level = skilledPlayer.getLevel(SkillType.FORAGING);
        int chance = level * 2;
        Random random = ThreadLocalRandom.current();
        int hash = random.nextInt(100);
        if (hash < chance) {
            Collection<ItemStack> drops = e.getBlock().getDrops();
            drops.addAll(new ArrayList<ItemStack>(e.getBlock().getDrops()));
            e.getBlock().getDrops().clear();
            for (ItemStack is : drops)
                player.getWorld().dropItemNaturally(e.getBlock().getLocation(), is);
        }
        System.out.println("FORAGING XP: " + skilledPlayer.getCurrentXp(SkillType.FORAGING) + " / XP TO NEXT LEVEL: " + skilledPlayer.getXpToNextLevel(SkillType.FORAGING) + " (ADDED " + xpToAdd + " FOR BREAKING " + e.getBlock().getType().toString() + ")");
    }
}
