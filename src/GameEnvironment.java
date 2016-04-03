import java.util.ArrayList;
import java.util.List;

/**
 * A GameEnvironment class.
 *
 * @author Nir Dunetz and Haim Gil.
 */
public class GameEnvironment {
    private List collidablesList;

    public GameEnvironment() {
        this.collidablesList = new ArrayList();
    }

    // add the given collidable to the environment.
    public void addCollidable(Collidable c) {
        collidablesList.add(c);
    }

    // Assume an object moving from line.start() to line.end().
    // If this object will not collide with any of the collidables
    // in this collection, return null. Else, return the information
    // about the closest collision that is going to occur.
    public CollisionInfo getClosestCollision(Line trajectory) {
        Collidable closestCollidable;
        Point closestHit, tmpHit=null;
        double closestDist, tmpDist;
        Collidable collidable;
        int i = 0;
        //check if there is any collidables.
        if (this.collidablesList.isEmpty()) {
            return null;
        }
        //find the first Collidable that collision.
        while (tmpHit == null && i < this.collidablesList.size()) {
            collidable = (Collidable) this.collidablesList.get(i);
            tmpHit = trajectory.closestIntersectionToStartOfLine(collidable.getCollisionRectangle());
            i++;
        }
        if (tmpHit == null) {
            return null;
        }
        i--;
        closestHit = tmpHit;
        closestCollidable = (Collidable) this.collidablesList.get(i);
        closestDist = trajectory.start().distance(closestHit);
        for (int j = i+1; j < this.collidablesList.size(); j++) {
            collidable = (Collidable) this.collidablesList.get(j);
            if (!collidable.getCollisionRectangle().isIntersectWithLine(trajectory)) {
                continue;
            }
            tmpHit = trajectory.closestIntersectionToStartOfLine(collidable.getCollisionRectangle());
            tmpDist = trajectory.start().distance(tmpHit);
            if (tmpDist < closestDist) {
                //update closest Point and Collidable.
                closestHit = tmpHit;
                closestCollidable = (Collidable) this.collidablesList.get(j);

            }
        }
        return new CollisionInfo(closestHit, closestCollidable);


    }

}
        


