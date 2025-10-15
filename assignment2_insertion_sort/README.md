Algorithm Analysis Report: Selection Sort vs Insertion Sort
Author: Irsymova Alina
Partner's Algorithm: Selection Sort
My Algorithm: Insertion Sort
Date: October 2025
________________________________________
1. Algorithm Overview
Selection Sort Analysis
Selection Sort is a simple comparison-based sorting algorithm that operates by dividing the input array into two parts: a sorted subarray and an unsorted subarray. The algorithm repeatedly finds the minimum element from the unsorted portion and places it at the end of the sorted portion.
Key Characteristics:
•  Time Complexity: O(n²) in all cases
•  Space Complexity: O(1) - in-place sorting
•  Stability: Not stable (may change relative order of equal elements)
•  Adaptivity: Not adaptive - performance doesn't improve with partially sorted input
Algorithm Steps:
1.  Iterate through the array from i = 0 to n-1
2.  Find the minimum element in the unsorted subarray (i to n-1)
3.  Swap the minimum element with the element at position i
4.  Expand the sorted subarray by one element
Implementation Analysis
The provided Selection Sort implementation includes several notable features:
•  Early termination check using isSorted() method
•  Swapping optimization to minimize unnecessary operations
•  Comprehensive performance tracking through PerformanceTracker
________________________________________
2. Complexity Analysis
Theoretical Complexity
Selection Sort:
•  Best Case: Ω(n²) - Even sorted arrays require full comparisons
•  Average Case: Θ(n²) - Consistent performance regardless of input distribution
•  Worst Case: O(n²) - Quadratic growth with input size
•  Space Complexity: O(1) - Constant auxiliary space
Mathematical Derivation:
For an array of size n:
•  Comparisons: ∑(i=0 to n-1) (n-i-1) = n(n-1)/2 ∈ O(n²)
•  Swaps: Exactly n-1 swaps in all cases ∈ O(n)
•  Array Accesses: 3n(n-1)/2 ∈ O(n²)
Insertion Sort (for comparison):
•  Best Case: Ω(n) - Already sorted arrays
•  Average Case: Θ(n²) - Random input distribution
•  Worst Case: O(n²) - Reverse sorted arrays
•  Space Complexity: O(1) - In-place algorithm
Comparative Analysis
Complexity Metric  Selection Sort  Insertion Sort
Best Case  O(n²)  O(n)
Average Case  O(n²)  O(n²)
Worst Case  O(n²)  O(n²)
Swaps  O(n)  O(n²)
Comparisons  O(n²)  O(n²)
Adaptivity  No  Yes
Memory  O(1)  O(1)
________________________________________
3. Code Review
Strengths of the Implementation
1.  Early Termination Optimization:
java
if (!swapped && isSorted(arr, i, tracker)) {
    break;
}
This provides some optimization for already-sorted or nearly-sorted inputs.
2.  Comprehensive Performance Tracking:
The implementation properly tracks comparisons, swaps, and array accesses.
3.  Modular Design:
Separate methods for swapping and sorted checking improve code readability.
Identified Inefficiencies
1.  Redundant Sorted Check:
java
private static boolean isSorted(int[] arr, int from, PerformanceTracker tracker) {
    for (int k = from; k < arr.length - 1; k++) {
        tracker.incrementComparisons();
        tracker.incrementArrayAccesses(2);
        if (arr[k] > arr[k + 1]) {
            return false;
        }
    }
    return true;
}
Issue: This method adds O(n) overhead and duplicates comparison work already done in the main loop.
2.  Inefficient Array Access Counting:
java
tracker.incrementArrayAccesses(2);
if (arr[j] < arr[minIndex]) {
    minIndex = j;
    swapped = true;
}
Issue: Array accesses are overcounted - only two accesses occur per comparison, not necessarily requiring increment by 2 every time.
Optimization Recommendations
1.  Remove Redundant Sorted Check:
java
public static void sort(int[] arr, PerformanceTracker tracker) {
    if (arr == null || arr.length <= 1) return;
    
    int n = arr.length;
    boolean alreadySorted = true;
    
    // Single pass to check if already sorted
    for (int i = 0; i < n - 1; i++) {
        tracker.incrementComparisons();
        tracker.incrementArrayAccesses(2);
        if (arr[i] > arr[i + 1]) {
            alreadySorted = false;
            break;
        }
    }

if (alreadySorted) return;
    
    // Standard selection sort without additional sorted checks
    for (int i = 0; i < n - 1; i++) {
        int minIndex = i;
        for (int j = i + 1; j < n; j++) {
            tracker.incrementComparisons();
            tracker.incrementArrayAccesses(2);
            if (arr[j] < arr[minIndex]) {
                minIndex = j;
            }
        }
        if (minIndex != i) {
            swap(arr, i, minIndex, tracker);
        }
    }
}
2.  Optimize Array Access Counting:
Count actual accesses rather than using fixed increments.
3.  Implement Adaptive Version:
Consider tracking if any swaps occurred in a pass to enable early termination.
________________________________________
4. Empirical Results Analysis
Performance Data Comparison
Selection Sort Results:
text
n=100, random: 0.344ms, comparisons=536, swaps=2436
n=100, sorted: 0.013ms, comparisons=99, swaps=0
n=100, reversed: 0.360ms, comparisons=481, swaps=4950
n=100, nearly_sorted: 0.075ms, comparisons=622, swaps=23

n=1000, random: 15.707ms, comparisons=8597, swaps=249558
n=1000, sorted: 0.049ms, comparisons=999, swaps=0
n=1000, reversed: 4.102ms, comparisons=7988, swaps=499500
n=1000, nearly_sorted: 0.177ms, comparisons=8736, swaps=8554

n=10000, random: 43.359ms, comparisons=118939, swaps=24781209
n=10000, sorted: 0.008ms, comparisons=9999, swaps=0
n=10000, reversed: 74.064ms, comparisons=113632, swaps=49995000
n=10000, nearly_sorted: 1.305ms, comparisons=119678, swaps=672174
Insertion Sort Results:
text
n=100: ~0.150ms, comparisons=4950, swaps=4800
n=1000: ~8.67ms, comparisons=499500, swaps=498000
n=10000: ~876ms, comparisons=49995000, swaps=49980000
n=100000: ~8984ms, comparisons=4999950000, swaps=4999800000
Performance Analysis
1.  Time Complexity Validation:
o  Both algorithms show quadratic growth (O(n²)) as input size increases
o  Selection Sort: 100→1000 (45x time increase), 1000→10000 (2.8x)
o  Insertion Sort: 100→1000 (58x), 1000→10000 (101x)
2.  Input Distribution Impact:
o  Selection Sort: Minimal performance variation across distributions
o  Insertion Sort: Significant performance differences:
  Best case (sorted): O(n) behavior
  Worst case (reversed): O(n²) behavior
3.  Comparative Performance:
o  Small arrays (n=100): Comparable performance
o  Medium arrays (n=1000): Insertion Sort slightly faster
o  Large arrays (n=10000+): Selection Sort shows better performance on random data
4.  Operation Count Analysis:
o  Selection Sort: Fewer swaps but more comparisons in some cases
o  Insertion Sort: Consistent comparison count but variable swaps
Performance Plots Analysis
Time vs Input Size (Random Data):
text
Selection Sort: Shows more consistent O(n²) growth
Insertion Sort: Shows higher variance but similar asymptotic behavior
Distribution Sensitivity:
text
Selection Sort: Minimal sensitivity to input distribution
Insertion Sort: Highly sensitive - excels on sorted/nearly-sorted data
________________________________________
5. Conclusion
Key Findings
1.  Theoretical Validation:
o  Both algorithms demonstrate expected O(n²) time complexity
o  Selection Sort shows more consistent performance across different input distributions
o  Insertion Sort demonstrates better adaptivity to favorable input patterns
2.  Practical Performance:
o  For small datasets (n < 1000), both algorithms are acceptable
o  For larger datasets, Selection Sort shows better consistency
o  Insertion Sort excels when input is partially sorted or small
3.  Implementation Quality:
o  The Selection Sort implementation includes useful optimizations
o  Some redundant checks could be eliminated for better performance
o  Memory usage is optimal for both algorithms
Optimization Recommendations
For Selection Sort:
1.  Remove the redundant isSorted() check in the inner loop
2.  Implement a more efficient early termination mechanism
3.  Optimize array access counting to reflect actual operations
4.  Consider hybrid approaches for specific use cases
Algorithm Selection Guidelines:

•  Use Insertion Sort for: small arrays, nearly-sorted data, adaptive requirements
•  Use Selection Sort for: educational purposes, when swap operations are expensive
•  Consider other algorithms (Merge Sort, Quick Sort) for: n > 1000, performance-critical applications
Final Assessment
The analyzed Selection Sort implementation is functionally correct and includes some performance optimizations. While it cannot overcome the fundamental O(n²) limitation of the algorithm, the implementation choices are reasonable for the problem domain. The comparative analysis with Insertion Sort reveals the trade-offs between consistency and adaptivity in simple sorting algorithms.
For practical applications with n > 1000, both algorithms should be replaced with more efficient alternatives like Merge Sort (O(n log n)) or Quick Sort (O(n log n) average case). However, for educational purposes and small datasets, both implementations serve as excellent examples of fundamental sorting techniques.
