public class GameBoard {
    /**Number of rows on gameboard. Default size is 6.*/
    int numRows;
    /**Number of columns on gameboard. Default size is 15.*/
    int numCols;
    /**2-d array holding creature objects. Size is numRows x numCols.*/
    BoardItem[][] gameBoard;
    /**Contains the number of obstacles currently on the board.*/
    int numObstacles;
    /**Contains the number of prizes currently on the board.*/
    int numPrizes;

    /**
     * Default constructor which initializes data members as follows
     * @param nRows used to set numRows and rows of the Gameboard array
     * @param nCols used to set numCols and cols of the Gameboard array
     * @param nObstacles used to set numObstacles
     * @param nPrizes used to set numPrizes
     */
    public GameBoard(int nRows, int nCols, int nObstacles, int nPrizes) {
        numRows = nRows;
        numCols = nCols;
        numObstacles = nObstacles;
        numPrizes = nPrizes;
        gameBoard = new BoardItem[nRows][nCols];
    }

    /**Creates Obstacle and Prize objects and calls the addBoardItem() method to place them on the gameboard one at a time.*/
    public void populateBoard() {

    }

    /**
     * Randomly generates co-ordinates until an empty board cell is found. Once found, it assigns the item to the gameBoard array.
     * @param item item to assign to gameboard 
     */
    public void addBoardItem(BoardItem item) {

    }

    /**
     * Used to remove the prize located at the position indicated by row and col. Returns true once the operation was successful.
     * @param row row to locate prize
     * @param col col to locate prize
     * @return boolean value for success
     */
    public boolean removePrize(int row, int col) {
        return true;
    }

    /**
     * Used to display the board. This will include a summary of the number of prizes currently left on the board.
     */
    public void displayBoard() {

    }
}