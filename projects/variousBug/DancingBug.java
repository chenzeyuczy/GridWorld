import info.gridworld.actor.Bug;
import info.gridworld.grid.Location;

// Construct a dancing bug that traces out a DIY shape.
public class DancingBug extends Bug {
    private int state;
    private int direction[] = {1, 1, 2, 3, 5, 8};

    // Default constructor.
    public DancingBug() {
        state = 0;
    }

    // Move to the next location of the shape.
    public void act() {
        setDirection(getDirection() + direction[state++] * Location.HALF_RIGHT);
        if (canMove()) {
            move();
        }
        state %= direction.length;
    }
}
