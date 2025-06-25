package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

/**
 * Abstract class Geometry is the basic class representing any geometry in Cartesian
 * 3-Dimensional coordinate system. Implements Intersectable interface.
 *
 * @author Maor Atari
 */
public abstract class Geometry implements Intersectable {

    /**
     * Default constructor for Geometry.
     */
    public Geometry() {
    }

    /**
     * Returns the normal vector to the geometry at a given point
     *
     * @param point the point on the geometry's surface
     * @return normal vector at the point
     */
    public abstract Vector getNormal(Point point);
}