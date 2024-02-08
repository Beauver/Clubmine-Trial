package com.beauver.clubminetrial.GUI;

import com.beauver.clubminetrial.Clubmine_Trial;
import com.beauver.clubminetrial.Items.minesweeper.*;
import jdk.jshell.spi.ExecutionControl;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import xyz.xenondevs.invui.gui.Gui;
import xyz.xenondevs.invui.item.Item;
import xyz.xenondevs.invui.item.builder.ItemBuilder;
import xyz.xenondevs.invui.item.impl.SimpleItem;
import xyz.xenondevs.invui.window.Window;

import java.util.Random;

public class MinesweeperGUI {

    public Gui minesweeperGui;
    private final int rows = 5;
    private final int columns = 9;
    private int bombNum;
    private int[][] board;
    private boolean[][] revealedBoard;
    private int clickAmount = 0;
    private int minesGuessed = 0;
    private Player minesweperPlayer;
    private InventoryClickEvent invClickEvent;
    private Window minesweeperWindow;
    private int timeTaken = 0;
    public int timerId;
    private final int maxMines = 14;
    private final int minMines = 5;
    private int totalSafeCells;
    private int correctGuesses;


    public void openMinesweeper(Player player){
        minesweperPlayer = player;
        Random rn = new Random();
        bombNum = rn.nextInt((maxMines - minMines) + 1) + minMines;
        totalSafeCells = (rows * columns) - bombNum;

        //Creates the GuiBuilder for a normal GUI
        minesweeperGui = Gui.normal()
                .setStructure(
                        "? ? ? ? ? ? ? ? ?",
                        "? ? ? ? ? ? ? ? ?",
                        "? ? ? ? ? ? ? ? ?",
                        "? ? ? ? ? ? ? ? ?",
                        "? ? ? ? ? ? ? ? ?",
                        "# M # # R # # T #")
                .addIngredient('#', new SimpleItem(new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE)))
                .addIngredient('?', new UnknownItem(this))
                .addIngredient('M', new MinesAmount(bombNum))
                .addIngredient('R', new ResetItem(this))
                .addIngredient('T', new TimerItem(timeTaken))
                .build();

        minesweeperWindow = Window.single()
                .setViewer(player)
                .setTitle("Minesweeper")
                .setGui(minesweeperGui)
                .build();
        minesweeperWindow.open();

        if(clickAmount <= 0){
            board = new int[rows][columns];
            revealedBoard = new boolean[rows][columns];
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
    private void placeBomb(){
        Random random = new Random();

        for(int i = 0; i < bombNum; i++){
            int row = random.nextInt(rows);
            int column = random.nextInt(columns);

            //if a mine is already present
            while(board[row][column] == -1){
                row = random.nextInt(rows);
                column = random.nextInt(columns);
            }
            //place mine (sets the location to -1 defining its a mine)
            board[row][column] = -1;
            updateCellToNum(row, column);
        }
    }

    //when clicked does:
    private void handleCellClicked(int row, int column) {
        if (!revealedBoard[row][column]) {
            revealedBoard[row][column] = true;

            if (board[row][column] == -1) {
                loseCondition();
            } else if (board[row][column] == 0) {
                revealSafeCells(row, column);
            } else {
                int num = board[row][column];
                dangerCondition(num, invClickEvent.getSlot());
            }

            // Increment correctGuesses when a safe cell is correctly guessed
            if (board[row][column] != -1) {
                correctGuesses++;
            }
            checkWin();
        }
    }

    private void checkWin(){
        if (correctGuesses == totalSafeCells) {
            Clubmine_Trial.getPlugin().getLogger().info("Win found");
            winCondition();
        }
    }

    private void revealSafeCells(int row, int column) {
        // Goofy math that looks through all adjacent squares
        for (int i = row - 1; i <= row + 1; i++) {
            for (int j = column - 1; j <= column + 1; j++) {
                // If it's an existing cell
                if (isValidCell(i, j) && !revealedBoard[i][j]) {
                    // Reveal it
                    revealedBoard[i][j] = true;

                    if (board[i][j] == 0) {
                        // If it's a safe cell, reveal the cell and increment correctGuesses
                        revealSafeCells(i, j);
                        int slotId = i * columns + j;
                        safeCell(slotId);
                        safeCell(invClickEvent.getSlot());
                        correctGuesses++;
                    } else if (board[i][j] >= 1) {
                        // If it's a numbered cell, update the UI and increment correctGuesses
                        int slotId = i * columns + j;
                        dangerCondition(board[i][j], slotId);
                        correctGuesses++;
                    }
                }
            }
        }
        checkWin(); // Check for a win after revealing safe cells
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
    private boolean isValidCell(int row, int column){
        return row >= 0 && row < rows && column >= 0 && column < columns;
    }

    public void clickedUnknownItem(ClickType clickType, Player player, InventoryClickEvent event){
        if(clickAmount <= 0){
            initBoard();
            timerId = Bukkit.getScheduler().scheduleSyncRepeatingTask(Clubmine_Trial.getPlugin(), this::startTimer, 20, 20);
        }
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
        minesweeperGui.setItem(46, new MinesAmount(bombNum -minesGuessed));
    }

    private void mineMath(InventoryClickEvent event){
        //some goofy math to convert the 0-53 to column and rows
        int row = event.getSlot() / columns; // Calculate the row
        int column = event.getSlot() % columns; // Calculate the column

        if(clickAmount <= 0){
            initBoard();
            placeBomb();
        }
        handleCellClicked(row, column);
        clickAmount++;
    }

    private  void dangerCondition(int num, int slotId){
        minesweeperGui.setItem(slotId, new AdjecentMine(num));
    }

    private  void safeCell(int slotId){
        // slotId must be 0-44
        minesweeperGui.setItem(slotId, new SafeCell());
    }

    private  void winCondition(){
        Bukkit.getScheduler().cancelTask(timerId);
        replaceCells();
        minesweeperWindow.changeTitle("You Won!");
    }

    private  void loseCondition(){
        Bukkit.getScheduler().cancelTask(timerId);
        replaceCells();
        minesweeperWindow.changeTitle("You lost!");
    }

    private void setAsGuessedMine(InventoryClickEvent event){
        if(minesGuessed >= bombNum){
            return;
        }
        minesweeperGui.setItem(event.getSlot(), new GuessedMine(this));
        minesGuessed++;
        minesweeperGui.setItem(46, new MinesAmount(bombNum - minesGuessed));
    }

    private void replaceCells() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                int slotId = i * columns + j;

                if (board[i][j] == -1) {
                    minesweeperGui.setItem(slotId, new ActualMine());
                }else if(board[i][j] == 0){
                    safeCell(slotId);
                }else{
                    dangerCondition(board[i][j], slotId);
                }
            }
        }
    }

    public void startTimer(){
        minesweeperGui.setItem(52, new TimerItem(timeTaken++));
    }
    public void resetGame(){
        Bukkit.getScheduler().cancelTask(timerId);
        timeTaken = 0;
        minesGuessed = 0;
        clickAmount = 0;
        minesweeperGui.closeForAllViewers();
        openMinesweeper(minesweperPlayer);
    }
}
