import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

/**
 * Simple test runner for environments without IDE test support.
 */
public class RunTests {
    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(GenericCatalogTest.class);
        
        System.out.println("\n=== Test Results ===");
        System.out.println("Tests run: " + result.getRunCount());
        System.out.println("Failures: " + result.getFailureCount());
        System.out.println("Ignored: " + result.getIgnoreCount());
        
        if (result.wasSuccessful()) {
            System.out.println("✅ All tests passed!");
        } else {
            System.out.println("❌ Test failures:");
            for (Failure failure : result.getFailures()) {
                System.out.println("  - " + failure.toString());
            }
        }
    }
}