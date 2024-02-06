package com.beauver.clubminetrial.listeners;

import com.beauver.clubminetrial.Clubmine_Trial;
import com.beauver.clubminetrial.Util.InventoryItems;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryInteractEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.CraftingInventory;
import org.bukkit.inventory.ItemStack;

public class SurvivalInventoryListener implements Listener {

    @EventHandler
    public void onInventoryOpen(InventoryClickEvent event) {
        ItemStack item = event.getCurrentItem();
        if(item == null){
            return;
        }
        if(!item.hasItemMeta() || !item.getItemMeta().hasCustomModelData()){
            return;
        }

        if(event.getInventory().getType().equals(InventoryType.CRAFTING) && item.getItemMeta().getCustomModelData() == Clubmine_Trial.getPlugin().getConfig().getInt("craftingSlotsCustomModelId")){
            Clubmine_Trial plugin = Clubmine_Trial.getPlugin();
            if(item.equals(InventoryItems.discordItem())){
                event.getInventory().close();
                event.getWhoClicked().sendMessage(Component.text("Join our discord server here:\nhttps://discord.com/invite/" + plugin.getConfig().getString("discordInviteCode")).color(TextColor.fromHexString(plugin.getConfig().getString("discordInviteTextColorHex"))).clickEvent(ClickEvent.openUrl("https://discord.com/invite/" + plugin.getConfig().getString("discordInviteCode"))));
            }
            event.setCancelled(true);
        }
    }
}
