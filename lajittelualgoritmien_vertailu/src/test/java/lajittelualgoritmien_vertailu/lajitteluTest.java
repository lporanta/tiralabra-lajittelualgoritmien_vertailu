package lajittelualgoritmien_vertailu;

import java.util.Arrays;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import tkt.lajittelualgoritmien_vertailu.Countingsort;
import tkt.lajittelualgoritmien_vertailu.Heapsort;
import tkt.lajittelualgoritmien_vertailu.Insertionsort;
import tkt.lajittelualgoritmien_vertailu.Introsort;
import tkt.lajittelualgoritmien_vertailu.Quicksort;
import tkt.lajittelualgoritmien_vertailu.TimsortOf;

/**
 * Testit
 * 
 * Jokaisen testin alussa luodaan arpomalla taulukko, jonka pituus on
 * TEST_ARRAY_SIZE ja arvojen vaihteluväli on TEST_ARRAY_RANDOM_RANGE.
 * 
 * Luotu taulukko kopioidaan ja annetaan järjestettäväksi Javan omalle
 * järjestysalgoritmille ja itse tehdylle algoritmille.
 * 
 * Jos taulukot ovat identtiset, algoritmi toimii.
 * 
 */
public class lajitteluTest {
    final static int TEST_ARRAY_SIZE = Integer.MAX_VALUE / 10000;
    final static int TEST_ARRAY_RANDOM_RANGE = 10000000;
    int[] a;
    int[] b;

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        a = new int[TEST_ARRAY_SIZE];
        for (int i = 0; i < a.length; i++) {
            a[i] = (int) (((Math.random()-0.5) * TEST_ARRAY_RANDOM_RANGE));
        }
        b = Arrays.copyOf(a, a.length);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void countingSortKaantaaOikein() {
        Countingsort.sort(a);
        Arrays.sort(b);
        Assert.assertArrayEquals(a, b);
    }

    @Test
    public void insertionSortKaantaaOikein() {
        Insertionsort.sort(a);
        Arrays.sort(b);
        Assert.assertArrayEquals(a, b);
    }

    @Test
    public void quickSortKaantaaOikein() {
        Quicksort.sort(a);
        Arrays.sort(b);
        Assert.assertArrayEquals(a, b);
    }
    
    @Test
    public void heapSortKaantaaOikein() {
        Heapsort.sort(a);
        Arrays.sort(b);
        Assert.assertArrayEquals(a, b);
    }

    @Test
    public void introSortKaantaaOikein() {
        Introsort.sort(a);
        Arrays.sort(b);
        Assert.assertArrayEquals(a, b);
    }
    
    @Test
    public void timSortKaantaaOikein() {
        TimsortOf.sort(a);
        Arrays.sort(b);
        Assert.assertArrayEquals(a, b);
    }

}
