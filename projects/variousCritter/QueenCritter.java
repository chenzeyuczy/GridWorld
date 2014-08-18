import info.gridworld.actor.*;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

import java.util.ArrayList;

/**
* A QueenCritter acts like the queen in the chess.
* If there are actors in the neighborhood can be jumped over,
* that is to say the next location in the same direction is empty too.
* it will choose one of them randomly and jump over the actor.
* The actor which the QueenCritter jumps over will disappear from the grid.
* Rock and Critter cannot be jumped over.
* If there is no actor can be jumped over,
* it moves to an adjacent empty location randomly.
*/
public class QueenCritter extends Critter {
    private ArrayList<Actor> actors = new ArrayList<Actor>();

    /**
    * Queen Critter searches alternative actors and locations first,
    * then randomly choose a location to move to.
    * After that it process the actor and move eventually.
    */
    public void act() {
        if (getGrid() == null) {
            return;
        }
        actors = getActors();
        ArrayList<Location> moveLocs = getMoveLocations();
        Location loc = selectMoveLocation(moveLocs);
        processActors(loc);
        makeMove(loc);
    }

    /**
    * QueenCritter gets its neighbors first.
    * Rocks and Critters are got rid of the ArrayList
    * If the adjacent location the QueenCritter may jump to is not empty,
    * the actor will be removed from the ArrayList too.
    * The alternative actors will be return in a ArrayList.
    **/
    public ArrayList<Actor> getActors() {
        Grid gr = getGrid();
        Location loc = getLocation();
        ArrayList<Actor> all = gr.getNeighbors(loc);
        ArrayList<Actor> actor = new ArrayList<Actor>();
        for (Actor a : all) {
            if (a instanceof Rock || a instanceof Critter) {
                continue;
            }
            int dir = loc.getDirectionToward(a.getLocation());
            Location next = a.getLocation().getAdjacentLocation(dir);
            if (gr.isValid(next) && gr.get(next) == null) {
                actor.add(a);
            }
        }
        return actor;
    }

    /**
      * The actor which QueenCritter jumps over is removed from the grid.
      * If no actor is jumped over, nothing happens.
      */
    public void processActors(Location loc) {
        if (actors.size() != 0) {
            int dir = getLocation().getDirectionToward(loc);
            Location target = getLocation().getAdjacentLocation(dir);
            Actor a = getGrid().get(target);
            a.removeSelfFromGrid();
        }
    }

    /**
      * If there are alternative actors,
      * the QueenCritter will choose one of them to jump over
      * and return the adjacent location it will move to,
      * otherwise it moves to one of its adjacent empty locations.
      * All of the choices is make randomly.
      */
    public ArrayList<Location> getMoveLocations() {
        Grid gr = getGrid();
        Location loc = getLocation();
        if (actors.size() == 0) {
            return gr.getEmptyAdjacentLocations(loc);
        }
        ArrayList<Location> locs = new ArrayList<Location>();
        for (Actor a : actors) {
            int dir = loc.getDirectionToward(a.getLocation());
            Location next = a.getLocation().getAdjacentLocation(dir);
            if (gr.isValid(next) && (gr.get(next) == null)) {
                locs.add(next);
            }
        }
        return locs;
    }

    /**
    * QueenCritter turn to the direction towards the destination fisrt,
    * and then move to the target.
    */
    public void makeMove(Location loc) {
        setDirection(getLocation().getDirectionToward(loc));
        super.makeMove(loc);
    }
}
