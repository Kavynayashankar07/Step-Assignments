import java.util.*;

class Trade {
    String id;
    int volume;

    Trade(String id, int volume) {
        this.id = id;
        this.volume = volume;
    }

    public String toString() {
        return id + ":" + volume;
    }
}

public class VolumeAnalysis{

    static void mergeSort(Trade[] arr, int l, int r) {
        if (l < r) {
            int m = (l + r) / 2;
            mergeSort(arr, l, m);
            mergeSort(arr, m + 1, r);
            merge(arr, l, m, r);
        }
    }

    static void merge(Trade[] arr, int l, int m, int r) {
        int n1 = m - l + 1;
        int n2 = r - m;

        Trade[] left = new Trade[n1];
        Trade[] right = new Trade[n2];

        for (int i = 0; i < n1; i++) left[i] = arr[l + i];
        for (int j = 0; j < n2; j++) right[j] = arr[m + 1 + j];

        int i = 0, j = 0, k = l;

        while (i < n1 && j < n2) {
            if (left[i].volume <= right[j].volume) {
                arr[k++] = left[i++];
            } else {
                arr[k++] = right[j++];
            }
        }

        while (i < n1) arr[k++] = left[i++];
        while (j < n2) arr[k++] = right[j++];
    }

    static void quickSort(Trade[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    static int partition(Trade[] arr, int low, int high) {
        int pivot = arr[high].volume;
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (arr[j].volume > pivot) {
                i++;
                Trade temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        Trade temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;

        return i + 1;
    }

    static Trade[] mergeTwoSorted(Trade[] a, Trade[] b) {
        int n1 = a.length, n2 = b.length;
        Trade[] result = new Trade[n1 + n2];

        int i = 0, j = 0, k = 0;

        while (i < n1 && j < n2) {
            if (a[i].volume <= b[j].volume) {
                result[k++] = a[i++];
            } else {
                result[k++] = b[j++];
            }
        }

        while (i < n1) result[k++] = a[i++];
        while (j < n2) result[k++] = b[j++];

        return result;
    }

    static int totalVolume(Trade[] arr) {
        int sum = 0;
        for (Trade t : arr) sum += t.volume;
        return sum;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of trades: ");
        int n = sc.nextInt();

        Trade[] trades = new Trade[n];

        for (int i = 0; i < n; i++) {
            System.out.print("Enter id volume: ");
            String id = sc.next();
            int vol = sc.nextInt();
            trades[i] = new Trade(id, vol);
        }

        Trade[] mergeArr = Arrays.copyOf(trades, n);
        Trade[] quickArr = Arrays.copyOf(trades, n);

        mergeSort(mergeArr, 0, n - 1);
        System.out.println("Merge Sort (Ascending):");
        for (Trade t : mergeArr) System.out.print(t + " ");
        System.out.println();

        quickSort(quickArr, 0, n - 1);
        System.out.println("Quick Sort (Descending):");
        for (Trade t : quickArr) System.out.print(t + " ");
        System.out.println();

        System.out.print("Enter morning session size: ");
        int m = sc.nextInt();
        Trade[] morning = new Trade[m];
        for (int i = 0; i < m; i++) {
            System.out.print("Enter id volume: ");
            morning[i] = new Trade(sc.next(), sc.nextInt());
        }

        System.out.print("Enter afternoon session size: ");
        int p = sc.nextInt();
        Trade[] afternoon = new Trade[p];
        for (int i = 0; i < p; i++) {
            System.out.print("Enter id volume: ");
            afternoon[i] = new Trade(sc.next(), sc.nextInt());
        }

        mergeSort(morning, 0, m - 1);
        mergeSort(afternoon, 0, p - 1);

        Trade[] merged = mergeTwoSorted(morning, afternoon);

        System.out.println("Merged Sorted Trades:");
        for (Trade t : merged) System.out.print(t + " ");
        System.out.println();

        System.out.println("Total Volume: " + totalVolume(merged));
    }
}