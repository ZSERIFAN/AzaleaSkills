package by.thm.commands;

import by.thm.AzaleaSkills;
import by.thm.inventory.StatsGUI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class StatsCommand implements CommandExecutor {

    public StatsCommand() {
        AzaleaSkills.getInstance().getCommand("stats").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) return false;
        Player player = (Player) sender;
        if (args.length >= 1) {
            OfflinePlayer offlineTarget = Bukkit.getOfflinePlayer(args[0]);
            Player target = offlineTarget.getPlayer();
            if (target == null) {
                player.sendMessage(ChatColor.RED + "Player not found, please try again later.");
                return false;
            }
            StatsGUI sg = new StatsGUI(target);
            sg.stats.open(player);
            return true;
        } else {
            StatsGUI sg = new StatsGUI(player);
            sg.stats.open(player);
            return true;
        }
    }
}
