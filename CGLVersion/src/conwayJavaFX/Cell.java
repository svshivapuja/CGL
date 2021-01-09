package conwayJavaFX;
/**
 * 
 * This is the Cell class used inside our Board object
 * Represents an individual cell in game of Life
 * 
 * @author K.Sreenivas - 2020501026
 * 
 * @version 2.3
 * 
 */


public class Cell {	

	/**
	 * The attributes of a cell
	 */
	boolean status;
	int row;
	int col;
	
	/**
	 * Default constructor that sets the initial cell state as dead - false
	 * 
	 * Cell state alive - true
	 * Cell state dead - false
	 * 
	 */
	Cell() {		
		this.status = false;
	}
	
	/**
	 * Setter method to set status of a cell
	 * @param bool
	 */
	void setStatus(boolean bool) {
		this.status = bool;		
	}
	

	/**
	 * Getter method to get the status of a cell
	 * @return boolean
	 */
	boolean getStatus() {
		return this.status;
	}

}
