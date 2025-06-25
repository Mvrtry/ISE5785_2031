package unittests.geometries;

import geometries.*;
import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for geometries.Geometries class
 *
 * @author Maor Atari
 */
public class GeometriesTests {
    /**
     * Default constructor for GeometriesTests
     */
    public GeometriesTests() {
    }

    /**
     * Test method for {@link Geometries#findIntersections(Ray)}.
     */
    @Test
    void testFindIntersections() {
        Geometries geometries = new Geometries();

        // =============== Boundary Values Tests ==================
        // TC01: Empty collection (0 points)
        assertNull(geometries.findIntersections(new Ray(new Point(0, 0, 1), new Vector(0, 0, -1))),
                "Empty geometries collection must return null");

        // Add geometries to collection
        geometries.add(
                new Sphere(new Point(1, 0, 0), 1),
                new Triangle(new Point(-1, 0, 0), new Point(0, 1, 0), new Point(0, -1, 0)),
                new Plane(new Point(0, 0, -2), new Vector(0, 0, 1))
        );

        // TC02: No geometry is intersected (0 points)
        assertNull(geometries.findIntersections(new Ray(new Point(4, 4, 4), new Vector(1, 0, 0))),
                "Ray misses all geometries");

        // TC03: Only one geometry is intersected (1 point)
        var result = geometries.findIntersections(new Ray(new Point(0.5, 0, 2), new Vector(0, 0, -1)));
        assertNotNull(result, "Ray should intersect one geometry");
        assertEquals(1, result.size(), "Wrong number of intersections");

        // ============ Equivalence Partitions Tests ==============
        // TC04: Some (but not all) geometries are intersected (2 points)
        result = geometries.findIntersections(new Ray(new Point(0.5, 0, 2), new Vector(0.5, 0, -2.5)));
        assertNotNull(result, "Ray should intersect some geometries");
        assertEquals(2, result.size(), "Wrong number of intersections");

        // =============== Boundary Values Tests ==================
        // TC05: All geometries are intersected (4 points)
        result = geometries.findIntersections(new Ray(new Point(0.5, 0, 2), new Vector(-0.5, 0, -2.5)));
        assertNotNull(result, "Ray should intersect all geometries");
        assertEquals(4, result.size(), "Wrong number of intersections");
    }
}