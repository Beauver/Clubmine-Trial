package com.beauver.clubminetrial.Items.minesweeper;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.jetbrains.annotations.NotNull;
import xyz.xenondevs.invui.item.ItemProvider;
import xyz.xenondevs.invui.item.builder.ItemBuilder;
import xyz.xenondevs.invui.item.impl.AbstractItem;

public class MinesAmount extends AbstractItem {

    final int minesAmount;
    public MinesAmount(int minesAmountNum){
        this.minesAmount = minesAmountNum;
    }

    @Override
    public ItemProvider getItemProvider() {
        int minesAmountCounter = 0;
        minesAmountCounter = (minesAmount == 0) ?  1 : minesAmount;
        return new ItemBuilder(Material.RED_STAINED_GLASS_PANE)
                .setCustomModelData(1)
                .setDisplayName("Amount of mines: " + minesAmount)
                .setAmount(minesAmountCounter);
    }

    @Override
    public void handleClick(@NotNull ClickType clickType, @NotNull Player player, @NotNull InventoryClickEvent event) {

    }
}
