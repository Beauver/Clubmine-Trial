package com.beauver.clubminetrial.Items.minesweeper;

import com.beauver.clubminetrial.GUI.MinesweeperGUI;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.jetbrains.annotations.NotNull;
import xyz.xenondevs.invui.item.ItemProvider;
import xyz.xenondevs.invui.item.builder.ItemBuilder;
import xyz.xenondevs.invui.item.impl.AbstractItem;

public class GuessedMine extends AbstractItem {

    final MinesweeperGUI minesweeperGUI;
    public GuessedMine(MinesweeperGUI minesweeperGui){
        this.minesweeperGUI = minesweeperGui;
    }
    public ItemProvider getItemProvider() {
        return new ItemBuilder(Material.RED_BANNER)
                .setCustomModelData(1)
                .setDisplayName("Assumed Mine");
    }

    @Override
    public void handleClick(@NotNull ClickType clickType, @NotNull Player player, @NotNull InventoryClickEvent event) {
        minesweeperGUI.clickedGuessedMine(clickType, player, event);
        notifyWindows();
    }
}
