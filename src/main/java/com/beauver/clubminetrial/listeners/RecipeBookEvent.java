package com.beauver.clubminetrial.listeners;

import com.destroystokyo.paper.event.player.PlayerRecipeBookClickEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerRecipeBookSettingsChangeEvent;

public class RecipeBookEvent implements Listener {


    @EventHandler
    public static void onRecipeBookInteract(PlayerRecipeBookClickEvent event){

        if(event.getPlayer().getOpenInventory().getType() == InventoryType.CRAFTING){
            event.setCancelled(true);
        }
    }
}
