import info.gridworld.actor.Actor;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

import java.util.ArrayList;
import java.awt.Color;

/**
 * A <code>ChameleonCritter</code> takes on the color of neighboring actors as
 * it moves through the grid. <br />
 * The implementation of this class is testable on the AP CS A and AB exams.
 */
public class ChameleonKid extends ModifiedChameleon {

    /**
     * Default constructor.
     */
    public ChameleonKid() {}

    /**
     * Construct a ChameleonKid in given color.
     */
    public ChameleonKid(Color color) {
        super(color);
    }

    /**
     * Return a list of actors immediately in front of or behind the Critter.
     */
    public ArrayList<Actor> getActors() {
        ArrayList<Actor> actors = new ArrayList<Actor>();
        Grid<Actor> gr = getGrid();
        Location loc = getLocation();
        Location next = loc.getAdjacentLocation(getDirection());
        Actor actor;
        if (gr.isValid(next)) {
            actor = gr.get(loc.getAdjacentLocation(getDirection()));
            if (actor != null) {
                actors.add(actor);
            }
        }
        next = loc.getAdjacentLocation(getDirection() + Location.HALF_CIRCLE);
        if (gr.isValid(next)) {
            actor = gr.get(loc.getAdjacentLocation(getDirection() + Location.HALF_CIRCLE));
            if (actor != null) {
                actors.add(actor);
            }
        }
        return actors;
    }
}
