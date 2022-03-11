public class BoardItem {
    /**Contains the type of the item. The descriptor will be a word.*/
    private String type;
    
    /**Contains the character which will be used to represent the creature on the board. */
    private char symbol;

    /**Used to get type of Board Item
     * @return the board item's type as string
    */
    public String getType() {
        return type;
    }

    /**Used to set type of Board Item
     *@param type type to set board item to
    */
    public void setType(String type) {
        this.type = type;
    }

    /**Used to get symbol of Board Item
     * @return the board item's symbol as char
    */
    public char getSymbol() {
        return symbol;
    }

    /**Used to set symbol of Board Item
     * @param symbol symbol to set board item to
    */
    public void setSymbol(char symbol) {
        this.symbol = symbol;
    }
} //BoardItem