public abstract class PlayerItem extends BoardItem {
    /**Array used to hold the row and column location of the object.*/
    private int[] location;

    /**Used to indicate whether the object has reached FINISH or not. */
    private boolean isFinished;

    /**Constructor which initializes location array to the appropriate size. */
    public PlayerItem() {
        location = new int[2];
    }

    /**Used to store the current location of the object in the location
        variable. You may assume the first position is used for row and the
        other for column. 
        @param r row coordinate
        @param c column coordinate
    */
    public void setLocation(int r, int c) {
        location[0] = r;
        location[1] = c;
    }

    /**Returns the location of the object. 
     * @return int[] representing object's coordinates
    */
    public int[] getLocation() {
        return location;
    }

    /**
     * Returns whether or not the object has reached FINISH
     * @return boolean value indicating if the item has reached FINISH
     */
    public boolean getIsFinished() {
        return isFinished;
    }

    /**Uses status to update the isFinished data member.
     * @param status boolean value representing whether object is at finish line or not
     */
    public void setIsFinished(boolean status) {
        isFinished = status;
    }

    /**An abstract method to be defined by sub-classes */
    public abstract int[] calcDestination(int direction);
} 