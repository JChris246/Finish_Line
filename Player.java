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
        name = n;
        numFinish = 0;
        loseTurn = false;
    }

    /**
     * Method used to increment numFinish by 1.
     */
    public void updateNumFinish() {
        numFinish++;
    }

    /**
     * Method used to set name
     * @param n used to set the player's name
     */
    public void setName(String n) {
        name = n;
    }

    /**
     * Method used to set numFinish
     * @param n used to set the number finished pieces
     */
    public void setNumFinish(int n) {
        numFinish = n;
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
}   