public class Player {
    /**Contains the name of the player This will be a single word*/
    private String name;

    /**Contains the number of game pieces the player has successfully gotten to FINISH.*/
    private int numFinish;

    /**Used to indicate whether or not the player should lose a turn on the next round*/
    private boolean loseTurn;

    /**Used to indicate whether or not the player should get an extra turn on the next round*/
    private boolean extraTurn;

    /**player's stepper item */
    private Stepper steppy;

    /**player's zigzagger item */
    private Zigzagger ziggy;

    /**player's bumper item */
    private Bumper bumpy;

    /** 
     * Constructor used to initialize name to n; numFinish to 0; loseTurn to false
     * @param n Used to set the player's name
    */
    public Player(String n) {
        setName(n);
        setNumFinish(0);
        setLoseTurn(false);
        setExtraTurn(false);
    }

    /**
     * Method used to increment numFinish by 1.
     */
    public void updateNumFinish() {
        numFinish++;
    }

    /**
     * Method used to set name. If name is empty / null default name to Player. 
     * If name contains spaces first word in string will be used as name
     * @param n used to set the player's name
     */
    public void setName(String n) {
        name = n == null || n.isEmpty() ? "Player" : n.split(" ")[0]; 
    }

    /**
     * Method used to set numFinish. If n is below 0, will default to 0
     * @param num used to set the number finished pieces
     */
    public void setNumFinish(int num) {
        numFinish = num < 0 ? 0 : num;
    }

    /**
     * Method used to set loseTurn
     * @param l used to set the loseTurn's value
     */
    public void setLoseTurn(boolean l) {
        loseTurn = l;
    }

    /**
     * Method used to set extraTurn
     * @param e used to set the extraTurn's value
     */
    public void setExtraTurn(boolean e) {
        extraTurn = e;
    }

    /**
     * Method used to get name
     * @return the player's name
     */
    public String getName() {
        return name;
    }

    /**
     * Method used to get numFinish
     * @return number of finished pieces
     */
    public int getNumFinish() {
        return numFinish;
    }

    /**
     * Method used to get loseTurn
     * @return if to lose a turn
     */
    public boolean getLoseTurn() {
        return loseTurn;
    }

    /**
     * Method used to get extraTurn
     * @return if to get an extra turn
     */
    public boolean getExtraTurn() {
        return extraTurn;
    }

    /**Method used to instantiate the player’s pieces.
     * @param s Stepper to initialize player's stepper to
     * @param z Zigzagger to initialize player's zigzagger to
     * @param b Bumper to initialize player's bumper to
     */
    public void setPieces(Stepper s, Zigzagger z, Bumper b) {
        steppy = s;
        ziggy = z;
        bumpy = b;
    }

    /**Method used to set the isFinished data member of player’s piece 
     * (indicated by the parameter) to true. 
     * @param piece piece to set as finished*/
    public void setPieceFinished(PlayerItem piece) {
        piece.setIsFinished(true);
    }

    /**
     * Returns whether or not the player’s piece (indicated by the
     * parameter) is finished. Values for parameter are 1 (stepper), 2 (zigzagger) or 3 (bumper).
    */
    public boolean isPieceFinished(int item) {
        switch(item) {
            case 1:
                return steppy.getIsFinished();
            case 2:
                return ziggy.getIsFinished();
            case 3:
            default:
                return bumpy.getIsFinished();
        }
    }

    /**Returns a reference to the player’s piece (indicated by the 
     * parameter). Values for parameter are 1 (stepper), 2 (zigzagger) or 3 (bumper). */
    public PlayerItem getPiece(int piece) {
        switch(piece) {
            case 1:
                return steppy;
            case 2:
                return ziggy;
            case 3:
            default:
                return bumpy;
        }
    }
} //Player