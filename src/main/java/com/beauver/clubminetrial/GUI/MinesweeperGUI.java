package com.beauver.clubminetrial.GUI;

import com.beauver.clubminetrial.Clubmine_Trial;
import com.beauver.clubminetrial.Items.minesweeper.*;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.jetbrains.annotations.NotNull;
import xyz.xenondevs.invui.gui.Gui;
import xyz.xenondevs.invui.item.builder.ItemBuilder;
import xyz.xenondevs.invui.item.impl.SimpleItem;
import xyz.xenondevs.invui.window.Window;

import java.util.Random;

public class MinesweeperGUI {

    private Gui minesweeperGui;
    private final int rows = 6;
    private final int columns = 9;
    private final int bombNum = 6;
    private int[][] board;
    private boolean[][] revealedBoard;
    private int clickAmount = 0;
    private int minesGuessed = 0;
    private Player minesweperPlayer;
    private InventoryClickEvent invClickEvent;

    public void openMinesweeper(Player player){
        minesweperPlayer = player;
        //Creates the GuiBuilder for a normal GUI
        minesweeperGui = Gui.normal()
                .setStructure(
                        "? ? ? ? ? ? ? ? ?",
                        "? ? ? ? ? ? ? ? ?",
                        "? ? ? ? ? ? ? ? ?",
                        "? ? ? ? ? ? ? ? ?",
                        "? ? ? ? ? ? ? ? ?",
                        "? ? ? ? ? ? ? ? ?")
                .addIngredient('#', new SimpleItem(new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE)))
                .addIngredient('?', new UnknownItem(this))
                .build();

        Window window = Window.single()
                .setViewer(player)
                .setTitle("Minesweeper")
                .setGui(minesweeperGui)
                .build();
        window.open();

        if(clickAmount <= 0){
            board = new int[rows][columns];
            revealedBoard = new boolean[rows][columns];
            initBoard();
            placeBomb();
        }
    }

    //initialzes every square without a mine
    //initializes every square that it's not revealed
    private  void initBoard(){
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < columns; j++){
                board[i][j] = 0;
                revealedBoard[i][j] = false;
            }
        }
    }

    //randomly places X amount of bombs on the map
    private  void placeBomb(){
        Random random = new Random();
        for(int i = 0; i < bombNum; i++){
            int row = random.nextInt(rows);
            int column = random.nextInt(columns);

            //if a mine is already present
            while(board[row][column] == -1){
                row = random.nextInt(rows);
                column = random.nextInt(columns);
            }
            //place mine (sets the location to -1 defening its a mine)
            board[row][column] = -1;
            updateCellToNum(row, column);
        }
    }

    //when clicked does:
    private  void handleCellClicked(int row, int column) {
        if (!revealedBoard[row][column]) {
            revealedBoard[row][column] = true;

            //if it's a mine lose
            if (board[row][column] == -1) {
                loseCondition();
                //if it's a safe space, open all other safe spaces
            } else if (board[row][column] == 0) {
                revealSafeCells(row, column);
                //else it must be danger
            } else {
                int num = board[row][column];
                dangerCondition(num, invClickEvent.getSlot());
            }
        }
    }

    private  void revealSafeCells(int row, int column) {
        //goofy math that looks thru all adjacent squares
        for (int i = row - 1; i <= row + 1; i++) {
            for (int j = column - 1; j <= column + 1; j++) {
                //if it's an existing cell
                if (isValidCell(i, j) && !revealedBoard[i][j]) {
                    //reveal it
                    revealedBoard[i][j] = true;

                    if (board[i][j] == 0) {
                        //reveal the cell
                        revealSafeCells(i, j);
                        int slotId = i * columns + j;
                        safeCell(slotId);
                        safeCell(invClickEvent.getSlot());
                    } else {
                        // Update the UI for cells with mine count >= 0
                        int slotId = i * columns + j;
                        if (board[i][j] == -1) {
                            minesweeperGui.setItem(slotId, new ActualMine());
                        } else if (board[i][j] >= 1){
                            dangerCondition(board[i][j], slotId);
                        }
                    }
                }
            }
        }
    }

    //when clicked, checks if it's next to a mine, if it is, make it a number cell
    private  void updateCellToNum(int row, int column){
        for (int i = row - 1; i <= row + 1; i++) {
            for (int j = column - 1; j <= column + 1; j++) {
                if (isValidCell(i, j) && board[i][j] != -1) {
                    board[i][j]++;
                }
            }
        }
    }
    //checks if the cell is in the grid
    private  boolean isValidCell(int row, int column){
        return row >= 0 && row < rows && column >= 0 && column < columns;
    }

    public void clickedUnknownItem(ClickType clickType, Player player, InventoryClickEvent event){
        invClickEvent = event;
        if(clickType.isLeftClick()){
            mineMath(event);
        }else{
            setAsGuessedMine(event);
        }
    }

    public void clickedGuessedMine(ClickType clickType, Player player, InventoryClickEvent event){
        minesweeperGui.setItem(event.getSlot(), new UnknownItem(this));
        minesGuessed--;
    }

    private void mineMath(InventoryClickEvent event){
        clickAmount++;

        //some goofy math to convert the 0-53 to column and rows
        int row = event.getSlot() / columns; // Calculate the row
        int column = event.getSlot() % columns; // Calculate the column

        handleCellClicked(row, column);
    }

    private  void loseCondition(){
        clickAmount = 0;
        minesweeperGui.closeForAllViewers();
        MinesweeperLoseGUI.openLoss(minesweperPlayer);
    }

    private  void dangerCondition(int num, int slotId){
        AdjecentMine.mineNum = num;
        minesweeperGui.setItem(slotId, new AdjecentMine());
    }

    private  void safeCell(int slotId){
        // slotId must be 0-53
        minesweeperGui.setItem(slotId, new SafeCell());
    }

    private  void winCondition(){
        minesweeperGui.closeForAllViewers();
        clickAmount = 0;
        MinesweeperVictoryGUI.openVictory(minesweperPlayer);
    }

    private void setAsGuessedMine(InventoryClickEvent event){
        if(minesGuessed >= bombNum){
            return;
        }
        minesweeperGui.setItem(event.getSlot(), new GuessedMine(this));
        minesGuessed++;
    }
}
