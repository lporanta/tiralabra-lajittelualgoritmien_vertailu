package tkt.algoritmit;

/**
 * Introsort
 */
public class Introsort {
    final static int MAXDEPTH_FACTOR = 2;
    final static int THRESHOLD = 32;

    public static void sort(int[] arr) {
        int maxdepth = (int) Math.log(arr.length);
        maxdepth *= MAXDEPTH_FACTOR;
        introsort(arr, maxdepth, 0, arr.length - 1);
    }

    public static void introsort(int[] arr, int maxdepth, int left, int right) {
        int n = right - left;
        if (n > 0) {
            if (n < THRESHOLD) {
                Insertionsort.sort(arr, left, right);
            } else  if (maxdepth == 0) {
                Heapsort.sort(arr, left, right+1);
            } else {
                int p = Quicksort.hoaresPartition(arr, left, right);
                introsort(arr, maxdepth - 1, left, p);
                introsort(arr, maxdepth - 1, p + 1, right);
            }
        }
    }
}
