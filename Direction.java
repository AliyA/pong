/**
 * A Direction object represents a direction in 2-dimensional space. The angle
 * of a direction may be obtained by using either the getDegrees() method or
 * the getRadians() method. The angle is measured anti-clockwise from the East
 * direction . For example, 0 degrees indicates a direction of East, 90
 * degrees indicates North, 
 * <p>
 * A new Direction object may be created by specifying the degrees as a
 * parameter to the constructor. In addition, common Directions have been
 * defined as static members of this class. For example:
 * <pre>Direction.NORTH</pre>
 * may be used instead of
 * <pre>new Direction(90.0)</pre>
 *
 * @author
 */
public class Direction
{
    /**
     * A predefined Direction representing East.
     */
    public static final Direction EAST = new Direction(0.0);

    /**
     * A predefined Direction representing Northeast.
     */
    public static final Direction NORTH_EAST = new Direction(45.0);

    /**
     * A predefined Direction representing North.
     */
    public static final Direction NORTH = new Direction(90.0);

    /**
     * A predefined Direction representing Northwest.
     */
    public static final Direction NORTH_WEST = new Direction(135.0);

    /**
     * A predefined Direction representing West.
     */
    public static final Direction WEST = new Direction(180.0);

    /**
     * A predefined Direction representing Southwest.
     */
    public static final Direction SOUTH_WEST = new Direction(225.0);

    /**
     * A predefined Direction representing South.
     */
    public static final Direction SOUTH = new Direction(270.0);

    /**
     * A predefined Direction representing Southeast.
     */
    public static final Direction SOUTH_EAST = new Direction(315.0);

    /**
     * The angle of this direction in degrees.
     */
    private final double degrees;

    /**
     * Creates a new Direction object, specified in degrees.
     *
     * @param degrees the angle of this direction, specified in degrees.
     */
    public Direction(double degrees)
    {
        if ( degrees < 0.0 )
            throw new RuntimeException("Can't have a direction < 0 degrees");
        if ( degrees > 360.0 )
            throw new RuntimeException("Can't have a direction > 360 degrees");

        this.degrees = degrees;
    }
    
    /**
     * Gets the angle of this Direction in degrees.
     *
     * @return the angle of this Direction in degrees.
     */
    public double getDegrees()
    {
        return degrees;
    }
    
    /**
     * Gets the angle of this Direction in radians.
     *
     * @return the angle of this Direction in radians.
     */
    public double getRadians()
    {
        return Math.toRadians(degrees);
    }
    
    /**
     * Gets a new direction relative to this direction by adding the number of
     * degrees specified. For example:
     *
     * <pre>
     * Direction north = new Direction(90.0);
     * Direction newDirection = north.getRelativeDirection(-20.0);
     * </pre>
     *
     * <code>newDirection.getDegrees()</code> should return <code>70.0</code>.
     *
     * @param degrees specifies how many degrees should be added to produce
     * the new direction.
     * @return the new relative direction.
     */
    public Direction getRelativeDirection(double degrees)
    {
        double newDegrees = this.degrees + degrees;
        newDegrees = fmod(newDegrees, 360.0);
        if (newDegrees < 0.0)
            newDegrees += 360.0;

        return new Direction(newDegrees);
    }
    
    /**
     * Calculates the modulus of number/divisor using floating-point
     * arithmetic.
     *
     * @param number the number to divide
     * @param divisor the size of divisions
     * @return the remainder
     */
    private double fmod(double number, double divisor)
    {
        double division = number / divisor;
        int intPortion = (int)division;
        double fractionPortion = division - intPortion;
        double remainder = fractionPortion * divisor;
        return remainder;
    }
    
    /**
     * Returns a new direction object that points in the opposite direction to
     * this one.
     *
     * @return the opposite direction of this direction.
     */
    public Direction getOppositeDirection()
    {
        return getRelativeDirection(180.0);
    }

    /**
     * Returns a new direction object that points 90 degrees to the left of
     * this one.
     *
     * @return the direction 90 degrees to the left of this one.
     */
    public Direction getLeftDirection()
    {
        return getRelativeDirection(90.0);
    }

    /**
     * Returns a new direction object that points 90 degrees to the right of
     * this one.
     *
     * @return the direction 90 degrees to the right of this one.
     */
    public Direction getRightDirection()
    {
        return getRelativeDirection(-90.0);
    }
    
    /**
     * Returns all information about an instance of a Direction as a string. 
     * <P>
     * It can be very useful to have a "toString" method in any class you write.
     * It allows you to use a reference to an object of that class as if that
     * object is a string.  For example, you can use the name of the object in
     * some sort of an output, as if the object is a string, and the Java
     * runtime environment will use your "toString" to produce a real String. 
     * 
     * @return all information about the Direction object as a string.
     */
    public String toString()
    {
        if ( this == EAST)       return ("Direction.EAST");
        if ( this == NORTH_EAST) return ("Direction.NORTH_EAST");
        if ( this == NORTH)      return ("Direction.NORTH");
        if ( this == NORTH_WEST) return ("Direction.NORTH_WEST");
        if ( this == WEST)       return ("Direction.WEST");
        if ( this == SOUTH_EAST) return ("Direction.SOUTH_EAST");
        if ( this == SOUTH)      return ("Direction.SOUTH");
        if ( this == SOUTH_WEST) return ("Direction.SOUTH_WEST");
        return ("direction=" + degrees);
    }
}
