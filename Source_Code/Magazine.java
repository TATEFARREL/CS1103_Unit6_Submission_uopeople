// File: Magazine.java
/**
 * Represents a Magazine library item.
 * @param <T> The type of the item identifier
 */
public class Magazine<T> extends LibraryItem<T> {
    private final int issueNumber;
    
    public Magazine(String title, String publisher, T itemID, int issueNumber) {
        super(title, publisher, itemID);
        this.issueNumber = issueNumber;
    }

    public int getIssueNumber() {
        return issueNumber;
    }

    @Override
    public String getFormat() {
        return "Magazine";
    }

    @Override
    public String toString() {
        return String.format("[%s] ID: %s | Title: '%s' | Publisher: %s | Issue: #%d", 
                             getFormat(), getItemID(), getTitle(), getAuthor(), issueNumber);
    }
}