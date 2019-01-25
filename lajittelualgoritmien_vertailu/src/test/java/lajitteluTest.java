/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import tkt.lajittelualgoritmien_vertailu.Main;

/**
 *
 * @author Lauri
 */
public class lajitteluTest {

    Main mein;

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        mein = new Main();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void countingSortKaantaaOikein() {
        int[] b = {5, 4, 3, 2, 1};
        mein.countingSort(b);
        Assert.assertArrayEquals(new int[]{1, 2, 3, 4, 5}, b);
    }

    @Test
    public void countingSortIsoLuku() {
        int[] b = {39999999, 4, 3, 2, 1, 1};
        mein.countingSort(b);
        Assert.assertArrayEquals(new int[]{1, 1, 2, 3, 4, 39999999}, b);
    }

    @Test
    public void insertionSortKaantaaOikein() {
        int[] b = {5, 4, 3, 2, 1, 10, 9, 8, 7, 6};
        mein.insertionSort(b);
        Assert.assertArrayEquals(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, b);
    }

    @Test
    public void quickSortKaantaaOikein() {
        int[] b = {5, 4, 3, 2, 1, 1, 1, 10, 9, 8, 8, 7, 6};
        mein.quickSort(b);
        Assert.assertArrayEquals(new int[]{1, 1, 1, 2, 3, 4, 5, 6, 7, 8, 8, 9, 10}, b);
    }

    @Test
    public void introSortKaantaaOikein() {
        int[] b = {5, 4, 3, 2, 1, 1, 1, 10, 9, 8, 8, 7, 6};
        mein.introSort(b);
        Assert.assertArrayEquals(new int[]{1, 1, 1, 2, 3, 4, 5, 6, 7, 8, 8, 9, 10}, b);
    }

}
