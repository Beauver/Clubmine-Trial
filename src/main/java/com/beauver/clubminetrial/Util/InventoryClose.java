package com.beauver.clubminetrial.Util;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryType;

public class InventoryClose implements Listener {

    //clears the items in the crafting slots when the inventory is closed.
    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event) {
        if (event.getPlayer() instanceof Player) {
            // Check if the closed inventory is a crafting inventory
            if (event.getInventory().getType() == InventoryType.CRAFTING) {
                // Clear the crafting slots to prevent item transfer
                event.getInventory().clear();
            }
        }
    }
}
