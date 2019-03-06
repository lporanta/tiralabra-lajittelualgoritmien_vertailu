package tkt.algoritmit;

/**
 * Lisäysjärjestäminen
 */
public class Insertionsort {

    public static void sort(int[] arr) {
        sort(arr, 0, arr.length-1);
    }

    public static void sort(int[] arr, int start, int end) {
        int x, i;
        for (int j = start; j <= end; j++) {
            x = arr[j];
            i = j - 1;
            while (i >= 0 && arr[i] > x) {
                arr[i + 1] = arr[i];
                i--;
            }
            arr[i + 1] = x;
        }
    }
}
