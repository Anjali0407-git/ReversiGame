package reversi;

import org.junit.*;
import static org.junit.Assert.*;

public class ReversiGameBoardTest {

	@Test
    public void findNoOfHorizontalFlipsTest()
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
        int horizontalScore = board.findHorizontalScore("B", 2, 4);
        assertEquals("findHorizontalScore() " + horizontalScore, 1, horizontalScore);
    }

    @Test
    public void findNoOfVerticalFlipsTest()
    {
		ReversiGameBoard board = new ReversiGameBoard();
        board.addTile(2, 3, "B");
        board.addTile(3, 3, "B");
        board.addTile(4, 4, "B");
        board.addTile(3, 4, "W");
        board.addTile(4, 3, "W");

        int verticalScore = board.findVerticalScore("W", 1, 3);
        assertEquals("findVerticalScore() " + verticalScore, 2, verticalScore);
    }

    @Test
    public void findNoOfRightDiagonalFlipsTest()
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
        int rightDiagonalScore = board.findRightDiagonalScore("B", 1, 0);
        assertEquals("findRightDiagonalScore() " + rightDiagonalScore, 3, rightDiagonalScore);
    }

    @Test
    public void findNoOfLeftDiagonalFlipsTest()
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
        int leftDiagonalScore = board.findLeftDiagonalScore("B", 5, 2);
        assertEquals("findLeftDiagonalScore() " + leftDiagonalScore, 2, leftDiagonalScore);
    }

    @Test
    public void findScoreTest()
    {
		ReversiGameBoard board = new ReversiGameBoard();
        board.addTile(2, 2, "B");
        board.addTile(2, 3, "W");
        board.addTile(3, 4, "W");
        board.addTile(4, 4, "W");
        board.addTile(4, 2, "W");
        board.addTile(3, 2, "W");
        board.addTile(3, 3, "W");
        board.addTile(4, 3, "W");
        board.addTile(5, 4, "B");
        board.addTile(2, 5, "B");
        int findScore = board.findScore(5, 2, "B");
        assertEquals("findScore() " + findScore, 4, findScore);
    }
}
