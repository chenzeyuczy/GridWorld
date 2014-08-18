import info.gridworld.actor.*;
import info.gridworld.grid.*;

import java.util.ArrayList;
import java.awt.Color;

/**
 * BlusterCritter extends Critter.
 * A BlusterCritter counts the number of neighbors
 * within two steps of its current location.
 * Its color gets brighter if the number is less than its value of courage,
 * and gets darker otherwise.
 * Beside this, a BlusterCritter acts like a Critter.
 */
public class BlusterCritter extends Critter {
    private static final int DEFAULT_COURAGE = 2;
    private static final double CHANGE_FACTOR = 0.05;
    private static final double MAX_COLOR_VALUE = 255;
    private int courage;
    private static final int DX[] = {-2, -1, 0, 1, 2};
    private static final int DY[] = {-2, -1, 0, 1, 2};

    /**
     * Default constructor.
     */
    public BlusterCritter() {
        courage = DEFAULT_COURAGE;
    }

    /**
     * Construct a BlusterCritter with given courage value.
     */
    public BlusterCritter(int givenCourage) {
        courage = givenCourage;
    }

    /**
     * A BlusterCritter searches for all the neighbors
     * within two steps of its current location.
     */
    public ArrayList<Actor> getActors() {
        Grid<Actor> gr = getGrid();
        Location loc = getLocation();
        Actor actor;
        ArrayList<Actor> actors = new ArrayList<Actor>();
        for (int x: DX) {
            for (int y: DY) {
                Location next = new Location(loc.getRow() + x, loc.getCol() + y);
                if (!gr.isValid(next)) {
                    continue;
                }
                if (loc.equals(next)) {
                    continue;
                }
                actor = gr.get(next);
                if (actor instanceof Critter) {
                    actors.add(actor);
                }
            }
        }
        return actors;
    }

    /**
     * If there are fewer than c critters,
     * the BlusterCritter's color gets brighter,
     * otherwise it gets darker.
     * c stands for the courage of the critter.
     */
    public void processActors(ArrayList<Actor> actors) {
        Color c = getColor();
        int red, green, blue;
        if (actors.size() < courage) {
            red = (int)(c.getRed() + (MAX_COLOR_VALUE - c.getRed()) * CHANGE_FACTOR);
            green = (int)(c.getGreen() + (MAX_COLOR_VALUE - c.getGreen()) * CHANGE_FACTOR);
            blue = (int)(c.getBlue() + (MAX_COLOR_VALUE - c.getBlue()) * CHANGE_FACTOR);
        } else {
            red = (int)(c.getRed() * (1 - CHANGE_FACTOR));
            green = (int)(c.getGreen() * (1 - CHANGE_FACTOR));
            blue = (int)(c.getBlue() * (1 - CHANGE_FACTOR));
        }
        setColor(new Color(red, green, blue));
    }
}
