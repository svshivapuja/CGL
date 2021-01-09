import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


/**
 * 
 * Test cases
 * 
 * @author K.Sreenivas - 2020501026
 * 
 * @version 2.3
 * 
 */
public class TestJunit {


    /**
     * 
     * Test for checking if board size is setting
     * 
     */
   @Test
   public void testMainOne() {
	   Board board = new Board();
	   board.setSize(5);
       System.out.println("======TEST ONE EXECUTED=======");
       Assertions.assertEquals(5, board.size);
   }
   

   /**
    * 
    * Test for checking cell object initialisation and checking its status

    */
   @Test
   public void testMainTwo() {
	   Cell cell = new Cell();
	   cell.setStatus(true);
	   System.out.println("======TEST TWO EXECUTED=======");
       Assertions.assertEquals(true, cell.getStatus());
   }
   

   /**
    * 
    * Test for checking board object and its inner lengths creation

    */
   @Test
   public void testMainThree() {
	   Board obj = new Board();
	   obj.setSize(10);
	   obj.createBoard();
	   System.out.println("======TEST THREE EXECUTED=======");
       Assertions.assertEquals(10, obj.board.length);
       Assertions.assertEquals(10, obj.board[0].length);
   }

    /**
     * 
     * Test for checking cell object inside board object with a given length
     * 
     */
    @Test
    public void testMainFour() {
        Board obj = new Board();
        obj.setSize(10);
        obj.createBoard();
        System.out.println("======TEST FOUR EXECUTED=======");
        Assertions.assertEquals(10, obj.board[0].length);
        Assertions.assertEquals(false, obj.board[0][0].status);
        obj.board[0][0].setStatus(true);
        Assertions.assertEquals(true, obj.board[0][0].status);
    }
    
    
    /**
     *  
     *  Test to check if nextGen method is working properly
     *
     */
    @Test
    public void testMainFive() {
        Board obj = new Board();
        obj.setSize(10);
        obj.createBoard();
        obj.board[1][1].setStatus(true);
        obj.board[1][2].setStatus(true);
        
        System.out.println("======TEST FIVE EXECUTED=======");
        obj.printBoard();
        Assertions.assertEquals("*** Generation: 0 ***\n. . . . . . . . . .\n. x x . . . . . . .\n. . . . . . . . . .\n. . . . . . . . . .\n. . . . . . . . . .\n. . . . . . . . . .\n. . . . . . . . . .\n. . . . . . . . . .\n. . . . . . . . . .\n. . . . . . . . . .\n", obj.res);
        obj.nextGen();
        obj.printBoard();
        Assertions.assertEquals("*** Generation: 1 ***\n. . . . . . . . . .\n. . . . . . . . . .\n. . . . . . . . . .\n. . . . . . . . . .\n. . . . . . . . . .\n. . . . . . . . . .\n. . . . . . . . . .\n. . . . . . . . . .\n. . . . . . . . . .\n. . . . . . . . . .\n", obj.res);
        
        
    }
}


