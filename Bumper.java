public class Bumper extends PlayerItem 
{

    /**Constructor which initializes type to “bumper” and symbol to the
     * value of parameter s and isFinished to the appropriate value. 
        @param s symbol to set bumper to*/
    public Bumper(char s) {
        setType("bumper");
        setSymbol(s);
        setIsFinished(false);
    }

    /**
     * Based on the direction supplied, this method calculates the destination row & column
     * IF the object were to be moved. It returns this destination row & column pair in an array.
     *  Note: You may assume integer values for directions as follows: 1 (north), 2(south),
     * 3 (east), 4 (west), 5 (northeast), 6 (southeast), 7 (northwest), 8 (southwest)
     * @param direction cardinal direction represented as an int
     */
    @Override
    public int[] calcDestination(int direction) {
        assert(direction >= 1 && direction <= 8);
        //clone so u dont edit actual location ref
        int[] coords = getLocation().clone();
        switch(direction) 
        {
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
            case 5:
                coords[0]--;
                coords[1]++;
                break;
            case 6:
                coords[0]++;
                coords[1]++;
                break;
            case 7:
                coords[0]--;
                coords[1]--;
                break;
            case 8:
                coords[0]++;
                coords[1]--;
                break;
        }
        return coords;
    } //calcDestination
}