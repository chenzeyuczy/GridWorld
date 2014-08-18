package info.gridworld.maze;

import info.gridworld.actor.Actor;
import info.gridworld.actor.Bug;
import info.gridworld.actor.Flower;
import info.gridworld.actor.Rock;
import info.gridworld.grid.*;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Stack;
import java.util.Random;

import javax.swing.JOptionPane;

/**
 * A <code>MazeBug</code> can find its way in a maze. <br />
 * The implementation of this class is testable on the AP CS A and AB exams.
 */
public class MazeBug extends Bug {
    private Location next;
    private boolean isEnd = false;
    private Stack<Location> crossLocation = new Stack<Location>();
    private Integer stepCount = 0;
    private boolean hasShown = false;
    private int dirCount[] = {1, 1, 1, 1};
    //final message has been shown

    /**
     * Constructs a box bug that traces a square of a given side length
     * 
     * @param length
     *            the side length
     */
    public MazeBug() {
        setColor(Color.GREEN);
    }

    /**
     * Moves to the next location of the square.
     */
    public void act() {
        if (stepCount == 0) {
            crossLocation.push(getLocation());
        }
        if (isEnd) {
        //to show step count when reach the goal        
            if (!hasShown) {
                String msg = stepCount.toString() + " steps";
                JOptionPane.showMessageDialog(null, msg);
                hasShown = true;
            }
        } else if (canMove()) {
            move();
            //increase step count when move 
        } else {
            if (!hasShown) {
                String msg = "Fail to reach destination.";
                JOptionPane.showMessageDialog(null, msg);
                hasShown = true;
                isEnd = true;
            }
        }
    }

    /**
     * Find all positions that can be move to.
     * 
     * @param loc
     *            the location to detect.
     * @return List of positions.
     */
    public ArrayList<Location> getValid(Location loc) {
        int dirs[] = {Location.EAST, Location.SOUTH, Location.WEST, Location.NORTH};

        Grid<Actor> gr = getGrid();
        if (gr == null) {
            return null;
        }
        ArrayList<Location> valid = new ArrayList<Location>();
        for (int dir : dirs) {
            Location spot = loc.getAdjacentLocation(dir);
            if (!gr.isValid(spot)) {
                continue;
            }
            Actor actor = gr.get(spot);
            if (actor == null) {
                valid.add(spot);
            } else if ((actor instanceof Rock) && (actor.getColor().equals(Color.RED))) {
                valid.clear();
                valid.add(spot);
                return valid;
            }
        }
        return valid.isEmpty() ? null : valid;
    }

    /**
     * Tests whether this bug can move forward into a location that is empty or
     * contains a flower.
     * 
     * @return true if this bug can move.
     */
    public boolean canMove() {
        Grid<Actor> gr = getGrid();
        if (gr == null) {
            return false;
        }
        ArrayList<Location> locs = getValid(getLocation());
        System.out.print("Current location: " + getLocation() + ". ");
        if (locs == null) {
            if (crossLocation.isEmpty()) {
                return false;
            } else {
                crossLocation.pop();
                if (crossLocation.isEmpty()) {
                    return false;
                }
                next = crossLocation.peek();
                countDecrease();
            }
        } else {
            System.out.print("Choices : ");
            for (int i = 0; i < locs.size(); i++) {
                System.out.print(locs.get(i) + " ");
            }
            selectLocation(locs);
            crossLocation.push(next);
            countIncrease();
        }
        System.out.println("Next : " + next);
        System.out.print("Stack state: ");
        for (int i = 0; i < crossLocation.size(); i++) {
            System.out.print(crossLocation.get(i) + " ");
        }
        System.out.println();
        return true;
    }

    /**
     * Moves the bug forward, putting a flower into the location it previously
     * occupied.
     */
    public void move() {
        Grid<Actor> gr = getGrid();
        if (gr == null) {
            return;
        }
        Location loc = getLocation();
        if (gr.isValid(next)) {
            setDirection(getLocation().getDirectionToward(next));
            Actor other = gr.get(next);
            if (other instanceof Rock) {
                isEnd = true;
            }
            moveTo(next);
            stepCount++;
        } else {
            removeSelfFromGrid();
        }
        Flower flower = new Flower(getColor());
        flower.putSelfInGrid(gr, loc);
    }

    /**
    * Decrease the counter of steps in the opposite direction at the moment.
    */
    public void countIncrease() {
        int dir = getLocation().getDirectionToward(next);
        switch (dir) {
            case Location.EAST:
                dirCount[0] += 2;
                break;
            case Location.SOUTH:
                dirCount[1] += 2;
                break;
            case Location.WEST:
                dirCount[2] += 2;
                break;
            default:
                dirCount[3] += 2;
                break;
        }
    }

    /**
    * Increase the counter of steps in the current direction.
    */
    public void countDecrease() {
        int dir = getLocation().getDirectionToward(next);
        switch (dir) {
            case Location.EAST:
                dirCount[2]--;
                break;
            case Location.SOUTH:
                dirCount[3]--;
                break;
            case Location.WEST:
                dirCount[0]--;
                break;
            default:
                dirCount[1]--;
                break;
        }
    }

    /**
    * Selcect the next location from the arraylist.
    * The MazeBug goes ahead if possible.
    * Otherwise, it will choose a direction randomly
    * based on the steps it has walked through.
    * The more steps it makes,
    * it greater probability it will choose such direction.
    */
    public void selectLocation(ArrayList<Location> locs) {
        int total = 0, dir, i;
        int direction[] = new int [4];
        boolean canGo[] = {false, false, false, false};
        Location pos[] = new Location[locs.size()];
        for (int time : dirCount) {
            total += time;
        }
        for (i = 0; i < locs.size(); i++) {
            pos[i] = locs.get(i);
            switch (getLocation().getDirectionToward(pos[i])) {
                case Location.EAST:
                    canGo[0] = true;
                    direction[0] = i;
                    break;
                case Location.SOUTH:
                    canGo[1] = true;
                    direction[1] = i;
                    break;
                case Location.WEST:
                    canGo[2] = true;
                    direction[2] = i;
                    break;
                default:
                    canGo[3] = true;
                    direction[3] = i;
                    break;
            }
        }
        dir = (int)((getDirection() / 90) + 3) % 4;
        if (canGo[dir]) {
            next = pos[direction[dir]];
            return;
        }
        Random ran = new Random();
        do {
            int randomNum = (int)(ran.nextInt(total));
            if (randomNum < dirCount[0]) {
                dir = 0;
            } else if (randomNum < dirCount[0] + dirCount[1]) {
                dir = 1;
            } else if (randomNum < dirCount[0] + dirCount[1] + dirCount[2]) {
                dir = 2;
            } else {
                dir = 3;
            }
        } while (canGo[dir]);
        next = pos[direction[dir]];
    }
}
