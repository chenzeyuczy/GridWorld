import info.gridworld.actor.*;
import info.gridworld.grid.*;

import java.util.ArrayList;

/**
 * A KingCrab gets actors the same way a CrabCritter does.
 * A KingCrab causes each actor that it processes to move on location
 * further away from the KingCrab.
 * If the actor cannot move away, the KingCrab removes it from the world. 
 * After processing the actors, it moves like a CrabCritter.
 */
public class KingCrab extends CrabCritter {
    /**
     * Move each actor one cell further away from the KingCrab.
     * If actor cannot move away, KingCrab remove it from the world.
     */
    public void processActors(ArrayList<Actor> actors) {
        Grid gr = getGrid();
        Location loc = getLocation();
        for (Actor a : actors) {
            int dir = loc.getDirectionToward(a.getLocation());
            Location next = a.getLocation().getAdjacentLocation(dir);
            if (gr.isValid(next)) {
                a.moveTo(next);
            } else {
                a.removeSelfFromGrid();
            }
        }
    }
}
