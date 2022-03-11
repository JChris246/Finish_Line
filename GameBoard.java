import java.util.Random;

public class GameBoard 
{
    /**Number of rows on gameboard. Default size is 6.*/
    private int numRows;

    /**Number of columns on gameboard. Default size is 15.*/
    private int numCols;

    /**2-d array holding creature objects. Size is numRows x numCols.*/
    private BoardItem[][] gameBoard;

    /**Contains the number of obstacles currently on the board.*/
    private int numObstacles;
    
    /**Contains the number of prizes currently on the board.*/
    private int numPrizes;

    /**Default values for fields if user wants standard gameboard*/
    private final static int DEFAULT_ROWS, DEFAULT_COLS, DEFAULT_OBSTACLES, DEFAULT_PRIZES;

    static {
        DEFAULT_ROWS = 6;
        DEFAULT_COLS = 15;
        DEFAULT_OBSTACLES = 6;
        DEFAULT_PRIZES = 3;
    }

    /**
     * Default constructor which initializes data members as follows:
     * numRows to 6 and numCols to 15
     * numObstacles to 6 and numPrizes to 3
     * Size of gameboard to that of numRows and numCols
     */
    public GameBoard() {
        this(DEFAULT_ROWS, DEFAULT_COLS, DEFAULT_OBSTACLES, DEFAULT_PRIZES);
    }

    /**
     * Default constructor which initializes data members as follows
     * @param nRows used to set numRows and rows of the Gameboard array
     * @param nCols used to set numCols and cols of the Gameboard array
     * @param nObstacles used to set numObstacles
     * @param nPrizes used to set numPrizes
     */
    public GameBoard(int nRows, int nCols, int nObstacles, int nPrizes) {
        /*probably add exceptions later for bad values that are currently being
        handled by finish line*/
        numRows = nRows;
        numCols = nCols;
        numObstacles = nObstacles;
        numPrizes = nPrizes;
        gameBoard = new BoardItem[nRows][nCols];
    }

    /**
     * get a valid cell at random
     * @return return coords of the cell as an int array
     */
    private int[] getValidCell() {
        boolean occupied = true;
        int column = 0, row = 0; //default value to satisfy jvm

        while (occupied) { 
            Random randNum = new Random();
            column = randNum.nextInt(numCols - 2) + 1; //exclude start and finish cols
            row = randNum.nextInt(numRows); 
            
            if (gameBoard[row][column] == null)
                occupied = false;
        }
        
        return new int[] {row, column};
    }

    /**
     * Creates Obstacle and Prize objects and calls the addBoardItem() method to place 
     * them on the gameboard one at a time.
     * */
    public void populateBoard() {        
        for(int times = 0; times < numObstacles; times++) 
            addBoardItem(new Obstacle());

        for(int times = 0; times < numPrizes; times++)
            addBoardItem(new Prize());
    }

    /**
     * Randomly generates co-ordinates until an empty board cell is found. Once found, 
     * it assigns the item to the gameBoard array.
     * @param item item to assign to gameboard 
     */
    public void addBoardItem(BoardItem item) {
        int[] coord = getValidCell();
        gameBoard[coord[0]][coord[1]] = item;
    }

    /**
     * Used to remove the prize located at the position indicated by row and col. 
     * Returns true once the operation was successful.
     * @param row row to locate prize
     * @param col col to locate prize
     * @return boolean value for success
     */
    public boolean removePrize(int row, int col) {
        if (gameBoard[row][col] != null) {
            //remove ref to board item (mark it for garbage collection)
			gameBoard[row][col] = null;
			return true;
		} else
			return false;
    }

    /**
     * Used to print the dash (-) separation when drawing the game board
     * @param n indicated whether or not to print a new line before drawing separation
     */
    private void printSeparation(boolean n) {
        if (n)
            System.out.println();
        for(int times = 0; times < (numCols * 4) + 4; times++) //trust the formula :)
            System.out.print("-");
        System.out.println();
    }

    /**
     * Used to display the board. This will include a summary of the number of prizes currently 
     * left on the board.
     */
    public void displayBoard() {
        System.out.print((numRows > 8 ? " " : "")+"  | S |");
        for(int times = 0; times < numCols - 2; times++)
            System.out.print(" > |");
        System.out.println(" F");
        printSeparation(false);

        for(int row = 0; row < numRows; row++) {
            //ternary used to evenly space col index when they become double digits
            System.out.print(row+1+(numRows > 8 && row+1 < 10 ? " " : "")+ " |"); 
            for(int column = 0; column < numCols; column++)
                System.out.print(" "+(gameBoard[row][column] == null ? " " : gameBoard[row][column].getSymbol()) + " |" );
                printSeparation(true);
        }
        System.out.println("Prizes left on board: "+numPrizes);
    } //displayBoard
} //GameBoard
