package by.thm.api.types;

import by.thm.api.SkillManager;
import by.thm.api.SkillType;
import by.thm.api.SkilledBlock;
import by.thm.api.SkilledPlayer;
import org.bukkit.Bukkit;
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

public class MiningSkill implements Listener {

    @EventHandler (priority = EventPriority.HIGHEST)
    public void onBlockBreak(BlockBreakEvent e) {
        Player player = e.getPlayer();
        SkilledPlayer skilledPlayer = SkillManager.getSkilledPlayer(player);
        Block block = e.getBlock();
        if (!SkilledBlock.exists(block.getType())) return;
        SkilledBlock skilledBlock = SkilledBlock.getFromMaterial(block.getType());
        if (skilledBlock == null) return;
        if (!skilledBlock.getSkillType().equals(SkillType.MINING)) return;
        int xpToAdd = skilledBlock.getXp();
        if (skilledPlayer.getCurrentXp(SkillType.MINING) + xpToAdd >= skilledPlayer.getXpToNextLevel(SkillType.MINING)) {
            if (skilledPlayer.getLevel(SkillType.MINING) >= 50)
                skilledPlayer.setCurrentXp(SkillType.MINING, skilledPlayer.getCurrentXp(SkillType.MINING) + xpToAdd);
            else {
                skilledPlayer.setCurrentXp(SkillType.MINING,skilledPlayer.getCurrentXp(SkillType.MINING) + xpToAdd);
                skilledPlayer.levelUp(SkillType.MINING);
            }
        }
        else skilledPlayer.setCurrentXp(SkillType.MINING, skilledPlayer.getCurrentXp(SkillType.MINING) + xpToAdd);
        int level = skilledPlayer.getLevel(SkillType.MINING);
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
        System.out.println("MINING XP: " + skilledPlayer.getCurrentXp(SkillType.MINING) + " / XP TO NEXT LEVEL: " + skilledPlayer.getXpToNextLevel(SkillType.MINING) + " (ADDED " + xpToAdd + " FOR BREAKING " + e.getBlock().getType().toString() + ")");
    }
}
