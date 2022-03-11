public class OffBoardException extends Exception {
    /**version id for this serializable class */
    final static long serialVersionUID = 1l;

    /**
     * Initialize OffBoardException with a default message
     */
    public OffBoardException() {
        this("Destination off board!");
    }

    /**
     * Initialize OffBoardException with a message defined by msg
     * @param msg message to use for this exception
     */
    public OffBoardException(String msg) {
        super(msg);
    }
}