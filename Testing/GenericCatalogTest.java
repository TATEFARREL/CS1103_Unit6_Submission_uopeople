import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Comprehensive JUnit 5 tests for GenericCatalog and LibraryItem classes.
 * Tests generic functionality, operations, and error handling.
 */
class GenericCatalogTest {
    
    private GenericCatalog<LibraryItem<String>, String> catalog;
    
    @BeforeEach
    void setUp() {
        catalog = new GenericCatalog<>();
    }
    
    @Test
    @DisplayName("Test: Add Book to Catalog")
    void testAddBook() {
        Book<String> book = new Book<>("Clean Code", "Robert Martin", "BK001");
        catalog.addItem(book);
        
        assertEquals(1, catalog.getItemCount());
        assertNotNull(catalog.getItemDetails("BK001"));
        assertEquals("Clean Code", catalog.getItemDetails("BK001").getTitle());
    }
    
    @Test
    @DisplayName("Test: Add DVD to Catalog")
    void testAddDVD() {
        DVD<String> dvd = new DVD<>("Inception", "Christopher Nolan", "DVD001");
        catalog.addItem(dvd);
        
        assertEquals(1, catalog.getItemCount());
        assertEquals("DVD", catalog.getItemDetails("DVD001").getFormat());
    }
    
    @Test
    @DisplayName("Test: Add Magazine to Catalog")
    void testAddMagazine() {
        Magazine<String> magazine = new Magazine<>("National Geographic", "NG Society", "MAG001", 42);
        catalog.addItem(magazine);
        
        assertEquals(1, catalog.getItemCount());
        assertEquals(42, ((Magazine<String>) catalog.getItemDetails("MAG001")).getIssueNumber());
    }
    
    @Test
    @DisplayName("Test: Remove Existing Item")
    void testRemoveExistingItem() throws ItemNotFoundException {
        Book<String> book = new Book<>("Effective Java", "Joshua Bloch", "BK002");
        catalog.addItem(book);
        
        assertTrue(catalog.removeItem("BK002"));
        assertEquals(0, catalog.getItemCount());
        assertNull(catalog.getItemDetails("BK002"));
    }
    
    @Test
    @DisplayName("Test: Remove Non-Existent Item Throws Exception")
    void testRemoveNonExistentItem() {
        assertThrows(ItemNotFoundException.class, () -> {
            catalog.removeItem("NONEXISTENT");
        });
    }
    
    @Test
    @DisplayName("Test: Add Duplicate Item Throws Exception")
    void testAddDuplicateItem() {
        Book<String> book1 = new Book<>("Test Book", "Author", "DUP001");
        Book<String> book2 = new Book<>("Another Book", "Author", "DUP001");
        
        catalog.addItem(book1);
        
        assertThrows(IllegalArgumentException.class, () -> {
            catalog.addItem(book2);
        });
    }
    
    @Test
    @DisplayName("Test: Add Null Item Throws Exception")
    void testAddNullItem() {
        assertThrows(IllegalArgumentException.class, () -> {
            catalog.addItem(null);
        });
    }
    
    @Test
    @DisplayName("Test: GetAllItems Returns Unmodifiable List")
    void testGetAllItemsUnmodifiable() {
        catalog.addItem(new Book<>("Test", "Author", "T001"));
        
        assertThrows(UnsupportedOperationException.class, () -> {
            catalog.getAllItems().add(new Book<>("Hacker", "Bad", "H001"));
        });
    }
    
    @Test
    @DisplayName("Test: Catalog Works with Multiple Item Types")
    void testMultipleItemTypes() {
        catalog.addItem(new Book<>("Book Title", "Book Author", "B001"));
        catalog.addItem(new DVD<>("DVD Title", "Director", "D001"));
        catalog.addItem(new Magazine<>("Mag Title", "Publisher", "M001", 10));
        
        assertEquals(3, catalog.getItemCount());
        assertEquals("Book", catalog.getItemDetails("B001").getFormat());
        assertEquals("DVD", catalog.getItemDetails("D001").getFormat());
        assertEquals("Magazine", catalog.getItemDetails("M001").getFormat());
    }
}