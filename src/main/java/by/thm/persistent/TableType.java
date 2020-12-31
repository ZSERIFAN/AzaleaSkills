package by.thm.persistent;

public enum TableType {

    PLAYER_DATA("player_data"),
    DATA_MINING("data_mining"),
    DATA_COMBAT("data_combat"),
    DATA_FARMING("data_farming"),
    DATA_FORAGING("data_foraging"),
    DATA_HEALTH("data_health"),
    DATA_DEFENSE("data_defense"),
    DATA_DAMAGE("data_damage"),
    DATA_MANA("data_mana");

    private String name;

    TableType(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
