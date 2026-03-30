import java.util.*;

class Transaction {
    String id;
    double fee;
    String timestamp;

    Transaction(String id, double fee, String timestamp) {
        this.id = id;
        this.fee = fee;
        this.timestamp = timestamp;
    }

    public String toString() {
        return id + ":" + fee + "@" + timestamp;
    }
}

public class TransactionFeeSorting {
    static void bubbleSort(ArrayList<Transaction> list) {
        int n = list.size();
        int passes = 0, swaps = 0;

        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false;
            passes++;
            for (int j = 0; j < n - i - 1; j++) {
                if (list.get(j).fee > list.get(j + 1).fee) {
                    Transaction temp = list.get(j);
                    list.set(j, list.get(j + 1));
                    list.set(j + 1, temp);
                    swaps++;
                    swapped = true;
                }
            }
            if (!swapped) break;
        }

        System.out.println("Bubble Sort Result:");
        for (Transaction t : list) System.out.print(t + " ");
        System.out.println("\nPasses: " + passes + " Swaps: " + swaps);
    }

    static void insertionSort(ArrayList<Transaction> list) {
        int n = list.size();

        for (int i = 1; i < n; i++) {
            Transaction key = list.get(i);
            int j = i - 1;

            while (j >= 0 && (list.get(j).fee > key.fee ||
                    (list.get(j).fee == key.fee &&
                            list.get(j).timestamp.compareTo(key.timestamp) > 0))) {
                list.set(j + 1, list.get(j));
                j--;
            }
            list.set(j + 1, key);
        }

        System.out.println("Insertion Sort Result:");
        for (Transaction t : list) System.out.print(t + " ");
        System.out.println();
    }

    static void findOutliers(ArrayList<Transaction> list) {
        System.out.println("High-fee Outliers (>50):");
        boolean found = false;
        for (Transaction t : list) {
            if (t.fee > 50) {
                System.out.println(t);
                found = true;
            }
        }
        if (!found) System.out.println("None");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of transactions: ");
        int n = sc.nextInt();

        ArrayList<Transaction> transactions = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            System.out.print("Enter id fee timestamp: ");
            String id = sc.next();
            double fee = sc.nextDouble();
            String ts = sc.next();
            transactions.add(new Transaction(id, fee, ts));
        }

        ArrayList<Transaction> bubbleList = new ArrayList<>(transactions);
        ArrayList<Transaction> insertionList = new ArrayList<>(transactions);

        if (n <= 100) {
            bubbleSort(bubbleList);
        } else if (n <= 1000) {
            insertionSort(insertionList);
        } else {
            System.out.println("Large dataset - Use advanced sorting");
        }

        findOutliers(transactions);
    }
}