package geometries;

import primitives.Point;
import primitives.Ray;

import java.util.LinkedList;
import java.util.List;

/**
 * Class Geometries represents a collection of intersectable geometries
 * Implements the Intersectable interface using the Composite design pattern
 * Allows treating individual geometries and collections of geometries uniformly
 *
 * @author Maor Atari
 */
public class Geometries implements Intersectable {

    /**
     * Collection of intersectable geometries
     * Private and final to ensure encapsulation and immutability of the reference
     */
    private final List<Intersectable> geometries;

    /**
     * Default constructor
     */
    public Geometries() {
        geometries = new LinkedList<>();
    }

    /**
     * Constructor with initial geometries
     * Creates a collection and adds the provided geometries to it
     *
     * @param geometries initial geometries to add to the collection (varargs)
     */
    public Geometries(Intersectable... geometries) {
        this.geometries = new LinkedList<>();
        add(geometries);
    }

    /**
     * Add geometries to the collection
     * Filters out null values to maintain collection integrity
     *
     * @param geometries geometries to add to the collection (varargs)
     */
    public void add(Intersectable... geometries) {
        if (geometries != null) {
            for (Intersectable geometry : geometries) {
                if (geometry != null) {
                    this.geometries.add(geometry);
                }
            }
        }
    }

    /**
     * Find intersection points between a ray and all geometries in the collection
     * Implements the Composite pattern by delegating to each geometry and collecting results
     *
     * @param ray the ray to intersect with the geometries
     * @return list of intersection points, or null if no intersections found
     */
    @Override
    public List<Point> findIntersections(Ray ray) {
        if (ray == null) return null;

        List<Point> result = null;

        for (Intersectable geometry : geometries) {
            List<Point> intersections = geometry.findIntersections(ray);

            if (intersections != null) {
                if (result == null) {
                    result = new LinkedList<>();
                }
                result.addAll(intersections);
            }
        }

        return result;
    }
}