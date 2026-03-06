import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class EcommerceFlashSale {
    private static ConcurrentHashMap<String, AtomicInteger> inventory = new ConcurrentHashMap<>();
    private static ConcurrentHashMap<String, Queue<String>> waitingList = new ConcurrentHashMap<>();

    public static void main(String[] args) throws InterruptedException {

        // Initialize product with 100 units
        inventory.put("Samsung s23 fe", new AtomicInteger(100));
        waitingList.put("Samsung s23 fe", new ConcurrentLinkedQueue<>());
        int customers = 50000;
        ExecutorService executor = Executors.newFixedThreadPool(100);

        for (int i = 1; i <= customers; i++) {
            String customerId = "Customer-" + i;
            executor.execute(() -> purchaseProduct(customerId, "Samsung s23 fe"));
        }

        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.MINUTES);

        System.out.println("Final Stock: " + checkStock("Samsung s23 fe"));
        System.out.println("Waiting List Size: " + waitingList.get("Samsung s23 fe").size());
    }
    public static void purchaseProduct(String customerId, String productId) {

        AtomicInteger stock = inventory.get(productId);

        if (stock == null) {
            System.out.println("Product not found.");
            return;
        }

        while (true) {
            int currentStock = stock.get();

            if (currentStock <= 0) {
                waitingList.get(productId).add(customerId);
                return;
            }
            if (stock.compareAndSet(currentStock, currentStock - 1)) {
                System.out.println(customerId + " purchased " + productId +
                        " | Remaining Stock: " + (currentStock - 1));
                return;
            }
        }
    }
    public static int checkStock(String productId) {
        AtomicInteger stock = inventory.get(productId);
        return stock != null ? stock.get() : 0;
    }
}
