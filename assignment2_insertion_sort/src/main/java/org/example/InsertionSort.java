package org.example;

import metrics.PerformanceTracker;

public class InsertionSort {

    public static void sort(int[] arr) {
        if (arr == null || arr.length <= 1) return;
        for (int i = 1; i < arr.length; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }

    public static void sort(int[] arr, PerformanceTracker tracker) {
        if (arr == null || arr.length <= 1) return;
        tracker.start();

        boolean alreadySorted = true;
        for (int i = 1; i < arr.length; i++) {
            tracker.incArrayAccesses();
            tracker.incComparisons();
            if (arr[i] < arr[i - 1]) {
                alreadySorted = false;
                break;
            }
        }
        if (alreadySorted) {
            tracker.stop();
            return;
        }

        for (int i = 1; i < arr.length; i++) {
            int key = arr[i];
            tracker.incArrayAccesses();
            int insertPos = binarySearchInsertPos(arr, key, 0, i - 1, tracker);
            for (int j = i - 1; j >= insertPos; j--) {
                arr[j + 1] = arr[j];
                tracker.incArrayAccesses();
                tracker.incSwaps();
            }
            arr[insertPos] = key;
            tracker.incArrayAccesses();
        }

        tracker.stop();
    }

    private static int binarySearchInsertPos(int[] arr, int key, int low, int high, PerformanceTracker tracker) {
        while (low <= high) {
            int mid = low + (high - low) / 2;
            tracker.incArrayAccesses();
            tracker.incComparisons();
            if (arr[mid] > key) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }
}
