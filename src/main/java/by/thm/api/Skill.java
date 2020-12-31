package by.thm.api;

public interface Skill {

    Integer getMiningXP();

    Integer getMiningXPToNextLevel();

    void setMiningXP(Integer value);

    void setMiningXPToNextLevel(Integer value);

    Integer getCombatXP();

    Integer getCombatXPToNextLevel();

    void setCombatXP(Integer value);

    void setCombatXPToNextLevel(Integer value);

    Integer getFarmingXP();

    Integer getFarmingXPToNextLevel();

    void setFarmingXP(Integer value);

    void setFarmingXPToNextLevel(Integer value);

    Integer getForagingXP();

    Integer getForagingXPToNextLevel();

    void setForagingXP(Integer value);

    void setForagingXPToNextLevel(Integer value);
}
