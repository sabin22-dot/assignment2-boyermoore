package algorithms;

import metrics.PerformanceTracker;
import java.util.OptionalInt;

/**
 * Boyer-Moore Majority Vote
 * Returns the majority element if it exists (> n/2), otherwise empty.
 * Time: Θ(n) best/avg/worst; Space: Θ(1).
 */
public class BoyerMooreMajority {

    private final PerformanceTracker tracker;

    public BoyerMooreMajority(PerformanceTracker tracker) {
        this.tracker = tracker;
    }

    public OptionalInt findMajority(int[] arr) {
        if (arr == null || arr.length == 0) return OptionalInt.empty();

        tracker.reset();
        tracker.startTimer();

        // Phase 1: find candidate
        int count = 0;
        int candidate = 0; // value unused until count==0 first time
        tracker.incAssignments(2);

        for (int i = 0; i < arr.length; i++) {
            tracker.incComparisons(1); // i < n
            tracker.incArrayAccesses(1); // arr[i]
            int x = arr[i];
            tracker.incAssignments(1);

            if (count == 0) {
                candidate = x;
                count = 1;
                tracker.incAssignments(2);
            } else {
                tracker.incComparisons(1); // compare x with candidate (conceptual)
                if (x == candidate) {
                    count++;
                } else {
                    count--;
                }
                tracker.incAssignments(1);
            }
        }
        // Phase 2: verify candidate appears > n/2
        int freq = 0;
        tracker.incAssignments(1);
        for (int value : arr) {
            tracker.incArrayAccesses(1);
            tracker.incComparisons(1);
            if (value == candidate) freq++;
        }
        tracker.incComparisons(1);
        boolean isMajority = freq > arr.length / 2;

        tracker.stopTimer();
        if (isMajority) {
            return OptionalInt.of(candidate);
        } else {
            return OptionalInt.empty();
        }
    }
}
