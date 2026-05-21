import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Generic catalog class that can store and manage different types of library items.
 * Implements add, remove, and retrieve operations with proper error handling.
 * @param <T> The type of LibraryItem this catalog will store
 * @param <ID> The type of identifier used for items
 */
public class GenericCatalog<T extends LibraryItem<ID>, ID> {
    private final List<T> items;
    
    /**
     * Constructs an empty generic catalog.
     */
    public GenericCatalog() {
        this.items = new ArrayList<>();
    }
    
    /**
     * Adds a new library item to the catalog.
     * @param item The item to add (must not be null)
     * @throws IllegalArgumentException if item is null or already exists
     */
    public void addItem(T item) {
        if (item == null) {
            throw new IllegalArgumentException("Cannot add null item to catalog");
        }
        if (getItemById(item.getItemID()) != null) {
            throw new IllegalArgumentException("Item with ID '" + item.getItemID() + "' already exists");
        }
        items.add(item);
    }
    
    /**
     * Removes an item from the catalog by its ID.
     * @param id The ID of the item to remove
     * @return true if item was found and removed, false otherwise
     * @throws ItemNotFoundException if no item with the given ID exists
     */
    public boolean removeItem(ID id) throws ItemNotFoundException {
        T itemToRemove = getItemById(id);
        if (itemToRemove == null) {
            throw new ItemNotFoundException("Cannot remove: No item found with ID '" + id + "'");
        }
        return items.remove(itemToRemove);
    }
    
    /**
     * Retrieves details of a specific item by its ID.
     * @param id The ID of the item to retrieve
     * @return The LibraryItem if found, null otherwise
     */
    public T getItemDetails(ID id) {
        return getItemById(id);
    }
    
    /**
     * Returns all items currently in the catalog.
     * @return Unmodifiable list of catalog items
     */
    public List<T> getAllItems() {
        return Collections.unmodifiableList(items);
    }
    
    /**
     * Returns the total number of items in the catalog.
     * @return Item count
     */
    public int getItemCount() {
        return items.size();
    }
    
    /**
     * Helper method to find an item by ID.
     * @param id The ID to search for
     * @return The matching LibraryItem or null if not found
     */
    private T getItemById(ID id) {
        for (T item : items) {
            if (item.getItemID().equals(id)) {
                return item;
            }
        }
        return null;
    }
}