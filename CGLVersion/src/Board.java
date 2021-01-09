import java.util.Arrays;

/**
 * 
 * The Board class is the class which initiates the base for game of life and performs some methods like
 * createBoard - which creates empty board with empty cells
 * printBoard - prints out current state of the board
 * 
 * @author K.Sreenivas - 2020501026
 * 
 * @version 2.3
 * 
 */

public class Board {
	/**
	 * 
	 * The attributes of this Board class.
	 * 
	 */
	int size;
	int iter;
	Cell[][] board;
	Cell[][] board2;
	String res;
	
	
	/**
	 * 
	 * Default constuctor which sets the size to 0
	 * and iteration number to 0
	 * 
	 */
	Board() {
		this.size=0;
		this.iter=0;
		this.res="";
	}


	/**
	 * 
	 * Creates an empty board with empty cells
	 * 
	 */
	void createBoard() {
		this.board = new Cell[size][size];
		for (int i=0;i<this.size;i++) {
			for (int j=0;j<this.size;j++) {
				this.board[i][j] = new Cell();
				this.board[i][j].row = i;
				this.board[i][j].col = j;
			}
		}		
	}


	/**
	 * 
	 * Prints the current state of the board
	 * 
	 */
	void printBoard() {
		String res = "";
		res+="*** Generation: "+this.iter+" ***\n";
		for (int i=0;i<board.length;i++) {
			for (int j=0;j<board.length;j++) {
				if (j<board.length-1) {
					if (board[i][j].status == false) {
						res+=". ";
					}else {
						res+="x ";
					}
				}else {
					if (board[i][j].status == false) {
						res+="."+"\n";
					}else {
						res+="x"+"\n";
					}
				}
			}
		}
		this.res = res;
		System.out.print(res);
	}
	
	
	/**
	 * 
	 * Calculates a new board instance based on the previous board and replaces the 
	 * cells inside based on the game of life rules.
	 * Takes the help of aliveNcells method to decide the next generation status.
	 * 
	 */
	Board nextGen() {
		this.iter+=1;
		this.board2 = new Cell[this.size][this.size];  // new board instance

		for (int i=0;i<this.size;i++) {
			for (int j=0;j<this.size;j++) {

				int alive = this.aliveNCells(i, j);  // calculate the number of alive neighbour cells to current cell
				this.board2[i][j] = new Cell();


				/**
				 * Based on the rules:
				 * 
				 * 1.Each cell with three neighbors becomes populated. (Reproduction)
				 * 2.Each cell with one or no neighbors dies, as if by solitude. (Loneliness)
				 * 3.Each cell with two or three neighbors survives. (Survival)
				 * 4.Each cell with four or more neighbors dies, as if by overpopulation. (Over population)
				 * 
				 */
				if (this.board[i][j].status==false) {
					if (alive==3) {
						this.board2[i][j].setStatus(true);
					}else {
						this.board2[i][j].setStatus(false);
					}
				}else {
					if (alive==0 || alive==1) {
						this.board2[i][j].setStatus(false);
					}else if (alive==2 || alive==3) {
						this.board2[i][j].setStatus(true);
					}else {
						this.board2[i][j].setStatus(false);
					}
				}
				this.board2[i][j].row = i;
				this.board2[i][j].col = j;

			}
		}

		// Updating the old board with new board
		this.board = null;
		this.board = Arrays.stream(this.board2).map(a -> Arrays.copyOf(a, a.length)).toArray(Cell[][]::new);
		
		// Prints the new board
		this.printBoard();
		return this;
	}
	
	
	/**
	 * 
	 * Checks if the current board has reached end game - all cells dead is the condition or 500 generations
	 * 
	 * @return boolean - to continue to next generation or not
	 * 
	 */
	boolean checkEnd() {
		
		for(int i=0;i<this.size;i++) {
			for (int j=0;j<this.size;j++) {
				if (this.board[i][j].status == true) {
					return true;
				}
			}
		}		
		return false;	
	}
	
	
	
	/**
	 * 
	 * Calculates the number of neighbouring alive cells to the current cell position
	 * 
	 * @param row - current row position
	 * @param col - current col position
	 * 
	 * @return count - number of alive neighbour cells
	 * 
	 */
	public int aliveNCells(int row,int col) {
		int count =0;  // alive count initilally 0

		/**
		 * Checking the condition if cell is positioned at any corner or the board
		 */
		if (row == 0 && col == 0) {
			if (this.board[0][1].status == true) {count++;}
			if (this.board[1][0].status == true) {count++;}
			if (this.board[1][1].status == true) {count++;}
		}else if (row==0 && col==this.size-1) {
			if (this.board[0][this.size-2].status == true) {count++;}
			if (this.board[1][this.size-2].status == true) {count++;}
			if (this.board[1][this.size-1].status == true) {count++;}
		}else if (row==this.size-1 && col==0) {
			if (this.board[this.size-2][0].status == true) {count++;}
			if (this.board[this.size-2][1].status == true) {count++;}
			if (this.board[this.size-1][1].status == true) {count++;}
		}else if (row==this.size-1 && col==this.size-1) {
			if (this.board[this.size-2][this.size-2].status == true) {count++;}
			if (this.board[this.size-1][this.size-2].status == true) {count++;}
			if (this.board[this.size-2][this.size-1].status == true) {count++;}
		}
		/**
		 * Checking the condition if cell is positioned on any edge of the board but not the corners
		 */
		else if (row==0) {
			for (int i=0;i<=1;i++) {
				for (int j=-1;j<=1;j++) {
					if (this.board[row+i][col+j].status==true) {count++;}
				}
			}
			if(this.board[row][col].status==true) {count-=1;}
		}else if (row==this.size-1) {
			for (int i=-1;i<1;i++) {
				for (int j=-1;j<=1;j++) {
					if (this.board[row+i][col+j].status==true) {count++;}
				}
			}
			if(this.board[row][col].status==true) {count-=1;}
		}else if (col==0) {
			for (int i=-1;i<=1;i++) {
				for (int j=0;j<=1;j++) {
					if (this.board[row+i][col+j].status==true) {count++;}
				}
			}
			if(this.board[row][col].status==true) {count-=1;}
		}else if (col==this.size-1) {
			for (int i=-1;i<=1;i++) {
				for (int j=-1;j<1;j++) {
					if (this.board[row+i][col+j].status==true) {count++;}
				}
			}
			if(this.board[row][col].status==true) {count-=1;}
		}
		/**
		 * If its neither of the above position then we can freely check for all 8 neighbouring positions for life status
		 */
		else {
			for (int i=-1;i<=1;i++) {
				for (int j=-1;j<=1;j++) {
					if (this.board[row+i][col+j].status==true) {count++;}
				}
			}
			if(this.board[row][col].status==true) {count-=1;}
		}
		
		// returns alive neighbour count
		return count;
	}
	
	
	
	/**
	 * Setter method - sets the size of board
	 * @param size
	 */
	void setSize(int size) {
		this.size = size;
	}


	/**
	 * Getter method - gets the size of the board
	 * @return size
	 */
	int getSize() {
		return this.size;
	}

}
