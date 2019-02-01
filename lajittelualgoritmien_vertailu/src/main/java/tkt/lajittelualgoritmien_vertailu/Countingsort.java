package tkt.lajittelualgoritmien_vertailu;

/**
 * Laskemisjärjestäminen
 */
public class Countingsort {

    final static int ARRAY_SIZE = 40000000;

    public static void sort(int[] arr) {
        int[] a = new int[ARRAY_SIZE];
        int j = 0;
        int max = 0;

        for (int i : arr) {
            if (i > max) {
                max = i;
            }
            a[i]++;
        }

        for (int i = 0; i <= max; i++) {
            int count = a[i];
            if (count > 0) {
                for (int k = 0; k < count; k++) {
                    arr[j] = i;
                    j++;
                }
            }
        }
    }
}
