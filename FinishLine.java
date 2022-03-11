import java.util.Scanner;

public class FinishLine {
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
            System.out.print("What size board do you want (enter rows followed by columns)?: ");
            rows = reader.nextInt(); 
            columns = reader.nextInt();

            /*must have at least 2 columns (1 for start other for finish)
            and at least 1 column for the game board to make sense*/
            if (rows < 1 || columns  < 2) {
                System.out.println("These values cannot create a valid game board");
                continue;
            }

            //get desired number prizes
            System.out.print("How many prizes should be on the board?: ");
            prizes = reader.nextInt();

            //get desired number obstacles
            System.out.print("How many obstacles should be on the board?: ");
            obstacles = reader.nextInt();
            reader.nextLine(); // ignore left over newline char

            /*if num of prizes + num of obstacles is more than valid cells (cells other than
            first and last column (rows * 2)) avaliable for them, program will hang trying to find
            a spot for them in vain.*/
            if ((prizes + obstacles) > ((rows * columns) - rows * 2))
                System.out.println("This combination of prizes and obstacles is more than can fit on the custom game board!");
            else 
                valid = true;
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

        //display gameboard
        board.displayBoard();

        //display player status
        System.out.println("\nPLAYER 1 STATUS");
        System.out.println(player1.getName()+": "+player1.getNumFinish()+" Finish;\n");    

        System.out.println("PLAYER 2 STATUS");
        System.out.println(player2.getName()+": "+player2.getNumFinish()+" Finish;");

        reader.close(); //release scanner object's resources
    } //main
} //FinishLine