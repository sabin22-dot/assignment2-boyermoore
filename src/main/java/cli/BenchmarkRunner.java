package cli;

import algorithms.BoyerMooreMajority;
import metrics.PerformanceTracker;

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.OptionalInt;

public class BenchmarkRunner {

    private static final SecureRandom RNG = new SecureRandom();

    public static void main(String[] args) {
        // Args: sizes=100,1000,10000 distributions=random,majority,balanced trials=5 out=data/metrics.csv
        String sizesArg = getArg(args, "sizes", "100,1000,10000");
        String distsArg = getArg(args, "distributions", "random,majority,balanced");
        int trials = Integer.parseInt(getArg(args, "trials", "5"));
        String out = getArg(args, "out", "docs/performance-plots/data.csv");

        int[] sizes = Arrays.stream(sizesArg.split(",")).mapToInt(Integer::parseInt).toArray();
        String[] distributions = distsArg.split(",");

        PerformanceTracker tracker = new PerformanceTracker();
        BoyerMooreMajority algo = new BoyerMooreMajority(tracker);

        for (int n : sizes) {
            for (String dist : distributions) {
                for (int t = 0; t < trials; t++) {
                    int[] arr = generate(n, dist.trim());
                    OptionalInt res = algo.findMajority(arr);
                    tracker.exportCSV(out, "BoyerMooreMajority", n, dist.trim());
                    // tiny guard to avoid dead-code elimination by JIT in longer benches
                    if (res.isPresent() && res.getAsInt() ==  Integer.MIN_VALUE) {
                        System.out.println("Impossible branch");
                    }
                }
            }
        }
        System.out.println("Benchmark complete. CSV at: " + out);
    }

    private static String getArg(String[] args, String key, String def) {
        for (String a : args) {
            if (a.startsWith(key + "=")) return a.substring(key.length()+1);
        }
        return def;
    }

    private static int[] generate(int n, String distribution) {
        int[] a = new int[n];
        switch (distribution) {
            case "majority": {
                // Ensure a clear majority > n/2
                int maj = RNG.nextInt(5);
                int threshold = n/2 + 1;
                for (int i=0;i<threshold;i++) a[i]=maj;
                for (int i=threshold;i<n;i++) a[i]=RNG.nextInt(10);
                shuffle(a);
                return a;
            }
            case "balanced": {
                // No element occurs more than n/2
                for (int i=0;i<n;i++) a[i]=i%2; // balanced 0/1 counts
                shuffle(a);
                return a;
            }
            default: {
                for (int i=0;i<n;i++) a[i]=RNG.nextInt(1000);
                return a;
            }
        }
    }

    private static void shuffle(int[] a) {
        for (int i=a.length-1;i>0;i--) {
            int j = RNG.nextInt(i+1);
            int tmp=a[i]; a[i]=a[j]; a[j]=tmp;
        }
    }
}
