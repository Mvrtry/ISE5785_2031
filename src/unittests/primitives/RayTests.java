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
        Ray ray = new Ray(new Point(1, 2, 3), new Vector(0, 1, 0));

        // ============ Equivalence Partitions Tests ==============
        // TC01: Positive distance
        Point p1 = ray.getPoint(2);
        assertEquals(new Point(1, 4, 3), p1, "Wrong point for positive distance");

        // TC02: Negative distance
        Point p2 = ray.getPoint(-1);
        assertEquals(new Point(1, 1, 3), p2, "Wrong point for negative distance");

        // =============== Boundary Values Tests ==================
        // TC11: Zero distance
        Point p3 = ray.getPoint(0);
        assertEquals(new Point(1, 2, 3), p3, "Wrong point for zero distance");

        // Additional test cases for better coverage
        // TC12: Distance of 1
        Point p4 = ray.getPoint(1);
        assertEquals(new Point(1, 3, 3), p4, "Wrong point for distance of 1");

        // TC13: Small positive distance
        Point p5 = ray.getPoint(0.5);
        assertEquals(new Point(1, 2.5, 3), p5, "Wrong point for small positive distance");

        // TC14: Large positive distance
        Point p6 = ray.getPoint(10);
        assertEquals(new Point(1, 12, 3), p6, "Wrong point for large positive distance");
    }
}