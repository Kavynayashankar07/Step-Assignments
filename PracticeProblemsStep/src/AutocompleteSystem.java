import java.util.*;

public class AutocompleteSystem {

    // query -> frequency
    static HashMap<String, Integer> queryCount = new HashMap<>();

    // add or update search query
    public static void addQuery(String query) {
        queryCount.put(query, queryCount.getOrDefault(query, 0) + 1);
    }

    // get suggestions for prefix
    public static void getSuggestions(String prefix) {

        System.out.println("Suggestions:");

        for (String query : queryCount.keySet()) {
            if (query.startsWith(prefix)) {
                System.out.println(query + " (searched " + queryCount.get(query) + " times)");
            }
        }
    }

    public static void main(String[] args) {

        // sample previous searches
        addQuery("java tutorial");
        addQuery("java interview questions");
        addQuery("java tutorial");
        addQuery("javascript basics");
        addQuery("java hashmap example");
        addQuery("python tutorial");

        Scanner sc = new Scanner(System.in);

        System.out.print("Type search prefix: ");
        String prefix = sc.nextLine();

        getSuggestions(prefix);

        sc.close();
    }
}