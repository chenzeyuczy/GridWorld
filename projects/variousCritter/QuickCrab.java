import info.gridworld.actor.*;
import info.gridworld.grid.*;

import java.util.ArrayList;

/**
 * A QuickCrab processes actors the same way a CrabCritter does.
 * A QuickCrab moves to one of the two locationds, randomly selected,
 * that are two spaces to its left or right,
 * if that location zmc the intervening location are both empty.
 * Otherwise, a QuickCrab moves like a CrabCritter.
 */
public class QuickCrab extends CrabCritter {
    /**
     * A QuickCrab searches for the locations immediately in front,
     * to the left-front or to the left-front of it.
     */
    public ArrayList<Actor> getActors() {
        ArrayList<Actor> actors = new ArrayList<Actor>();
        int[] dirs = {Location.AHEAD, Location.HALF_LEFT, Location.HALF_RIGHT};
        for (Location loc : super.getLocationsInDirections(dirs)) {
            Actor a = getGrid().get(loc);
            if (a != null) {
                actors.add(a);
            }
        }
        return actors;
    }

    /**
    * Find the valid adjacent locations of the critter
    * in different directions.
    * Return a set of valid locations that are neighbors
    * of the current location in the given directions.
    */
    public ArrayList<Location> getLocationsInDirections(int[] directions) {
        ArrayList<Location> locs = new ArrayList<Location>();
        Grid gr = getGrid();
        Location loc = getLocation();
        for (int dir : directions) {
            Location next = loc.getAdjacentLocation(getDirection() + dir);
            if (gr.isValid(next)) {
                if (gr.get(next) != null) {
                    continue;
                }
                next = next.getAdjacentLocation(getDirection() + dir);
                if (gr.isValid(next) && (gr.get(next) == null)) {
                    locs.add(next);
                }
            }
        }
        return locs;
    }
}
