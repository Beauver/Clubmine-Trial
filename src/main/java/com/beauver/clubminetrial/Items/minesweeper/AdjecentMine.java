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
        Material material = Material.LIGHT_BLUE_STAINED_GLASS_PANE;
        /*
        switch (mineNum){
            case 1:
                material = Material.LIGHT_BLUE_STAINED_GLASS_PANE;
                break;
            case 2:
                material = Material.GREEN_STAINED_GLASS_PANE;
                break;
            case 3:
                material = Material.PINK_STAINED_GLASS_PANE;
                break;
            case 4:
                material = Material.BLUE_STAINED_GLASS_PANE;
                break;
            case 5:
                material = Material.RED_STAINED_GLASS_PANE;
                break;
            case 6:
                material = Material.CYAN_STAINED_GLASS_PANE;
                break;
            case 7:
                material = Material.BLACK_STAINED_GLASS_PANE;
                break;
            case 8:
                material = Material.GRAY_STAINED_GLASS_PANE;
                break;
        }
        */

        return new ItemBuilder(material)
                .setCustomModelData(1)
                .setDisplayName(String.valueOf(mineNum))
                .setAmount(mineNum);
    }

    @Override
    public void handleClick(@NotNull ClickType clickType, @NotNull Player player, @NotNull InventoryClickEvent event) {

    }
}
