package org.example;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.*;

class InsertionSortTest {

    @Test
    void testEmpty() {
        int[] arr = {};
        InsertionSort.sort(arr);
        assertArrayEquals(new int[]{}, arr);
    }

    @Test
    void testSingle() {
        int[] arr = {42};
        InsertionSort.sort(arr);
        assertArrayEquals(new int[]{42}, arr);
    }

    @Test
    void testSorted() {
        int[] arr = {1,2,3,4,5};
        InsertionSort.sort(arr);
        assertArrayEquals(new int[]{1,2,3,4,5}, arr);
    }

    @Test
    void testReverse() {
        int[] arr = {5,4,3,2,1};
        InsertionSort.sort(arr);
        assertArrayEquals(new int[]{1,2,3,4,5}, arr);
    }

    @Test
    void testDuplicates() {
        int[] arr = {3,1,2,3,1};
        InsertionSort.sort(arr);
        assertArrayEquals(new int[]{1,1,2,3,3}, arr);
    }

    @Test
    void testRandomCompareToArraysSort() {
        int[] arr = {10,-2,7,0,5};
        int[] expected = Arrays.copyOf(arr, arr.length);
        Arrays.sort(expected);
        InsertionSort.sort(arr);
        assertArrayEquals(expected, arr);
    }
}
