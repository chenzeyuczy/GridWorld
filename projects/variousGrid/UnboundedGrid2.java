import info.gridworld.grid.AbstractGrid;
import info.gridworld.grid.Location;

import java.util.ArrayList;

/**
 * A <code>UnboundedGrid2</code> is a rectangular grid with infinite
 * rows and columns. <br />
 * The implementation of this class is testable on the AP CS AB exam.
 */
public class UnboundedGrid2<E> extends AbstractGrid<E> {
    private Object[][] occupantArray;
    // the array storing the grid elements
    private int dim;
    // Store the dimension of the grid.
    private static final int DEFAULT_SIZE = 16;

    /**
     * Constructs an empty unbounded grid with default dimension.
     */
    public UnboundedGrid2() {
        occupantArray = new Object[DEFAULT_SIZE][DEFAULT_SIZE];
        dim = DEFAULT_SIZE;
    }

    public int getNumRows() {
        return -1;
    }

    public int getNumCols() {
        return -1;
    }

    // Return false if either of the number of row or colume is negative.
    public boolean isValid(Location loc) {
        return 0 <= loc.getRow() && 0 <= loc.getCol();
    }

    public ArrayList<Location> getOccupiedLocations() {
        ArrayList<Location> locations = new ArrayList<Location>();

        // Look at all grid locations.
        for (int r = 0; r < dim; r++) {
            for (int c = 0; c < dim; c++) {
                // If there's an object at this location, put it in the array.
                Location loc = new Location(r, c);
                if (get(loc) != null) {
                    locations.add(loc);
                }
            }
        }

        return locations;
    }

    public E get(Location loc) {
        if (!isValid(loc)) {
            throw new IllegalArgumentException("Location " + loc
                    + " is not valid");
        }
        if (loc.getRow() >= dim || loc.getCol() >= dim) {
            return null;
        }
        return (E) occupantArray[loc.getRow()][loc.getCol()];
        // unavoidable warning
    }

    public E put(Location loc, E obj) {
        if (!isValid(loc)) {
            throw new IllegalArgumentException("Location " + loc
                    + " is not valid");
        }
        if (obj == null) {
            throw new NullPointerException("obj == null");
        }

        // Resize the grid if index values are out of range.
        if (loc.getRow() >= dim || loc.getCol() >= dim) {
            int oldDim = dim;
            do {
                dim *= 2;
            } while (loc.getRow() >= dim && loc.getCol() >= dim);
            Object[][] newArray = new Object[dim][dim];
            for (int i = 0; i < oldDim; i++) {
                for (int j = 0; j < oldDim; j++) {
                    newArray[i][j] = occupantArray[i][j];
                }
            }
            occupantArray = newArray;
        }

        E oldOccupant = get(loc);
        // Add the object to the grid.
        occupantArray[loc.getRow()][loc.getCol()] = obj;
        return oldOccupant;
    }

    /**
      * Remove the object in the given location.
      * Return the removed object,
      * return null if empty.
      */
    public E remove(Location loc) {
        if (!isValid(loc)) {
            throw new IllegalArgumentException("Location " + loc
                    + " is not valid");
        }
        
        // Remove the object from the grid.
        E r = get(loc);
        if (r != null) {
            occupantArray[loc.getRow()][loc.getCol()] = null;
        }
        return r;
    }
}
