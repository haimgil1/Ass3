import java.awt.Color;
import java.util.Random;
import java.util.ArrayList;
import java.util.List;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;
/**
 * nothing.
 * @author 2540p
 *
 */
public class BlockChecks {
    private static Random rand = new Random();
    /**
     * boom.
     * @return array of blocks.
     */
    public Block[] createBlock() {
        Block[] block = new Block[13];
        for (int i = 0; i < 9; i++) {
            Point point = new Point(rand.nextInt(700) + 1,
                    rand.nextInt(500) + 1);
            Rectangle rec = new Rectangle(point, rand.nextInt(100) + 4, rand.nextInt(100) + 10);
            block[i] = new Block(rec, Color.black);
        }
        Rectangle up=new Rectangle(0,0,800,2);
        Rectangle left=new Rectangle(0,0,1,600);
        Rectangle bottom=new Rectangle(0,600,800,3);
        Rectangle right=new Rectangle(800,0,2,800);
        block[9]=new Block(up, Color.black);
        block[10]=new Block(left, Color.black);
        block[11]=new Block(bottom, Color.black);
        block[12]=new Block(right, Color.black);
        return block;
//        }
//        for (int i = 0; i < 9; i++) {
//            Point point = new Point(i*60 + 1,
//                    i*10 + 1);
//            Rectangle rec=new Rectangle(point, i+40 + 4, i+40 + 10);
//            block[i] = new Block(rec,Color.black);
//        }
//        return block;
    }
    /**
     * checks intersections.
     */
    //javac -cp biuoop-1.4.jar:. BlockChecks.java -Xlint
    public void checkIntersections() {
        Point point = new Point(50, 10);
        Point pnt1 = new Point(20, 25);
        Point pnt2 = new Point(150, 25);
        Line line = new Line(pnt1, pnt2);
        Rectangle rect = new Rectangle(point, 60, 30);
        List<Point> list = rect.intersectionPoints(line);
        // If the list isn't empty prints the intersection points.
        if (!list.isEmpty()) {
            System.out.println("OK");
            if (list.get(0).getX() == 50 && list.get(0).getY() == 25
                    && list.get(1).getX() == 110 && list.get(1).getY() == 25) {
                System.out.println("intersections are OK!");
            }
        }

        Point pnt3 = new Point(20, 80);
        Point pnt4 = new Point(150, 80);
        Line notIntersectingLine = new Line(pnt3, pnt4);
        java.util.List<Point> list2 =
                rect.intersectionPoints(notIntersectingLine);
        if (list2.isEmpty()) {
            System.out.println("not intersecting works!");
        }

        Point pnt5 = new Point(50, 40);
        Point pnt6 = new Point(110, 10);
        Line diagonal = new Line(pnt5, pnt6);
        java.util.List<Point> list3 = rect.intersectionPoints(diagonal);
        if (!list3.isEmpty()) {
            System.out.println("diagonal check is good!");
            if (list3.get(0).getX() == 110 && list3.get(0).getY() == 10
                    && list3.get(1).getX() == 50 && list3.get(1).getY() == 40) {
                System.out.println("intersections are OK!");
            } else {
                System.out.println("intersections points arent correct!");
            }
            System.out.println("");
        }
    }

    /**
     * checks intersection point.
     */
    public void pointShortestIntersection() {
        Point point = new Point(50, 10);
        Point pnt1 = new Point(20, 25);
        Point pnt2 = new Point(150, 25);
        Line line = new Line(pnt1, pnt2);
        Rectangle rect = new Rectangle(point, 60, 30);

        Point closest = line.closestIntersectionToStartOfLine(rect);

        Point intersectionPoint = new Point(50, 25);
        if (closest.equals(intersectionPoint)) {
            System.out.println("Good point finding!");
        } else {
            System.out.println("Not good intersection");
        }

        pnt1 = new Point(50, 40);
        pnt2 = new Point(110, 10);
        line = new Line(pnt1, pnt2);
        intersectionPoint = new Point(50, 40);

        closest = line.closestIntersectionToStartOfLine(rect);
        if (closest.equals(intersectionPoint)) {
            System.out.println("Good point finding!");
        } else {
            System.out.println("Not good intersection");
        }

        line = new Line(pnt2, pnt1);
        intersectionPoint = new Point(110, 10);
        closest = line.closestIntersectionToStartOfLine(rect);
        if (closest.equals(intersectionPoint)) {
            System.out.println("Good point finding!");
        } else {
            System.out.println("Not good intersection");
        }
    }

    /**
     * printing rectangles.
     */
    public void printRectangles() {
        BlockChecks checks = new BlockChecks();
        Block[] block = checks.createBlock();
        GUI gui = new GUI("title",800,600);
        Sleeper sleeper = new Sleeper();
        Ball ball=new Ball(400,30,8,Color.black);
   //     Ball ball2=new Ball(45,45,5,Color.RED,800,600);
        //Ball ball3=new Ball(67,20,4,Color.GREEN,800,600);
       // Velocity v = Velocity.fromAngleAndSpeed(185, 10);מקרה קצה
        Velocity v = Velocity.fromAngleAndSpeed(70, 15);
        ball.setVelocity(v);
     //   ball2.setVelocity(v);
       // ball3.setVelocity(v);
        GameEnvironment gb=new GameEnvironment();
        ball.setGameEnvironment(gb);
      //  ball2.setGameEnvironment(gb);
      //  ball3.setGameEnvironment(gb);
        for (int i = 0; i < 13; i++) {
            gb.addCollidable(block[i]);
        }
        while (true) {
            DrawSurface d = gui.getDrawSurface();
            for (int i = 0; i < 13; i++) {
                block[i].drawOn(d);
            }
            ball.drawOn(d);
            ball.moveOneStep();
//            ball2.drawOn(d);
//            ball2.moveOneStep();
//            ball3.drawOn(d);
//            ball3.moveOneStep();
            gui.show(d);
            sleeper.sleepFor(50);
        }
    }
    /**
     * main.
     * @param args - none
     */
    public static void main(String[] args) {

        BlockChecks checks = new BlockChecks();
        checks.checkIntersections();
        checks.pointShortestIntersection();

        // for printing random rectangles.
        checks.printRectangles();
    }


}
