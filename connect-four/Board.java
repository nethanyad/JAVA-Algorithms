/**
 * CPSC 233 W24 Assignment 1 Starter to use to make Board.java
 * @author Jonathan Hudson
 * @email jwhudson@ucalgary.ca
 * @version 1.0
 */
public class Board{

    /**
     * No piece in board (empty)
     */
    public static final int EMP = Game.EMP;
    /**
     * Connect-L Red Piece
     */
    public static final int RED = Game.RED;
    /**
     * Connect-L Blue Piece
     */
    public static final int BLU = Game.BLU;




    //Students should enter their functions below here

    /**
     * CPSC 233 W24 Board.java
     * @author Nethanya Dhaiphule
     * @tutorial T12
     * @date February 2, 2024
     * /

    /**
     * This method creates and return a 2D integer array for the board of the game
     *  @param rows number of rows in board
     * @param columns number of columns in board
     * @return board
     */
    public static int[][] createBoard(int rows, int columns)
    {
        int[][] board = new int[rows][columns];
        return board;
        }



    /**
     *This method returns the number of rows the board has
     * @param board board
     * @return number of rows in the board
     */
    public static int rowCount(int[][] board)
    {
        return board.length;
    }


    /**
     * This method returns the number of columns the board has
     * @param board board
     * @return number of columns in the board
     */
    public static int columnCount(int[][] board)
    {
        return board[0].length;
    }


    /**
     * This method validates the board dimensions and returns true if they are valid
     * @param board board
     * @param row index of chosen row
     * @param column index of chosen column
     * @return true if the dimensions are valid and false otherwise
     */
    public static boolean valid(int[][]board, int row, int column)
    {
        return row>= 0 && row < rowCount(board) && column >= 0 && column < columnCount(board);
    }

    /**
     * This method checks to see if the chosen column has space to add more elements
     * @param board board
     * @param column index of chosen column
     * @return true if the chosen column has space or false otherwise
     */
    public static boolean canPlay(int[][]board, int column)
    {
        //check to see if the column has space
       for (int i = board.length - 1; i>= 0; i--)
       {
           if (board[i][column] == 0)
           {
               return true;
           }
       }
       return false;
    }

    /**
     * This method finds the first available row in the column given as a parameter
     * @param board board
     * @param column index of chosen column
     * @param piece chosen piece
     * @return the index of the row the piece stopped at when played or -1 if there is no room to play a new piece in the provided column
     */
    public static int play(int[][]board, int column,int piece)
    {
        for (int row = board.length - 1; row >= 0; row--)
        {
            if (board[row][column]== 0)
            {
                //Play the piece in position selected
                board[row][column] = piece;
                return row;
            }
        }
        //if there was no room to play a new piece in provided column
        return -1;
    }

    /**
     * This method removes the prior attempt and assigns an EMP
     * @param board board
     * @param column index of chosen column
     * @return index of row where piece was removed or -1 if there are no pieces to remove
     */
    public static int removeLastPlay(int[][]board, int column)
    {
        //find the first cell which is empty in the specified column
        for (int row = 0; row <board.length; row++)
        {
            if (board[row][column] != 0)
            {
                //remove the selected piece and store it
                board[row][column] = 0;
                return row;
            }
        }
        // if there are no pieces in the column to remove
        return -1;

    }

    /**
     * This method checks if the board is completely filled hence the game has ended
     * @param board board
     * @return true if board is filled or false otherwise
     */
    public static boolean full(int[][] board)
    {
        // nested for loop which goes through every element in the array
        for (int row = 0; row < board.length; row++)
        {
            for (int column = 0; column < board[row].length;column++)
            {
                int value = board[row][column];
                if (value == 0)
                {
                    //if any piece is empty false is returned
                    return false;
                }
            }

        }
        // if the board is filled with non-EMPTY pieces
        return true;
    }

    /**
     * This method checks if there are at least given length consecutive entries in a row
     * as well as a perpendicular L piece for the specified piece
     * If the conditions are met, it signifies that the user has won the game
     * @param board board
     * @param row index of chosen row
     * @param piece chosen piece
     * @param length number of consecutive entries
     * @return true if there was a win in the row based on the conditions or false otherwise
     */
    public static boolean winInRow(int[][] board, int row, int piece, int length) {
        int columnCount = board[0].length;
        int consecutiveCount =0;

        // Iterate through each column index
        for (int i = 0; i <= columnCount - length; i++) {
            boolean consecutive = true;
            // Check for consecutive pieces of the specified type in the row
            for (int j = i; j < i + length && consecutive; j++)
            {
                if (board[row][j] == piece)
                {
                    consecutiveCount++;
                }
                else
                {
                    consecutiveCount = 0;
                    consecutive = false;
                }
                // Return true if there are enough consecutive pieces
                if (consecutiveCount == length)
                {
                    if (row + 1 < board.length)
                    {
                        if (board [row][j] == board [row + 1][j])
                        {
                            return true;
                        }
                        if (board [row][j - length +1] == board [row + 1][j - length +1])
                        {
                            return true;
                        }
                    }
                    if (row-1 > -1 && j-1>-1 )
                    {
                        if (board[row][j] == board[row-1][j])
                        {
                            return true;// Win condition found
                        }

                        if (board[row][j-length+1] == board[row-1][j-length+1])
                        {
                            return true;// Win condition found
                        }
                    }
                }
            }
        }
        return false;
    }


    /**
     * This method checks if there are at least given length consecutive entries in a column
     * as well as a perpendicular L piece for the specified piece
     *If the conditions are met, it signifies that the user has won the game
     * @param board board
     * @param column index of chosen column
     * @param piece chosen piece
     * @param length number of consecutive entries
     * @return true if there was a win in the column based on the conditions or false otherwise
     */
    public static boolean winInColumn(int[][] board, int column, int piece, int length) {
        int rowCount = board.length;
        // Iterate over the rows
        for (int i = 0; i <= rowCount - length; i++)
        {
            int consecutiveCount = 0;
            boolean consecutive = true;

            // Check for consecutive pieces of the specified type in the column
            for (int j = i; j < i + length && consecutive; j++)
            {
                if (board[j][column] == piece) {
                    consecutiveCount++;
                } else {
                    // Reset count if piece is not of the specified type
                    consecutiveCount = 0;
                    consecutive = false; // Exit the loop if consecutive pieces are broken
                }
                // Check if there are enough consecutive pieces
                if (consecutiveCount == length)
                {
                    if (column + 1 < board[0].length)
                    {
                        if (board[j][column] == board[j][column+1])
                        {
                            return true; // Win condition found
                        }


                        if (board[j-length+1][column] == board[j-length+1][column+1])
                        {
                            return true;// Win condition found
                        }
                    }
                    if (j - 1 > -1 && column-1>-1)
                    {
                        if (board[j][column] == board[j][column-1])
                        {
                            return true;// Win condition found
                        }

                        if (board[j-length+1][column] == board[j-length+1][column-1])
                        {
                            return true;// Win condition found
                        }
                    }
                }
            }
        }
        // No win found with the specified conditions
        return false;
    }

    /**
     *This method checks if there are at least given length consecutive entries in a
     *diagonal backslash if the conditions are met, it signifies that the user has won
     *the game
     * @param board board
     * @param piece chosen piece
     * @param length number of consecutive entries
     * @return true if there was a win based on the conditions or false otherwise
     */
    public static boolean winInDiagonalBackslash(int[][] board, int piece, int length)
    {

        for (int i = 0; i < board.length; i++)
        {
            for (int j = 0; j < board[0].length; j++)
            {
                int consecutiveCount = 0;
                boolean consecutive = true;//keeps track of consecutive pieces
                //iterate length number of times for each diagonal

                for (int k = 0; k < length && consecutive; k++)
                {
                    if (i + k < board.length && j + k < board[0].length)
                    {
                        if (board[i + k][j + k] == piece)
                        {
                            consecutiveCount++;
                        }
                        else
                        {
                            consecutiveCount = 0;
                            consecutive = false;
                        }
                    }

                    if (consecutiveCount == length)
                    {
                        // if "length" number of consecutive pieces are found in the column, return true
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * This method checks if there are at least given length consecutive entries in a
     * diagonal forward slash if the conditions are met, it signifies that the user has
     * won the game
     * @param board board
     * @param piece chosen piece
     * @param length number of consecutive entries
     * @return true if there was a win based on the conditions or false otherwise
     */
    public static boolean winInDiagonalForwardSlash(int[][] board, int piece, int length)
    {
        // nested for loop which goes through every element in the array
        for (int i = 0; i < board.length; i++)
        {
            for (int j = 0; j < board[0].length; j++)
            {
                int consecutiveCount = 0;
                boolean consecutive = true;//keeps track of consecutive pieces
                //iterate length number of times for each diagonal
                for (int k = 0; k < length && consecutive; k++)
                {
                    if (i - k > -1 && i - k < board.length && j + k > -1 && j + k < board[0].length)
                    {
                        if (board[i - k][j + k] == piece)
                        {
                            consecutiveCount++;
                        }
                        else
                        {
                            consecutiveCount = 0;
                            consecutive = false;
                        }
                    }
                    else
                    {
                        consecutive = false;
                    }
                }

                if (consecutiveCount == length)
                {
                    // if "length" number of consecutive pieces are found in the column, return true
                    return true;
                }
            }
        }
        //if "length" number of consecutive pieces are not found, return false
        return false;
    }

    /**
     * This method determines if playing one piece will win the game for a player piece
     * @param board board
     * @param piece chosen piece
     * @param length number of consecutive entries
     * @return the dimensions of where the hint is located
     */
    public static int[] hint(int[][] board, int piece, int length)
    {

        int[] hint = new int[]{-1, -1};

        //Iterate through every column
        for (int col = 0; col < board[0].length; col++) {
            if (canPlay(board, col))
            {
                int row = play(board, col, piece);


                if (row > -1 && won(board, piece, length))
                {
                    removeLastPlay(board, col);
                    hint[0] = row;
                    hint[1] = col;
                    return hint;
                }
                else
                {
                    removeLastPlay(board, col);
                }
            }
        }
        // If no hint is found, return {-1, -1}
        return hint;
    }

    //Students should enter their functions above here
    /**
     * Is there a win in given board in any row of board
     *
     * @param board The 2D array board of size rows (dimension 1) and columns (dimension 2)
     * @param piece The piece to look for length in a row for any row
     * @return True if there is length in any row, false otherwise
     */
    private static boolean winInAnyRow(int[][] board, int piece, int length) {
        for (int row = 0; row < board.length; row++) {
            if (winInRow(board, row, piece, length)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Is there a win in given board in any column of board
     *
     * @param board The 2D array board of size rows (dimension 1) and columns (dimension 2)
     * @param piece The piece to look for length in a row for any column
     * @return True if there is length in any column, False otherwise
     */
    private static boolean winInAnyColumn(int[][] board, int piece, int length) {
        for (int col = 0; col < board[0].length; col++) {
            if (winInColumn(board, col, piece, length)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Is there a win in given board in any diagonal of board
     *
     * @param board The 2D array board of size rows (dimension 1) and columns (dimension 2)
     * @param piece The piece to look for length in a row for any diagonal
     * @return True if there is length in any diagonal /\, False otherwise
     */
    private static boolean winInAnyDiagonal(int[][] board, int piece, int length) {
        return winInDiagonalBackslash(board, piece, length) || winInDiagonalForwardSlash(board, piece, length);
    }

    /**
     * Has the given piece won the board
     *
     * @param board The 2D array board of size rows (dimension 1) and columns (dimension 2)
     * @param piece The piece to check for a win
     * @return True if piece has won
     */
    public static boolean won(int[][] board, int piece, int length) {
        return winInAnyRow(board, piece, length) || winInAnyColumn(board, piece, length) || winInAnyDiagonal(board, piece, length);
    }

    /**
     * This function determines if the game is complete due to a win or tie by either player
     *
     * @param board The 2D array board of size rows (dimension 1) and columns (dimension 2)
     * @return True if game is complete, False otherwise
     */
    public static boolean isGameOver(int[][] board, int length) {
        return full(board) || won(board, RED, length) || won(board, BLU, length);
    }
}



