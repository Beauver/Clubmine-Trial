package com.beauver.clubminetrial.Items.minesweeper;

import com.beauver.clubminetrial.Clubmine_Trial;
import com.beauver.clubminetrial.GUI.MinesweeperGUI;
import com.beauver.clubminetrial.GUI.MinesweeperLoseGUI;
import com.beauver.clubminetrial.GUI.MinesweeperVictoryGUI;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.jetbrains.annotations.NotNull;
import xyz.xenondevs.invui.item.ItemProvider;
import xyz.xenondevs.invui.item.builder.ItemBuilder;
import xyz.xenondevs.invui.item.impl.AbstractItem;

public class UnknownItem extends AbstractItem {
    final MinesweeperGUI minesweeperGUI;
    public UnknownItem(MinesweeperGUI minesweeperGui){
        this.minesweeperGUI = minesweeperGui;
    }
    @Override
    public ItemProvider getItemProvider() {
        return new ItemBuilder(Material.GRAY_STAINED_GLASS_PANE)
                .setCustomModelData(1)
                .setDisplayName("?");
    }

    @Override
    public void handleClick(@NotNull ClickType clickType, @NotNull Player player, @NotNull InventoryClickEvent event) {
        minesweeperGUI.clickedUnknownItem(clickType, player, event);
        notifyWindows();
    }
}
