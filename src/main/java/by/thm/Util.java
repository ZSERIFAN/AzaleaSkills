package by.thm;

import by.thm.api.SkilledPlayer;
import by.thm.api.types.CombatSkill;
import by.thm.api.types.FarmingSkill;
import by.thm.api.types.ForagingSkill;
import by.thm.api.types.MiningSkill;
import by.thm.commands.StatsCommand;
import by.thm.listeners.PlayerJoinListener;
import by.thm.listeners.PlayerLeaveListener;
import by.thm.persistent.SQLMethods;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class Util {

    private static SQLMethods sql = new SQLMethods();

    public static void setupFiles() {
        File dir = new File("plugins", "AzaleaSkills");
        if (!dir.exists()) dir.mkdir();
        if (!AzaleaSkills.config.exists()) AzaleaSkills.getInstance().saveDefaultConfig();
        try {
            AzaleaSkills.cfg.load(AzaleaSkills.config);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }

    public static void registerCommands() {
        StatsCommand statsCommand = new StatsCommand();
    }

    public static ItemStack createItem(final Material material, final String name, final String... lore) {
        final ItemStack item = new ItemStack(material, 1);
        final ItemMeta meta = item.getItemMeta();

        meta.setDisplayName(name);

        meta.setLore(Arrays.asList(lore));
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);

        item.setItemMeta(meta);

        return item;
    }

    public static String IntegerToRomanNumeral(int input) {
        if (input < 1 || input > 3999)
            return "Invalid Roman Number Value";
        String s = "";
        while (input >= 1000) {
            s += "M";
            input -= 1000;        }
        while (input >= 900) {
            s += "CM";
            input -= 900;
        }
        while (input >= 500) {
            s += "D";
            input -= 500;
        }
        while (input >= 400) {
            s += "CD";
            input -= 400;
        }
        while (input >= 100) {
            s += "C";
            input -= 100;
        }
        while (input >= 90) {
            s += "XC";
            input -= 90;
        }
        while (input >= 50) {
            s += "L";
            input -= 50;
        }
        while (input >= 40) {
            s += "XL";
            input -= 40;
        }
        while (input >= 10) {
            s += "X";
            input -= 10;
        }
        while (input >= 9) {
            s += "IX";
            input -= 9;
        }
        while (input >= 5) {
            s += "V";
            input -= 5;
        }
        while (input >= 4) {
            s += "IV";
            input -= 4;
        }
        while (input >= 1) {
            s += "I";
            input -= 1;
        }
        return s;
    }

    public static void registerEvents() {
        AzaleaSkills.getInstance().events = Arrays.asList(new PlayerJoinListener(),
                new PlayerLeaveListener(),
                new MiningSkill(),
                new ForagingSkill(),
                new FarmingSkill(),
                new CombatSkill());
        AzaleaSkills.getInstance().events.forEach(listener -> {
            Bukkit.getPluginManager().registerEvents(listener, AzaleaSkills.getInstance());
        });
    }

    public static void setupObjects() {
        for (Player player : Bukkit.getOnlinePlayers())
            initPlayer(player);
    }

    public static void initPlayer(Player player) {
        if (!(sql.playerExists(player.getUniqueId()))) {
            System.out.println("[AzaleaAttributes] Player " + player.getName() + " (UUID: " + player.getUniqueId().toString() + ") was not found in our database records.");
            System.out.println("[AzaleaAttributes] Initiating new player.");
            sql.createPlayer(player);
        }
        new SkilledPlayer(player);
    }

}
