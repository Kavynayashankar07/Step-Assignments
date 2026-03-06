import java.util.HashMap;

public class problem3 {

    // Entry class to store IP and expiry time
    static class Entry {
        String ip;
        long expiryTime;

        Entry(String ip, int ttlSeconds) {
            this.ip = ip;
            this.expiryTime = System.currentTimeMillis() + (ttlSeconds * 1000);
        }
    }

    // Hash table for DNS cache
    static HashMap<String, Entry> cache = new HashMap<>();

    static int hits = 0;
    static int misses = 0;

    // Resolve domain
    public static String resolve(String domain) {

        Entry e = cache.get(domain);

        // Check if entry exists and not expired
        if (e != null && System.currentTimeMillis() < e.expiryTime) {
            hits++;
            return e.ip;
        }

        // Cache miss
        misses++;

        // Simulate DNS lookup
        String ip = "192.168.1." + (int)(Math.random() * 100);

        // Store in cache with TTL = 5 seconds
        cache.put(domain, new Entry(ip, 5));

        return ip;
    }

    public static void main(String[] args) throws Exception {

        System.out.println("google.com -> " + resolve("google.com"));
        System.out.println("google.com -> " + resolve("google.com")); // cache hit

        Thread.sleep(6000); // wait for TTL to expire

        System.out.println("google.com -> " + resolve("google.com")); // cache miss

        System.out.println("Cache Hits: " + hits);
        System.out.println("Cache Misses: " + misses);
    }
}