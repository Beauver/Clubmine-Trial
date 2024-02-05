package com.beauver.clubminetrial.listeners;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.CraftingInventory;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class SurvivalInventoryListener implements Listener {

    @EventHandler
    public void onInventoryOpen(InventoryOpenEvent event) {
        if (!(event.getPlayer() instanceof Player)) {
            return;
        }

        Player player = (Player) event.getPlayer();
        // Check if the opened inventory is the player's main inventory
        if (event.getInventory().getType() == InventoryType.CRAFTING) {
            // Fill the 2x2 crafting grid with dirt blocks
            fillCraftingSlots((CraftingInventory) player.getOpenInventory().getTopInventory());
        }
    }

    private void fillCraftingSlots(CraftingInventory craftingInventory) {
        for (int i = 1; i <= 4; i++) {
            // Set the crafting grid slot to a dirt block (you can customize this)
            craftingInventory.setItem(i, new ItemStack(Material.DIRT));
        }
    }

}
