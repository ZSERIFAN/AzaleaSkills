package by.thm.persistent;

import by.thm.AzaleaSkills;
import org.bukkit.entity.Player;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class SQLMethods {

    private SQLConnection con = new SQLConnection();

    public boolean playerExists(UUID uuid) {
        PreparedStatement ps = null;
        try {
            ps = con.getConnection().prepareStatement("SELECT * FROM player_data WHERE UUID = ?");
            ps.setString(1, uuid.toString());

            ResultSet rs = ps.executeQuery();
            if (rs.next()) return true;
            return false;
        } catch (SQLException exception) {
            exception.printStackTrace();
            return false;
        }
    }

    public void createPlayer(Player player) {
        try {
            PreparedStatement ps = con.getConnection().prepareStatement("SELECT * FROM player_data WHERE UUID = ?");
            ps.setString(1, player.getUniqueId().toString());
            ResultSet rs = ps.executeQuery();
            rs.next();
            if (!(playerExists(player.getUniqueId())))
                for (TableType type : TableType.values())
                    setupTable(type, player);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    public void setupTable(TableType type, Player player) throws SQLException {
        switch (type) {
            case PLAYER_DATA:
                PreparedStatement ps1 = con.getConnection()
                        .prepareStatement("INSERT INTO `player_data` (UUID, NAME) VALUE (?, ?)");
                ps1.setString(1, player.getUniqueId().toString());
                ps1.setString(2, player.getName());
                ps1.execute();
                break;
            default:
                PreparedStatement ps2 = con.getConnection()
                        .prepareStatement("INSERT INTO `" + type.name() + "` (UUID, LEVEL, CURRENT_XP, XP_TO_NEXT_LEVEL) VALUE (?, ?, ?, ?)");
                ps2.setString(1, player.getUniqueId().toString());
                ps2.setInt(2, 1);
                ps2.setInt(3, 0);
                boolean isIncremental = AzaleaSkills.cfg.getBoolean("leveling.milestones.increment.enabled");
                if (isIncremental)
                    ps2.setInt(4, AzaleaSkills.cfg.getInt("leveling.milestones.increment.increment_value"));
                else
                    ps2.setInt(4, AzaleaSkills.cfg.getInt("leveling.milestones.level_1"));
                ps2.execute();
                break;
        }
    }

    public void updateTable(TableType type, String field, String UUID, int value) throws SQLException {
        String statement = "UPDATE " + type.name() + " SET " + field + " = ? WHERE UUID = ?";
        PreparedStatement ps = con.getConnection().prepareStatement(statement);
        ps.setInt(1, value);
        ps.setString(2, UUID);
        ps.executeUpdate();
    }

    public int getValue(TableType table, String field, String UUID) throws SQLException {
        String statement = "SELECT * FROM " + table.name() + " WHERE UUID = ?";
        PreparedStatement ps = con.getConnection().prepareStatement(statement);
        ps.setString(1, UUID);
        ResultSet rs = ps.executeQuery();
        if (rs.next())
            return rs.getInt(field);
        else
            return -1;
    }
}
