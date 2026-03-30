import java.util.*;

class Asset {
    String name;
    double returnRate;
    double volatility;

    Asset(String name, double returnRate, double volatility) {
        this.name = name;
        this.returnRate = returnRate;
        this.volatility = volatility;
    }

    public String toString() {
        return name + ":" + returnRate + "%";
    }
}

public class PortfolioReturnSorting{

    static void mergeSort(Asset[] arr, int l, int r) {
        if (l < r) {
            int m = (l + r) / 2;
            mergeSort(arr, l, m);
            mergeSort(arr, m + 1, r);
            merge(arr, l, m, r);
        }
    }

    static void merge(Asset[] arr, int l, int m, int r) {
        int n1 = m - l + 1;
        int n2 = r - m;

        Asset[] left = new Asset[n1];
        Asset[] right = new Asset[n2];

        for (int i = 0; i < n1; i++) left[i] = arr[l + i];
        for (int j = 0; j < n2; j++) right[j] = arr[m + 1 + j];

        int i = 0, j = 0, k = l;

        while (i < n1 && j < n2) {
            if (left[i].returnRate <= right[j].returnRate) {
                arr[k++] = left[i++];
            } else {
                arr[k++] = right[j++];
            }
        }

        while (i < n1) arr[k++] = left[i++];
        while (j < n2) arr[k++] = right[j++];
    }

    static void insertionSort(Asset[] arr, int low, int high) {
        for (int i = low + 1; i <= high; i++) {
            Asset key = arr[i];
            int j = i - 1;

            while (j >= low && (arr[j].returnRate < key.returnRate ||
                    (arr[j].returnRate == key.returnRate &&
                            arr[j].volatility > key.volatility))) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }

    static int medianOf3(Asset[] arr, int low, int high) {
        int mid = (low + high) / 2;

        if (arr[low].returnRate > arr[mid].returnRate) swap(arr, low, mid);
        if (arr[low].returnRate > arr[high].returnRate) swap(arr, low, high);
        if (arr[mid].returnRate > arr[high].returnRate) swap(arr, mid, high);

        return mid;
    }

    static void quickSort(Asset[] arr, int low, int high) {
        if (high - low < 10) {
            insertionSort(arr, low, high);
            return;
        }

        if (low < high) {
            int pivotIndex = medianOf3(arr, low, high);
            swap(arr, pivotIndex, high);

            int pi = partition(arr, low, high);

            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    static int partition(Asset[] arr, int low, int high) {
        Asset pivot = arr[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (arr[j].returnRate > pivot.returnRate ||
                    (arr[j].returnRate == pivot.returnRate &&
                            arr[j].volatility < pivot.volatility)) {
                i++;
                swap(arr, i, j);
            }
        }

        swap(arr, i + 1, high);
        return i + 1;
    }

    static void swap(Asset[] arr, int i, int j) {
        Asset temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of assets: ");
        int n = sc.nextInt();

        Asset[] assets = new Asset[n];

        for (int i = 0; i < n; i++) {
            System.out.print("Enter name returnRate volatility: ");
            String name = sc.next();
            double rate = sc.nextDouble();
            double vol = sc.nextDouble();
            assets[i] = new Asset(name, rate, vol);
        }

        Asset[] mergeArr = Arrays.copyOf(assets, n);
        Asset[] quickArr = Arrays.copyOf(assets, n);

        mergeSort(mergeArr, 0, n - 1);
        System.out.println("Merge Sort (Ascending):");
        for (Asset a : mergeArr) System.out.print(a + " ");
        System.out.println();

        quickSort(quickArr, 0, n - 1);
        System.out.println("Quick Sort (Descending + Volatility):");
        for (Asset a : quickArr) System.out.print(a + " ");
        System.out.println();
    }
}