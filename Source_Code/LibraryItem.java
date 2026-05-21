/**
 * Generic abstract base class for all library items.
 * @param <T> The type of the item identifier (e.g., String, Integer)
 */
public abstract class LibraryItem<T> {
    private final String title;
    private final String author;  // Fixed
    private final T itemID;

    /**
     * Constructs a new LibraryItem.
     * @param title The title of the item
     * @param author The author/creator of the item
     * @param itemID The unique identifier for the item
     */
    public LibraryItem(String title, String author, T itemID) {
        if (title == null || title.isEmpty()) {
            throw new IllegalArgumentException("Title cannot be empty");
        }
        if (itemID == null) {
            throw new IllegalArgumentException("Item ID cannot be null");
        }
        this.title = title;
        this.author = author;
        this.itemID = itemID;
    }

    public String getTitle() { return title; }
    public String getAuthor() { return author; }  // Fixed: Getter name matches field
    public T getItemID() { return itemID; }

    /**
     * Returns the format type of this library item.
     * @return String representation of item format
     */
    public abstract String getFormat();

    @Override
    public String toString() {
        return String.format("[%s] ID: %s | Title: '%s' | Author: %s", 
                             getFormat(), itemID, title, author);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof LibraryItem)) return false;
        LibraryItem<?> other = (LibraryItem<?>) obj;
        return itemID != null && itemID.equals(other.getItemID());
    }

    @Override
    public int hashCode() {
        return itemID != null ? itemID.hashCode() : 0;
    }
}