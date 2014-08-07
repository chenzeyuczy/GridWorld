import info.gridworld.actor.*;

import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

import java.awt.Color;

/*
 * A jumper is an actor that can move and turn. It drops nothing as it mon\ves.
 * In each move, it can move forward two cells and "jump" over rocks and flowers.
*/
public class Jumper extends Bug  {

    // Default constructor.
    public Jumper() {
        setColor(Color.RED);
    }

    // Construct a jumper of a given color.
    public Jumper(Color jumperColor) {
        setColor(jumperColor);
    }

    // Move a jumper two cells forward.
    public void move() {
        Grid<Actor> gr = getGrid();
        if (gr == null)
            return;
        Location loc = getLocation();
        Location next = loc.getAdjacentLocation(getDirection());
        if (gr.isValid(next)) {
            next = next.getAdjacentLocation(getDirection());
            // Get the location two cells forward.
            if (gr.isValid(next))
                moveTo(next);
        } else 
            removeSelfFromGrid();
    }

    /**
     * Test whether the jumper can move forward into a location that is empty
     * contains a flower.
     * Return ture if the jumper can move.
    */
    public boolean canMove() {
        Grid<Actor> gr = getGrid();
        if (gr == null)
            return false;
        Location loc = getLocation();
        Location next = loc.getAdjacentLocation(getDirection());
        if (gr.isValid(next)) {
            next = next.getAdjacentLocation(getDirection());
            if (gr.isValid(next)) {
            // Judge whether thelocation two cells forward is valid.
                Actor neighbor = gr.get(next);
                return (neighbor == null) || (neighbor instanceof Flower);
                // Judge whether the target location is empty or contains a flower.
            } 
        }
        return false;
    }
}
