package com.github.albardoo02;

import com.github.albardoo02.command.TabComplete;
import com.github.albardoo02.command.WikiCommandExecutor;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public final class Wikipebia extends JavaPlugin {

    public String version = getDescription().getVersion();
    @Override
    public void onEnable() {
        // Plugin startup logic
        this.getServer().getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&',"&7[&aWikipebia&7] &bプラグインが起動しました"));
        this.getServer().getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&',"&7[&aWikipebia&7] &eバージョン: &d" + version));

        this.getCommand("wiki").setExecutor(new WikiCommandExecutor(this));
        this.getCommand("wiki").setTabCompleter(new TabComplete(this));

        this.saveDefaultConfig();

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getLogger().info("プラグインが停止しました");
    }
}
