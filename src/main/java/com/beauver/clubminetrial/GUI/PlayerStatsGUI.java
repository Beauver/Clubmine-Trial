package com.beauver.clubminetrial.GUI;

import com.beauver.clubminetrial.Util.InventoryItems;
import org.bukkit.Material;
import org.bukkit.Statistic;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import xyz.xenondevs.invui.gui.Gui;
import xyz.xenondevs.invui.item.builder.ItemBuilder;
import xyz.xenondevs.invui.item.impl.SimpleItem;
import xyz.xenondevs.invui.window.Window;
public class PlayerStatsGUI {

    public static void openPlayerStatsGUI(Player player){
        //Creates the GuiBuilder for a normal GUI
        Gui gui = Gui.normal()
                .setStructure(
                        "# # # # # # # # #",
                        "# W . J . K . D #",
                        "# # # # # # # # #")
                .addIngredient('#', new SimpleItem(new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE)))
                .addIngredient('W', InventoryItems.getStatItem("Blocks Walked", player.getStatistic(Statistic.WALK_ONE_CM) / 100,  Material.PAPER))
                .addIngredient('J', InventoryItems.getStatItem("Jumps", player.getStatistic(Statistic.JUMP), Material.PAPER))
                .addIngredient('K', InventoryItems.getStatItem("Player Kills", player.getStatistic(Statistic.PLAYER_KILLS), Material.PAPER))
                .addIngredient('D', InventoryItems.getStatItem("Deaths", player.getStatistic(Statistic.DEATHS), Material.PAPER))
                .build();

        Window window = Window.single()
                .setViewer(player)
                .setTitle("Your Stats")
                .setGui(gui)
                .build();
        window.open();
    }

}
