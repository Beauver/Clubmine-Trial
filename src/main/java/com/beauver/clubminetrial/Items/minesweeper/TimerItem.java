package com.beauver.clubminetrial.Items.minesweeper;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.jetbrains.annotations.NotNull;
import xyz.xenondevs.invui.item.ItemProvider;
import xyz.xenondevs.invui.item.builder.ItemBuilder;
import xyz.xenondevs.invui.item.impl.AbstractItem;

public class TimerItem extends AbstractItem {
    private int time;
    public TimerItem(int timer){
        this.time = timer;
    }
    @Override
    public ItemProvider getItemProvider() {
        return new ItemBuilder(Material.CLOCK)
                .setCustomModelData(1)
                .setDisplayName("Time: " + time + "s");
    }

    @Override
    public void handleClick(@NotNull ClickType clickType, @NotNull Player player, @NotNull InventoryClickEvent event) {

    }
}
