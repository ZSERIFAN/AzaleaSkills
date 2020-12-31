package by.thm.persistent;

import by.thm.AzaleaSkills;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class SQLConnection {

    public static Connection connection;
    public static String host, database, username, password;
    public static Integer port;

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public void createTable() {
        try {
            PreparedStatement ps1 = getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS player_data (UUID varchar(256), NAME varchar(256))");
            PreparedStatement ps2 = getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS data_mining (UUID varchar(256), LEVEL bigint(255), CURRENT_XP bigint(255), XP_TO_NEXT_LEVEL bigint(255))");
            PreparedStatement ps3 = getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS data_foraging (UUID varchar(256), LEVEL bigint(255), CURRENT_XP bigint(255), XP_TO_NEXT_LEVEL bigint(255))");
            PreparedStatement ps4 = getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS data_combat (UUID varchar(256), LEVEL bigint(255), CURRENT_XP bigint(255), XP_TO_NEXT_LEVEL bigint(255))");
            PreparedStatement ps5 = getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS data_farming (UUID varchar(256), LEVEL bigint(255), CURRENT_XP bigint(255), XP_TO_NEXT_LEVEL bigint(255))");
            PreparedStatement ps6 = getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS data_health (UUID varchar(256), LEVEL bigint(255), CURRENT_XP bigint(255), XP_TO_NEXT_LEVEL bigint(255))");
            PreparedStatement ps7 = getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS data_defense (UUID varchar(256), LEVEL bigint(255), CURRENT_XP bigint(255), XP_TO_NEXT_LEVEL bigint(255))");
            PreparedStatement ps8 = getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS data_damage (UUID varchar(256), LEVEL bigint(255), CURRENT_XP bigint(255), XP_TO_NEXT_LEVEL bigint(255))");
            PreparedStatement ps9 = getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS data_mana (UUID varchar(256), LEVEL bigint(255), CURRENT_XP bigint(255), XP_TO_NEXT_LEVEL bigint(255))");
            List <PreparedStatement> statements = Arrays.asList(ps1, ps2, ps3, ps4, ps5, ps6, ps7, ps8, ps9);
            statements.forEach(statement -> {
                try {
                    statement.executeUpdate();
                } catch (SQLException exception) {
                    exception.printStackTrace();
                }
            });
        } catch (SQLException exception) {
            exception.printStackTrace();
            System.out.println("[AzaleaSkills] An error has occured while setting up SQL drivers. Tables couldn't get created. (Check if your database has been set up correctly!)");
        }
    }

    public void sqlConnect() {
        host = AzaleaSkills.cfg.getString("MySQL.host");
        port = AzaleaSkills.cfg.getInt("MySQL.port");
        database = AzaleaSkills.cfg.getString("MySQL.database");
        username = AzaleaSkills.cfg.getString("MySQL.username");
        password = AzaleaSkills.cfg.getString("MySQL.password");

        try {
            synchronized (this) {
                if (getConnection() != null && !getConnection().isClosed()) return;
                Class.forName("com.mysql.jdbc.Driver");
                setConnection(DriverManager.getConnection("jdbc:mysql://" + this.host + ":" + this.port + "/" + this.database, this.username, this.password));
            }
        } catch (SQLException | ClassNotFoundException exception) {
            exception.printStackTrace();
        }
    }
}
