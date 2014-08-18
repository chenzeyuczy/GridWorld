import info.gridworld.grid.Location;
import info.gridworld.grid.AbstractGrid;

import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

/**
 * An <code>SparseBoundedGrid</code> is a rectangular grid with an bounded number of rows and
 * columns. <br />
 * The implementation of this class is testable on the AP CS AB exam.
 */
public class SparseBoundedGrid2<E> extends AbstractGrid<E>
{
    private Map<Location, E> occupantMap;
    private int rowNum, colNum;

    /**
     * Constructs an empty sparse bounded grid with given rows and columns.
     */
    public SparseBoundedGrid2(int rows, int cols) {
        occupantMap = new HashMap<Location, E>();
        rowNum = rows;
        colNum = cols;
    }

    // Return the number of rows.
    public int getNumRows() {
        return rowNum;
    }

    // Return the number of columns.
    public int getNumCols() {
        return colNum;
    }

    // Judge whether the location is valid in the grid.
    public boolean isValid(Location loc) {
        return 0 <= loc.getRow() && loc.getRow() < getNumRows()
            && 0 <= loc.getCol() && loc.getCol() < getNumCols();
    }

    // Return all the occupied locations.
    public ArrayList<Location> getOccupiedLocations() {
        ArrayList<Location> a = new ArrayList<Location>();
        for (Location loc : occupantMap.keySet()) {
            a.add(loc);
        }
        return a;
    }

    /**
    * Return the object in given location,
    * return null if empty.
    */
    public E get(Location loc) {
        if (!isValid(loc)) {
            throw new IllegalArgumentException("Location " + loc + " is not valid");
        }
        if (loc == null) {
            throw new NullPointerException("loc == null");
        }
        return occupantMap.get(loc);
    }

    /**
    * Put an object into given location, 
    * return the previous object in the location.
    */
    public E put(Location loc, E obj) {
        if (!isValid(loc)) {
            throw new IllegalArgumentException("Location " + loc + " is not valid");
        }
        if (loc == null) {
            throw new NullPointerException("loc == null");
        }
        if (obj == null) {
            throw new NullPointerException("obj == null");
        }
        return occupantMap.put(loc, obj);
    }

    /**
      * Remove the object in given location,
      * return the previous object.
      */
    public E remove(Location loc) {
        if (!isValid(loc)) {
            throw new IllegalArgumentException("Location " + loc + " is not valid");
        }
        if (loc == null) {
            throw new NullPointerException("loc == null");
        }
        return occupantMap.remove(loc);
    }
}
