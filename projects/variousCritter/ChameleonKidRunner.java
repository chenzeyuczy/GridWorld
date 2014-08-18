import info.gridworld.actor.*;
import info.gridworld.grid.Location;

import java.awt.Color;

/**
 * This class runs a world that contains chameleon-kid critters. <br />
 * This class is not tested on the AP CS A and AB exams.
 */
public class ChameleonKidRunner {
    public static void main(String[] args) {
        ActorWorld world = new ActorWorld();
        world.add(new Location(7, 8), new Rock());
        world.add(new Location(3, 3), new Flower());
        world.add(new Location(2, 8), new Rock(Color.BLUE));
        world.add(new Location(5, 5), new Bug(Color.PINK));
        world.add(new Location(1, 5), new Rock(Color.RED));
        world.add(new Location(7, 2), new Rock(Color.YELLOW));
        world.add(new Location(4, 4), new ChameleonKid());
        world.add(new Location(5, 8), new ChameleonKid());
        world.show();
    }
}
