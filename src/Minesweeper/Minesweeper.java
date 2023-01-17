package Minesweeper;
import java.util.Random;
import java.util.Scanner;


//This code will randomly generate 10 mines in a 10x10 grid, and then calculate the number of mines surrounding each square. 
// The user can then enter coordinates to check for mines, and the application will display the number of mines surrounding that location or "boom!" if the user selected a mine. 
//The game will also check if all non-mine squares have been revealed, and if so, the game is won. 
// The grid is rendered to the console after every user command.
//Please note that this is a basic version of the game and can be improved with more features. 


public class Minesweeper {
    static int[][] grid = new int[10][10];
    static boolean[][] revealed = new boolean[10][10];
    static boolean gameOver = false;

    public static void main(String[] args) {
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

    Scanner scanner = new Scanner(System.in);
    while (!gameOver) {
        // Get user input
        System.out.print("Enter a coordinate to check (x y): ");
        int x = scanner.nextInt();
        int y = scanner.nextInt();

        // Check if the user selected a mine
        if (grid[x][y] == -1) {
            System.out.println("Boom!");
            gameOver = true;
        } else {
            revealed[x][y] = true;
            renderGrid();

            // Check if the game is won
            boolean won = true;
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 10; j++) {
                    if (grid[i][j] != -1 && !revealed[i][j]) {
                        won = false;
                        break;
                    }
                }
            }
            if (won) {
                System.out.println("Congratulations, you won!");
                gameOver = true;
            }
        }
    }
}

public static void renderGrid() {
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
}
	    
	    


