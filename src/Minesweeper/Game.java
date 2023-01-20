package Minesweeper;

import java.util.Random;

public class Game {
    int[][] grid = new int[10][10];
    boolean[][] revealed = new boolean[10][10];
    boolean gameOver = false;

    public void start() {
        // Randomly generate 10 mines
        Random rand = new Random();
        int minesPlaced = 0;
        while (minesPlaced < 10) {
            int x = rand.nextInt(10);
            int y = rand.nextInt(10);
            if (grid[x][y] != -1) {
                grid[x][y] = -1;
                minesPlaced++;
            }
        }
        // Calculate the number of mines surrounding each square
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (grid[i][j] == -1) continue;
                int mines = 0;
                for (int dx = -1; dx <= 1; dx++) {
                    for (int dy = -1; dy <= 1; dy++) {
                        if (i + dx >= 0 && i + dx < 10 && j + dy >= 0 && j + dy < 10) {
                            if (grid[i + dx][j + dy] == -1) mines++;
                        }
                    }
                }
                grid[i][j] = mines;
            }
        }
    }

    public void renderGrid() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (revealed[i][j]) {
                    if (grid[i][j] == -1) {
                        System.out.print("* ");
                    } else {
                        System.out.print(grid[i][j] + " ");
                    }
                } else {
                    System.out.print("_ ");
                }
            }
            System.out.println();
        }
    }
    public boolean checkSelected(int x, int y) {
        if (grid[x][y] == -1) {
            return true;
        }
        return false;
    }
    public void setRevealed(int x, int y) {
    	revealed[x][y] = true;
    }
    
    public boolean checkWin() {
        boolean won = true;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (grid[i][j] != -1 && !revealed[i][j]) {
                    won = false;
                    break;
                }
            }
        }
        return won;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }
}
