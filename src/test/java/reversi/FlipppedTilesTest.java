package reversi;

import org.junit.*;
import static org.junit.Assert.*;

import java.util.ArrayList;

public class FlipppedTilesTest {
		
		@Test
	    public void findHorizontalFlippedTilesTest()
	    {
			ReversiGameBoard board = new ReversiGameBoard();
	        board.addTile(2, 2, "B");
	        board.addTile(2, 3, "W");
	        board.addTile(3, 4, "W");
	        board.addTile(4, 4, "W");
	        board.addTile(3, 2, "W");
	        board.addTile(3, 3, "W");
	        board.addTile(4, 3, "W");
	        board.addTile(5, 4, "B");
	        
	        board.findHorizontalFlips("B", 2, 4);
	        
			ArrayList<Position> expectedTiles = new ArrayList<Position>();
			expectedTiles.add(new Position(2, 2));
			expectedTiles.add(new Position(2, 3));
			
			ArrayList<Position> actualTiles = board.getFlippedTiles();
			assertEquals("findHorizontalFlips()", expectedTiles.size(), actualTiles.size());

			for (int i = 0; i < actualTiles.size(); i++) {
				Position ep = expectedTiles.get(i);
				Position ap = actualTiles.get(i);
				assertEquals("findHorizontalFlips()", true, ep.toString().equals(ap.toString()));
			}
	    }

	     @Test
	     public void findVerticalFlippedTilesTest()
	     {
	 		ReversiGameBoard board = new ReversiGameBoard();
	         board.addTile(2, 3, "B");
	         board.addTile(3, 3, "B");
	         board.addTile(4, 4, "B");
	         board.addTile(3, 4, "W");
	         board.addTile(4, 3, "W");

	         board.findVerticalFlips("W", 1, 3);
	         
	         ArrayList<Position> expectedTiles = new ArrayList<Position>();
			 expectedTiles.add(new Position(4, 3));
			 expectedTiles.add(new Position(3, 3));
			 expectedTiles.add(new Position(2, 3));
			
			 ArrayList<Position> actualTiles = board.getFlippedTiles();
			 assertEquals("findVerticalFlips()", expectedTiles.size(), actualTiles.size());

			 for (int i = 0; i < actualTiles.size(); i++) {
				Position ep = expectedTiles.get(i);
				Position ap = actualTiles.get(i);
				assertEquals("findVerticalFlips()", true, ep.toString().equals(ap.toString()));
			 }
	     }

	     @Test
	     public void findRightDiagonalFlippedTilesTest()
	     {
	 		ReversiGameBoard board = new ReversiGameBoard();
	         board.addTile(2, 1, "W");
	         board.addTile(2, 3, "W");
	         board.addTile(3, 4, "W");
	         board.addTile(4, 4, "B");
	         board.addTile(3, 2, "W");
	         board.addTile(3, 3, "W");
	         board.addTile(4, 3, "W");
	         board.addTile(5, 4, "B");
	         
	         board.findRightDiagonalFlips("B", 1, 0);
	         
	         ArrayList<Position> expectedTiles = new ArrayList<Position>();
	         expectedTiles.add(new Position(5, 4));
			 expectedTiles.add(new Position(4, 3));
			 expectedTiles.add(new Position(3, 2));
			 expectedTiles.add(new Position(2, 1));
			
			 ArrayList<Position> actualTiles = board.getFlippedTiles();
			 assertEquals("findRightDiagonalFlips()", expectedTiles.size(), actualTiles.size());

			 for (int i = 0; i < actualTiles.size(); i++) {
				Position ep = expectedTiles.get(i);
				Position ap = actualTiles.get(i);
				assertEquals("findRightDiagonalFlips()", true, ep.toString().equals(ap.toString()));
			 }
	     }

	     @Test
	     public void findLeftDiagonalFlippedTilesTest()
	     {
	 		ReversiGameBoard board = new ReversiGameBoard();
	         board.addTile(2, 2, "B");
	         board.addTile(2, 3, "W");
	         board.addTile(3, 4, "W");
	         board.addTile(4, 4, "W");
	         board.addTile(3, 2, "W");
	         board.addTile(3, 3, "W");
	         board.addTile(4, 3, "W");
	         board.addTile(5, 4, "B");
	         board.addTile(2, 5, "B");
	         
	         board.findLeftDiagonalFlips("B", 5, 2);
	         
	         ArrayList<Position> expectedTiles = new ArrayList<Position>();
	         expectedTiles.add(new Position(2, 5));
			 expectedTiles.add(new Position(3, 4));
			 expectedTiles.add(new Position(4, 3));
			
			 ArrayList<Position> actualTiles = board.getFlippedTiles();
			 assertEquals("findLeftDiagonalFlips()", expectedTiles.size(), actualTiles.size());

			 for (int i = 0; i < actualTiles.size(); i++) {
				Position ep = expectedTiles.get(i);
				Position ap = actualTiles.get(i);
				assertEquals("findLeftDiagonalFlips()", true, ep.toString().equals(ap.toString()));
			 }
	     }

}
