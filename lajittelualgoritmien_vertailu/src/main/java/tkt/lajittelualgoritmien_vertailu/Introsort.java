package tkt.lajittelualgoritmien_vertailu;

/**
 * Introsort
 */
public class Introsort {
    // Täällä on jokin bugi.
    final static int MAXDEPTH_FACTOR = 2;
    final static int THRESHOLD = 0;

    public static void sort(int[] arr) {
        int maxdepth = (int) Math.log(arr.length);
        maxdepth *= MAXDEPTH_FACTOR;
        introsort(arr, maxdepth, 0, arr.length - 1);
    }

    public static void introsort(int[] arr, int maxdepth, int left, int right) {
        int n = right - left;
        if (n < 1) {
            return;
        } 
//        else if (n < THRESHOLD) {
//            Insertionsort.sort(arr, left, right);
//        } 
        else if (maxdepth == 0) {
            Heapsort.sort(arr, left, right);
        } else {
            int p = Quicksort.hoaresPartition(arr, left, right);
            introsort(arr, maxdepth - 1, left, p);
            introsort(arr, maxdepth - 1, p + 1, right);
        }
    }
}
