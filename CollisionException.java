public class CollisionException extends Exception {
    /**Id for this serializable class */
    final static long serialVersionUID = 1l;

    /**
     * Initialize CollisionException with a default message
     */
    public CollisionException() {
        this("Collision imminent!");
    }

    /**
     * Initialize CollisionException with a message defined by msg
     * @param msg message to use for this exception
     */
    public CollisionException(String msg) {
        super(msg);
    }
} //CollisionException