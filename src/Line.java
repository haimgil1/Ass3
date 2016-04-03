import java.util.ArrayList;
import java.util.List;

/**
 * A Line class.
 *
 * @author Nir Dunetz and Haim Gil.
 */
public class Line {
    // Represent the start point of line
    private Point p1;
    // Represent the end point of line
    private Point p2;

    /**
     * Constructs a line given two points.
     *
     * @param start is where the start point of a line.
     * @param end   is where the end point of a line.
     */
    public Line(Point start, Point end) {
        this.p1 = start;
        this.p2 = end;
    }

    /**
     * Constructs a line by coordinates.
     *
     * @param x1 is the X coordinate of the start point.
     * @param y1 is the Y coordinate of the start point.
     * @param x2 is the X coordinate of the end point.
     * @param y2 is the Y coordinate of the end point.
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.p1 = new Point(x1, y1);
        this.p2 = new Point(x2, y2);
    }

    /**
     * @return the middle point of the line.
     */
    public Point middle() {
        double xMid = ((this.p1.getX() + this.p2.getX()) / 2);
        double yMid = ((this.p1.getY() + this.p2.getY()) / 2);
        return new Point(xMid, yMid);

    }

    /**
     * @return the length of the line.
     */
    public double length() {
        return this.p1.distance(p2);
    }

    /**
     * @return the start point of the line.
     */
    public Point start() {
        return this.p1;
    }

    /**
     * @return the end point of the line.
     */
    public Point end() {
        return this.p2;
    }

    /**
     * Checking if a point is lies on a line.
     *
     * @param point is a point.
     * @return true if lies, false otherwise.
     */
    public boolean ifPointOnLine(Point point) {
        double dist1 = this.p1.distance(point); // Calculating the distance.
        double dist2 = point.distance(this.p2); // Calculating the distance.
        if ((float) this.length() == (float) (dist1 + dist2)) {
            return true;
        }
        return false;
    }

    // If this line does not intersect with the rectangle, return null.
    // Otherwise, return the closest intersection point to the
    // start of the line.

    /**
     * @param rect
     * @return
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        List<Point> pointsList = rect.intersectionPoints(this);//list of intersection points
        int length = pointsList.size();
        if (pointsList.isEmpty()) {
            return null;
        }
        double tmpDistance;
        Point tmpPoint;
        Point closestIntersectPoint = pointsList.get(0);
        double closest = closestIntersectPoint.distance(this.p1);
        for (int i = 1; i < length; i++) {
            tmpPoint = pointsList.get(i);
            tmpDistance = tmpPoint.distance(this.p1);
            if (tmpDistance < closest) {
                closestIntersectPoint = tmpPoint;
            }
        }
        return closestIntersectPoint;
    }

    /**
     * Finding if the lines are intersecting.
     *
     * @param other is the suspected line to intersect with.
     * @return true if the lines intersect, false otherwise.
     */
    public boolean isIntersecting(Line other) {

        Point intersect = this.intersectionWith(other);
        if (intersect == null) { // No intersection.
            return false;
        }
        return (other.ifPointOnLine(intersect) && this.ifPointOnLine(intersect));
    }

    /**
     * Finding the intersection point between two lines.
     *
     * @param other is the suspected line to intersect with.
     * @return the intersection point if the lines intersect,
     * and null otherwise.
     */
    public Point intersectionWith(Line other) {

        double x1 = this.p1.getX();
        double x2 = this.p2.getX();
        double x3 = other.start().getX();
        double x4 = other.end().getX();
        double y1 = this.p1.getY();
        double y2 = this.p2.getY();
        double y3 = other.start().getY();
        double y4 = other.end().getY();
        double det = (x1 - x2) * (y3 - y4) - (y1 - y2) * (x3 - x4);//determinant

        if (det == 0) { //There is no intersection.
            return null;
        }
        //find point intersection
        double xIntersection = ((x3 - x4) * (x1 * y2 - y1 * x2) - (x1 - x2) * (x3 * y4 - y3 * x4)) / det;
        double yIntersection = ((y3 - y4) * (x1 * y2 - y1 * x2) - (y1 - y2) * (x3 * y4 - y3 * x4)) / det;
        return new Point(xIntersection, yIntersection);


    }

    /**
     * @param other is the line that check with if equals.
     * @return true if the lines are equal, false otherwise.
     */
    public boolean equals(Line other) {
        return (this.p1.getX() == other.start().getX() && this.p1.getY() == other.start().getY());
    }
}
