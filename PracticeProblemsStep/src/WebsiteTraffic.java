import java.util.*;
public class WebsiteTraffic {
    static HashMap<String, Integer> pageVisits = new HashMap<>();
    static HashMap<String, HashSet<String>> uniqueVisitors = new HashMap<>();
    static HashMap<String, Integer> trafficSource = new HashMap<>();
    public static void processEvent(String page, String userId, String source) {
        pageVisits.put(page, pageVisits.getOrDefault(page, 0) + 1);
        uniqueVisitors.putIfAbsent(page, new HashSet<>());
        uniqueVisitors.get(page).add(userId);
        trafficSource.put(source, trafficSource.getOrDefault(source, 0) + 1);
    }
    public static void showDashboard() {
        System.out.println("\nDashboard");
        System.out.println("Page Visits:");
        for (String page : pageVisits.keySet()) {
            System.out.println(page + " : " + pageVisits.get(page));
        }
        System.out.println("\nUnique Visitors:");
        for (String page : uniqueVisitors.keySet()) {
            System.out.println(page + " : " + uniqueVisitors.get(page).size());
        }
        System.out.println("\nTraffic Sources:");
        for (String src : trafficSource.keySet()) {
            System.out.println(src + " : " + trafficSource.get(src));
        }
    }
    public static void main(String[] args) {
        processEvent("Home", "U1", "Google");
        processEvent("Sports", "U2", "Facebook");
        processEvent("Home", "U3", "Google");
        processEvent("Politics", "U1", "Direct");
        processEvent("Home", "U1", "Google");
        showDashboard();
    }
}