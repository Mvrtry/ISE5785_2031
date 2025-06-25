package geometries;

import primitives.Point;
import primitives.Ray;

import java.util.LinkedList;
import java.util.List;

/**
 * Composite class representing a collection of Intersectable geometries
 *
 * @author Maor Atari
 */
public class Geometries implements Intersectable {
    /**
     * List of intersectable geometries
     */
    private final List<Intersectable> geometries = new LinkedList<>();

    /**
     * Default constructor
     */
    public Geometries() {
    }

    /**
     * Constructor that accepts geometries to add to the collection
     *
     * @param geometries variable list of geometries to add
     */
    public Geometries(Intersectable... geometries) {
        add(geometries);
    }

    /**
     * Adds geometries to the collection
     *
     * @param geometries variable list of geometries to add
     */
    public void add(Intersectable... geometries) {
        if (geometries != null && geometries.length > 0) {
            this.geometries.addAll(List.of(geometries));
        }
    }

    @Override
    public List<Point> findIntersections(Ray ray) {
        return null;
    }
}