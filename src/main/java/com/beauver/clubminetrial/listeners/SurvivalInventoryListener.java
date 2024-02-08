package com.beauver.clubminetrial.listeners;

import com.beauver.clubminetrial.ClubmineTrial;
import com.beauver.clubminetrial.GUI.MinesweeperGUI;
import com.beauver.clubminetrial.GUI.PlayerStatsGUI;
import com.beauver.clubminetrial.GUI.SocialGUI;
import com.beauver.clubminetrial.GUI.StaffGUI;
import com.beauver.clubminetrial.Util.InventoryItems;
import org.bukkit.Bukkit;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.ItemStack;
import xyz.xenondevs.invui.util.MojangApiUtils;

import java.io.IOException;

public class SurvivalInventoryListener implements Listener {

    @EventHandler
    public void onInventoryOpen(InventoryClickEvent event) throws MojangApiUtils.MojangApiException, IOException {
        ItemStack item = event.getCurrentItem();
        if(item == null){
            return;
        }
        if(!item.hasItemMeta() || !item.getItemMeta().hasCustomModelData()){
            return;
        }

        if(event.getInventory().getType().equals(InventoryType.CRAFTING) && item.getItemMeta().getCustomModelData() == ClubmineTrial.getPlugin().getConfig().getInt("craftingSlotsCustomModelId")){
            event.setCancelled(true);
            ClubmineTrial plugin = ClubmineTrial.getPlugin();
            HumanEntity playerHuman = event.getWhoClicked();
            Player player = Bukkit.getPlayer(playerHuman.getUniqueId());

            if(player == null){
                return;
            }

            if(item.equals(InventoryItems.socialItem())){
                event.getInventory().close();
                //Running it with a 1 tick delay, sync (inventory must be handled on the main thread) as that should fix a bug. --> Clicked item still being on cursor when clicked
                Bukkit.getScheduler().runTaskLater(ClubmineTrial.getPlugin(), new Runnable() {
                    @Override
                    public void run() {
                        SocialGUI socialGUI = new SocialGUI();
                        socialGUI.openSocials(player);
                    }
                }, 1L);

            }else if(item.equals(InventoryItems.playerStats(player.getName()))){
                event.getInventory().close();
                //Running it with a 1 tick delay, sync (inventory must be handled on the main thread) as that should fix a bug. --> Clicked item still being on cursor when clicked
                Bukkit.getScheduler().runTaskLater(ClubmineTrial.getPlugin(), new Runnable() {
                    @Override
                    public void run() {
                        PlayerStatsGUI.openPlayerStatsGUI(player);
                    }
                }, 1L);

            }else if(item.equals(InventoryItems.staffItem())){
                event.getInventory().close();
                //Running it with a 1 tick delay, sync (inventory must be handled on the main thread) as that should fix a bug. --> Clicked item still being on cursor when clicked
                Bukkit.getScheduler().runTaskLater(ClubmineTrial.getPlugin(), new Runnable() {
                    @Override
                    public void run() {
                        try {
                            StaffGUI.openStaffGUI(player);
                        } catch (MojangApiUtils.MojangApiException | IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }, 1L);
            }
            else if(item.equals(InventoryItems.CustomItem())) {
                event.getInventory().close();
                //Running it with a 1 tick delay, sync (inventory must be handled on the main thread) as that should fix a bug. --> Clicked item still being on cursor when clicked
                Bukkit.getScheduler().runTaskLater(ClubmineTrial.getPlugin(), () -> {
                    MinesweeperGUI minesweeperGUI = new MinesweeperGUI();
                    minesweeperGUI.openMinesweeper(player);
                }, 1L);
            }
        }
    }
}
