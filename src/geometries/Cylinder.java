package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import static primitives.Util.*;

/**
 * Class Cylinder is the basic class representing a cylinder in Cartesian
 * 3-Dimensional coordinate system.
 * @author Maor Atari
 */
public class Cylinder extends Tube {
    /** The height of the cylinder */
    private final double height;

    /**
     * Constructor to initialize Cylinder with axis ray, radius and height
     * @param axisRay the axis ray
     * @param radius the radius value
     * @param height the height value
     */
    public Cylinder(Ray axisRay, double radius, double height) {
        super(axisRay, radius);
        this.height = height;
    }

    @Override
    public Vector getNormal(Point point) {
        // Calculate the point on the axis closest to the given point
        Vector pointToAxisOrigin = point.subtract(axisRay.p0);
        double projectionLength = pointToAxisOrigin.dotProduct(axisRay.dir);

        // Check if the point is on one of the bases
        if (isZero(projectionLength)) {
            // Point is on the bottom base
            return axisRay.dir.scale(-1);
        }

        if (isZero(projectionLength - height)) {
            // Point is on the top base
            return axisRay.dir;
        }

        // Point is on the side of the cylinder - use Tube's normal calculation
        return super.getNormal(point);
    }
}