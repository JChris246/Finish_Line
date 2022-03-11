import java.util.Random;
import java.util.Scanner;

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

        Random randNum = new Random();
        while (occupied) { 
            column = randNum.nextInt(numCols - 2) + 1; //exclude start and finish cols
            row = randNum.nextInt(numRows); 
            
            if (gameBoard[row][column] == null)
                occupied = false;
        }
        
        return new int[] {row, column};
    } //getValidCell

    /**
     * get a valid cell at random (for player item)
     * @return return coords of the cell as an int array
     */
    private int[] getValidPlayerCell() {
        boolean occupied = true;
        //column always 0 to stay in the start column
        int column = 0, row = 0; //default value to satisfy jvm

        Random randNum = new Random();
        while (occupied) { 
            row = randNum.nextInt(numRows); 
            
            if (gameBoard[row][column] == null)
                occupied = false;
        }
        
        return new int[] {row, column};
    } //getValidPlayerCell

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
    private void addBoardItem(BoardItem item) {
        int[] coord = item instanceof PlayerItem ? getValidPlayerCell() : getValidCell();
        gameBoard[coord[0]][coord[1]] = item;
        
        if (item instanceof PlayerItem)
            ((PlayerItem)item).setLocation(coord[0], coord[1]);
    }

    /**
     * Used to remove the prize located at the position indicated by row and col. 
     * Returns true once the operation was successful.
     * @param row row to locate prize
     * @param col col to locate prize
     * @return boolean value for success
     */
    private boolean removePrize(int row, int col) {
        if (gameBoard[row][col] != null && gameBoard[row][col] instanceof Prize) {
            //remove ref to board item (mark it for garbage collection)
            gameBoard[row][col] = null;
            numPrizes--;
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

    /**
     * Method to initialize game pieces for players and add them to the board
     * @param p1 reference to player 1
     * @param p2 reference to player 2
     */
    public void placePlayerPieces(Player p1, Player p2) {
        //create 3 pieces for Player 1 using UPPERCASE symbols
        //insert code to add these pieces to player 1
        p1.setPieces(new Stepper('S'), new Zigzagger('Z'), new Bumper('B'));

        //insert code to add each piece to the board.
        for(byte i = 1; i < 4; i++)
            addBoardItem(p1.getPiece(i));
        
        //create 3 pieces for Player 2 using lowercase symbols
        //insert code to add these pieces to player 2
        p2.setPieces(new Stepper('s'), new Zigzagger('z'), new Bumper('b'));

        //insert code to add each piece to the board.
        for(byte i = 1; i < 4; i++)
            addBoardItem(p2.getPiece(i));
    }//placePlayerPieces

    /**displays a message with the appropriate command instructions
     * for moving the player piece indicated via the piece parameter, 
     * where 1 = stepper, 2 = zigzagger. 3 = bumper. RECALL: pieces have different
     * movements which they can perform therefore, you should ONLY display instructions
     * related to the type of piece passed to this method. 
     * RECALL: 1 (north), 2(south), 3 (east), 4 (west), 5 (northeast),
     * 6 (southeast), 7 (northwest), 8 (southwest)*/
    private void displayMovementOptions(int piece) {
        switch(piece) {
            case 1:
                System.out.println("You may move in directions: North (1), South (2), East (3) or West (4)");
                break;
            case 2:
                System.out.println("You may move in directions: Northeast (5), Southeast (6), Northwest (7) or Southwest (8)");
                break;
            case 3:
                System.out.println("You may move in any direction: North (1), South (2), East (3), West (4), Northeast (5), Southeast (6), Northwest (7) or Southwest (8)");
                break;
        }
    }// displayMovementOptions

    /**If the destination is off the edge of the board,
     * throw an OffBoardException exception with the message "Re-enter or die!"
     * If the destination contains an Obstacle, throw a CollisionException
     * exception with the message “Obstacle at destination”.
     * If the destination contains a player piece,
     * throw a CollisionException exception with the message “Piece at destination”.
     * Otherwise, this is a valid move. */
    private boolean validateMove(int row, int col) throws OffBoardException, CollisionException {
        if (row >= numRows || col >= numCols || col < 0 || row < 0) 
            throw new OffBoardException("Re-enter or die!");
        else if (gameBoard[row][col] instanceof Obstacle)
            throw new CollisionException("Obstacle at destination");
        else if (gameBoard[row][col] instanceof PlayerItem)
            throw new CollisionException("Piece at destination");
        else
            return true;
    }//validateMove

    /**
     * Allow user the player to enter a piece to move and validate the choice
     * @param p The current player
     * @param reader reader for user input
     * @return a validated user choice
     */
    private int getPlayerChoice(Player p, Scanner reader) {
        StringBuilder choices = new StringBuilder();
        for (byte i = 1; i < 4; i++)
            choices.append(p.getPiece(i).getIsFinished() ? "" : p.getPiece(i).getType() + " (" + i + ")   ");

        int numChoice = 0;

        do {
            System.out.println("\nWhich piece do you want to move "+p.getName()+"?");
            System.out.print(choices+"\n?: ");
    
            String choice = reader.nextLine();
            if (choice.equals("1") && !p.isPieceFinished(1))
                numChoice = 1;
            else if (choice.equals("2") && !p.isPieceFinished(2))
                numChoice = 2;
            else if (choice.equals("3") && !p.isPieceFinished(3))
                numChoice = 3;
            else {
                System.out.println("Sorry that is an invalid choice ... Please try again");
                numChoice = 0;
            }
        } while(numChoice == 0);

        assert(numChoice < 4 && numChoice > 0);
        return numChoice;
    } //getPlayerChoice

    /**
     * allow user to enter a direction to move in and validate it
     * @param type type of player item to get movement for
     * @param reader reader for user input
     * @return a validated move inputted by the user
     */
    private int getMove(int type, Scanner reader) {
        final int MIN = type == 1 || type == 3 ? 1 : 5;
        final int MAX = type == 2 || type == 3 ? 8 : 4;
        int input = -1;

        boolean valid = false;
        do {
            System.out.print("?: ");
            input = reader.nextInt();

            if (input <= MAX && input >= MIN)
                valid = true;
            else 
                System.out.println("This is not a valid move....Please try again");
        } while (!valid);

        return input; 
    } //getMove

    /**
     * Execute the current player's turn
     * @param p the current player
     */
    public void runPlayerTurn(Player p) {
        Scanner reader = new Scanner(System.in);
        // display instructions for selecting piece to move
        // instructions should only be displayed for pieces which are NOT FINISHED.
        // RECALL: 1 = stepper, 2 = zigzagger. 3 = bumper.

        int pieceChoice = getPlayerChoice(p, reader);

        // create a reference to the piece the player wants to move
        // HINT: player contains a getPiece method
        PlayerItem item = p.getPiece(pieceChoice);

        boolean reenter = false; //probably not the best choice for this particular var
        do {
            //display the movement options based on type of piece they have selected
            displayMovementOptions(pieceChoice);
            int moveChoice = getMove(pieceChoice, reader);

            //calculate destination for the piece based on the direction of movement selected.
            int[] destination = item.calcDestination(moveChoice);

            //validate the move; a valid move would result in the piece being moved.
            //If a CollisionException was thrown for a collision with an obstacle,
            //set the player to lose a turn. (NOTE: the player will not move on this round.)
            //If a CollisionException was thrown for a collision with another player piece,
            //the player should be asked and allowed to re-enter new movement.
            //HINT: movement is recorded in two places: the piece and the gameboard array
            try {
                //no real reason to catch this value ... but
                boolean valid = validateMove(destination[0], destination[1]);
                reenter = true;

                //if location had a prize make the play have an extra turn
                p.setExtraTurn(removePrize(destination[0], destination[1]));

                //remove item from position
                gameBoard[item.getLocation()[0]][item.getLocation()[1]] = null;

                //update new position on board and in item
                gameBoard[destination[0]][destination[1]] = item;
                item.setLocation(destination[0], destination[1]);
                if (destination[1] == numCols - 1) {
                    item.setIsFinished(true);
                    p.updateNumFinish();
                }
            } catch (OffBoardException e) {
                System.out.println(e.getMessage());
                reenter = false;
            } catch (CollisionException e) {
                if (gameBoard[destination[0]][destination[1]] instanceof PlayerItem) {
                    System.out.println(e.getMessage()+", Please reenter a destination");
                    reenter = false;
                } else { //assume item has to be an obstacle (if not player) 
                    System.out.println("Oops you hit an obstacle");
                    p.setLoseTurn(true);
                    reenter = true;
                }
            }
        } while(!reenter); 
    }//runPlayerTurn
} //GameBoard