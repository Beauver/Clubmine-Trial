package com.beauver.clubminetrial.GUI;

import com.beauver.clubminetrial.Clubmine_Trial;
import com.beauver.clubminetrial.Items.minesweeper.ActualMine;
import com.beauver.clubminetrial.Items.minesweeper.UnknownItem;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import xyz.xenondevs.invui.gui.Gui;
import xyz.xenondevs.invui.item.builder.ItemBuilder;
import xyz.xenondevs.invui.item.impl.SimpleItem;
import xyz.xenondevs.invui.window.Window;

import java.util.Random;

public class MinesweeperGUI {

    public static Gui minesweeperGui;
    public static final int rows = 6;
    public static final int columns = 9;
    public static final int bombNum = 6;
    public static int[][] board;
    public static boolean[][] revealedBoard;
    public static int clickAmount = 0;
    public static int minesGuessed = 0;
    public static Player minesweperPlayer;

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
                .addIngredient('?', new UnknownItem())
                .build();

        Window window = Window.single()
                .setViewer(player)
                .setTitle("Minesweeper")
                .setGui(minesweeperGui)
                .build();
        window.open();
    }

    //initialzes every square without a mine
    //initializes every square that it's not revealed
    public static void initBoard(){
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < columns; j++){
                board[i][j] = 0;
                revealedBoard[i][j] = false;
            }
        }
    }

    //randomly places X amount of bombs on the map
    public static void placeBomb(){
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
    public static void handleCellClicked(int row, int column) {
        if (!revealedBoard[row][column]) {
            revealedBoard[row][column] = true;

            //if it's a mine lose
            if (board[row][column] == -1) {
                UnknownItem.loseCondition();
                //if it's a safe space, open all other safe spaces
            } else if (board[row][column] == 0) {
                revealSafeCells(row, column);
                //else it must be danger
            } else {
                int num = board[row][column];
                UnknownItem.dangerCondition(num, UnknownItem.Event.getSlot());
            }
        }
    }

    private static void revealSafeCells(int row, int column) {
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
                        int slotId = i * MinesweeperGUI.columns + j;
                        UnknownItem.safeCell(slotId);
                        UnknownItem.safeCell(UnknownItem.Event.getSlot());
                    } else {
                        // Update the UI for cells with mine count >= 0
                        int slotId = i * MinesweeperGUI.columns + j;
                        if (board[i][j] == -1) {
                            MinesweeperGUI.minesweeperGui.setItem(slotId, new ActualMine());
                        } else if (board[i][j] >= 1){
                            UnknownItem.dangerCondition(board[i][j], slotId);
                        }
                    }
                }
            }
        }
    }

    //when clicked, checks if it's next to a mine, if it is, make it a number cell
    private static void updateCellToNum(int row, int column){
        for (int i = row - 1; i <= row + 1; i++) {
            for (int j = column - 1; j <= column + 1; j++) {
                if (isValidCell(i, j) && board[i][j] != -1) {
                    board[i][j]++;
                }
            }
        }
    }
    //checks if the cell is in the grid
    private static boolean isValidCell(int row, int column){
        return row >= 0 && row < rows && column >= 0 && column < columns;
    }
}
