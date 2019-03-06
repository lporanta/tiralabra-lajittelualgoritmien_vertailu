package tkt.algoritmit;

/**
 * Laskemisjärjestäminen
 *
 *
 * Toimii myös negatiivisilla luvuilla. 
 *
 */
public class Countingsort {

    public static void sort(int[] arr) {
        int min = arr[0];
        int max = arr[0];
        int mapping_plus[];
        int mapping_minus[];
        int output[] = new int[arr.length + 1];

        for (int i : arr) {
            if (i < min) {
                min = i;
            } else if (i > max) {
                max = i;
            }
        }

        if (min < 0) {
            min = -min;
        }

        mapping_plus = new int[max + 1];
        mapping_minus = new int[min + 1];

        for (int i : arr) {
            if (i >= 0) {
                mapping_plus[i]++;
            } else {
                mapping_minus[-i]++;
            }
        }

        for (int i = min - 1; i >= 0; i--) {
            mapping_minus[i] += mapping_minus[i + 1];
        }
        for (int i = 1; i <= max; i++) {
            mapping_plus[i] += mapping_plus[i - 1];
        }
        for (int i = 0; i <= max; i++) {
            mapping_plus[i] += mapping_minus[0];
        }
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] >= 0) {
                output[mapping_plus[arr[i]]] = arr[i];
                mapping_plus[arr[i]]--;
            } else {
                output[mapping_minus[-arr[i]]] = arr[i];
                mapping_minus[-arr[i]]--;
            }
        }
        for (int i = 0; i < arr.length; i++) {
            arr[i] = output[i + 1];
        }
    }
}
