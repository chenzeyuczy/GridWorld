import info.gridworld.actor.Bug;
import info.gridworld.grid.Location;

// Construct a Z-type bug that traces a shape of 'Z'.
public class ZBug extends Bug {
    private int steps, sideLength, state;

    // Default constructor.
    public ZBug() {
        this(3);
    }

    // Construct a Z-type bug that traces out a shape of 'Z' of a given size.
    public ZBug(int length) {
        steps = 0;
        sideLength = length;
        state = 0;
        setDirection(Location.EAST);
    }

    // Move to the next location of the 'Z' shape.
    public void act() {
        if (steps < sideLength) {
            if (canMove() && state < 3) {
                move();
                steps++;
            }
        } else {
            switch (state++) {
                case 0:
                    setDirection(Location.SOUTHWEST);
                    break;
                case 1:
                    setDirection(Location.EAST);
                    break;
            }
            steps = 0;
        }
    }
}
