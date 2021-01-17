package by.thm.api.types;

import by.thm.api.SkillManager;
import by.thm.api.SkillType;
import by.thm.api.SkilledBlock;
import by.thm.api.SkilledPlayer;
import org.bukkit.CropState;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.Crops;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class FarmingSkill implements Listener {

    @EventHandler (priority = EventPriority.HIGHEST)
    public void onBlockBreak(BlockBreakEvent e) {
        Player player = e.getPlayer();
        SkilledPlayer skilledPlayer = SkillManager.getSkilledPlayer(player);
        Block block = e.getBlock();
        if (!SkilledBlock.exists(block.getType())) return;
        SkilledBlock skilledBlock = SkilledBlock.getFromMaterial(block.getType());
        if (skilledBlock == null) return;
        if (!skilledBlock.getSkillType().equals(SkillType.FARMING)) return;
        int xpToAdd = 0;
        if (block.getState().getData() instanceof Crops) {
            CropState cropState = ((Crops) block.getState().getData()).getState();
            if (!cropState.equals(CropState.RIPE) && !cropState.equals(CropState.TALL)) return;
            xpToAdd = skilledBlock.getXp();
        } else xpToAdd = skilledBlock.getXp();
        if (skilledPlayer.getCurrentXp(SkillType.FARMING) + xpToAdd >= skilledPlayer.getXpToNextLevel(SkillType.FARMING)) {
            if (skilledPlayer.getLevel(SkillType.FARMING) >= 50)
                skilledPlayer.setCurrentXp(SkillType.FARMING, skilledPlayer.getCurrentXp(SkillType.FARMING) + xpToAdd);
            else {
                skilledPlayer.setCurrentXp(SkillType.FARMING, skilledPlayer.getCurrentXp(SkillType.FARMING) + xpToAdd);
                skilledPlayer.levelUp(SkillType.FARMING);
            }
        }
        else skilledPlayer.setCurrentXp(SkillType.FARMING, skilledPlayer.getCurrentXp(SkillType.FARMING) + xpToAdd);
        int level = skilledPlayer.getLevel(SkillType.FARMING);
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
        System.out.println("FARMING XP: " + skilledPlayer.getCurrentXp(SkillType.FARMING) + " / XP TO NEXT LEVEL: " + skilledPlayer.getXpToNextLevel(SkillType.FARMING) + " (ADDED " + xpToAdd + " FOR BREAKING " + e.getBlock().getType().toString() + ")");
    }
}
