package com.github.albardoo02;

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
        if (sender instanceof Player) {
            Player player = (Player) sender;
        } else {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cコンソールからは実行できません!"));
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
                        sender.sendMessage("/wiki set <URL>");
                        return true;
                    }
                    if (args[1].equalsIgnoreCase(args[1])) {
                        sender.sendMessage("URLを" + args[1] + "に設定しました");
                        plugin.getConfig().set("URL", args[1]);
                        plugin.saveConfig();
                    }
                }
                else {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&',"&cあなたには権限がありません!"));
                    return true;
                }
            }
            else if (args[0].equalsIgnoreCase("reload")) {
                if (sender.hasPermission("wikipebia.command.admin")) {
                    sender.sendMessage("Configを再読み込みしました");
                    plugin.reloadConfig();
                }
                else {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&',"&cあなたには権限がありません!"));
                    return true;
                }
            }
            else if (args[0].equalsIgnoreCase("add")) {
                if (sender.hasPermission("wikipebia.command.admin")) {
                    if (args.length == 1) {
                        sender.sendMessage("/wiki add <名前> <URL>");
                        return true;
                    }
                    if (args.length == 2) {
                        sender.sendMessage("/wiki add " + args[1] + " <URL>");
                        return true;
                    }
                    if (args[2].equalsIgnoreCase(args[2])) {
                        sender.sendMessage(args[1] + "のURLを" + args[2] + "で登録しました");
                        plugin.getConfig().set("message." + args[1] + ".URL", args[2]);
                        List<String> TAB = plugin.getConfig().getStringList("TAB");
                        TAB.add(args[1]);
                        plugin.getConfig().set("TAB",TAB);
                        plugin.saveConfig();
                    }
                }
                else {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&',"&cあなたには権限がありません!"));
                    return true;
                }
            }
            for (String line : plugin.getConfig().getStringList("TAB")) {
                if (args[0].equalsIgnoreCase(line)) {
                    sender.sendMessage(plugin.getConfig().getString("message." + args[0] + ".URL"));
                }
            }

        }
        return true;
    }
}
