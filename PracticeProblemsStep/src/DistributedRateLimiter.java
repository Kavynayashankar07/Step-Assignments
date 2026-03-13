import java.util.HashMap;

public class DistributedRateLimiter {

    // clientId -> request count
    static HashMap<String, Integer> requestCount = new HashMap<>();

    // clientId -> start time
    static HashMap<String, Long> startTime = new HashMap<>();

    static int LIMIT = 5; // request limit (example)

    public static boolean allowRequest(String clientId) {

        long currentTime = System.currentTimeMillis();

        // first request
        if (!requestCount.containsKey(clientId)) {
            requestCount.put(clientId, 1);
            startTime.put(clientId, currentTime);
            return true;
        }

        long firstTime = startTime.get(clientId);

        // check if 1 hour passed
        if (currentTime - firstTime > 3600000) {
            requestCount.put(clientId, 1);
            startTime.put(clientId, currentTime);
            return true;
        }

        int count = requestCount.get(clientId);

        if (count < LIMIT) {
            requestCount.put(clientId, count + 1);
            return true;
        }

        return false;
    }

    public static void main(String[] args) {

        String client = "Client1";

        for (int i = 1; i <= 7; i++) {

            if (allowRequest(client)) {
                System.out.println("Request " + i + " Allowed");
            } else {
                System.out.println("Request " + i + " Blocked: Rate limit exceeded");
            }
        }
    }
}