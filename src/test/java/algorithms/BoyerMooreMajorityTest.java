package algorithms;

import metrics.PerformanceTracker;
import org.junit.jupiter.api.Test;
import java.util.OptionalInt;
import static org.junit.jupiter.api.Assertions.*;

public class BoyerMooreMajorityTest {

    @Test
    void emptyArray() {
        var algo = new BoyerMooreMajority(new PerformanceTracker());
        assertTrue(algo.findMajority(new int[]{}).isEmpty());
    }

    @Test
    void singleElement() {
        var algo = new BoyerMooreMajority(new PerformanceTracker());
        OptionalInt res = algo.findMajority(new int[]{5});
        assertTrue(res.isPresent());
        assertEquals(5, res.getAsInt());
    }

    @Test
    void withMajority() {
        var algo = new BoyerMooreMajority(new PerformanceTracker());
        OptionalInt res = algo.findMajority(new int[]{2,2,1,2,3,2,2});
        assertTrue(res.isPresent());
        assertEquals(2, res.getAsInt());
    }

    @Test
    void noMajority() {
        var algo = new BoyerMooreMajority(new PerformanceTracker());
        OptionalInt res = algo.findMajority(new int[]{1,2,3,1,2,3});
        assertTrue(res.isEmpty());
    }

    @Test
    void negativesAndDuplicates() {
        var algo = new BoyerMooreMajority(new PerformanceTracker());
        OptionalInt res = algo.findMajority(new int[]{-1,-1,-1,2,2,-1});
        assertTrue(res.isPresent());
        assertEquals(-1, res.getAsInt());
    }
}
