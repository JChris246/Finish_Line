public class Player {
    /**Contains the name of the player This will be a single word*/
    private String name;

    /**Contains the number of game pieces the player has successfully gotten to FINISH.*/
    private int numFinish;

    /**Used to indicate whether or not the player should lose a turn on the next round*/
    boolean loseTurn;

    /** 
     * Constructor used to initialize name to n; numFinish to 0; loseTurn to false
     * @param n Used to set the player's name
    */
    public Player(String n) {
        setName(n);
        setNumFinish(0);
        setLoseTurn(false);
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
     * Method used to get numFinish
     * @return if to lose a turn
     */
    public boolean getLoseTurn() {
        return loseTurn;
    }
} //Player