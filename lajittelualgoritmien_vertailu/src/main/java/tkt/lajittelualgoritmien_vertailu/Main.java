package tkt.lajittelualgoritmien_vertailu;

/**
 *
 * @author Lauri
 */


// Alustavasti kaikki Main-luokassa. TODO refaktorointi.
public class Main {

    // main
    public static void main(String[] args) {
        int[] b = {5,4,3,2,1};
        quickSort(b);
        printArray(b);
    }
    
    // printArray tulostaa listan
    public static void printArray(int[] arr) {
        for (int i : arr) {
            System.out.print(i + " ");
        }
        System.out.println("");
    }
    
    //introSort TODO, odottaa puuttuvia komponentteja
    public static void introSort(int[] arr) {
        
    }
    
    // partition-metodi quickSortia varten
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
    
    // quickSort3, eli tilavaativuudeltaan optimoitu versio
    // Ei toimi odotetulla tavalla! TODO korjaus?
    static void quickSort3(int[] arr, int left, int right) {
        int div;    
        while (left < right) {
            div = partition(arr, left, right);
            // tässä kohdassa jokin ei toimi
            // TODO FIX
            if (div-left<right-div) {
                quickSort3(arr, left, div);
                left = div+1;
            } else {
                quickSort3(arr, div+1, right);
                left = div;
            }
        }
    }
    
    // quickSort2, eli optimoitu versio. Toimii kuten pitääkin.
    static void quickSort2(int[] arr, int left, int right) {
        int div;
        while (left<right) {
            div = partition(arr, left, right);
            quickSort2(arr, left, div);
            left = div+1;
        }
    }
    
    // quickSort, eli quicksortin aloitusmetodi
    // kutsuu quickSort2-metodia annetulla listalla
    public static void quickSort(int[] arr) {
        quickSort2(arr, 0, arr.length-1);
    }
    
    // Lisäysjärjestäminen
    public static void insertionSort(int[] arr) {
        int x, i;
        // for-looppi taulukon toisesta alkiosta taulukon loppuun
        for (int j = 1; j < arr.length; j++) {
            // x on taulukon arvo kohdassa j
            x = arr[j];
            i = j-1;
            while (i>=0 && arr[i]>x) {
                arr[i+1] = arr[i];
                i--;
            }
            arr[i+1] = x;
        }
    }
    
    // Laskemisjärjestäminen
    // Suurin mahdollinen luku 39999999
    public static void countingSort(int[] arr) {
        int[] a = new int[40000000];
        int j = 0;
        int max = 0;
        for (int i : arr) {
            // pidetään kirjaa suurimmasta luetusta luvusta
            if (i>max) {
                max = i;
            }
            // lisätään taulukon a indeksin i kohdan arvoa
            a[i]++;
        }
        // kasvatetaan i:n arvoa vain suurimpaan lukuun asti
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
