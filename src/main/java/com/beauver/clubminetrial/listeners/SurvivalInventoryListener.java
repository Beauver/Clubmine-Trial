package com.beauver.clubminetrial.listeners;

import com.beauver.clubminetrial.Clubmine_Trial;
import com.beauver.clubminetrial.GUI.PlayerStatsGUI;
import com.beauver.clubminetrial.GUI.SocialGUI;
import com.beauver.clubminetrial.GUI.StaffGUI;
import com.beauver.clubminetrial.Util.InventoryItems;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
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
            event.setCancelled(true);
            Clubmine_Trial plugin = Clubmine_Trial.getPlugin();
            HumanEntity playerHuman = event.getWhoClicked();
            Player player = Bukkit.getPlayer(playerHuman.getUniqueId());

            if(player == null){
                return;
            }

            if(item.equals(InventoryItems.socialItem())){
                event.getInventory().close();

                SocialGUI socialGUI = new SocialGUI();
                socialGUI.openSocials(player);

            }else if(item.equals(InventoryItems.playerStats(player.getName()))){
                event.getInventory().close();
                PlayerStatsGUI.openPlayerStatsGUI(player);

            }else if(item.equals(InventoryItems.staffItem())){
                StaffGUI.openStaffGUI();
                event.getInventory().close();
            }
        }
    }
}
