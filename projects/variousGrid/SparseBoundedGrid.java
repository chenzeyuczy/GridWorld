import info.gridworld.grid.Location;
import info.gridworld.grid.AbstractGrid;

import java.util.ArrayList;

public class SparseBoundedGrid<E> extends AbstractGrid<E> {
    // Store the starting grid elements.
    private SparseGridNode[] occupants;
    private int rowNum, colNum;

    /**
    * Construct an empty bounded grid with the given numbers of rows and cols.
    */
    public SparseBoundedGrid(int rows, int cols) {
        if (rows <= 0) {
            throw new IllegalArgumentException("rows <= 0");
        }
        if (cols <= 0) {
            throw new IllegalArgumentException("cols <= 0");
        }
        occupants = new SparseGridNode[rows];
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

    // Judge whether a location is valid in the grid.
    public boolean isValid(Location loc) {
        return 0 <= loc.getRow() && loc.getRow() < getNumRows()
            && 0 <= loc.getCol() && loc.getCol() < getNumCols();
    }

    // Return all the locations occupied.
    public ArrayList<Location> getOccupiedLocations() {
        ArrayList<Location> locations = new ArrayList<Location>();
        SparseGridNode node;
        for (int i = 0; i < getNumRows(); i++) { 
            node = occupants[i];
            while (node != null) {
                locations.add(new Location(i, node.getCol()));
                node = node.getNext();
            }
        }
        return locations;
    }

    /**
    * Return the object in given position, 
    * if the location isn't occupied,
    * null will be return.
    */
    public E get(Location loc) {
        if (!isValid(loc)) {
            throw new IllegalArgumentException("Location " + loc + " is not valid");
        }
        SparseGridNode node = occupants[loc.getRow()];
        while (node != null) {
            if (node.getCol() == loc.getCol()) {
                return (E)node.getObj();
            }
            node = node.getNext();
        }
        return null;
    }

    /**
    * Put an object into given position,
    * the removed object will be returned.
    * If the location is not occupied,
    * function returns null.
    */
    public E put(Location loc, E obj) {
        if (!isValid(loc)) {
            throw new IllegalArgumentException("Location " + loc + " is not valid");
        }
        if (obj == null) {
            throw new NullPointerException("obj == null");
        }
        SparseGridNode node = occupants[loc.getRow()];
        while (node != null) {
            if (node.getCol() == loc.getCol()) {
                break;
            }
            node = node.getNext();
        }
        if (node == null) {
            node = new SparseGridNode(loc.getCol(), obj, occupants[loc.getRow()]);
            occupants[loc.getRow()] = node;
            return null;
        } else {
            E oldOccupant = (E)node.getObj();
            node.setObj(obj);
            return oldOccupant;
        }
    }

    // Remove the object in given location.
    public E remove(Location loc) {
        if (!isValid(loc)) {
            throw new IllegalArgumentException("Location " + loc + " is not valid");
        }
        E obj = get(loc);
        if (obj == null) {
            return null;
        }
        SparseGridNode node = occupants[loc.getRow()];
        if ((node != null) && (node.getCol() == loc.getCol())) {
            occupants[loc.getRow()] = node.getNext();
        } else {
            SparseGridNode front = node;
            while ((node != null) && (node.getCol() != loc.getCol())) {
                front = node;
                node = node.getNext();
            }
            if (node != null) {
                front.setNext(node.getNext());
            }
        }
        return obj;
    }
}
