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

public class ResetItem extends AbstractItem {

    MinesweeperGUI minesweeperGUI;
    public ResetItem(MinesweeperGUI minesweeperGUI1){
        this.minesweeperGUI = minesweeperGUI1;
    }

    @Override
    public ItemProvider getItemProvider() {
        return new ItemBuilder(Material.BARRIER)
                .setCustomModelData(1)
                .setDisplayName("Reset");
    }

    @Override
    public void handleClick(@NotNull ClickType clickType, @NotNull Player player, @NotNull InventoryClickEvent event) {
        minesweeperGUI.resetGame();
    }
}
