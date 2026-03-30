import java.util.*;

public class TransactionFeeSorting{

    static void linearSearch(String[] arr, String target) {
        int first = -1, last = -1;
        int comparisons = 0;

        for (int i = 0; i < arr.length; i++) {
            comparisons++;
            if (arr[i].equals(target)) {
                if (first == -1) first = i;
                last = i;
            }
        }

        System.out.println("Linear Search:");
        System.out.println("First occurrence: " + first);
        System.out.println("Last occurrence: " + last);
        System.out.println("Comparisons: " + comparisons);
        System.out.println("Time Complexity: O(n)");
    }

    static int binarySearch(String[] arr, String target, int[] comp) {
        int low = 0, high = arr.length - 1;

        while (low <= high) {
            comp[0]++;
            int mid = (low + high) / 2;

            if (arr[mid].equals(target)) return mid;
            else if (arr[mid].compareTo(target) < 0) low = mid + 1;
            else high = mid - 1;
        }
        return -1;
    }

    static int countOccurrences(String[] arr, String target, int index) {
        if (index == -1) return 0;

        int count = 1;

        int i = index - 1;
        while (i >= 0 && arr[i].equals(target)) {
            count++;
            i--;
        }

        i = index + 1;
        while (i < arr.length && arr[i].equals(target)) {
            count++;
            i++;
        }

        return count;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of logs: ");
        int n = sc.nextInt();

        String[] logs = new String[n];

        System.out.println("Enter account IDs:");
        for (int i = 0; i < n; i++) {
            logs[i] = sc.next();
        }

        System.out.print("Enter target account ID: ");
        String target = sc.next();

        linearSearch(logs, target);

        Arrays.sort(logs);

        int[] comp = {0};
        int index = binarySearch(logs, target, comp);
        int count = countOccurrences(logs, target, index);

        System.out.println("\nBinary Search (on sorted data):");
        System.out.println("Found at index: " + index);
        System.out.println("Comparisons: " + comp[0]);
        System.out.println("Count occurrences: " + count);
        System.out.println("Time Complexity: O(log n)");
    }
}