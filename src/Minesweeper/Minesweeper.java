package Minesweeper;
import java.util.Scanner;


public class Minesweeper {
	public static void main(String[] args) {
        Game game = new Game();
        game.start();
        Scanner scanner = new Scanner(System.in);
        while (!game.isGameOver()) {
            // Get user input
            System.out.print("Enter a coordinate to check (x y): ");
            int x = scanner.nextInt();
            int y = scanner.nextInt();

            // Check if the user selected a mine
            if (game.checkSelected(x, y)) {
                System.out.println("Boom!");
                game.setGameOver(true);
            } else {
                game.setRevealed(x, y);
                game.renderGrid();
                if (game.checkWin()) {
                    System.out.println("Congratulations, you won!");
                    game.setGameOver(true);
                }
            }
        }
    }
}
	    
	    


