package com.github.albardoo02;

import org.bukkit.plugin.java.JavaPlugin;

public final class Wikipebia extends JavaPlugin {
    @Override
    public void onEnable() {
        // Plugin startup logic
        getLogger().info("プラグインが起動しました");

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
