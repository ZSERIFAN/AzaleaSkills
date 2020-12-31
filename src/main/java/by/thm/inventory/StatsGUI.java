package by.thm.inventory;

import by.thm.Util;
import by.thm.api.SkillManager;
import by.thm.api.SkillType;
import by.thm.api.SkilledPlayer;
import by.thm.persistent.SQLMethods;
import by.thm.persistent.TableType;
import by.thmdev.api.AttributeManager;
import by.thmdev.api.AttributedPlayer;
import com.cryptomorin.xseries.XMaterial;
import com.cryptomorin.xseries.XSound;
import fr.minuskube.inv.ClickableItem;
import fr.minuskube.inv.SmartInventory;
import fr.minuskube.inv.content.InventoryContents;
import fr.minuskube.inv.content.InventoryProvider;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.sql.SQLException;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class StatsGUI implements InventoryProvider {

    private Random random = ThreadLocalRandom.current();

    private Player player;

    public StatsGUI(Player player) {
        this.player = player;
    }

    SQLMethods sql = new SQLMethods();

    public final SmartInventory stats = SmartInventory.builder()
            .id("selector")
            .size(6, 9)
            .title(ChatColor.translateAlternateColorCodes('&', "&3Displaying statistics"))
            .provider(this)
            .build();

    @Override
    public void init(Player player, InventoryContents contents) {
        try {
            player.playSound(player.getLocation(), XSound.BLOCK_NOTE_BLOCK_PLING.parseSound(), 1.0f, 1.0f);
            contents.set(2, 2, ClickableItem.empty(Util.createItem(XMaterial.ENCHANTED_GOLDEN_APPLE.parseMaterial(), "§cHealth Skill", "§8§m------------------------", "§7By leveling up this skill, you", "§7gain permanent §cHP§7! Can be increased", "§7by finding and utilizing §c§nHealth Artifacts§7.", "", "§6§l➢ §e" + this.player.getName() + "§7's current Health Level: §3§l" + Util.IntegerToRomanNumeral(sql.getValue(TableType.DATA_HEALTH, "LEVEL", this.player.getUniqueId().toString())), "   §e§lCURRENT PROGRESS§7: §3" + String.format("%,d", sql.getValue(TableType.DATA_HEALTH, "CURRENT_XP", this.player.getUniqueId().toString())) + "§8/§3" + String.format("%,d", sql.getValue(TableType.DATA_HEALTH, "XP_TO_NEXT_LEVEL", this.player.getUniqueId().toString())), "", "§7§o(Refreshes every 5 minutes)", "§8§m------------------------")));
            contents.set(2, 3, ClickableItem.empty(Util.createItem(XMaterial.DIAMOND_CHESTPLATE.parseMaterial(), "§3Defense Skill", "§8§m------------------------", "§7By leveling up this skill, you", "§7gain permanent §3DEFENSE§7! Can be increased", "§7by finding and utilizing §3§nDefense Artifacts§7.", "", "§6§l➢ §e" + this.player.getName() + "§7's current Defense Level: §3§l" + Util.IntegerToRomanNumeral(sql.getValue(TableType.DATA_DEFENSE, "LEVEL", this.player.getUniqueId().toString())), "   §e§lCURRENT PROGRESS§7: §3" + String.format("%,d", sql.getValue(TableType.DATA_DEFENSE, "CURRENT_XP", this.player.getUniqueId().toString())) + "§8/§3" + String.format("%,d", sql.getValue(TableType.DATA_DEFENSE, "XP_TO_NEXT_LEVEL", this.player.getUniqueId().toString())), "", "§7§o(Refreshes every 5 minutes)", "§8§m------------------------")));
            contents.set(2, 4, ClickableItem.empty(Util.createItem(XMaterial.IRON_SWORD.parseMaterial(), "§cCombat Skill", "§8§m------------------------", "§7Level up this skill by killing", "§c§nMobs§7 and §c§nPlayers§7. Grants small", "§7damage buffs to you.", "", "§6§l➢ §e" + this.player.getName() + "§7's current Combat Level: §3§l" + Util.IntegerToRomanNumeral(sql.getValue(TableType.DATA_COMBAT, "LEVEL", this.player.getUniqueId().toString())), "   §e§lCURRENT PROGRESS§7: §3" + String.format("%,d", sql.getValue(TableType.DATA_COMBAT, "CURRENT_XP", this.player.getUniqueId().toString())) + "§8/§3" + String.format("%,d", sql.getValue(TableType.DATA_COMBAT, "XP_TO_NEXT_LEVEL", this.player.getUniqueId().toString())), "", "§7§o(Refreshes every 5 minutes)", "§8§m------------------------")));
            contents.set(2, 5, ClickableItem.empty(Util.createItem(XMaterial.STICK.parseMaterial(), "§bMana Skill", "§8§m------------------------", "§7By leveling up this skill, you", "§7gain permanent §bMANA§7! Can be increased", "§7by finding and utilizing §b§nMana Artifacts§7.", "", "§6§l➢ §e" + this.player.getName() + "§7's current Mana Level: §3§l" + Util.IntegerToRomanNumeral(sql.getValue(TableType.DATA_MANA, "LEVEL", this.player.getUniqueId().toString())), "   §e§lCURRENT PROGRESS§7: §3" + String.format("%,d", sql.getValue(TableType.DATA_MANA, "CURRENT_XP", this.player.getUniqueId().toString())) + "§8/§3" + String.format("%,d", sql.getValue(TableType.DATA_MANA, "XP_TO_NEXT_LEVEL", this.player.getUniqueId().toString())), "", "§7§o(Refreshes every 5 minutes)", "§8§m------------------------")));
            contents.set(2, 6, ClickableItem.empty(Util.createItem(XMaterial.FLINT_AND_STEEL.parseMaterial(), "§cDamage Skill", "§8§m------------------------", "§7By leveling up this skill, you", "§7gain permanent §cDAMAGE§7! Can be increased", "§7by finding and utilizing §c§nDamage Artifacts§7.", "", "§6§l➢ §e" + this.player.getName() + "§7's current Damage Level: §3§l" + Util.IntegerToRomanNumeral(sql.getValue(TableType.DATA_DAMAGE, "LEVEL", this.player.getUniqueId().toString())), "   §e§lCURRENT PROGRESS§7: §3" + String.format("%,d", sql.getValue(TableType.DATA_DAMAGE, "CURRENT_XP", this.player.getUniqueId().toString())) + "§8/§3" + String.format("%,d", sql.getValue(TableType.DATA_DAMAGE, "XP_TO_NEXT_LEVEL", this.player.getUniqueId().toString())), "", "§7§o(Refreshes every 5 minutes)", "§8§m------------------------")));
            contents.set(3, 3, ClickableItem.empty(Util.createItem(XMaterial.OAK_SAPLING.parseMaterial(), "§2Treecutter Skill", "§8§m------------------------", "§7Level up this skill by cutting down", "§2Logs§7. Grants a §22% §7chance", "§7per level to gain double logs, adding up to §2100%§7.", "", "§6§l➢ §e" + this.player.getName() + "§7's current Treecutter Level: §3§l" + Util.IntegerToRomanNumeral(sql.getValue(TableType.DATA_FORAGING, "LEVEL", this.player.getUniqueId().toString())), "   §e§lCURRENT PROGRESS§7: §3" + String.format("%,d", sql.getValue(TableType.DATA_FORAGING, "CURRENT_XP", this.player.getUniqueId().toString())) + "§8/§3" + String.format("%,d", sql.getValue(TableType.DATA_FORAGING, "XP_TO_NEXT_LEVEL", this.player.getUniqueId().toString())), "", "§7§o(Refreshes every 5 minutes)", "§8§m------------------------")));
            contents.set(3, 4, ClickableItem.empty(Util.createItem(XMaterial.IRON_PICKAXE.parseMaterial(), "§aMining Skill", "§8§m------------------------", "§7Level up this skill by minings", "§aOres§7 and §aBlocks§7. Grants a §a2% §7chance", "§7per level to gain double ores, adding up to §a100%§7.", "", "§6§l➢ §e" + this.player.getName() + "§7's current Mining Level: §3§l" + Util.IntegerToRomanNumeral(sql.getValue(TableType.DATA_MINING, "LEVEL", this.player.getUniqueId().toString())), "   §e§lCURRENT PROGRESS§7: §3" + String.format("%,d", sql.getValue(TableType.DATA_MINING, "CURRENT_XP", this.player.getUniqueId().toString())) + "§8/§3" + String.format("%,d", sql.getValue(TableType.DATA_MINING, "XP_TO_NEXT_LEVEL", this.player.getUniqueId().toString())), "", "§7§o(Refreshes every 5 minutes)", "§8§m------------------------")));
            contents.set(3, 5, ClickableItem.empty(Util.createItem(XMaterial.WHEAT.parseMaterial(), "§eFarming Skill", "§8§m------------------------", "§7Level up this skill by harvesting", "§eCrops§7. Grants a §e2% §7chance", "§7per level to gain double crops, adding up to §e100%§7.", "", "§6§l➢ §e" + this.player.getName() + "§7's current Farming Level: §3§l" + Util.IntegerToRomanNumeral(sql.getValue(TableType.DATA_FARMING, "LEVEL", this.player.getUniqueId().toString())), "   §e§lCURRENT PROGRESS§7: §3" + String.format("%,d", sql.getValue(TableType.DATA_FARMING, "CURRENT_XP", this.player.getUniqueId().toString())) + "§8/§3" + String.format("%,d", sql.getValue(TableType.DATA_FARMING, "XP_TO_NEXT_LEVEL", this.player.getUniqueId().toString())), "", "§7§o(Refreshes every 5 minutes)", "§8§m------------------------")));
            if (this.player.isOnline()) {
                SkilledPlayer skilledPlayer = SkillManager.getSkilledPlayer(player);
                AttributedPlayer attributedPlayer = AttributeManager.getAttributedPlayer(player);
                contents.set(4, 7, ClickableItem.empty(Util.createItem(XMaterial.NETHER_STAR.parseMaterial(), "§ePlayer Stats", "§8§m------------------------", "§7These are the §e§lSTATS §7of " + this.player.getName() + ".", "§7Gain items and explore the territories", "§7to boost up yours as well!", "§6§l➢ §eDisplaying statistics", "  §eHealth: §3" + attributedPlayer.getMaxHealth(), "  §eDefense: §3§l" + attributedPlayer.getDefense(), "  §eMana: §3" + attributedPlayer.getMaxMana(), "  §eCrit Damage: §3" + attributedPlayer.getCritDmg() + "%", "  §eCrit Chance: §3" + attributedPlayer.getCritChance() + "%", "  §eDamage: §3" + attributedPlayer.getDamage(), "§8§m------------------------")));
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public void update(Player player, InventoryContents contents) {
        int state = contents.property("state", 0);
        contents.setProperty("state", state + 1);

        if(state % 5 != 0)
            return;

        short durability = (short) random.nextInt(15);

        ItemStack glass = new ItemStack(XMaterial.WHITE_STAINED_GLASS_PANE.parseMaterial(), 1, durability);
        ItemMeta meta = glass.getItemMeta();
        meta.setDisplayName(ChatColor.RED + "");
        glass.setItemMeta(meta);

        contents.fillBorders(ClickableItem.empty(glass));
    }
}
