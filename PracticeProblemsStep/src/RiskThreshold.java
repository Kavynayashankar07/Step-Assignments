import java.util.*;

public class RiskThreshold {

    static void linearSearch(int[] arr, int target) {
        int comps = 0;
        boolean found = false;

        for (int i = 0; i < arr.length; i++) {
            comps++;
            if (arr[i] == target) {
                System.out.println("Linear: Found at index " + i);
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Linear: Not found");
        }

        System.out.println("Comparisons: " + comps);
    }

    static void binarySearch(int[] arr, int target) {
        int low = 0, high = arr.length - 1;
        int comps = 0;
        int floor = -1, ceil = -1;

        while (low <= high) {
            comps++;
            int mid = (low + high) / 2;

            if (arr[mid] == target) {
                floor = ceil = arr[mid];
                break;
            } else if (arr[mid] < target) {
                floor = arr[mid];
                low = mid + 1;
            } else {
                ceil = arr[mid];
                high = mid - 1;
            }
        }

        int insertionPoint = low;

        System.out.println("\nBinary Search:");
        System.out.println("Insertion index: " + insertionPoint);
        System.out.println("Floor: " + (floor == -1 ? "None" : floor));
        System.out.println("Ceiling: " + (ceil == -1 ? "None" : ceil));
        System.out.println("Comparisons: " + comps);
        System.out.println("Time Complexity: O(log n)");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of risk bands: ");
        int n = sc.nextInt();

        int[] arr = new int[n];

        System.out.println("Enter risk values:");
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        System.out.print("Enter threshold: ");
        int target = sc.nextInt();

        linearSearch(arr, target);

        Arrays.sort(arr);

        System.out.println("\nSorted Risk Bands:");
        for (int x : arr) System.out.print(x + " ");

        binarySearch(arr, target);
    }
}