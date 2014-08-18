import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;
import info.gridworld.grid.Location;

import java.util.ArrayList;
import java.awt.Color;

/**
 * A <code>ChameleonCritter</code> takes on the color of neighboring actors as
 * it moves through the grid. <br />
 * The implementation of this class is testable on the AP CS A and AB exams.
 */
public class ModifiedChameleon extends Critter {
    private static final double DARKENING_FACTOR = 0.05;
    private static final Color DEFAULT_COLOR = Color.YELLOW;

    /**
      * Constructs a Chameleon critter in default color.
    */
    public ModifiedChameleon() {
        setColor(DEFAULT_COLOR);
    }

    /**
     * Construct a Chameleon critter in given color.
     */
    public ModifiedChameleon(Color color) {
        setColor(color);
    }

    /**
     * Randomly selects a neighbor and changes this critter's color to be
     * that of the neighbor.
     * If there are no neighbors, the color of the ChameleonCritter will darken.
     */
    public void processActors(ArrayList<Actor> actors) {
        int n = actors.size();
        if (n == 0) {
            Color c = getColor();
            int red = (int)(c.getRed() * (1 -DARKENING_FACTOR));
            int green = (int)(c.getGreen() * (1 -DARKENING_FACTOR));
            int blue = (int)(c.getBlue() * (1 -DARKENING_FACTOR));
            setColor(new Color(red, green, blue));
            return;
        }
        int r = (int) (Math.random() * n);
        Actor other = actors.get(r);
        setColor(other.getColor());
    }

    /**
     * Turns towards the new location as it moves.
     */
    public void makeMove(Location loc) {
        setDirection(getLocation().getDirectionToward(loc));
        super.makeMove(loc);
    }
}
