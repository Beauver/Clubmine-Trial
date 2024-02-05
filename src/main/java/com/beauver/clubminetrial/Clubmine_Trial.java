package com.beauver.clubminetrial;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class Clubmine_Trial extends JavaPlugin {

    private static Clubmine_Trial plugin;
    public static Clubmine_Trial getPlugin() {
        return plugin;
    }

    @Override
    public void onEnable() {
        // Plugin startup logic
        plugin = this;

        getLogger().info("Started Clubmine-Trial plugin.");
    }
    @Override
    public void onDisable() {
        getLogger().info("Stopped Clubmine-Trial plugin.");
    }
}
