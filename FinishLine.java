import java.util.Scanner;

public class FinishLine {
    /**Describes the total number of game pieces a player has initially */
    private final static int TOTAL_PIECES;
    
    static {
        TOTAL_PIECES = 3;
    }

    /**
     * Loop until user enters a valid response to creating standard game board prompt
     * @param reader why create a new Scanner object when i can use back the one from main
     * @return if to create a standard game board or not
     */
    private static boolean getBoardType(Scanner reader) {
        boolean valid = false, confirm = false;
        do {
            System.out.print("Do you want to use a standard game board (y/n)?: ");
            String response = reader.nextLine().toLowerCase(); //make response case independent

            switch(response) {
                case "y":
                case "yes": //just in case user try to be smart
                    confirm = true;
                    valid = true;
                    break;
                case "n":
                case "no": //same here as above
                    confirm = false;
                    valid = true;
                    break;
                //no need for default case
            }
        } 
        while(!valid);
        return confirm;
    } //getBoardType

    /**
     * Print the gameboard and player status
     * @param p1 first player
     * @param p2 second player
     * @param board the gameboard
     */
    private static void printBoardAndStatus(Player p1, Player p2, GameBoard board) {
        // display gameboard
        board.displayBoard();

        // display player status
        System.out.println("\nPLAYER 1 STATUS");
        System.out.println(p1.getName() + ": " + p1.getNumFinish() + " Finish; " + (TOTAL_PIECES - p1.getNumFinish()) + " in play;\n");

        System.out.println("PLAYER 2 STATUS");
        System.out.println(p2.getName() + ": " + p2.getNumFinish() + " Finish; " + (TOTAL_PIECES - p2.getNumFinish()) + " in play;\n");
    }

     /**
      * Execute player's turn
      * @param p the current player
      * @param b ref to the game board
      */
    private static byte runPlay(Player p, GameBoard b) {
        if (p.getLoseTurn()) {
            p.setLoseTurn(false);
            System.out.println("Sorry "+p.getName()+" your turn was skipped\n");
        } else {
            System.out.println(p.getName()+" it is your turn");
            b.runPlayerTurn(p);
        }

        if (p.getNumFinish() == TOTAL_PIECES)
            return 0; //gameover
        else if (p.getExtraTurn())
            return 1; //player get extra turn
        else 
            return 2; //continue as normal
         
    } //runPlay

    /**
     * Loop until user enters a valid params for creation of game board...may later put these
     *  as exceptions in gameboard
     * @param reader why create a new Scanner object when i can use back the one from main
     * @return game board with user's prefered settings
     */
    private static GameBoard getCustomGameBoard(Scanner reader) {
        boolean valid = false;
        int rows = 0, columns = 0, prizes = 0, obstacles = 0; //give default value to satisfy jvm

        do {
            //ask for type of board (size)
            System.out.println("Custom board choices\nRows: 6-9\nColumns: 12-15");
            System.out.print("What size board do you want (enter rows followed by columns)?: ");
            rows = reader.nextInt(); 
            columns = reader.nextInt();

            if (rows < 10 && rows > 5 && columns > 11 && columns < 16)
                valid = true;
            else 
                System.out.println("Invalid rows and/or columns entered\n");
        }
        while(!valid);

        valid = false;
        do {
            //get desired number of prizes
            System.out.print("How many prizes should be on the board? (Must be within the range 3-6): ");
            prizes = reader.nextInt();
            
            if (prizes > 2 && prizes < 7)
                valid = true;
            else
                System.out.println("Invalid choice of prizes\n");
        } while(!valid);    
        
        valid = false;
        do {
            //get desired number of obstacles
            System.out.print("How many obstacles should be on the board? (Must be within range 3-9): ");
            obstacles = reader.nextInt();
            reader.nextLine(); // ignore left over newline char

            if (obstacles > 2 && obstacles < 10)
                valid = true;
            else
                System.out.println("Invalid choice of obstacles\n");
        } while(!valid);

        //init gameboard with the user's derired params
        return new GameBoard(rows, columns, obstacles, prizes);
    } //getCustomGameBoard

    public static void main(String args[]) {
        Scanner reader = new Scanner(System.in);

        GameBoard board = getBoardType(reader) ? new GameBoard() : getCustomGameBoard(reader);

        //get names of the players
        System.out.print("Please enter your name Player 1: ");
        Player player1 = new Player(reader.nextLine());

        System.out.print("Please enter your name Player 2: ");
        Player player2 = new Player(reader.nextLine());

        //populate game board
        board.populateBoard();
        board.placePlayerPieces(player1, player2);

        boolean gameOver = false;
        do {
            byte status;

            do {
                //trying to keep things modular
                printBoardAndStatus(player1, player2, board);
                status = runPlay(player1, board);
                gameOver = status == 0;
                if (gameOver)
                    System.out.println("Congratulations "+player1.getName()+", You have won");
                if (status == 1)
                    System.out.println(player1.getName()+" you gained an extra turn");
            } while(status == 1); //1 being extra turn from prize

            if (gameOver)
                break; //if gameover before next player's turn break here
            
            do {
                //trying to keep things modular
                printBoardAndStatus(player1, player2, board);
                status = runPlay(player2, board);
                gameOver = status == 0;
                if (gameOver)
                    System.out.println("Congratulations "+player2.getName()+", You have won");
                if (status == 1)
                    System.out.println(player2.getName()+" you gained an extra turn");
            } while(status == 1); //1 being extra turn from prize
        } while(!gameOver);

        reader.close(); //release scanner object's resources
    } //main
} //FinishLine