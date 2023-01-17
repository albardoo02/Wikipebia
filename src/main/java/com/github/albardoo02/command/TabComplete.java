package com.github.albardoo02.command;

import com.github.albardoo02.Wikipebia;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.util.StringUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TabComplete implements TabCompleter {

    Wikipebia plugin;

    public TabComplete(Wikipebia plugin) {
        this.plugin = plugin;
    }

    Player player;

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String commandLable, String[] args) {
        List<String> completions = new ArrayList<>();
        if (args.length == 1) {
            completions.add("url");
            completions.add("set");
            completions.add("add");
            completions.add("reload");
            completions.add("url");
            for (String line : plugin.getConfig().getStringList("TAB")) {
                completions.add(line);
            }
            return StringUtil.copyPartialMatches(args[0], completions, new ArrayList<>());
        }
        else if(args.length == 2){
            switch(args[0]){
                case "set":
                    completions.add("<URL>");
                    break;
                case "add":
                    completions.add("<設定名>");
                    break;
            }
            return StringUtil.copyPartialMatches(args[1], completions, new ArrayList<>());
        }
        else if(args.length == 3) {
            if (args[0].equalsIgnoreCase("add")) {
                if (args[1].equalsIgnoreCase(args[1])) {
                    completions.add("<URL>");
                }
            }
            return StringUtil.copyPartialMatches(args[2], completions, new ArrayList<>());
        }
        return null;
    }
}