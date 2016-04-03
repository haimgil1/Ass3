/**
 * A Velocity class.
 *
 * @author Nir Dunetz and Haim Gil.
 */
public class Velocity {

    private double dx;
    private double dy;

    /**
     * Constructs a velocity by coordinates.
     *
     * @param dx represent the change in position on the `x` axe
     * @param dy represent the change in position on the `y` axe
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;

    }

    /**
     * @return the x coordinate.
     */
    public double getDx() {

        return this.dx;
    }

    /**
     * @return the y coordinate.
     */
    public double getDy() {
        return this.dy;
    }
    /**
     * Setting the velocity according to the angle and speed.
     *
     * @param angle is the angle.
     * @param speed is the speed.
     * @return the velocity.
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double dx = Math.cos(Math.toRadians(angle)) * speed;
        double dy = Math.sin(Math.toRadians(angle)) * speed;
        return new Velocity(dx, dy);
    }

    /**
     * Taking a point with position (x,y) and return a new point with position (x+dx, y+dy).
     *
     * @param p is a point.
     * @return the new point.
     */
    public Point applyToPoint(Point p) {
        double newX = (this.dx + p.getX());
        double newY = (this.dy + p.getY());
        return new Point(newX, newY);
    }
    public Point applyToPointCorner(Point p) {
        double x = this.dx * 0.1 + p.getX(); // dx + x
        double y = this.dy * 0.1 + p.getY(); // dy + y
        Point newpoint = new Point(x, y);
        return newpoint;
    }
}