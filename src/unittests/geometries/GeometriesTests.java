package unittests.geometries;

import geometries.*;
import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for {@link geometries.Geometries} class
 * Tests the findIntersections method with various scenarios including
 * boundary value analysis (BVA) and equivalence partitioning (EP)
 *
 * @author Maor Atari
 */
public class GeometriesTests {

    /**
     * Default constructor
     */
    public GeometriesTests() {
    }

    /**
     * Test method for {@link geometries.Geometries#findIntersections(primitives.Ray)}
     * Tests various intersection scenarios:
     * - Empty collection (BVA)
     * - No intersections (BVA)
     * - Single intersection (BVA)
     * - Multiple intersections (EP)
     */
    @Test
    void testFindIntersections() {
        Geometries geometries = new Geometries();

        // =============== Boundary Values Tests ==================

        // TC01: Empty geometries collection (BVA)
        assertNull(geometries.findIntersections(new Ray(new Point(0, 0, 1), new Vector(0, 0, -1))),
                "Empty geometries collection must return null");

        // Add geometries: sphere, triangle, and plane
        geometries.add(
                new Sphere(new Point(1, 0, 0), 1),
                new Triangle(new Point(-1, 0, 0), new Point(0, 1, 0), new Point(0, -1, 0)),
                new Plane(new Point(0, 0, 1), new Vector(0, 0, 1))
        );

        // TC02: Ray that misses all geometries (BVA)
        assertNull(geometries.findIntersections(new Ray(new Point(4, 4, 4), new Vector(1, 0, 0))),
                "Ray misses all geometries");

        // TC03: Ray that intersects one geometry only - the plane (BVA)
        var result = geometries.findIntersections(new Ray(new Point(0, 0, -1), new Vector(0, 0, 1)));
        assertNotNull(result, "Ray should intersect one geometry");
        assertEquals(1, result.size(), "Wrong number of intersections");

        // ============ Equivalence Partitions Tests ==============

        // TC04: Ray that intersects some geometries (EP) - sphere and plane
        result = geometries.findIntersections(new Ray(new Point(1, 0, -1), new Vector(0, 0, 1)));
        assertNotNull(result, "Ray should intersect some geometries");
        assertEquals(2, result.size(), "Wrong number of intersections"); // 1 from sphere + 1 from plane

        // TC05: Ray that intersects multiple geometries (EP) - sphere (2 points) and plane (1 point)
        result = geometries.findIntersections(new Ray(new Point(0.5, 0, -1), new Vector(0, 0, 1)));
        assertNotNull(result, "Ray should intersect multiple geometries");
        assertEquals(3, result.size(), "Should intersect sphere and plane"); // 2 from sphere + 1 from plane
    }
}