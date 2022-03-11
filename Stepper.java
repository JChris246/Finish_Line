public class Stepper extends PlayerItem {

    /**Constructor which initializes type to “stepper” and symbol to the
        value of parameter s and isFinished to the appropriate value 
        @param s symbol to set stepper to*/
    public Stepper(char s) {
        setType("stepper");
        setSymbol(s);
        setIsFinished(false);
    }

    /**
     * Based on the direction supplied, this method calculates the destination
     * row & column IF the object were to be moved. It returns this destination 
     * row & column pair in an array. Note: You may assume integer values for directions
     * as follows: 1 (north), 2(south), 3 (east), 4 (west)
     * @param direction cardinal direction represented as an int
     */
    @Override
    public int[] calcDestination(int direction) {
        //clone so u dont edit actual location ref
        int[] coords = getLocation().clone();
        switch(direction) {
            case 1:
                coords[0]--;
                break;
            case 2:
                coords[0]++;
                break;
            case 3:
                coords[1]++;
                break;
            case 4:
                coords[1]--;
                break;
        }
        return coords;
    } //calcDestination
}