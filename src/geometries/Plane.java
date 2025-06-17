package geometries;

import primitives.Point;
import primitives.Vector;

/**
 * Class Plane is the basic class representing a plane in Cartesian
 * 3-Dimensional coordinate system.
 *
 * @author Maor Atari
 */
public class Plane extends Geometry {
    /**
     * A point on the plane
     */
    private final Point q0;
    /**
     * The normal vector to the plane
     */
    private final Vector normal;

    /**
     * Constructor to initialize Plane based on three points
     * Note: This is a partial implementation for Stage 1.
     * The normal calculation will be fully implemented in the next stage.
     *
     * @param p1 first point on the plane
     * @param p2 second point on the plane
     * @param p3 third point on the plane
     * @throws IllegalArgumentException if any two points are the same or if all three points are collinear
     */
    public Plane(Point p1, Point p2, Point p3) {
        q0 = p1;
        normal = null;
    }

    /**
     * Constructor to initialize Plane based on a point and normal vector
     *
     * @param q0     point on the plane
     * @param normal normal vector to the plane (will be normalized)
     */
    public Plane(Point q0, Vector normal) {
        this.q0 = q0;
        this.normal = normal.normalize();
    }

    @Override
    public Vector getNormal(Point point) {
        return normal;
    }
}