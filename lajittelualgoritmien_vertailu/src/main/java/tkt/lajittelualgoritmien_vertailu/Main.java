package tkt.lajittelualgoritmien_vertailu;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    private static Scanner reader = new Scanner(System.in);
    private static final int ARRAY_MAX_SIZE = 100000;
    private static final int ARRAY_MAX_RANGE = 10000000;

    /*---- Main ----*/
    public static void main(String[] args) {
        int TEST_ARRAY_SIZE = ARRAY_MAX_SIZE;
        int TEST_ARRAY_RANDOM_RANGE = ARRAY_MAX_RANGE;
        int[] a;
        int[][] b;
        long clockIn;

        DecimalFormat df = new DecimalFormat("0.00");
        df.setRoundingMode(RoundingMode.CEILING);

        System.out.println("Welcome!\nType EXIT to exit, press enter to continue or type array length and value range (separated by space)\n"
                + "Maximum array length: " + ARRAY_MAX_SIZE + ", Maximum value range: " + ARRAY_MAX_RANGE + "\n");

        while (true) {
            System.out.print("NEW TEST\nArray length: " + TEST_ARRAY_SIZE
                    + "\nValue range: " + TEST_ARRAY_RANDOM_RANGE + "\nNew values: ");

            String input = reader.nextLine();

            if (input.equals("EXIT")) {
                break;
            }
            if (input.length() != 0) {
                String[] splitted = input.split("\\s+");
                if (splitted.length == 2) {
                    try {
                        int size = Integer.parseInt(splitted[0]);
                        if (size < ARRAY_MAX_SIZE) {
                            if (size < 1) {
                                TEST_ARRAY_SIZE = 1;
                            } else {
                                TEST_ARRAY_SIZE = size;
                            }
                        }
                    } catch (NumberFormatException e) {
                        TEST_ARRAY_SIZE = ARRAY_MAX_SIZE;
                    }
                    try {
                        int range = Integer.parseInt(splitted[1]);
                        if (range < 0) {
                            range = -range;
                        }
                        if (range < ARRAY_MAX_RANGE) {
                            TEST_ARRAY_RANDOM_RANGE = range;

                        }
                    } catch (NumberFormatException e) {
                        TEST_ARRAY_RANDOM_RANGE = ARRAY_MAX_RANGE;
                    }

                }
            }

            System.out.println("\nSorting with array length of " + TEST_ARRAY_SIZE + " and value range of " + TEST_ARRAY_RANDOM_RANGE);

            a = new int[TEST_ARRAY_SIZE];
            b = new int[7][TEST_ARRAY_SIZE];
            randomizeArray(a, TEST_ARRAY_RANDOM_RANGE);
            for (int j = 0; j < 7; j++) {
                b[j] = Arrays.copyOf(a, a.length);
            }

            clockIn = System.nanoTime();
            Arrays.sort(b[6]);
            System.out.println(
                    "-Arrays.sort: " + df.format((System.nanoTime() - clockIn) / 1000000.0) + " ms");

            clockIn = System.nanoTime();
            Countingsort.sort(b[4]);
            System.out.println(
                    "-Counting sort: " + df.format((System.nanoTime() - clockIn) / 1000000.0) + " ms");

            clockIn = System.nanoTime();
            Heapsort.sort(b[2]);
            System.out.println(
                    "-Heapsort: " + df.format((System.nanoTime() - clockIn) / 1000000.0) + " ms");

            clockIn = System.nanoTime();
            Insertionsort.sort(b[3]);
            System.out.println(
                    "-Insertion sort: " + df.format((System.nanoTime() - clockIn) / 1000000.0) + " ms");

            clockIn = System.nanoTime();
            Introsort.sort(b[1]);
            System.out.println(
                    "-Introsort: " + df.format((System.nanoTime() - clockIn) / 1000000.0) + " ms");

            clockIn = System.nanoTime();
            TimsortOf.sort(b[0]);
            System.out.println(
                    "-Timsort(ish): " + df.format((System.nanoTime() - clockIn) / 1000000.0) + " ms");

            clockIn = System.nanoTime();
            Quicksort.sort(b[5]);
            System.out.println(
                    "-Quicksort: " + df.format((System.nanoTime() - clockIn) / 1000000.0) + " ms");
            System.out.println("End reached (press enter to continue)\n");
            input = reader.nextLine();
            if (input.equals("EXIT")) {
                break;
            }

        }
        System.out.println("Goodbye!");
    }

    public static void randomizeArray(int[] arr, int range) {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (((Math.random() - 0.5) * range));
        }
    }

    public static void printArray(int[] arr) {
        for (int i : arr) {
            System.out.print(i + " ");
        }
        System.out.println("");
    }
}
