package com.beauver.clubminetrial.GUI;

import com.beauver.clubminetrial.Items.minesweeper.UnknownItem;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import xyz.xenondevs.invui.gui.Gui;
import xyz.xenondevs.invui.item.builder.ItemBuilder;
import xyz.xenondevs.invui.item.impl.SimpleItem;
import xyz.xenondevs.invui.window.Window;

public class MinesweeperVictoryGUI {

    public static void openVictory(Player player){
        //Creates the GuiBuilder for a normal GUI
        Gui victory = Gui.normal()
                .setStructure(
                        "# # # # # # # # #",
                        "# . . . . . . . #",
                        "# # # # # # # # #")
                .addIngredient('#', new SimpleItem(new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE)))
                .build();

        Window window = Window.single()
                .setViewer(player)
                .setTitle("Victory!")
                .setGui(victory)
                .build();
        window.open();
    }

}
