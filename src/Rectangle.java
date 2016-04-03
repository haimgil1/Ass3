import java.util.ArrayList;
import java.util.List;

/**
 * A Rectangle class.
 *
 * @author Nir Dunetz and Haim Gil.
 */
 public class Rectangle {
    private Point upperLeft;
    private double width;
    private double height;

    /**
     * Create a new rectangle with location and width/height.
     *
     * @param upperLeft
     * @param width
     * @param height
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
    }

    public Rectangle(double x,double y, double width, double height) {
        this.upperLeft = new Point(x,y);
        this.width = width;
        this.height = height;
    }

    /**
     * @param line
     * @return a (possibly empty) List of intersection points with the specified line.
     */

    public java.util.List<Point> intersectionPoints(Line line) {
        List<Point> pointsList = new ArrayList<Point>();//list of intersections points

        pointsList = addIntersectionToLIst(line, this.getUpperLine(), pointsList);
        pointsList = addIntersectionToLIst(line, this.getLeftLine(), pointsList);
        pointsList = addIntersectionToLIst(line, this.getBottomLine(), pointsList);
        pointsList = addIntersectionToLIst(line, this.getRightLine(), pointsList);

        return pointsList;
    }

    /**
     *
     * @param line
     * @param rectangleLine
     * @param pointsList
     * @return
     */
    public List<Point> addIntersectionToLIst (Line line, Line rectangleLine, List<Point> pointsList){
        Point intersection = line.intersectionWith(rectangleLine);
        if (line.isIntersecting(rectangleLine)) {
            pointsList.add(intersection);
        }
        return pointsList;

    }
    /**
     * @param line
     * @return
     */
    public boolean isIntersectWithLine(Line line) {
        List<Point> pointsList = this.intersectionPoints(line);
        if (pointsList.isEmpty()) {
            return false;
        }
        return true;
    }


    /**
     * @return the width of the rectangle
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * @return the height of the rectangle
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * @return the upper-left point of the rectangle.
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }

    /**
     * @return the upper right point of the rectangle.
     */
    public Point getUpperRight() {
        double xUpperRight = this.upperLeft.getX() + width;
        return  new Point(xUpperRight, this.upperLeft.getY());
    }

    /**
     * @return the Bottom left point of the rectangle.
     */
    public Point getBottomLeft() {
        double yBottomLeft = this.upperLeft.getY() + height;
        return new Point(this.upperLeft.getX(), yBottomLeft);
    }

    /**
     * @return the bottom right point of the rectangle.
     */
    public Point getBottomRight() {
        double x = this.upperLeft.getX() + width;
        double y = this.upperLeft.getY() + height;
        return new Point(x, y);
    }

    /**
     * @return the left line of the rectangle.
     */
    public Line getLeftLine() {
        return new Line(this.upperLeft, this.getBottomLeft());
    }

    /**
     * @return the bottom line of the rectangle.
     */
    public Line getBottomLine() {
        return new Line(this.getBottomLeft(), this.getBottomRight());
    }

    /**
     * @return the upper line of the rectangle.
     */
    public Line getUpperLine() {
        return new Line(this.upperLeft, this.getUpperRight());
    }

    /**
     * @return the right line of the rectangle.
     */
    public Line getRightLine() {
        return new Line(this.getBottomRight(), this.getUpperRight());
    }

}