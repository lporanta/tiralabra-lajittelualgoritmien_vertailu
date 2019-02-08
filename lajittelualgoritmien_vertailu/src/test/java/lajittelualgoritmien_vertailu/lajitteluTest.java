package lajittelualgoritmien_vertailu;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import tkt.lajittelualgoritmien_vertailu.Countingsort;
import tkt.lajittelualgoritmien_vertailu.Heapsort;
import tkt.lajittelualgoritmien_vertailu.Insertionsort;
import tkt.lajittelualgoritmien_vertailu.Introsort;
import tkt.lajittelualgoritmien_vertailu.Main;
import tkt.lajittelualgoritmien_vertailu.Quicksort;

/**
 *
 * @author Lauri
 */
public class lajitteluTest {

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void countingSortKaantaaOikein() {
        int[] b = {5, 4, 3, 2, 1};
        Countingsort.sort(b);
        Assert.assertArrayEquals(new int[]{1, 2, 3, 4, 5}, b);
    }

    @Test
    public void countingSortIsoLuku() {
        int[] b = {39999999, 4, 3, 2, 1, 1};
        Countingsort.sort(b);
        Assert.assertArrayEquals(new int[]{1, 1, 2, 3, 4, 39999999}, b);
    }

    @Test
    public void insertionSortKaantaaOikein() {
        int[] b = {5, 4, 3, 2, 1, 10, 9, 8, 7, 6};
        Insertionsort.sort(b);
        Assert.assertArrayEquals(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, b);
    }

    @Test
    public void quickSortKaantaaOikein() {
        int[] b = {5, 4, 3, 2, 1, 1, 1, 10, 9, 8, 8, 7, 6};
        Quicksort.sort(b);
        Assert.assertArrayEquals(new int[]{1, 1, 1, 2, 3, 4, 5, 6, 7, 8, 8, 9, 10}, b);
    }
    
    @Test
    public void heapSortKaantaaOikein() {
        int[] b = {5, 4, 3, 2, 1, 1, 1, 10, 9, 8, 8, 7, 6};
        Heapsort.sort(b);
        Assert.assertArrayEquals(new int[]{1, 1, 1, 2, 3, 4, 5, 6, 7, 8, 8, 9, 10}, b);
    }

    @Test
    public void introSortKaantaaOikein() {
        int[] b = {5, 4, 3, 2, 1, 1, 1, 10, 9, 8, 8, 7, 6};
        Introsort.sort(b);
        Assert.assertArrayEquals(new int[]{1, 1, 1, 2, 3, 4, 5, 6, 7, 8, 8, 9, 10}, b);
    }

}
