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
    public static InventoryClickEvent Event;
    @Override
    public ItemProvider getItemProvider() {
        return new ItemBuilder(Material.GRAY_STAINED_GLASS_PANE)
                .setCustomModelData(1)
                .setDisplayName("?");
    }

    @Override
    public void handleClick(@NotNull ClickType clickType, @NotNull Player player, @NotNull InventoryClickEvent event) {
        Event = event;
        if(clickType.equals(ClickType.LEFT)){
            if(MinesweeperGUI.clickAmount <= 0){
                MinesweeperGUI.board = new int[MinesweeperGUI.rows][MinesweeperGUI.columns];
                MinesweeperGUI.revealedBoard = new boolean[MinesweeperGUI.rows][MinesweeperGUI.columns];

                MinesweeperGUI.initBoard();
                MinesweeperGUI.placeBomb();
                mineMath(event);
            }else{
                mineMath(event);
            }
        }else{
            setAsGuessedMine(event);
        }
        notifyWindows();
    }

    public void mineMath(InventoryClickEvent event){
        MinesweeperGUI.clickAmount++;

        //some goofy math to convert the 0-53 to column and rows
        int row = event.getSlot() / MinesweeperGUI.columns; // Calculate the row
        int column = event.getSlot() % MinesweeperGUI.columns; // Calculate the column

        MinesweeperGUI.handleCellClicked(row, column);
    }

    public static void loseCondition(){
        MinesweeperGUI.minesweeperGui.setItem(Event.getSlot(), new ActualMine());
        MinesweeperGUI.clickAmount = 0;
        MinesweeperGUI.minesweeperGui.closeForAllViewers();
        MinesweeperLoseGUI.openLoss(MinesweeperGUI.minesweperPlayer);
    }

    public static void dangerCondition(int num, int slotId){
        AdjecentMine.mineNum = num;
        MinesweeperGUI.minesweeperGui.setItem(slotId, new AdjecentMine());
    }

    public static void safeCell(int slotId){
        // slotId must be 0-53
        MinesweeperGUI.minesweeperGui.setItem(slotId, new SafeCell());
    }

    public static void winCondition(){
        MinesweeperGUI.minesweeperGui.closeForAllViewers();
        MinesweeperGUI.clickAmount = 0;
        MinesweeperVictoryGUI.openVictory(MinesweeperGUI.minesweperPlayer);
    }

    public void setAsGuessedMine(InventoryClickEvent event){
        if(MinesweeperGUI.minesGuessed >= MinesweeperGUI.bombNum){
            return;
        }
        MinesweeperGUI.minesweeperGui.setItem(event.getSlot(), new GuessedMine());
        MinesweeperGUI.minesGuessed++;
    }
}
