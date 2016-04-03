import biuoop.DrawSurface;
import biuoop.GUI;

import java.awt.Color;

/**
 * A MultipleFramesBouncingBallsAnimation class.
 *
 * @author Nir Dunetz and Haim Gil.
 */
public class MultipleFramesBouncingBallsAnimation {
    /**
     * Creating multiple frames.
     *
     * @param d      is the surface.
     * @param x      is the x coordinate start of the frame on the surface.
     * @param y      is the y coordinate start of the frame on the surface.
     * @param width  is the width.
     * @param height is the height.
     * @param color  is the color.
     */
    public static void createFrame(DrawSurface d, int x, int y, int width, int height, Color color) {
        d.setColor(color);
        d.drawRectangle(x, y, width, height);

    }

    /**
     * Splitting array according to indexs to new array.
     *
     * @param sizesOfBalls is an integer array.
     * @param first        is an index.
     * @param end          is an index.
     * @return the new array.
     */
    public static int[] splitInput(int[] sizesOfBalls, int first, int end) {
        int[] newArr = new int[end - first];

        // Splitting original array from first index to end index.
        for (int i = first; i < end; i++) {
            newArr[i - first] = sizesOfBalls[i];
        }
        return newArr;
    }

    /**
     * Drawing the bouncing ball animation in two frames.
     *
     * @param size        is the size of the array.
     * @param ballsArray  represent the first half of the balls
     * @param ballsArray2 represent the second half of the balls
     */
    public static void drawingBouncingBallsAnimation(int size, Ball[] ballsArray, Ball[] ballsArray2) {
        // Create a window with the title "Six balls Two frames"
        // which is 620 pixels wide and 620 pixels high.
        GUI gui = new GUI("Six balls Two frames", 620, 620);
        biuoop.Sleeper sleeper = new biuoop.Sleeper(); // Create a sleeper generator.
        while (true) {
            DrawSurface d = gui.getDrawSurface(); // Create a surface.

            // Creating the balls and make them move.
            for (int j = 0; j < size / 2; j++) {
                Ball ballInFirstFrame = ballsArray[j];
                Ball ballInSecondFrame = ballsArray2[j];
                ballInFirstFrame.moveOneStep();
                ballInSecondFrame.moveOneStep();
                ballInFirstFrame.drawOn(d);
                ballInSecondFrame.drawOn(d);
            }
            //draw rectangles frames on surface
            createFrame(d, 50, 50, 450, 450, Color.gray); // Create the big frame from (50,50) to (500,500).
            createFrame(d, 450, 450, 150, 150, Color.yellow); // Create the small frame from (450,450) to (600,600).
            gui.show(d);
            sleeper.sleepFor(50); // Wait for 50 milliseconds.
        }
    }

    /**
     * MultipleFramesBouncingBallsAnimation main method.
     * Receives the balls as sizes from commandline argument,
     * and represent half of them in gray frame and the second half in yellow frame.
     *
     * @param args representing sizes of balls
     *             <p/>
     *             Example usage:
     *             java -cp biuoop-1.4.jar:. MultipleFramesBouncingBallsAnimation 12 2 3 4 2 9
     */
    public static void main(String[] args) {
        // Checking if there is input.
        if (args.length > 0) {
            int[] sizesOfBalls = MultipleBouncingBallsAnimation.stringsToInts(args);
            int[] firstFrame = splitInput(sizesOfBalls, 0, args.length / 2);
            int[] secondFrame = splitInput(sizesOfBalls, args.length / 2, args.length);
            Ball[] ballsArray = MultipleBouncingBallsAnimation.createBalls(firstFrame, 50, 50, 500, 500);
            Ball[] ballsArray2 = MultipleBouncingBallsAnimation.createBalls(secondFrame, 450, 450, 600, 600);
            drawingBouncingBallsAnimation(args.length, ballsArray, ballsArray2);
        }
    }
}
