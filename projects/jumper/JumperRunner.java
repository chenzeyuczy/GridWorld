import info.gridworld.actor.*;

/** 
 * This class runs a world that contains jumpers.
 */
public class JumperRunner {
    public static void main(String[] args) {
        ActorWorld world = new ActorWorld();
        world.add(new Bug());
        world.add(new Rock());
        world.add(new Flower());
        world.add(new Jumper());
        world.show();
    }
}

