package tkt.algoritmit;

/**
 * Kekojärjestäminen
 */
public class Heapsort {

    public static void sort(int[] arr) {
        sort(arr, 0, arr.length);
    }

    public static void sort(int[] arr, int start, int end) {
        if (end - start < 2) {
            return;
        }

        heapify(arr, start, end);

        for (int i = end - 1; i > start; --i) {
            swap(arr, i, start);
            siftDown(arr, start, i, 0);
        }
    }

    public static void heapify(int[] arr, int start, int end) {
        int rangeLength = end - start;

        for (int i = rangeLength / 2; i >= 0; --i) {
            siftDown(arr, start, end, i);
        }
    }

    private static void siftDown(int[] arr, int start, int end, int i) {
        int left = left(i);
        int right = left + 1;
        int max = i;
        int target = arr[start + i];

        for (;;) {
            if (start + left < end
                    && arr[start + left] > target) {
                max = left;
                if (start + right < end
                        && arr[start + right]
                        > arr[start + left]) {
                    max = right;
                }
            } else {
                if (start + right < end
                        && arr[start + right] > target) {
                    max = right;
                } else {
                    arr[start + max] = target;
                    return;
                }
            }

            arr[start + i] = arr[start + max];

            i = max;
            left = left(i);
            right = left + 1;
        }
    }

    static int left(int i) {
        return (i << 1) + 1;
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

}
