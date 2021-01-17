package by.thm.api;

import by.thm.AzaleaSkills;
import by.thm.Util;
import by.thm.persistent.SQLMethods;
import by.thm.persistent.TableType;
import by.thm.tasks.DataSavingTask;
import com.cryptomorin.xseries.XSound;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public class SkilledPlayer {

    private SQLMethods sql = new SQLMethods();
    private Map<SkillType, Integer> currentXp = new HashMap<>();
    private Map<SkillType, Integer> xpToNextLevel = new HashMap<>();

    private Player player;
    private long uploadTime;
    private DataSavingTask dataSavingTask;
    private Integer miningXP;
    private Integer miningXPToNextLevel;
    private Integer combatXP;
    private Integer combatXPToNextLevel;
    private Integer farmingXP;
    private Integer farmingXPToNextLevel;
    private Integer foragingXP;
    private Integer foragingXPToNextLevel;
    private Integer healthXP;
    private Integer healthXPToNextLevel;
    private Integer defenseXP;
    private Integer defenseXPToNextLevel;
    private Integer damageXP;
    private Integer damageXPToNextLevel;
    private Integer manaXP;
    private Integer manaXPToNextLevel;

    public SkilledPlayer(Player player) {
        this.player = player;
        this.dataSavingTask = new DataSavingTask(this.player);
        this.setup();
        this.setupXp();
        SkillManager.addSkilledPlayer(this.player, this);
    }

    private void setup() {
        try {
            this.miningXP = sql.getValue(TableType.DATA_MINING, "CURRENT_XP", this.player.getUniqueId().toString());
            this.miningXPToNextLevel = sql.getValue(TableType.DATA_MINING, "XP_TO_NEXT_LEVEL", this.player.getUniqueId().toString());
            this.combatXP = sql.getValue(TableType.DATA_COMBAT, "CURRENT_XP", this.player.getUniqueId().toString());
            this.combatXPToNextLevel = sql.getValue(TableType.DATA_COMBAT, "XP_TO_NEXT_LEVEL", this.player.getUniqueId().toString());
            this.farmingXP = sql.getValue(TableType.DATA_FARMING, "CURRENT_XP", this.player.getUniqueId().toString());
            this.farmingXPToNextLevel = sql.getValue(TableType.DATA_FARMING, "XP_TO_NEXT_LEVEL", this.player.getUniqueId().toString());
            this.foragingXP = sql.getValue(TableType.DATA_FORAGING, "CURRENT_XP", this.player.getUniqueId().toString());
            this.foragingXPToNextLevel = sql.getValue(TableType.DATA_FORAGING, "XP_TO_NEXT_LEVEL", this.player.getUniqueId().toString());
            this.healthXP = sql.getValue(TableType.DATA_HEALTH, "CURRENT_XP", this.player.getUniqueId().toString());
            this.healthXPToNextLevel = sql.getValue(TableType.DATA_HEALTH, "XP_TO_NEXT_LEVEL", this.player.getUniqueId().toString());
            this.defenseXP = sql.getValue(TableType.DATA_DEFENSE, "CURRENT_XP", this.player.getUniqueId().toString());
            this.defenseXPToNextLevel = sql.getValue(TableType.DATA_DEFENSE, "XP_TO_NEXT_LEVEL", this.player.getUniqueId().toString());
            this.damageXP = sql.getValue(TableType.DATA_DAMAGE, "CURRENT_XP", this.player.getUniqueId().toString());
            this.damageXPToNextLevel = sql.getValue(TableType.DATA_DAMAGE, "XP_TO_NEXT_LEVEL", this.player.getUniqueId().toString());
            this.manaXP = sql.getValue(TableType.DATA_MANA, "CURRENT_XP", this.player.getUniqueId().toString());
            this.manaXPToNextLevel = sql.getValue(TableType.DATA_MANA, "XP_TO_NEXT_LEVEL", this.player.getUniqueId().toString());
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("[AzaleaSkills] (FATAL) Was not able to initiate SkilledPlayer object for player " + this.player.getName() + "! Report this stacktrace to thmihnea.");
        }
    }

    private void setupXp() {
        this.setupCurrentXp();
        this.setupXpToNextLevel();
    }

    private void setupCurrentXp() {
        this.currentXp.put(SkillType.MINING, this.miningXP);
        this.currentXp.put(SkillType.COMBAT, this.combatXP);
        this.currentXp.put(SkillType.FARMING, this.farmingXP);
        this.currentXp.put(SkillType.FORAGING, this.foragingXP);
        this.currentXp.put(SkillType.HEALTH, this.healthXP);
        this.currentXp.put(SkillType.DEFENSE, this.defenseXP);
        this.currentXp.put(SkillType.DAMAGE, this.damageXP);
        this.currentXp.put(SkillType.MANA, this.manaXP);
    }

    private void setupXpToNextLevel() {
        this.xpToNextLevel.put(SkillType.MINING, this.miningXPToNextLevel);
        this.xpToNextLevel.put(SkillType.COMBAT, this.combatXPToNextLevel);
        this.xpToNextLevel.put(SkillType.FARMING, this.farmingXPToNextLevel);
        this.xpToNextLevel.put(SkillType.FORAGING, this.foragingXPToNextLevel);
        this.xpToNextLevel.put(SkillType.HEALTH, this.healthXPToNextLevel);
        this.xpToNextLevel.put(SkillType.DEFENSE, this.defenseXPToNextLevel);
        this.xpToNextLevel.put(SkillType.DAMAGE, this.damageXPToNextLevel);
        this.xpToNextLevel.put(SkillType.MANA, this.manaXPToNextLevel);
    }

    private void updateCurrentXpTable(SkillType skillType) {
        try {
            sql.updateTable(skillType.getTableType(), "CURRENT_XP", this.player.getUniqueId().toString(), this.currentXp.get(skillType));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void updateXpToNextLevelTable(SkillType skillType) {
        try {
            sql.updateTable(skillType.getTableType(), "XP_TO_NEXT_LEVEL", this.player.getUniqueId().toString(), this.xpToNextLevel.get(skillType));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void updateTable(SkillType skillType) {
        this.updateCurrentXpTable(skillType);
        this.updateXpToNextLevelTable(skillType);
    }

    public void uploadData() {
        this.uploadTime = System.currentTimeMillis();
        System.out.println("[AzaleaSkills] Initiating data syncing for player " + this.player.getName() + ". Attempting to upload SQL data to Database.");
        Arrays.stream(SkillType.values()).forEach(skillType -> {
            CompletableFuture.runAsync(() -> this.updateTable(skillType));
        });
        System.out.println("[AzaleaSkills] Successfully synced data for player " + this.player.getName() + "! Process took: " + (System.currentTimeMillis() - this.uploadTime) + "ms");
    }

    public Integer getLevel(SkillType skillType) {
        try {
            return sql.getValue(skillType.getTableType(), "LEVEL", this.player.getUniqueId().toString());
        } catch (SQLException exception) {
            exception.printStackTrace();
            System.out.println("[AzaleaSkills] (FATAL) An error occured while attempting to retrieve level info for player " + this.player.getName() + ".");
            return -1;
        }
    }

    public Integer getCurrentXpSQL(SkillType skillType) {
        try {
            return sql.getValue(skillType.getTableType(), "CURRENT_XP", this.player.getUniqueId().toString());
        } catch (SQLException exception) {
            exception.printStackTrace();
            System.out.println("[AzaleaSkills] (FATAL) An error occured while attempting to retrieve current xp info for player " + this.player.getName() + ".");
            return -1;
        }
    }

    public Integer getXpToNextLevelSQL(SkillType skillType) {
        try {
            return sql.getValue(skillType.getTableType(), "XP_TO_NEXT_LEVEL", this.player.getUniqueId().toString());
        } catch (SQLException exception) {
            exception.printStackTrace();
            System.out.println("[AzaleaSkills] (FATAL) An error occured while attempting to retrieve current xp info for player " + this.player.getName() + ".");
            return -1;
        }
    }

    public void setXpToNextLevelSQL(SkillType skillType, Integer value) {
        try {
            sql.updateTable(skillType.getTableType(), "XP_TO_NEXT_LEVEL", player.getUniqueId().toString(), value);
        } catch (SQLException exception) {
            exception.printStackTrace();
            System.out.println("[AzaleaSkills] (FATAL) An error occured while attempting to update level for player " + this.player.getName() + ".");
        }
    }

    public void setCurrentXpSQL(SkillType skillType, Integer value) {
        try {
            sql.updateTable(skillType.getTableType(), "CURRENT_XP", player.getUniqueId().toString(), value);
        } catch (SQLException exception) {
            exception.printStackTrace();
            System.out.println("[AzaleaSkills] (FATAL) An error occured while attempting to update level for player " + this.player.getName() + ".");
        }
    }

    public void setLevel(SkillType skillType, Integer value) {
        try {
            sql.updateTable(skillType.getTableType(), "LEVEL", player.getUniqueId().toString(), value);
        } catch (SQLException exception) {
            exception.printStackTrace();
            System.out.println("[AzaleaSkills] (FATAL) An error occured while attempting to update level for player " + this.player.getName() + ".");
        }
    }

    public void initLevelUp(SkillType skillType) {
        int currentXp = this.getCurrentXpSQL(skillType);
        int xpToNextLevel = this.getXpToNextLevelSQL(skillType);
        while (currentXp >= xpToNextLevel) {
            currentXp -= xpToNextLevel;
            boolean isIncremental = AzaleaSkills.cfg.getBoolean("leveling.milestones.increment.enabled");
            if (isIncremental)
                xpToNextLevel = AzaleaSkills.cfg.getInt("leveling.milestones.increment.increment_value") * this.getLevel(skillType);
            else if (this.getLevel(skillType) == 50) xpToNextLevel = 0;
            else xpToNextLevel = AzaleaSkills.cfg.getInt("leveling.milestones.level_" + this.getLevel(skillType));
            this.setXpToNextLevelSQL(skillType, xpToNextLevel);
            this.setLevel(skillType, this.getLevel(skillType) + 1);
        }
        int finalCurrentXp = currentXp;
        CompletableFuture.runAsync(() -> this.setCurrentXpSQL(skillType, finalCurrentXp));
    }

    public void levelUp(SkillType skillType) {
        this.uploadData();
        int initialLevel = this.getLevel(skillType);
        initLevelUp(skillType);
        int xpToNextLevel = this.getXpToNextLevelSQL(skillType);
        this.currentXp.put(skillType, 0);
        this.xpToNextLevel.put(skillType, xpToNextLevel);
        this.player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8&m-------------------------"));
        this.player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6  Skill Level Up! &3&l" + skillType.getName() + " " + Util.IntegerToRomanNumeral(initialLevel) + " &8&l➢ &3&l" + Util.IntegerToRomanNumeral(this.getLevel(skillType))));
        this.player.sendMessage(" ");
        this.player.sendMessage(ChatColor.translateAlternateColorCodes('&', "  &7&oLeveling up allows you to gain access to different areas"));
        this.player.sendMessage(ChatColor.translateAlternateColorCodes('&', "  &7&oof the server, while also showing your intense work and dedication."));
        this.player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8&m-------------------------"));
        assert XSound.ENTITY_PLAYER_LEVELUP.parseSound() != null;
        this.player.playSound(this.player.getLocation(), XSound.ENTITY_PLAYER_LEVELUP.parseSound(), 1.0f, 1.0f);
    }

    public void setCurrentXp(SkillType skillType, Integer value) {
        this.currentXp.put(skillType, value);
    }

    public Integer getCurrentXp(SkillType skillType) {
        return this.currentXp.get(skillType);
    }

    public void setXpToNextLevel(SkillType skillType, Integer value) {
        this.xpToNextLevel.put(skillType, value);
    }

    public Integer getXpToNextLevel(SkillType skillType) {
        return this.xpToNextLevel.get(skillType);
    }

    public DataSavingTask getDataSavingTask() {
        return this.dataSavingTask;
    }

    public void removeDataSavingTask() {
        this.dataSavingTask = null;
    }
}
