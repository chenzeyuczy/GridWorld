import info.gridworld.actor.Critter;
import info.gridworld.actor.Rock;
import info.gridworld.actor.Actor;

import java.util.ArrayList;

/**
 * A RockHound is a critter that remove its neighbor rocks from the grid.
 */
public class RockHound extends Critter {
    /**
     *  The neighbour rocks are removed from the grid.
     */
    public void processActors(ArrayList<Actor> actors) {
        for (Actor a: actors) {
            if (a instanceof Rock) {
                a.removeSelfFromGrid();
            }
        }
    }
}
