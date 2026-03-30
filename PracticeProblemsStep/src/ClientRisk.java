import java.util.*;

class Client {
    String name;
    int riskScore;
    double accountBalance;

    Client(String name, int riskScore, double accountBalance) {
        this.name = name;
        this.riskScore = riskScore;
        this.accountBalance = accountBalance;
    }

    public String toString() {
        return name + ":" + riskScore;
    }
}

public class ClientRisk {
    static void bubbleSort(Client[] arr) {
        int n = arr.length;
        int swaps = 0;

        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j].riskScore > arr[j + 1].riskScore) {
                    Client temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swaps++;
                }
            }
        }

        System.out.println("Bubble Sort (Ascending):");
        for (Client c : arr) System.out.print(c + " ");
        System.out.println("\nSwaps: " + swaps);
    }

    static void insertionSort(Client[] arr) {
        int n = arr.length;

        for (int i = 1; i < n; i++) {
            Client key = arr[i];
            int j = i - 1;

            while (j >= 0 && (arr[j].riskScore < key.riskScore ||
                    (arr[j].riskScore == key.riskScore &&
                            arr[j].accountBalance < key.accountBalance))) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }

        System.out.println("Insertion Sort (Descending):");
        for (Client c : arr) System.out.print(c + " ");
        System.out.println();
    }

    static void topClients(Client[] arr) {
        System.out.println("Top Risk Clients:");
        int limit = Math.min(10, arr.length);
        for (int i = 0; i < limit; i++) {
            System.out.println(arr[i].name + "(" + arr[i].riskScore + ")");
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of clients: ");
        int n = sc.nextInt();

        Client[] clients = new Client[n];

        for (int i = 0; i < n; i++) {
            System.out.print("Enter name riskScore accountBalance: ");
            String name = sc.next();
            int risk = sc.nextInt();
            double balance = sc.nextDouble();
            clients[i] = new Client(name, risk, balance);
        }

        Client[] bubbleArr = Arrays.copyOf(clients, n);
        Client[] insertionArr = Arrays.copyOf(clients, n);

        bubbleSort(bubbleArr);
        insertionSort(insertionArr);
        topClients(insertionArr);
    }
}