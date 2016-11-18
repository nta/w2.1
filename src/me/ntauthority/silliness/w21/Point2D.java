package me.ntauthority.silliness.w21;

import com.google.java.contract.Ensures;
import com.google.java.contract.Requires;

/**
 * A basic point in Euclidean 2-dimensional space.
 */
public class Point2D
{
    private double x;
    private double y;
    private String name;

    /**
     * Default constructor.
     */
    public Point2D()
    {

    }

    /**
     * Default constructor with a name.
     * @param name the name of the point
     */
    public Point2D(String name)
    {
        this.name = name;
    }

    /**
     * Constructor setting both x/y values to a specific value.
     * @param x the new x
     * @param y the new y
     */
    public Point2D(double x, double y)
    {
        this.x = x;
        this.y = y;
    }

    /**
     * Gets the current name.
     * @return the name of the point
     */
    public String getName()
    {
        return name;
    }

    /**
     * Sets the name of the point.
     * @param name the point's new name
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * Returns the current x.
     * @return x value
     */
    public double getX()
    {
        return x;
    }

    /**
     * Returns the current y.
     * @return y value
     */
    public double getY()
    {
        return y;
    }

    /**
     * Sets the X value.
     * @param x X value
     */
    public void setX(double x)
    {
        this.x = x;
    }

    /**
     * Sets the Y value.
     * @param y Y value
     */
    public void setY(double y)
    {
        this.y = y;
    }

    /**
     * Returns the distance between this point and (0.0, 0.0).
     * @return distance
     */
    @Ensures("result >= 0.0")
    public double distanceToOrigin()
    {
        return distanceToPoint(new Point2D(0.0, 0.0));
    }

    /**
     * Returns the distance between two points.
     * @param other the other point
     * @return distance between this point and the specified point
     */
    @Requires("other != null")
    @Ensures("result >= 0.0")
    public double distanceToPoint(Point2D other)
    {
        double deltaX = this.getX() - other.getX();
        double deltaY = this.getY() - other.getY();
        return Math.sqrt((deltaX * deltaX) + (deltaY * deltaY));
    }
}
