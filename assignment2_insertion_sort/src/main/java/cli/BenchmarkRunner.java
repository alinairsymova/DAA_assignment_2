package cli;

import metrics.PerformanceTracker;
import org.example.InsertionSort;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

public class BenchmarkRunner {

    private static final int[] DEFAULT_SIZES = {100, 1000, 10000, 100000};

    public static void main(String[] args) throws IOException {
        int[] sizes = DEFAULT_SIZES;
        if (args.length > 0) {
            try {
                sizes = Arrays.stream(args).mapToInt(Integer::parseInt).toArray();
            } catch (NumberFormatException ignored) { sizes = DEFAULT_SIZES; }
        }

        try (FileWriter fw = new FileWriter("docs/performance_results.csv", false)) {
            fw.write("n,distribution,comparisons,swaps,arrayAccesses,timeMs\n");

            for (int n : sizes) {
                runAndRecord(n, "random", fw);
                runAndRecord(n, "sorted", fw);
                runAndRecord(n, "reversed", fw);
                runAndRecord(n, "nearly_sorted", fw);
            }
        }
        System.out.println("Benchmarks finished. Results saved to docs/performance_results.csv");
    }

    private static void runAndRecord(int n, String distribution, FileWriter fw) throws IOException {
        int[] arr = generate(n, distribution);
        int[] copy = Arrays.copyOf(arr, arr.length);
        PerformanceTracker tracker = new PerformanceTracker();
        InsertionSort.sort(copy, tracker);
        fw.write(String.format("%d,%s,%d,%d,%d,%.3f\n",
                n,
                distribution,
                tracker.getComparisons(),
                tracker.getSwaps(),
                tracker.getArrayAccesses(),
                tracker.getDurationMs()
        ));
        System.out.printf("n=%d dist=%s -> %s%n", n, distribution, tracker.toString());
    }

    private static int[] generate(int n, String distribution) {
        Random rnd = new Random(0);
        int[] arr = new int[n];
        if ("random".equals(distribution)) {
            for (int i = 0; i < n; i++) arr[i] = rnd.nextInt(n * 10 + 1);
        } else if ("sorted".equals(distribution)) {
            for (int i = 0; i < n; i++) arr[i] = i;
        } else if ("reversed".equals(distribution)) {
            for (int i = 0; i < n; i++) arr[i] = n - i;
        } else if ("nearly_sorted".equals(distribution)) {
            for (int i = 0; i < n; i++) arr[i] = i;
            int swaps = Math.max(1, n / 100);
            for (int i = 0; i < swaps; i++) {
                int a = rnd.nextInt(n);
                int b = rnd.nextInt(n);
                int t = arr[a];
                arr[a] = arr[b];
                arr[b] = t;
            }
        } else {
            for (int i = 0; i < n; i++) arr[i] = rnd.nextInt(n * 10 + 1);
        }
        return arr;
    }
}
