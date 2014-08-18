import info.gridworld.actor.Bug;

// Construct a spiral bug that traces a spiral.
public class SpiralBug extends Bug {
    private int steps;
    private int sideLength;

    // Default constructor.
    public SpiralBug() {
        this(1);
    }

    // Construct a spiral bug that traces out a spiral with a given initial length.
    public SpiralBug(int length) {
        steps = 0;
        sideLength = length;
    }

    // Move to the next location of the spiral.
    public void act() {
        if (steps < sideLength && canMove()) {
            move();
            steps++;
        } else {
            turn();
            turn();
            steps = 0;
            sideLength++;
        }
    }
}
