import java.util.Scanner;

/**
 * 
 * The Main class for starting Conway's Game of Life
 * Main method consists the required information to start the game.
 * 
 * @author K.Sreenivas - 2020501026
 * 
 * @version 2.3
 * 
 */

public class Main {
	/**
	 * 
	 * @param size - takes the size of the board
	 * @param co-ordinates - takes locations of alive cells on board
	 * @param user-trigger - to start the game or to exit before starting
	 * 
	 */

	// Main method
	public static void main(String[] args) {


		// Scanner objects for taking inputs
		Scanner scan = new Scanner(System.in);
		Scanner scan2 = new Scanner(System.in);
		Scanner scan3 = new Scanner(System.in);


		// Input size of board and board creation
		
//		System.out.println("Input size of board: ");
//		int size = scan.nextInt();
		int size = 200;
		Board game = new Board();
		game.setSize(size);
		game.createBoard();


		// Enter number of starting cells and their positions
//		System.out.println("How many live cells to start: ");
//		int t = scan.nextInt();
		int t = 5;
		for (int i=0;i<t;i++) {
			if (i==0) {
				System.out.print("Enter x,y of first cell: ");
				String s = scan2.nextLine();
				String s1[] = s.split(" ");
				int x = Integer.parseInt(s1[0]);
				int y = Integer.parseInt(s1[1]);
				game.board[x][y].setStatus(true);

			}else {
				System.out.print("Enter x,y of next cell: ");
				String s = scan2.nextLine();
				String s1[] = s.split(" ");
				int x = Integer.parseInt(s1[0]);
				int y = Integer.parseInt(s1[1]);
				game.board[x][y].setStatus(true);

				
			}
		}


		// Prints the board status before starting the game 
		game.printBoard();


		// Asks user input to start the game or no
		System.out.println("Game ready, start? Y/N");
		String reply = scan3.nextLine();
		long timeout = 1500;
		if (reply.equals("Y") || reply.equals("y")) {
			System.out.println("***** Game started *****");
			while(game.checkEnd()) {
				if (game.iter==500) {
					break;
				}

				// Go to next gen
				game.nextGen();
				
				// Sleep for 1.5 sec
				try {
					Thread.sleep(timeout);
				} catch (InterruptedException e) {
					continue;
				}
			}			
			System.out.println("***** Game stopped after "+game.iter+" iterations *****");
		} else {
			System.out.println("***** Game stopped *****");
		}

		// Close scanner objects
		scan3.close();
		scan2.close();
		scan.close();
	}
	

}
