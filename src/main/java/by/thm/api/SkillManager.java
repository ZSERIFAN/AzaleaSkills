package by.thm.api;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class SkillManager {

    private static Map<UUID, SkilledPlayer> skilledPlayers = new HashMap<UUID, SkilledPlayer>();

    public static void addSkilledPlayer(Player player, SkilledPlayer skilledPlayer) {
        skilledPlayers.put(player.getUniqueId(), skilledPlayer);
    }

    public static void removeSkilledPlayer(Player player) {
        skilledPlayers.remove(player.getUniqueId());
    }

    public static SkilledPlayer getSkilledPlayer(Player player) {
        return skilledPlayers.get(player.getUniqueId());
    }
}
