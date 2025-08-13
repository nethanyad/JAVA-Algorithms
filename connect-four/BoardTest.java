/* 
---------------------------------------------------------
Project:    connect-four
Author:     Nethanya Dhaiphule
Language:   Java
Description:
       Given the Connect Four game logic in Board.java, this program
       uses JUnit 5 to test its functions, verifying correct gameplay
       mechanics, win detection, and valid move handling.
---------------------------------------------------------
*/
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import org.junit.jupiter.api.Assertions;
import static org.junit.jupiter.api.Assertions.*;

public class BoardTest {

    /**
     * Used to make a copy of board before functions run, so that verify a function was non-destructive on board is easy
     *
     * @param board The board to make deep copy of
     * @return A deep copy of given board
     */


    public int[][] deepCopy(int[][] board) {
        int[][] copy = new int[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            copy[i] = Arrays.copyOf(board[i], board[i].length);
        }
        return copy;
    }


    @Test
    public void deepCopyTestWithoutDeepEquals() {
        int[][] expected = new int[][]{{0, 1}};
        int[][] actual = deepCopy(expected);
        assertEquals(expected[0][0], actual[0][0]);
        assertEquals(expected[0][1], actual[0][1]);
    }


    @Test
    public void deepCopyTestNoChange() {
        int[][] expected = new int[][]{{0, 1}};
        int[][] actual = deepCopy(expected);
        assertTrue(Arrays.deepEquals(expected, actual));
    }

    @Test
    public void deepCopyTestChangeEntryIn2D() {
        int[][] expected = new int[][]{{0, 1}};
        int[][] actual = deepCopy(expected);
        actual[0][0] = 99;
        assertTrue(!Arrays.deepEquals(expected, actual));
    }

    @Test
    public void deepCopyTestSet1DRefToDiffButIdenticalArray() {
        int[][] expected = new int[][]{{0, 1}};
        int[][] actual = deepCopy(expected);
        actual[0] = new int[]{0, 1};
        assertTrue(Arrays.deepEquals(expected, actual));
    }

    @Test
    public void deepCopyTestSet1DRefToDiffArray() {
        int[][] expected = new int[][]{{0, 1}};
        int[][] actual = deepCopy(expected);
        actual[0] = new int[]{0, 99};
        assertTrue(!Arrays.deepEquals(expected, actual));
    }


    //createBoard()
     @Test
    public void testCreateBoardWithValidDimensions() {
        int rows = 5;
        int columns = 7;
        int[][] board = Board.createBoard(rows, columns);

        assertEquals(rows, Board.rowCount(board));
        assertEquals(columns, Board.columnCount(board));
    }

    @Test
    public void testCreateBoardWithMaximumDimensions() {
        int rows = 8;
        int columns = 8;
        int[][] board = Board.createBoard(rows, columns);

        assertEquals(rows, Board.rowCount(board));
        assertEquals(columns, Board.columnCount(board));
    }

    @Test
    public void testCreateBoardWithMinimumDimensions() {
        int rows = 4;
        int columns = 4;
        int[][] board = Board.createBoard(rows, columns);

        assertEquals(rows, Board.rowCount(board));
        assertEquals(columns, Board.columnCount(board));
    }

    @Test
    public void testCreateBoardWithSquareBoard() {
        int rows = 6;
        int columns = 6;
        int[][] board = Board.createBoard(rows, columns);

        assertEquals(rows, Board.rowCount(board));
        assertEquals(columns, Board.columnCount(board));
    }

    @Test
    public void testCreateBoardWithRectangleBoard() {
        int rows = 4;
        int columns = 8;
        int[][] board = Board.createBoard(rows, columns);

        assertEquals(rows, Board.rowCount(board));
        assertEquals(columns, Board.columnCount(board));
    }

    //rowCount()
    @Test
    public void testRowCountFor4Rows() {
        int[][] board = new int[4][5];
        assertEquals(4, Board.rowCount(board));
    }

    @Test
    public void testRowCountFor5Rows() {
        int[][] board = new int[5][5];
        assertEquals(5, Board.rowCount(board));
    }

    @Test
    public void testRowCountFor6Rows() {
        int[][] board = new int[6][5];
        assertEquals(6, Board.rowCount(board));
    }

    @Test
    public void testRowCountFor7Rows() {
        int[][] board = new int[7][5];
        assertEquals(7, Board.rowCount(board));
    }

    @Test
    public void testRowCountFor8Rows() {
        int[][] board = new int[8][5];
        assertEquals(8, Board.rowCount(board));
    }

    //columnCount()
    @Test
    public void testColumnCountFor4Columns() {
        int[][] board = new int[7][4];
        assertEquals(4, Board.columnCount(board));
    }

    @Test
    public void testColumnCountFor5Columns() {
        int[][] board = new int[7][5];
        assertEquals(5, Board.columnCount(board));
    }

    @Test
    public void testColumnCountFor6Columns() {
        int[][] board = new int[7][6];
        assertEquals(6, Board.columnCount(board));
    }

    @Test
    public void testColumnCountFor7Columns() {
        int[][] board = new int[7][7];
        assertEquals(7, Board.columnCount(board));
    }

    @Test
    public void testColumnCountFor8Columns() {
        int[][] board = new int[7][8];
        assertEquals(8, Board.columnCount(board));
    }

    //Valid()
    @Test
    public void testValidWithValidDimensions() {
        int[][] board = new int[4][7];
        assertTrue(Board.valid(board, 3, 6));
    }

    @Test
    public void testValidWithInvalidDimensions() {
        int[][] board = new int[5][8];
        Assertions.assertFalse(Board.valid(board, 2, -3));
    }

    @Test
    public void testValidWithInvalidRowsAndValidDimensions() {
        int[][] board = new int[4][4];
        Assertions.assertFalse(Board.valid(board, 2, 4));
    }

    @Test
    public void testValidWithValidRowsAndInvalidDimensions() {
        int[][] board = new int[4][4];
        Assertions.assertFalse(Board.valid(board, 4, -3));
    }

    @Test
    public void testValidWithMinimumValues() {
        int[][] board = new int[4][4];
        Assertions.assertFalse(Board.valid(board, 4, 4));
    }

    @Test
    public void testValidWithMaximumValues() {
        int[][] board = new int[8][8];
        Assertions.assertFalse(Board.valid(board, 8, 8));
    }


    //CanPlay()
    @Test
    public void testCanPlayWithNoRoomLeftToPlay() {
        int[][] board =
                {
                        {1, 1, 1, 1},
                        {1, 1, 1, 1},
                        {1, 1, 1, 1},
                        {1, 1, 1, 1},
                };
        Assertions.assertFalse(Board.canPlay(board, 3));
    }

    @Test
    public void testCanPlayWithRoomLeftToPlay() {
        int[][] board =
                {
                        {1, 1, 1, 1},
                        {1, 1, 1, 1},
                        {1, 1, 1, 1},
                        {1, 1, 1, 0},
                };
        Assertions.assertTrue(Board.canPlay(board, 3));
    }

    @Test
    public void testCanPlayWithEmptyArray() {
        int[][] board =
                {
                        {0, 0, 0, 0},
                        {0, 0, 0, 0},
                        {0, 0, 0, 0},
                        {0, 0, 0, 0},

                };
        Assertions.assertTrue(Board.canPlay(board, 2));
    }

    @Test
    public void testCanPlayWithHalfEmptyArray() {
        int[][] board =
                {
                        {0, 1, 0, 1},
                        {1, 0, 2, 0},
                        {0, 1, 0, 2},
                        {1, 0, 1, 0},

                };
        Assertions.assertTrue(Board.canPlay(board, 2));
    }

    @Test
    public void testCanPlayWithValidColumnIndex() {
        int[][] board = new int[4][5];
        Assertions.assertTrue(Board.canPlay(board, 3));
    }


    // Play()
    @Test
    public void testPlayInEmptyArray() {
        int[][] board =
                {
                        {0, 0, 0, 0},
                        {0, 0, 0, 0},
                        {0, 0, 0, 0},
                        {0, 0, 0, 0},

                };
        int returnValue = Board.play(board, 0, 1);
        Assertions.assertEquals(returnValue, 3);
    }

    @Test
    public void testPlayInHalfEmptyColumn() {
        int[][] board =
                {
                        {0, 0, 0, 0},
                        {0, 0, 0, 1},
                        {0, 0, 2, 1},
                        {1, 0, 2, 2},

                };
        int returnValue = Board.play(board, 2, 1);
        Assertions.assertEquals(returnValue, 1);
    }

    @Test
    public void testPlayInFullColumn() {
        int[][] board =
                {
                        {0, 0, 0, 1},
                        {0, 0, 0, 1},
                        {0, 0, 2, 1},
                        {1, 0, 2, 2},

                };
        int returnValue = Board.play(board, 3, 1);
        Assertions.assertEquals(returnValue, -1);
    }

    @Test
    public void testPlayInFullArray() {
        int[][] board =
                {
                        {2, 2, 1, 1},
                        {1, 2, 2, 1},
                        {2, 1, 2, 1},
                        {1, 1, 2, 2},

                };
        int returnValue = Board.play(board, 1, 1);
        Assertions.assertEquals(returnValue, -1);
    }

    @Test
    public void testPlayInEmptyColumn() {
        int[][] board =
                {
                        {2, 2, 0, 1},
                        {1, 2, 0, 1},
                        {2, 1, 0, 1},
                        {1, 1, 0, 2},

                };
        int returnValue = Board.play(board, 2, 2);
        Assertions.assertEquals(returnValue, 3);
    }

    //removeLastPlay()

    @Test
    public void testRemoveLastPlayWithEmptyArray() {
        int[][] board = {
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0}
        };
        assertEquals(-1, Board.removeLastPlay(board, 0));
    }

    @Test
    public void testRemoveLastPlayWithFullArray() {
        int[][] board = {
                {1, 2, 1, 2},
                {2, 1, 2, 1},
                {1, 2, 1, 2},
                {2, 1, 2, 1}
        };
        assertEquals(0, Board.removeLastPlay(board, 3));
    }

    @Test
    public void testRemoveLastPlayWithFullColumn() {
        int[][] board = {
                {2, 0, 0, 0},
                {1, 0, 0, 0},
                {2, 0, 0, 0},
                {1, 0, 0, 0}
        };
        assertEquals(0, Board.removeLastPlay(board, 0));
    }

    @Test
    public void testRemoveLastPlayWithOnePieceInColumn() {
        int[][] board = {
                {2, 0, 0, 0},
                {1, 0, 0, 0},
                {2, 0, 0, 0},
                {1, 1, 0, 0}
        };
        assertEquals(3, Board.removeLastPlay(board, 1));
    }

    @Test
    public void testRemoveLastPlayWithHalfFullColumn() {
        int[][] board = {
                {2, 0, 0, 0},
                {1, 0, 0, 0},
                {2, 0, 1, 0},
                {1, 1, 2, 0}
        };
        assertEquals(2, Board.removeLastPlay(board, 2));
    }

    //full()
    @Test
    public void testFullWhenActuallyFull() {
        int[][] board = {
                {1, 2, 1, 2},
                {2, 1, 2, 1},
                {1, 2, 1, 2},
                {2, 1, 2, 1}
        };
        Assertions.assertTrue(Board.full(board));
    }

    @Test
    public void testFullWhenActuallyEmpty() {
        int[][] board = {
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0}
        };
        Assertions.assertFalse(Board.full(board));
    }

    @Test
    public void testFullWhenActuallyOnePiece() {
        int[][] board = {
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 1, 0, 0},
                {0, 0, 0, 0}
        };
        Assertions.assertFalse(Board.full(board));
    }

    @Test
    public void testFullWhenActuallyHalfEmpty() {
        int[][] board = {
                {2, 0, 0, 0},
                {1, 0, 0, 0},
                {2, 1, 2, 0},
                {1, 2, 1, 0}
        };
        Assertions.assertFalse(Board.full(board));
    }

    @Test
    public void testFullWhenAlmostFull() {
        int[][] board = {
                {2, 1, 2, 0},
                {1, 2, 1, 2},
                {2, 1, 2, 1},
                {1, 2, 1, 2}
        };
        Assertions.assertFalse(Board.full(board));
    }

    //winInRow()
    @Test
    public void testWinInRowWithWin1() //test where L-Shape creating piece is below row
    {
        int[][] board = {
                {0, 0, 0, 0},
                {1, 1, 1, 2},
                {1, 2, 0, 2},
                {2, 2, 2, 2}
        };
        Assertions.assertTrue(Board.winInRow(board, 1, 1, 3)); // Win in the second row for RED
    }


    @Test
    public void testWinInRowWithWin2() //test where L-shape creating piece is above row
    {
        int[][] board = {
                {0, 0, 0, 0},
                {1, 1, 1, 2},
                {1, 2, 0, 2},
                {2, 2, 2, 2}
        };
        Assertions.assertTrue(Board.winInRow(board, 3, 2, 4)); // Win in the fourth row for BLU
    }

    @Test
    public void testWinInRowWithNoWin() {
        int[][] board = {
                {0, 0, 2, 0},
                {1, 0, 1, 2},
                {0, 2, 0, 2},
                {2, 0, 2, 0}
        };
        Assertions.assertFalse(Board.winInRow(board, 1, 1, 3));
        Assertions.assertFalse(Board.winInRow(board, 3, 2, 4));
    }

    @Test
    public void testWinInRowWithNoLShape() {
        int[][] board = {
                {0, 0, 2, 0},
                {1, 1, 1, 2},
                {0, 2, 0, 0},
                {2, 2, 2, 2}
        };
        Assertions.assertFalse(Board.winInRow(board, 1, 1, 3));
        Assertions.assertFalse(Board.winInRow(board, 3, 2, 4));
    }

    @Test
    public void testWinInRowWithNotEnoughConsecutivePieces() {
        int[][] board = {
                {1, 0, 2, 0},
                {1, 1, 0, 2},
                {0, 2, 0, 2},
                {0, 2, 2, 2}
        };
        Assertions.assertFalse(Board.winInRow(board, 1, 1, 3));
        Assertions.assertFalse(Board.winInRow(board, 3, 2, 4));
    }


    //winInColumn()
    @Test
    public void testWinInColumnWithWin1() //test where L-Shape creating piece is in right column
    {
        int[][] board = {
                {0, 0, 2, 0},
                {1, 1, 2, 1},
                {1, 2, 2, 0},
                {1, 2, 2, 2}
        };
        Assertions.assertTrue(Board.winInColumn(board, 0, 1, 3));
        Assertions.assertTrue(Board.winInColumn(board, 2, 2, 4));
    }


    @Test
    public void testWinInColumnWithWin2() //test where L-shape creating piece is in left column
    {
        int[][] board = {
                {0, 1, 1, 2},
                {2, 2, 1, 0},
                {1, 2, 1, 1},
                {2, 2, 1, 0}
        };
        Assertions.assertTrue(Board.winInColumn(board, 1, 2, 3));
        Assertions.assertTrue(Board.winInColumn(board, 2, 1, 4));
    }

    @Test
    public void testWinInColumnWithNoWin() {
        int[][] board = {
                {0, 0, 2, 0},
                {1, 0, 1, 2},
                {0, 2, 0, 2},
                {2, 0, 2, 0}
        };
        Assertions.assertFalse(Board.winInColumn(board, 1, 2, 3));
        Assertions.assertFalse(Board.winInColumn(board, 2, 1, 4));
    }

    @Test
    public void testWinInColumnWithNoLShape() {
        int[][] board = {
                {0, 0, 0, 2},
                {1, 0, 1, 2},
                {1, 2, 0, 2},
                {1, 2, 0, 2}
        };
        Assertions.assertFalse(Board.winInColumn(board, 0, 1, 3));
        Assertions.assertFalse(Board.winInColumn(board, 3, 2, 4));
    }

    @Test
    public void testWinInColumnWithNotEnoughConsecutivePieces() {
        int[][] board = {
                {1, 0, 2, 0},
                {1, 1, 0, 2},
                {0, 2, 0, 2},
                {0, 0, 2, 2}
        };
        Assertions.assertFalse(Board.winInColumn(board, 0, 1, 3));
        Assertions.assertFalse(Board.winInColumn(board, 3, 2, 4));
    }

    //winInDiagonalBackslash()
    @Test
    public void testWinInDiagonalBackslashWithWin() {
        int[][] board = {
                {1, 0, 2, 0, 0},
                {2, 1, 2, 1, 0},
                {0, 2, 1, 0, 0},
                {0, 2, 2, 1, 0}
        };
        Assertions.assertTrue(Board.winInDiagonalBackslash(board, 1, 4));
        Assertions.assertTrue(Board.winInDiagonalBackslash(board, 2, 3));
    }

    @Test
    public void testWinInDiagonalBackslashWithNoWin1() //partially full board
    {
        int[][] board = {
                {1, 0, 2, 0, 0},
                {2, 0, 0, 1, 0},
                {0, 0, 0, 0, 0},
                {0, 2, 2, 1, 0}
        };
        Assertions.assertFalse(Board.winInDiagonalBackslash(board, 1, 4));
        Assertions.assertFalse(Board.winInDiagonalBackslash(board, 2, 3));
    }

    @Test
    public void testWinInDiagonalBackslashWithNoWin2() //full board
    {
        int[][] board = {
                {1, 2, 1, 2, 1},
                {1, 2, 1, 2, 1},
                {1, 2, 1, 2, 1},
                {1, 2, 1, 2, 1}
        };
        Assertions.assertFalse(Board.winInDiagonalBackslash(board, 1, 4));
        Assertions.assertFalse(Board.winInDiagonalBackslash(board, 2, 3));
    }

    @Test
    public void testWinInDiagonalBackslashWithNotEnoughConsecutivePieces() {
        int[][] board = {
                {1, 0, 2, 0, 0},
                {0, 1, 0, 1, 0},
                {0, 2, 1, 0, 0},
                {0, 2, 2, 0, 0}
        };
        Assertions.assertFalse(Board.winInDiagonalBackslash(board, 1, 4));
        Assertions.assertFalse(Board.winInDiagonalBackslash(board, 2, 3));
    }

    @Test
    public void testWinInDiagonalBackslashWithEmptyBoard() {
        int[][] board = {
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0}
        };
        Assertions.assertFalse(Board.winInDiagonalBackslash(board, 2, 4));
        Assertions.assertFalse(Board.winInDiagonalBackslash(board, 1, 3));
    }

    //winInDiagonalForwardSlash()
    @Test
    public void testWinInDiagonalForwardSlashWithWin() {
        int[][] board = {
                {1, 0, 2, 1, 0},
                {2, 1, 1, 2, 0},
                {0, 1, 2, 0, 0},
                {1, 2, 2, 1, 0}
        };
        Assertions.assertTrue(Board.winInDiagonalForwardSlash(board, 1, 4));
        Assertions.assertTrue(Board.winInDiagonalForwardSlash(board, 2, 3));
    }

    @Test
    public void testWinInDiagonalForwardSlashWithNoWin1() //partially full board
    {
        int[][] board = {
                {1, 0, 2, 0, 0},
                {2, 0, 0, 1, 0},
                {0, 0, 0, 0, 0},
                {0, 2, 2, 1, 0}
        };
        Assertions.assertFalse(Board.winInDiagonalForwardSlash(board, 1, 4));
        Assertions.assertFalse(Board.winInDiagonalForwardSlash(board, 2, 3));
    }

    @Test
    public void testWinInDiagonalForwardSlashWithNoWin2() //full board
    {
        int[][] board = {
                {1, 2, 1, 2, 1},
                {1, 2, 1, 2, 1},
                {1, 2, 1, 2, 1},
                {1, 2, 1, 2, 1}
        };
        Assertions.assertFalse(Board.winInDiagonalForwardSlash(board, 1, 4));
        Assertions.assertFalse(Board.winInDiagonalForwardSlash(board, 2, 3));
    }

    @Test
    public void testWinInDiagonalForwardSlashWithNotEnoughConsecutivePieces() {
        int[][] board = {
                {1, 0, 2, 0, 0},
                {0, 1, 1, 1, 0},
                {0, 1, 2, 0, 0},
                {1, 2, 2, 0, 0}
        };
        Assertions.assertFalse(Board.winInDiagonalForwardSlash(board, 1, 4));
        Assertions.assertFalse(Board.winInDiagonalForwardSlash(board, 2, 3));
    }

    @Test
    public void testWinInDiagonalForwardSlashWithEmptyBoard() {
        int[][] board = {
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0}
        };
        Assertions.assertFalse(Board.winInDiagonalForwardSlash(board, 2, 4));
        Assertions.assertFalse(Board.winInDiagonalForwardSlash(board, 1, 3));
    }

    //hint()
    @Test
    public void testHintWithWinningMoveInColumn() {
        int[][] board = {
                {0, 0, 0, 0},
                {1, 2, 1, 0},
                {1, 1, 2, 0},
                {1, 2, 1, 2}
        };
        assertArrayEquals(new int[]{0, 0}, Board.hint(board, 1, 3));
    }

    @Test
    public void testHintWithWinningMoveInRow() {
        int[][] board = {
                {0, 0, 0, 0},
                {2, 2, 0, 0},
                {1, 1, 1, 0},
                {2, 2, 2, 2}
        };
        assertArrayEquals(new int[]{1, 2}, Board.hint(board, 1, 3));
    }

    @Test
    public void testHintWithWinningMoveInDiagonalBackwardSlash() {
        int[][] board = {
                {0, 0, 0, 0},
                {2, 2, 0, 0},
                {1, 1, 2, 0},
                {1, 1, 2, 2}
        };
        assertArrayEquals(new int[]{0, 0}, Board.hint(board, 2, 4));
    }

    @Test
    public void testHintWithWinningMoveInDiagonalForwardSlash() {
        int[][] board = {
                {0, 0, 0, 0},
                {0, 1, 2, 1},
                {1, 2, 1, 1},
                {2, 2, 2, 1}
        };
        assertArrayEquals(new int[]{0, 3}, Board.hint(board, 2, 4));
    }

    @Test
    public void testHintWithEmptyArray() {
        int[][] board = {
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0}
        };
        assertArrayEquals(new int[]{-1, -1}, Board.hint(board, 2, 4));
    }
}
































