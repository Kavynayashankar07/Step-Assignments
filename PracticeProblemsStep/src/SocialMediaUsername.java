import java.util.HashMap;
import java.util.Scanner;
public class SocialMediaUsername {

        // username -> userId
        static HashMap<String, Integer> users = new HashMap<>();

        // username -> attempt count
        static HashMap<String, Integer> attempts = new HashMap<>();

        // Check if username exists
        public static boolean isAvailable(String username) {
            return !users.containsKey(username);
        }

        // Suggest similar usernames
        public static void suggestUsername(String username) {
            System.out.println("Username taken. Suggestions:");

            for (int i = 1; i <= 3; i++) {
                String suggestion = username + i;

                if (!users.containsKey(suggestion)) {
                    System.out.println(suggestion);
                }
            }
        }

        // Track how many times username is searched
        public static void trackAttempt(String username) {
            attempts.put(username, attempts.getOrDefault(username, 0) + 1);
        }

        public static void main(String[] args) {

            Scanner sc = new Scanner(System.in);

            // sample existing users
            users.put("john", 1);
            users.put("alex", 2);
            users.put("sam", 3);

            System.out.print("Enter username: ");
            String username = sc.nextLine();

            // track attempt
            trackAttempt(username);

            if (isAvailable(username)) {
                System.out.println("Username is available!");
            } else {
                suggestUsername(username);
            }

            // show attempt count
            System.out.println("Search count: " + attempts.get(username));

            sc.close();
        }
    }


