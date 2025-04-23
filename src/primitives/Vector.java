package primitives;

/**
 * Class Vector is the basic class representing a vector of Euclidean geometry in Cartesian
 * 3-Dimensional coordinate system.
 * @author Maor Atari
 */
public class Vector extends Point {
    /**
     * Constructor to initialize Vector based on three coordinate values
     * @param x first coordinate value
     * @param y second coordinate value
     * @param z third coordinate value
     * @throws IllegalArgumentException if the vector is zero
     */
    public Vector(double x, double y, double z) {
        super(x, y, z);
        if (xyz.equals(Double3.ZERO))
            throw new IllegalArgumentException("Zero vector is not allowed");
    }

    /**
     * Constructor to initialize Vector based on Double3 object
     * @param xyz Double3 object containing all three coordinates
     * @throws IllegalArgumentException if the vector is zero
     */
    public Vector(Double3 xyz) {
        super(xyz);
        if (xyz.equals(Double3.ZERO))
            throw new IllegalArgumentException("Zero vector is not allowed");
    }

    /**
     * Adds another vector to this vector
     * @param other the other vector to add
     * @return new vector resulting from the addition
     */
    public Vector add(Vector other) {

        return new Vector(xyz.add(other.xyz));
    }

    /**
     * Scales this vector by a scalar value
     * @param scalar the scalar value
     * @return new scaled vector
     */
    public Vector scale(double scalar) {

        return new Vector(xyz.scale(scalar));
    }

    /**
     * Calculates the dot product with another vector
     * @param other the other vector
     * @return dot product value
     */
    public double dotProduct(Vector other) {
        return xyz.d1() * other.xyz.d1() +
                xyz.d2() * other.xyz.d2() +
                xyz.d3() * other.xyz.d3();
    }

    /**
     * Calculates the cross product with another vector
     * @param other the other vector
     * @return new vector resulting from the cross product
     */
    public Vector crossProduct(Vector other) {
        double u1 = xyz.d1();
        double u2 = xyz.d2();
        double u3 = xyz.d3();

        double v1 = other.xyz.d1();
        double v2 = other.xyz.d2();
        double v3 = other.xyz.d3();

        return new Vector(
                u2 * v3 - u3 * v2,
                u3 * v1 - u1 * v3,
                u1 * v2 - u2 * v1
        );
    }

    /**
     * Calculates the squared length of the vector
     * @return squared length of the vector
     */
    public double lengthSquared() {

        return dotProduct(this);
    }

    /**
     * Calculates the length of the vector
     * @return length of the vector
     */
    public double length() {

        return Math.sqrt(lengthSquared());
    }

    /**
     * Normalizes this vector (returns a new vector in the same direction with length 1)
     * @return new normalized vector
     */
    public Vector normalize() {
        double len = length();
        return new Vector(xyz.reduce(len));
    }
}