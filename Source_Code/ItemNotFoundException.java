/**
 * Custom exception for handling scenarios where a library item is not found.
 */
public class ItemNotFoundException extends Exception {
    
    public ItemNotFoundException(String message) {
        super(message);
    }
    
    public ItemNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}