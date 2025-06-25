package unittests.primitives;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit tests for primitives.Ray class
 *
 * @author Maor Atari
 */
public class RayTests {
    /**
     * Default constructor for RayTests
     */
    public RayTests() {
    }

    /**
     * Test method for {@link Ray#getPoint(double)}.
     */
    @Test
    void testGetPoint() {
        Ray ray = new Ray(new Point(1, 0, 0), new Vector(0, 1, 0));

        // ============ Equivalence Partitions Tests ==============
        // TC01: Positive distance
        Point p1 = ray.getPoint(2);
        assertEquals(new Point(1, 2, 0), p1, "Wrong point for positive distance");

        // TC02: Negative distance
        Point p2 = ray.getPoint(-1);
        assertEquals(new Point(1, -1, 0), p2, "Wrong point for negative distance");

        // =============== Boundary Values Tests ==================
        // TC11: Zero distance
        Point p3 = ray.getPoint(0);
        assertEquals(new Point(1, 0, 0), p3, "Wrong point for zero distance");
    }
}