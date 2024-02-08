package com.beauver.clubminetrial;

import com.beauver.clubminetrial.listeners.InventoryClose;
import com.beauver.clubminetrial.Util.InventoryItems;
import com.beauver.clubminetrial.listeners.ItemSpawnEvent;
import com.beauver.clubminetrial.listeners.SurvivalInventoryListener;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.plugin.java.JavaPlugin;

public final class ClubmineTrial extends JavaPlugin {

    private static ClubmineTrial plugin;
    public static ClubmineTrial getPlugin() {
        return plugin;
    }
    public static int fillInvCraftSlotsSchedularID;

    @Override
    public void onEnable() {
        // Plugin startup logic
        plugin = this;

        getConfig().options().copyDefaults();
        saveDefaultConfig();

        registerListeners();
        getLogger().info("Started Clubmine-Trial plugin.");
        //loops the filling of the crafting slots
        fillInvCraftSlotsSchedularID = Bukkit.getScheduler().scheduleSyncRepeatingTask(this, this::fillInventoryCraftingSlots, 0L, 1L);
    }

    private void registerListeners(){
        getServer().getPluginManager().registerEvents(new SurvivalInventoryListener(), this);
        getServer().getPluginManager().registerEvents(new InventoryClose(), this);
        getServer().getPluginManager().registerEvents(new ItemSpawnEvent(), this);

        getLogger().info("Listeners started.");
    }
    @Override
    public void onDisable() {
        getLogger().info("Stopped Clubmine-Trial plugin.");
    }

    private void fillInventoryCraftingSlots() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            if(player.isDead()){
                continue;
            }
            if (player.getOpenInventory().getType() != InventoryType.CRAFTING) {
                continue;
            }

            for(int i = 0; i <= 4; i++){
                if(i == 1){
                    player.getOpenInventory().setItem(i, InventoryItems.socialItem());
                }else if(i == 2){
                    player.getOpenInventory().setItem(i, InventoryItems.playerStats(player.getName()));
                }else if(i == 3){
                    player.getOpenInventory().setItem(i, InventoryItems.staffItem());
                }else if(i == 4){
                    player.getOpenInventory().setItem(i, InventoryItems.CustomItem());
                }
            }
        }
    }
}
