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

public class SkilledPlayer implements Skill {

    private SQLMethods sql = new SQLMethods();

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
        } catch (SQLException exception) {
            exception.printStackTrace();
            System.out.println("[AzaleaSkills] (FATAL) Was not able to initiate SkilledPlayer object for player " + this.player.getName() + "! Report this stacktrace to thmihnea.");
        }
        SkillManager.addSkilledPlayer(this.player, this);
    }

    public void uploadData() throws SQLException {
        this.uploadTime = System.currentTimeMillis();
        System.out.println("[AzaleaSkills] Initiating data syncing for player " + this.player.getName() + ". Attempting to upload SQL data to Database.");
        for (SkillType skillType : SkillType.values()) {
            switch (skillType) {
                case MINING:
                    sql.updateTable(skillType.getTableType(), "CURRENT_XP", this.player.getUniqueId().toString(), this.getMiningXP());
                    sql.updateTable(skillType.getTableType(), "XP_TO_NEXT_LEVEL", this.player.getUniqueId().toString(), this.getMiningXPToNextLevel());
                    break;
                case COMBAT:
                    sql.updateTable(skillType.getTableType(), "CURRENT_XP", this.player.getUniqueId().toString(), this.getCombatXP());
                    sql.updateTable(skillType.getTableType(), "XP_TO_NEXT_LEVEL", this.player.getUniqueId().toString(), this.getCombatXPToNextLevel());
                    break;
                case FARMING:
                    sql.updateTable(skillType.getTableType(), "CURRENT_XP", this.player.getUniqueId().toString(), this.getFarmingXP());
                    sql.updateTable(skillType.getTableType(), "XP_TO_NEXT_LEVEL", this.player.getUniqueId().toString(), this.getFarmingXPToNextLevel());
                    break;
                case FORAGING:
                    sql.updateTable(skillType.getTableType(), "CURRENT_XP", this.player.getUniqueId().toString(), this.getForagingXP());
                    sql.updateTable(skillType.getTableType(), "XP_TO_NEXT_LEVEL", this.player.getUniqueId().toString(), this.getForagingXPToNextLevel());
                    break;
                case HEALTH:
                    sql.updateTable(skillType.getTableType(), "CURRENT_XP", this.player.getUniqueId().toString(), this.getHealthXP());
                    sql.updateTable(skillType.getTableType(), "XP_TO_NEXT_LEVEL", this.player.getUniqueId().toString(), this.getHealthXPToNextLevel());
                    break;
                case DEFENSE:
                    sql.updateTable(skillType.getTableType(), "CURRENT_XP", this.player.getUniqueId().toString(), this.getDefenseXP());
                    sql.updateTable(skillType.getTableType(), "XP_TO_NEXT_LEVEL", this.player.getUniqueId().toString(), this.getDefenseXPToNextLevel());
                    break;
                case DAMAGE:
                    sql.updateTable(skillType.getTableType(), "CURRENT_XP", this.player.getUniqueId().toString(), this.getDamageXP());
                    sql.updateTable(skillType.getTableType(), "XP_TO_NEXT_LEVEL", this.player.getUniqueId().toString(), this.getDamageXPToNextLevel());
                    break;
                case MANA:
                    sql.updateTable(skillType.getTableType(), "CURRENT_XP", this.player.getUniqueId().toString(), this.getManaXP());
                    sql.updateTable(skillType.getTableType(), "XP_TO_NEXT_LEVEL", this.player.getUniqueId().toString(), this.getManaXPToNextLevel());
                    break;
            }
        }
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

    public Integer getCurrentXP(SkillType skillType) {
        try {
            return sql.getValue(skillType.getTableType(), "CURRENT_XP", this.player.getUniqueId().toString());
        } catch (SQLException exception) {
            exception.printStackTrace();
            System.out.println("[AzaleaSkills] (FATAL) An error occured while attempting to retrieve current xp info for player " + this.player.getName() + ".");
            return -1;
        }
    }

    public Integer getXpToNextLevel(SkillType skillType) {
        try {
            return sql.getValue(skillType.getTableType(), "XP_TO_NEXT_LEVEL", this.player.getUniqueId().toString());
        } catch (SQLException exception) {
            exception.printStackTrace();
            System.out.println("[AzaleaSkills] (FATAL) An error occured while attempting to retrieve current xp info for player " + this.player.getName() + ".");
            return -1;
        }
    }

    public void setXpToNextLevel(SkillType skillType, Integer value) {
        try {
            sql.updateTable(skillType.getTableType(), "XP_TO_NEXT_LEVEL", player.getUniqueId().toString(), value);
        } catch (SQLException exception) {
            exception.printStackTrace();
            System.out.println("[AzaleaSkills] (FATAL) An error occured while attempting to update level for player " + this.player.getName() + ".");
        }
    }

    public void setCurrentXP(SkillType skillType, Integer value) {
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
        int currentXp = this.getCurrentXP(skillType);
        int xpToNextLevel = this.getXpToNextLevel(skillType);
        while (currentXp >= xpToNextLevel) {
            currentXp -= xpToNextLevel;
            boolean isIncremental = AzaleaSkills.cfg.getBoolean("leveling.milestones.increment.enabled");
            if (isIncremental)
                xpToNextLevel = AzaleaSkills.cfg.getInt("leveling.milestones.increment.increment_value") * this.getLevel(skillType);
            else if (this.getLevel(skillType) == 50) xpToNextLevel = 0;
            else xpToNextLevel = AzaleaSkills.cfg.getInt("leveling.milestones.level_" + this.getLevel(skillType));
            this.setXpToNextLevel(skillType, xpToNextLevel);
            this.setLevel(skillType, this.getLevel(skillType) + 1);
        }
        this.setCurrentXP(skillType, currentXp);
    }

    public void levelUp(SkillType skillType) {
        try {
            this.uploadData();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        int initialLevel = this.getLevel(skillType);
        initLevelUp(skillType);
        int xpToNextLevel = this.getXpToNextLevel(skillType);
        switch (skillType) {
            case MINING:
                this.miningXP = 0;
                this.miningXPToNextLevel = xpToNextLevel;
                break;
            case COMBAT:
                this.combatXP = 0;
                this.combatXPToNextLevel = xpToNextLevel;
                break;
            case FARMING:
                this.farmingXP = 0;
                this.farmingXPToNextLevel = xpToNextLevel;
                break;
            case FORAGING:
                this.foragingXP = 0;
                this.foragingXPToNextLevel = xpToNextLevel;
                break;
            case HEALTH:
                this.healthXP = 0;
                this.healthXPToNextLevel = xpToNextLevel;
                break;
            case DEFENSE:
                this.defenseXP = 0;
                this.defenseXPToNextLevel = xpToNextLevel;
                break;
            case DAMAGE:
                this.damageXP = 0;
                this.damageXPToNextLevel = xpToNextLevel;
                break;
            case MANA:
                this.manaXP = 0;
                this.manaXPToNextLevel = xpToNextLevel;
                break;
        }
        this.player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8&m-------------------------"));
        this.player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6  Skill Level Up! &3&l" + skillType.getName() + " " + Util.IntegerToRomanNumeral(initialLevel) + " &8&l➢ &3&l" + Util.IntegerToRomanNumeral(this.getLevel(skillType))));
        this.player.sendMessage(" ");
        this.player.sendMessage(ChatColor.translateAlternateColorCodes('&', "  &7&oLeveling up allows you to gain access to different areas"));
        this.player.sendMessage(ChatColor.translateAlternateColorCodes('&', "  &7&oof the server, while also showing your intense work and dedication."));
        this.player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8&m-------------------------"));
        this.player.playSound(this.player.getLocation(), XSound.ENTITY_PLAYER_LEVELUP.parseSound(), 1.0f, 1.0f);
    }

    public Integer getMiningXP() {
        return this.miningXP;
    }

    public Integer getMiningXPToNextLevel() {
        return this.miningXPToNextLevel;
    }

    public Integer getHealthXP() {
        return this.healthXP;
    }

    public Integer getHealthXPToNextLevel() {
        return this.healthXPToNextLevel;
    }

    public void setHealthXP(Integer value) {
        this.healthXP = value;
    }

    public void setHealthXPToNextLevel(Integer value) {
        this.healthXPToNextLevel = value;
    }

    public Integer getDefenseXP() {
        return this.defenseXP;
    }

    public Integer getDefenseXPToNextLevel() {
        return this.defenseXPToNextLevel;
    }

    public void setDefenseXP(Integer value) {
        this.defenseXP = value;
    }

    public void setDefenseXPToNextLevel(Integer value) {
        this.defenseXPToNextLevel = value;
    }

    public Integer getDamageXP() {
        return this.damageXP;
    }

    public Integer getDamageXPToNextLevel() {
        return this.damageXPToNextLevel;
    }

    public void setDamageXP(Integer value) {
        this.damageXP = value;
    }

    public void setDamageXPToNextLevel(Integer value) {
        this.damageXPToNextLevel = value;
    }

    public Integer getManaXP() {
        return this.manaXP;
    }

    public Integer getManaXPToNextLevel() {
        return this.manaXPToNextLevel;
    }

    public void setManaXP(Integer value) {
        this.manaXP = value;
    }

    public void setManaXPToNextLevel(Integer value) {
        this.manaXPToNextLevel = value;
    }

    public void setMiningXP(Integer value) {
        this.miningXP = value;
    }

    public void setMiningXPToNextLevel(Integer value) {
        this.miningXPToNextLevel = value;
    }

    public Integer getCombatXP() {
        return this.combatXP;
    }

    public Integer getCombatXPToNextLevel() {
        return this.combatXPToNextLevel;
    }

    public void setCombatXP(Integer value) {
        this.combatXP = value;
    }

    public void setCombatXPToNextLevel(Integer value) {
        this.combatXPToNextLevel = value;
    }

    public Integer getFarmingXP() {
        return this.farmingXP;
    }

    public Integer getFarmingXPToNextLevel() {
        return this.farmingXPToNextLevel;
    }

    public void setFarmingXP(Integer value) {
        this.farmingXP = value;
    }

    public void setFarmingXPToNextLevel(Integer value) {
        this.farmingXPToNextLevel = value;
    }

    public Integer getForagingXP() {
        return this.foragingXP;
    }

    public Integer getForagingXPToNextLevel() {
        return this.foragingXPToNextLevel;
    }

    public void setForagingXP(Integer value) {
        this.foragingXP = value;
    }

    public void setForagingXPToNextLevel(Integer value) {
        this.foragingXPToNextLevel = value;
    }

    public DataSavingTask getDataSavingTask() {
        return this.dataSavingTask;
    }

    public void removeDataSavingTask() {
        this.dataSavingTask = null;
    }
}
