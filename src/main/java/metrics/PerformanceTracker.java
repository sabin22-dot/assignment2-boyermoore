package metrics;

import java.io.*;
import java.nio.file.*;
import java.util.concurrent.TimeUnit;

/**
 * Tracks key operations for empirical analysis.
 */
public class PerformanceTracker {
    private long comparisons = 0;
    private long arrayAccesses = 0;
    private long assignments = 0;
    private long startNs;
    private long endNs;

    public void reset() {
        comparisons = arrayAccesses = assignments = 0;
        startNs = endNs = 0;
    }

    public void incComparisons(long c) { comparisons += c; }
    public void incArrayAccesses(long c) { arrayAccesses += c; }
    public void incAssignments(long c) { assignments += c; }

    public void startTimer() { startNs = System.nanoTime(); }
    public void stopTimer() { endNs = System.nanoTime(); }

    public long getComparisons() { return comparisons; }
    public long getArrayAccesses() { return arrayAccesses; }
    public long getAssignments() { return assignments; }
    public long getElapsedNs() { return endNs - startNs; }

    public void exportCSV(String path, String algorithm, int n, String distribution) {
        try {
            Path p = Paths.get(path);
            Files.createDirectories(p.getParent());
            boolean exists = Files.exists(p);
            try (BufferedWriter w = Files.newBufferedWriter(p, java.nio.charset.StandardCharsets.UTF_8,
                    StandardOpenOption.CREATE, StandardOpenOption.APPEND)) {
                if (!exists) {
                    w.write("algorithm,n,distribution,elapsed_ms,comparisons,array_accesses,assignments\n");
                }
                double ms = getElapsedNs() / 1_000_000.0;
                w.write(String.format("%s,%d,%s,%.3f,%d,%d,%d%n",
                        algorithm, n, distribution, ms, comparisons, arrayAccesses, assignments));
            }
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    public static String prettyTime(long ns) {
        return String.format("%.3f ms", ns / 1_000_000.0);
    }
}
