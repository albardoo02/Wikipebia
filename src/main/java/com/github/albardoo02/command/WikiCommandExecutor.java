package com.github.albardoo02.command;

import com.github.albardoo02.Wikipebia;
import org.bukkit.ChatColor;
import org.bukkit.command.*;
import org.bukkit.entity.Player;

import java.util.List;

public class WikiCommandExecutor implements CommandExecutor {
    Wikipebia plugin;

    public WikiCommandExecutor(Wikipebia plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        String url = plugin.getConfig().getString("URL");
        String prefix = ChatColor.translateAlternateColorCodes('&',plugin.getConfig().getString("prefix"));
        if (sender instanceof Player) {
            Player player = (Player) sender;
        } else {
            for (String line : plugin.getConfig().getStringList("Console")) {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&',line));
                return true;
            }
            return true;
        }
        if(cmd.getName().equalsIgnoreCase("wiki")) {
            if (args.length <= 0) {
                sender.sendMessage(url);
                return true;
            }
            if (args[0].equalsIgnoreCase("url")) {
                sender.sendMessage(url);
            }
            else if (args[0].equalsIgnoreCase("set")) {
                if (sender.hasPermission("wikipebia.command.admin")) {
                    if (args.length == 1) {
                        for (String line : plugin.getConfig().getStringList("Set")) {
                            sender.sendMessage(ChatColor.translateAlternateColorCodes('&',line.replace("%prefix", prefix)));
                            return true;
                        }
                    }
                    if (args[1].equalsIgnoreCase(args[1])) {
                        sender.sendMessage("URLを" + args[1] + "に設定しました");
                        plugin.getConfig().set("URL", args[1]);
                        plugin.saveConfig();
                    }
                }
                else {
                    for (String line : plugin.getConfig().getStringList("Error")) {
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&',line.replace("%prefix", prefix)));
                        return true;
                    }
                }
            }
            else if (args[0].equalsIgnoreCase("reload")) {
                if (sender.hasPermission("wikipebia.command.admin")) {
                    for (String line : plugin.getConfig().getStringList("Reload")) {
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&',line.replace("%prefix", prefix)));
                        return true;
                    }
                    plugin.reloadConfig();
                }
                else {
                    for (String line : plugin.getConfig().getStringList("Error")) {
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&',line.replace("%prefix", prefix)));
                        return true;
                    }
                }
            }
            else if (args[0].equalsIgnoreCase("add")) {
                if (sender.hasPermission("wikipebia.command.admin")) {
                    if (args.length == 1) {
                        for (String line : plugin.getConfig().getStringList("Add_1")) {
                            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', line.replace("%prefix", prefix)));
                            return true;
                        }
                    }
                    if (args.length == 2) {
                        for (String line : plugin.getConfig().getStringList("Add_2")) {
                            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', line.replace("%prefix", prefix).replace("%args1",args[1])));
                            return true;
                        }
                    }
                    if (args[2].equalsIgnoreCase(args[2])) {
                        for (String line : plugin.getConfig().getStringList("Add_3")) {
                            sender.sendMessage(ChatColor.translateAlternateColorCodes('&',line.replace("%prefix", prefix)).replace("%args1",args[1]).replace("%args2",args[2]));
                        }
                        plugin.getConfig().set("Link." + args[1] + ".URL", args[2]);
                        List<String> TAB = plugin.getConfig().getStringList("TAB");
                        TAB.add(args[1]);
                        plugin.getConfig().set("TAB",TAB);
                        plugin.saveConfig();
                    }
                }
                else {
                    for (String line : plugin.getConfig().getStringList("Error")) {
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&',line.replace("%prefix", prefix)));
                        return true;
                    }
                }
            }
            for (String line : plugin.getConfig().getStringList("TAB")) {
                if (args[0].equalsIgnoreCase(line)) {
                    for (String link : plugin.getConfig().getStringList("Link")){
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&',link.replace("%prefix", prefix).replace("%args0",args[0])).replace("%url",plugin.getConfig().getString("Links." + args[0] + ".URL")));
                    }
                }
            }

        }
        return true;
    }
}
