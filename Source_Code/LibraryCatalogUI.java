import java.util.List;
import java.util.Scanner;

/**
 * Simple command-line interface for interacting with the generic library catalog.
 * Supports adding books, DVDs, magazines; removing items; and viewing the catalog.
 */
public class LibraryCatalogUI {
    private final GenericCatalog<LibraryItem<String>, String> catalog;
    private final Scanner scanner;
    
    public LibraryCatalogUI(GenericCatalog<LibraryItem<String>, String> catalog) {
        this.catalog = catalog;
        this.scanner = new Scanner(System.in);
    }
    
    /**
     * Main execution loop for the CLI.
     */
    public void run() {
        boolean running = true;
        System.out.println("=== Generic Library Catalog System ===\n");
        
        while (running) {
            displayMenu();
            String choice = scanner.nextLine().trim();
            
            try {
                switch (choice) {
                    case "1" -> addBook();
                    case "2" -> addDVD();
                    case "3" -> addMagazine();
                    case "4" -> removeItem();
                    case "5" -> viewCatalog();
                    case "6" -> {
                        running = false;
                        System.out.println("Thank you for using the Library Catalog. Goodbye!");
                    }
                    default -> System.out.println("⚠️  Invalid option. Please select 1-6.");
                }
            } catch (ItemNotFoundException e) {
                System.err.println("❌ Error: " + e.getMessage());
            } catch (IllegalArgumentException e) {
                System.err.println("❌ Input Error: " + e.getMessage());
            } catch (Exception e) {
                System.err.println("❌ Unexpected error: " + e.getMessage());
            }
        }
    }
    
    private void displayMenu() {
        System.out.println("\n--- Main Menu ---");
        System.out.println("[1] Add Book");
        System.out.println("[2] Add DVD");
        System.out.println("[3] Add Magazine");
        System.out.println("[4] Remove Item by ID");
        System.out.println("[5] View All Items");
        System.out.println("[6] Exit");
        System.out.print("\nSelect an option (1-6): ");
    }
    
    private void addBook() {
        System.out.println("\n➕ Add New Book");
        System.out.print("Title: ");
        String title = scanner.nextLine();
        System.out.print("Author: ");
        String author = scanner.nextLine();
        System.out.print("Item ID (String): ");
        String id = scanner.nextLine();
        
        Book<String> book = new Book<>(title, author, id);
        catalog.addItem(book);
        System.out.println("✅ Book added successfully!");
    }
    
    private void addDVD() {
        System.out.println("\n➕ Add New DVD");
        System.out.print("Title: ");
        String title = scanner.nextLine();
        System.out.print("Director: ");
        String director = scanner.nextLine();
        System.out.print("Item ID (String): ");
        String id = scanner.nextLine();
        
        DVD<String> dvd = new DVD<>(title, director, id);
        catalog.addItem(dvd);
        System.out.println("✅ DVD added successfully!");
    }
    
    private void addMagazine() {
        System.out.println("\n➕ Add New Magazine");
        System.out.print("Title: ");
        String title = scanner.nextLine();
        System.out.print("Publisher: ");
        String publisher = scanner.nextLine();
        System.out.print("Item ID (String): ");
        String id = scanner.nextLine();
        System.out.print("Issue Number: ");
        int issue = Integer.parseInt(scanner.nextLine());
        
        Magazine<String> magazine = new Magazine<>(title, publisher, id, issue);
        catalog.addItem(magazine);
        System.out.println("✅ Magazine added successfully!");
    }
    
    private void removeItem() throws ItemNotFoundException {
        System.out.println("\n🗑️  Remove Item");
        System.out.print("Enter Item ID to remove: ");
        String id = scanner.nextLine();
        
        catalog.removeItem(id);
        System.out.println("✅ Item with ID '" + id + "' removed successfully!");
    }
    
    private void viewCatalog() {
        System.out.println("\n📚 Current Catalog (" + catalog.getItemCount() + " items)");
        System.out.println("=".repeat(50));
        
        List<LibraryItem<String>> items = catalog.getAllItems();
        if (items.isEmpty()) {
            System.out.println("📭 Catalog is empty. Add some items!");
            return;
        }
        
        for (LibraryItem<String> item : items) {
            System.out.println(item);
        }
        System.out.println("=".repeat(50));
    }
}