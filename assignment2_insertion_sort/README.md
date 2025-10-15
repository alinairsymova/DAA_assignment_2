# Assignment 2 — Insertion Sort (optimized)

## STUCTURE
- src/main/java/org/example/InsertionSort.java
- src/main/java/metrics/PerformanceTracker.java
- src/main/java/cli/BenchmarkRunner.java
- src/test/java/org/example/InsertionSortTest.java
- docs/performance_results.csv



## How to Run

### Run tests
mvn clean test

### Run benchmark and generate CSV
mvn clean compile exec:java -Dexec.mainClass="cli.BenchmarkRunner"

CSV file will be saved in: docs/performance_results.csv


## Algorithm Description

Insertion Sort works by iterating elements and inserting them in correct place.  
For nearly sorted arrays, it works faster because few swaps are needed.

- **Best case (Ω):** O(n)
- **Average case (Θ):** O(n^2)
- **Worst case (O):** O(n^2)
- **Space complexity:** O(1) (in-place)

## Metrics Collected

- comparisons
- swaps
- array accesses
- total execution time (ms)

## Branches Used

- `feature/algorithm` – base implementation
- `feature/metrics` – performance counters
- `feature/cli` – benchmark runner
- `feature/optimization` – optimized version for nearly sorted input
- `master` – final release branch

## Example Output

n=100 dist=random -> comparisons=480 swaps=325 arrayAccesses=1505 time=0.24ms
n=1000 dist=sorted -> comparisons=999 swaps=0 arrayAccesses=3000 time=2.3ms

## Report

Full analysis report and performance plots are in `/docs/` folder.  
Includes complexity derivation, peer code review and benchmark graphs.

docs/analysis-report.pdf
docs/performance-plots/

## Author
Student A (Insertion Sort) - Adildabek Nurassyl


