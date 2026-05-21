// File: Book.java
/**
 * Represents a Book library item.
 * @param <T> The type of the item identifier
 */
public class Book<T> extends LibraryItem<T> {
    public Book(String title, String author, T itemID) {
        super(title, author, itemID);
    }

    @Override
    public String getFormat() {
        return "Book";
    }
}