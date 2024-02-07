package com.beauver.clubminetrial.listeners;

import com.beauver.clubminetrial.Util.InventoryItems;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class ItemSpawnEvent implements Listener {
    //clears the items in the crafting slots when dying.
    @EventHandler
    public void onPlayerRespawned(org.bukkit.event.entity.ItemSpawnEvent event) {
        ItemStack item = event.getEntity().getItemStack();
        List<ItemStack> itemsRemoval = new ArrayList<>();
        itemsRemoval.add(InventoryItems.staffItem());
        itemsRemoval.add(InventoryItems.socialItem());
        itemsRemoval.add(InventoryItems.CustomItem());
        for(Player player : Bukkit.getOnlinePlayers()){
            itemsRemoval.add(InventoryItems.playerStats(player.getName()));
        }

        if(itemsRemoval.contains(item)){
            event.setCancelled(true);
        }
    }
}
