package metrics;

public class PerformanceTracker {

    private long comparisons = 0;
    private long swaps = 0;
    private long arrayAccesses = 0;
    private long startTimeNs = 0;
    private long endTimeNs = 0;

    public void start() { startTimeNs = System.nanoTime(); }
    public void stop() { endTimeNs = System.nanoTime(); }

    public void incComparisons() { comparisons++; }
    public void incSwaps() { swaps++; }
    public void incArrayAccesses() { arrayAccesses++; }

    public long getComparisons() { return comparisons; }
    public long getSwaps() { return swaps; }
    public long getArrayAccesses() { return arrayAccesses; }
    public long getDurationNs() { return endTimeNs - startTimeNs; }
    public double getDurationMs() { return (endTimeNs - startTimeNs) / 1_000_000.0; }

    public void reset() {
        comparisons = 0;
        swaps = 0;
        arrayAccesses = 0;
        startTimeNs = 0;
        endTimeNs = 0;
    }

    @Override
    public String toString() {
        return "comparisons=" + comparisons +
                ", swaps=" + swaps +
                ", arrayAccesses=" + arrayAccesses +
                ", timeMs=" + String.format("%.3f", getDurationMs());
    }
}
