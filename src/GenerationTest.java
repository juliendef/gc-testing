import java.util.ArrayList;
import java.util.List;

public class GenerationTest {
    // List to hold long-lived objects (simulating old generation)
    private static final List<Object> oldGeneration = new ArrayList<>();

    // List to hold short-lived objects (simulating young generation)
    private static final List<Object> youngGeneration = new ArrayList<>();

    public static void main(String[] args) {
        int oldGenHoldIterations = 200; // Keep old generation objects for 900 iterations

        for (int i = 0; i < 10000; i++) {
            // Create a mix of long-lived and short-lived objects
            createLongLivedObjects();
            createShortLivedObjects();

            // Periodically clear the young generation list to allow garbage collection
            if (i % 10 == 0) {
                System.out.println("Clearing young generation at iteration: " + i);
                youngGeneration.clear();
                //System.gc(); // Suggest garbage collection
            }

            // Clear old generation objects after certain iterations
            if (i % oldGenHoldIterations == 0) {
                System.out.println("Clearing old generation at iteration: " + i);
                oldGeneration.clear();
                //System.gc(); // Suggest garbage collection
            }

            // Sleep for a short period to slow down the loop
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        System.out.println("Test completed.");
    }

    // Method to create long-lived objects
    private static void createLongLivedObjects() {
        // Add a small number of large objects to old generation
        for (int i = 0; i < 10; i++) {
            oldGeneration.add(new byte[1024 * 1024]); // 1 MB objects
        }
    }

    // Method to create short-lived objects
    private static void createShortLivedObjects() {
        // Add a larger number of small objects to young generation
        for (int i = 0; i < 1000; i++) {
            youngGeneration.add(new byte[1024]); // 1 KB objects
        }
    }
}
