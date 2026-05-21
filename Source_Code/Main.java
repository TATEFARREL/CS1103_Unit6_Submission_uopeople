/**
 * Main entry point for the Generic Library Catalog application.
 * Demonstrates generic class usage with multiple item types.
 */
public class Main {
    public static void main(String[] args) {
        // Create generic catalog that works with LibraryItem<String>
        GenericCatalog<LibraryItem<String>, String> catalog = new GenericCatalog<>();
        
        // Initialize and run the user interface
        LibraryCatalogUI ui = new LibraryCatalogUI(catalog);
        ui.run();
    }
}