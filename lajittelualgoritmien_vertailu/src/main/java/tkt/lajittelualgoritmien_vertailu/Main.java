package tkt.lajittelualgoritmien_vertailu;

/**
 *
 * @author Lauri
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int[] b = {5,4,3,2,1};
        quickSort(b);
        printArray(b);
    }
    
    public static void printArray(int[] arr) {
        for (int i : arr) {
            System.out.print(i + " ");
        }
        System.out.println("");
    }
    
    public static void introSort(int[] arr) {
        
    }
    
    static int partition(int[] arr, int left, int right) {
        int p = arr[left];
        int i = left-1;
        int j = right+1;
        int temp;
        
        while (i<j) {
            do {
                j--;
            } while (arr[j]>p);
            do {
                i++;
            } while (arr[i]<p);
            if (i<j) {
                temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        return j;
    }
    
    static void quickSort3(int[] arr, int left, int right) {
        int div;    
        while (left < right) {
            div = partition(arr, left, right);
            if (div-left<right-div) {
                quickSort3(arr, left, div);
                left = div+1;
            } else {
                quickSort3(arr, div+1, right);
                left = div;
            }
        }
    }
    
    static void quickSort2(int[] arr, int left, int right) {
        int div;
        while (left<right) {
            div = partition(arr, left, right);
            quickSort2(arr, left, div);
            left = div+1;
        }
    }
    
    public static void quickSort(int[] arr) {
        quickSort2(arr, 0, arr.length-1);
    }
    
    public static void insertionSort(int[] arr) {
        int x, i;
        for (int j = 1; j < arr.length; j++) {
            x = arr[j];
            i = j-1;
            while (i>=0 && arr[i]>x) {
                arr[i+1] = arr[i];
                i--;
            }
            arr[i+1] = x;
        }
    }
    
    public static void countingSort(int[] arr) {
        int[] a = new int[40000000];
        int j = 0;
        int max = 0;
        for (int i : arr) {
            if (i>max) {
                max = i;
            }
            a[i]++;
        }
        for (int i = 0; i <= max; i++) {
            int lkm = a[i];
            if (lkm>0) {
                for (int k = 0; k < lkm; k++) {
                    arr[j] = i;
                    j++;
                }
            }
        }
    }
    
}
