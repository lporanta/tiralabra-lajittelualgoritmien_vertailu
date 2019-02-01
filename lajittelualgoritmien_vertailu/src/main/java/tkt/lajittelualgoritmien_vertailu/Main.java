package tkt.lajittelualgoritmien_vertailu;

import java.util.Arrays;

public class Main {

    final static int TEST_ARRAY_SIZE = Integer.MAX_VALUE / 1000000;
    final static int TEST_ARRAY_RANDOM_RANGE = 100000;
    final static int ITERATIONS = 1;

    /*---- Main ----*/
    //testailua
    public static void main(String[] args) {
        int[] a = new int[TEST_ARRAY_SIZE];
        int[] b = {};
        int first = 0;
        int second = 0;
        boolean success = true;

        for (int i = 0; i < ITERATIONS; i++) {

            randomizeArray(a, TEST_ARRAY_RANDOM_RANGE);
            b = Arrays.copyOf(a, TEST_ARRAY_SIZE);

            long clockIn = System.currentTimeMillis();
            Arrays.sort(a);
            long clockOut = System.currentTimeMillis();
            first += clockOut - clockIn;

            clockIn = System.currentTimeMillis();
            Introsort.sort(b);
            clockOut = System.currentTimeMillis();
            second += clockOut - clockIn;

            if (!Arrays.equals(a, b)) {
                success = false;
                for (int j = 0; j < TEST_ARRAY_SIZE; j++) {
                    if (a[j] != b[j]) {
                        System.out.print(j + " " + a[j] + " " + b[j] + "\n");
                    }
                }
                break;
            }
        }

        System.out.println("Arrays.sort: " + first / ITERATIONS + " ms");
        System.out.println("Introsort: " + second / ITERATIONS + " ms");
        System.out.println("Sort successful: " + success + "!");
    }

    public static void randomizeArray(int[] arr, int range) {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * range);
        }
    }

    public static void printArray(int[] arr) {
        for (int i : arr) {
            System.out.print(i + " ");
        }
        System.out.println("");
    }
}
