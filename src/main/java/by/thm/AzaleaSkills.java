package by.thm;

import by.thm.persistent.SQLConnection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.List;

public class AzaleaSkills extends JavaPlugin {

    private static AzaleaSkills instance;
    public static AzaleaSkills getInstance() {
        return instance;
    }
    private SQLConnection sql = new SQLConnection();
    public static File config = new File("plugins/AzaleaSkills/config.yml");
    public static FileConfiguration cfg = YamlConfiguration.loadConfiguration(config);
    private long timeEnabled;
    public List<Listener> events;

    @Override
    public void onEnable() {
        this.timeEnabled = System.currentTimeMillis();
        System.out.println("[AzaleaSkills] Attempting to enable plugin modules...");
        instance = this;
        Util.setupFiles();
        Util.registerEvents();
        Util.registerCommands();
        initSQL();
        Util.setupObjects();
        System.out.println("[AzaleaSkills] Plugin successfully enabled. Process took: " + (System.currentTimeMillis() - timeEnabled) + "ms");
    }

    @Override
    public void onDisable() {
        System.out.println("[AzaleaAttributes] Disabling plugin modules...");
    }

    public void initSQL() {
        sql.sqlConnect();
        sql.createTable();;
    }
}
