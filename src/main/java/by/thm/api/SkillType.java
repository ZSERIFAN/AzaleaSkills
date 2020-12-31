package by.thm.api;

import by.thm.persistent.TableType;

public enum SkillType {

    MINING(TableType.DATA_MINING, "Mining"),
    COMBAT(TableType.DATA_COMBAT, "Combat"),
    FARMING(TableType.DATA_FARMING, "Farming"),
    FORAGING(TableType.DATA_FORAGING, "Foraging"),
    HEALTH(TableType.DATA_HEALTH, "Health"),
    DEFENSE(TableType.DATA_DEFENSE, "Defense"),
    MANA(TableType.DATA_MANA, "Mana"),
    DAMAGE(TableType.DATA_DAMAGE, "Damage");

    private TableType type;
    private String name;

    SkillType(TableType tableType, String name) {
        this.type = tableType;
        this.name = name;
    }

    public TableType getTableType() {
        return this.type;
    }

    public String getName() {
        return this.name;
    }
}
