// File: DVD.java
/**
 * Represents a DVD library item.
 * @param <T> The type of the item identifier
 */
public class DVD<T> extends LibraryItem<T> {
    public DVD(String title, String director, T itemID) {
        super(title, director, itemID);  // Director stored as "author" per base class
    }

    @Override
    public String getFormat() {
        return "DVD";
    }
}