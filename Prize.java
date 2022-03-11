public class Prize extends BoardItem {
    /**Indicates the value of this prize.*/
    private int prizeValue;

    /**Constructor used to initializes type to “prize” and symbol to ‘P’. Sets prizeValue to the value to 2. */
    public Prize() {
        setType("prize");
        setSymbol('P');
        setPrizeValue(2);
    }

    /**
     * Get this prize's value
     * @return int representing the prize's value
     */
    public int getPrizeValue() {
        return prizeValue;
    }

    /**
     * Set this prize's value
     * @param prizeValue value to set this prize to
     */
    public void setPrizeValue(int prizeValue) {
        this.prizeValue = prizeValue;
    }
}