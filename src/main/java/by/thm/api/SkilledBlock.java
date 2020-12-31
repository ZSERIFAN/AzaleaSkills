package by.thm.api;

import by.thm.multiversion.XMaterial;
import org.bukkit.Material;

import java.util.Arrays;
import java.util.Optional;

public enum SkilledBlock {

    COBBLESTONE(XMaterial.COBBLESTONE.parseMaterial(), SkillType.MINING, 1),
    STONE(XMaterial.STONE.parseMaterial(), SkillType.MINING, 1),
    GRASS(XMaterial.GRASS.parseMaterial(), SkillType.FARMING, 1),
    GRASS_BLOCK(XMaterial.GRASS_BLOCK.parseMaterial(), SkillType.MINING, 1),
    DIRT(XMaterial.DIRT.parseMaterial(), SkillType.MINING, 1),
    ANDESITE(XMaterial.ANDESITE.parseMaterial(), SkillType.MINING, 2),
    DIORITE(XMaterial.DIORITE.parseMaterial(), SkillType.MINING, 2),
    GRANITE(XMaterial.GRANITE.parseMaterial(), SkillType.MINING, 2),
    GRAVEL(XMaterial.GRAVEL.parseMaterial(), SkillType.MINING, 2),
    SAND(XMaterial.SAND.parseMaterial(), SkillType.MINING, 1),
    SANDSTONE(XMaterial.SANDSTONE.parseMaterial(), SkillType.MINING, 1),
    IRON_ORE(XMaterial.IRON_ORE.parseMaterial(), SkillType.MINING, 2),
    LAPIS_ORE(XMaterial.LAPIS_ORE.parseMaterial(), SkillType.MINING, 2),
    GOLD_ORE(XMaterial.GOLD_ORE.parseMaterial(), SkillType.MINING, 4),
    EMERALD_ORE(XMaterial.EMERALD_ORE.parseMaterial(), SkillType.MINING, 15),
    ICE(XMaterial.ICE.parseMaterial(), SkillType.MINING, 2),
    SNOW(XMaterial.SNOW_BLOCK.parseMaterial(), SkillType.MINING, 1),
    SNOWY(XMaterial.SNOW.parseMaterial(), SkillType.MINING, 1),
    COAL_ORE(XMaterial.COAL_ORE.parseMaterial(), SkillType.MINING, 2),
    DIAMOND_ORE(XMaterial.DIAMOND_ORE.parseMaterial(), SkillType.MINING, 10),
    OBSIDIAN(XMaterial.OBSIDIAN.parseMaterial(), SkillType.MINING, 20),
    REDSTONE_ORE(XMaterial.REDSTONE_ORE.parseMaterial(), SkillType.MINING, 8),
    PODZOL(XMaterial.PODZOL.parseMaterial(), SkillType.MINING, 1),
    NETHERRACK(XMaterial.NETHERRACK.parseMaterial(), SkillType.MINING, 1),
    GLOWSTONE(XMaterial.GLOWSTONE.parseMaterial(), SkillType.MINING, 8),
    QUARTZ_ORE(XMaterial.NETHER_QUARTZ_ORE.parseMaterial(), SkillType.MINING, 10),
    SOULSAND(XMaterial.SOUL_SAND.parseMaterial(), SkillType.MINING, 4),
    ENDSTONE(XMaterial.END_STONE.parseMaterial(), SkillType.MINING, 5),
    TERRACOTA(XMaterial.TERRACOTTA.parseMaterial(), SkillType.MINING, 4),
    WHITE_TERRACOTTA(XMaterial.WHITE_TERRACOTTA.parseMaterial(), SkillType.MINING, 4),
    MAGENTA_TERRACOTTA(XMaterial.MAGENTA_TERRACOTTA.parseMaterial(), SkillType.MINING, 4),
    ORANGE_TERRACOTTA(XMaterial.ORANGE_TERRACOTTA.parseMaterial(), SkillType.MINING, 4),
    LIGHT_BLUE_TERRACOTTA(XMaterial.LIGHT_BLUE_TERRACOTTA.parseMaterial(), SkillType.MINING, 4),
    YELLOW_TERRACOTTA(XMaterial.YELLOW_TERRACOTTA.parseMaterial(), SkillType.MINING, 4),
    LIME_TERRACOTTA(XMaterial.LIME_TERRACOTTA.parseMaterial(), SkillType.MINING, 4),
    PINK_TERRACOTTA(XMaterial.PINK_TERRACOTTA.parseMaterial(), SkillType.MINING, 4),
    GRAY_TERRACOTTA(XMaterial.GRAY_TERRACOTTA.parseMaterial(), SkillType.MINING, 4),
    LIGHT_GRAY_TERRACOTTA(XMaterial.LIGHT_GRAY_TERRACOTTA.parseMaterial(), SkillType.MINING, 4),
    CYAN_TERRACOTTA(XMaterial.CYAN_TERRACOTTA.parseMaterial(), SkillType.MINING, 4),
    PURPLE_TERRACOTTA(XMaterial.PURPLE_TERRACOTTA.parseMaterial(), SkillType.MINING, 4),
    BLUE_TERRACOTTA(XMaterial.BLUE_TERRACOTTA.parseMaterial(), SkillType.MINING, 4),
    BROWN_TERRACOTTA(XMaterial.BROWN_TERRACOTTA.parseMaterial(), SkillType.MINING, 4),
    GREEN_TERRACOTTA(XMaterial.GREEN_TERRACOTTA.parseMaterial(), SkillType.MINING, 4),
    RED_TERRACOTTA(XMaterial.RED_TERRACOTTA.parseMaterial(), SkillType.MINING, 4),
    BLACK_TERRACOTTA(XMaterial.BLACK_TERRACOTTA.parseMaterial(), SkillType.MINING, 4),
    CLAY(XMaterial.CLAY.parseMaterial(), SkillType.MINING, 3),
    PACKED_ICE(XMaterial.PACKED_ICE.parseMaterial(), SkillType.MINING, 3),
    ACACIA_WOOD(XMaterial.ACACIA_WOOD.parseMaterial(), SkillType.FORAGING, 3),
    BIRCH_WOOD(XMaterial.BIRCH_WOOD.parseMaterial(), SkillType.FORAGING, 3),
    DARK_OAK_WOOD(XMaterial.DARK_OAK_WOOD.parseMaterial(), SkillType.FORAGING, 3),
    JUNGLE_WOOD(XMaterial.JUNGLE_WOOD.parseMaterial(), SkillType.FORAGING, 3),
    OAK_WOOD(XMaterial.OAK_WOOD.parseMaterial(), SkillType.FORAGING, 3),
    SPRUCE_WOOD(XMaterial.SPRUCE_WOOD.parseMaterial(), SkillType.FORAGING, 3),
    ACACIA_LOG(XMaterial.ACACIA_LOG.parseMaterial(), SkillType.FORAGING, 4),
    BIRCH_LOG(XMaterial.BIRCH_LOG.parseMaterial(), SkillType.FORAGING, 4),
    DARK_OAK_LOG(XMaterial.DARK_OAK_LOG.parseMaterial(), SkillType.FORAGING, 4),
    JUNGLE_LOG(XMaterial.JUNGLE_LOG.parseMaterial(), SkillType.FORAGING, 4),
    OAK_LOG(XMaterial.OAK_LOG.parseMaterial(), SkillType.FORAGING, 4),
    SPRUCE_LOG(XMaterial.SPRUCE_LOG.parseMaterial(), SkillType.FORAGING, 4),
    WHEAT_BLOCK(XMaterial.WHEAT.parseMaterial(), SkillType.FARMING, 1),
    CARROTS(XMaterial.CARROTS.parseMaterial(), SkillType.FARMING, 1),
    POTATOES(XMaterial.POTATOES.parseMaterial(), SkillType.FARMING, 1),
    MELON(XMaterial.MELON.parseMaterial(), SkillType.FARMING, 4),
    PUMPKIN(XMaterial.PUMPKIN.parseMaterial(), SkillType.FARMING, 4),
    COCOA(XMaterial.COCOA.parseMaterial(), SkillType.FARMING, 2),
    SUGAR_CANE(XMaterial.SUGAR_CANE.parseMaterial(), SkillType.FARMING, 2),
    MUSHROOMS(XMaterial.MUSHROOM_STEM.parseMaterial(), SkillType.FARMING, 2),
    NETHER_WART(XMaterial.NETHER_WART.parseMaterial(),  SkillType.FARMING, 2);

    private Material material;
    private SkillType skillType;
    private Integer xp;

    SkilledBlock(Material material, SkillType skillType, Integer xp) {
        this.material = material;
        this.skillType = skillType;
        this.xp = xp;
    }

    public Material getMaterial() {
        return this.material;
    }

    public SkillType getSkillType() {
        return this.skillType;
    }

    public Integer getXp() {
        return this.xp;
    }

    public static boolean exists(Material material) {
        Optional<SkilledBlock> match = Arrays.stream(values()).filter(obj -> obj.getMaterial().equals(material)).findFirst();
        return match.isPresent();
    }

    public static SkilledBlock getFromMaterial(Material material) {
        Optional<SkilledBlock> match = Arrays.stream(values()).filter(obj -> obj.getMaterial().equals(material)).findFirst();
        return match.orElse(null);
    }
}
