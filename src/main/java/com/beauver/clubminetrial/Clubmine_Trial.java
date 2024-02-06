package com.beauver.clubminetrial;

import com.beauver.clubminetrial.listeners.InventoryClose;
import com.beauver.clubminetrial.Util.InventoryItems;
import com.beauver.clubminetrial.listeners.SurvivalInventoryListener;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.ItemStack;
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

        getConfig().options().copyDefaults();
        saveDefaultConfig();

        registerListeners();
        getLogger().info("Started Clubmine-Trial plugin.");
        //loops the filling of the crafting slots
        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, this::fillInventoryCraftingSlots, 0L, 1L);
    }

    private void registerListeners(){
        getServer().getPluginManager().registerEvents(new SurvivalInventoryListener(), this);
        getServer().getPluginManager().registerEvents(new InventoryClose(), this);
        getLogger().info("Listeners started.");
    }
    @Override
    public void onDisable() {
        getLogger().info("Stopped Clubmine-Trial plugin.");
    }

    private void fillInventoryCraftingSlots() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            if (player.getOpenInventory().getType() == InventoryType.CRAFTING) {
                for(int i = 0; i <= 4; i++){

                    if(i == 1){
                        player.getOpenInventory().setItem(i, InventoryItems.socialItem());
                    }else if(i == 2){
                        player.getOpenInventory().setItem(i, InventoryItems.playerStats(player.getName()));
                    }else if(i == 3){
                        player.getOpenInventory().setItem(i, InventoryItems.staffItem());
                    }else if(i == 4){
                        player.getOpenInventory().setItem(i, new ItemStack(Material.DIRT));
                    }
                }
            }
        }
    }
}
