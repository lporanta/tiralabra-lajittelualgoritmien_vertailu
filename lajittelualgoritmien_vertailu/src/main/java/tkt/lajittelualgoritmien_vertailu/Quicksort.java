package tkt.lajittelualgoritmien_vertailu;

/**
 * Quicksort
 */
public class Quicksort {

    static int hoaresPartition(int[] arr, int left, int right) {
        int low = left - 1;
        int high = right + 1;
        int pivot = arr[(left + right) / 2];

        while (true) {
            do {
                low++;
            } while (arr[low] < pivot);
            do {
                high--;
            } while (arr[high] > pivot);
            if (low < high) {
                int temp = arr[low];
                arr[low] = arr[high];
                arr[high] = temp;
            } else {
                return high;
            }
        }
    }

    static int partition(int[] arr, int left, int right) {
        int low = left - 1;
        int high = right + 1;
        int pivot = arr[(left + right) / 2];

        while (low < high) {
            do {
                high--;
            } while (arr[high] > pivot);
            do {
                low++;
            } while (arr[low] < pivot);
            if (low < high) {
                swap(arr, low, high);
            }
        }
        return high;
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    static void quicksort3(int[] arr, int left, int right) {
        int div;
        while (left < right) {
            div = partition(arr, left, right);
            // tässä kohdassa jokin ei toimi
            // TODO FIX
            if (div - left < right - div) {
                quicksort3(arr, left, div);
                left = div + 1;
            } else {
                quicksort3(arr, div + 1, right);
                left = div;
            }
        }
    }

    static void quicksort2(int[] arr, int left, int right) {
        int div;
        while (left < right) {
            div = hoaresPartition(arr, left, right);
            quicksort2(arr, left, div);
            left = div + 1;
        }
    }

    public static void sort(int[] arr) {
        quicksort2(arr, 0, arr.length - 1);
    }
}
