package by.thm.api;

import org.bukkit.entity.Player;

public final class SkillProvider {

    private static SkilledPlayer instance = null;

    public static SkilledPlayer get(Player player) {
        instance = SkillManager.getSkilledPlayer(player);
        if (instance == null)
            throw new IllegalStateException("The AzaleaSkill API is not loaded.");
        return instance;
    }
}
