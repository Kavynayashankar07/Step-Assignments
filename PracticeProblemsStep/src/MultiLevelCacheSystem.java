import java.util.*;

class VideoData {
    String videoId;
    String content;

    VideoData(String videoId, String content) {
        this.videoId = videoId;
        this.content = content;
    }
}

public class MultiLevelCacheSystem {
    private LinkedHashMap<String, VideoData> L1;
    private HashMap<String, VideoData> L2;
    private HashMap<String, VideoData> database;
    private HashMap<String, Integer> accessCount;

    int L1_CAPACITY = 10000;
    int L2_CAPACITY = 100000;

    int L1_hits = 0;
    int L2_hits = 0;
    int DB_hits = 0;

    public MultiLevelCacheSystem () {

        accessCount = new HashMap<>();

        // LinkedHashMap with access order for LRU
        L1 = new LinkedHashMap<String, VideoData>(L1_CAPACITY, 0.75f, true) {
            protected boolean removeEldestEntry(Map.Entry<String, VideoData> eldest) {
                if(size() > L1_CAPACITY) {
                    System.out.println("L1 Evict: " + eldest.getKey());
                    return true;
                }
                return false;
            }
        };

        L2 = new HashMap<>();
        database = new HashMap<>();
    }

    // Fetch video
    public VideoData getVideo(String videoId) {

        // L1 cache check
        if(L1.containsKey(videoId)) {
            L1_hits++;
            System.out.println("L1 HIT: " + videoId);
            return L1.get(videoId);
        }

        // L2 cache check
        if(L2.containsKey(videoId)) {
            L2_hits++;
            System.out.println("L2 HIT: " + videoId);

            VideoData video = L2.get(videoId);

            promoteToL1(video);

            return video;
        }

        // Database fetch
        if(database.containsKey(videoId)) {
            DB_hits++;
            System.out.println("DB HIT: " + videoId);

            VideoData video = database.get(videoId);

            addToL2(video);

            return video;
        }

        return null;
    }

    // Promote video to L1
    private void promoteToL1(VideoData video) {
        L1.put(video.videoId, video);
    }

    // Add to L2 cache
    private void addToL2(VideoData video) {

        if(L2.size() >= L2_CAPACITY) {

            Iterator<String> it = L2.keySet().iterator();
            String evict = it.next();
            it.remove();

            System.out.println("L2 Evict: " + evict);
        }

        L2.put(video.videoId, video);
    }

    // Access tracking
    public void trackAccess(String videoId) {

        int count = accessCount.getOrDefault(videoId, 0) + 1;

        accessCount.put(videoId, count);

        if(count > 5 && L2.containsKey(videoId)) {
            promoteToL1(L2.get(videoId));
        }
    }

    // Cache invalidation
    public void invalidate(String videoId) {

        L1.remove(videoId);
        L2.remove(videoId);
        database.remove(videoId);

        System.out.println("Cache invalidated for " + videoId);
    }

    // Load database
    public void addVideoToDB(String videoId, String content) {
        database.put(videoId, new VideoData(videoId, content));
    }

    // Show hit ratios
    public void printStats() {
        System.out.println("L1 Hits: " + L1_hits);
        System.out.println("L2 Hits: " + L2_hits);
        System.out.println("DB Hits: " + DB_hits);
    }

    public static void main(String[] args) {

        MultiLevelCacheSystem cache = new MultiLevelCacheSystem();

        // Add videos to database
        cache.addVideoToDB("v1", "Movie A");
        cache.addVideoToDB("v2", "Movie B");
        cache.addVideoToDB("v3", "Movie C");

        // Access videos
        cache.getVideo("v1");
        cache.trackAccess("v1");

        cache.getVideo("v1");
        cache.trackAccess("v1");

        cache.getVideo("v2");
        cache.getVideo("v3");

        cache.invalidate("v2");

        cache.printStats();
    }
}