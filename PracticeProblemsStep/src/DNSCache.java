import java.util.HashMap;
    public class DNSCache {
        static class Entry {
            String ip;
            long expiryTime;

            Entry(String ip, int ttlSeconds) {
                this.ip = ip;
                this.expiryTime = System.currentTimeMillis() + (ttlSeconds * 1000);
            }
        }

        static HashMap<String, Entry> cache = new HashMap<>();

        static int hits = 0;
        static int misses = 0;

        public static String resolve(String domain) {

            Entry e = cache.get(domain);
            if (e != null && System.currentTimeMillis() < e.expiryTime) {
                hits++;
                return e.ip;
            }
            misses++;
            String ip = "192.168.1." + (int) (Math.random() * 100);
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

