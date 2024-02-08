package com.beauver.clubminetrial.Items.minesweeper;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.jetbrains.annotations.NotNull;
import xyz.xenondevs.invui.item.ItemProvider;
import xyz.xenondevs.invui.item.builder.ItemBuilder;
import xyz.xenondevs.invui.item.impl.AbstractItem;

public class AdjecentMine extends AbstractItem {

    private int mineNum;
    public AdjecentMine(int mineNum1){
        this.mineNum = mineNum1;
    }


    public ItemProvider getItemProvider() {
        return new ItemBuilder(Material.LIGHT_BLUE_STAINED_GLASS_PANE)
                .setCustomModelData(1)
                .setDisplayName(String.valueOf(mineNum))
                .setAmount(mineNum);
    }

    @Override
    public void handleClick(@NotNull ClickType clickType, @NotNull Player player, @NotNull InventoryClickEvent event) {

    }
}
