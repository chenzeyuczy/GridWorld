/**
* This class implemente a basic structure class for SparseBoundedGrid.
* It is implemented in the form of one-direction linked list.
*/
public class SparseGridNode {
    // The object that occupies the location.
    private Object occupant;
    // The location of the column.
    private int col;
    // The pointer tp the next node.
    private SparseGridNode next;

    // Constructor.
    public SparseGridNode(int c, Object obj, SparseGridNode node) {
        col = c;
        occupant = obj;
        next = node;
    }

    // Set the next node.
    public void setNext(SparseGridNode other) {
        next = other;
    }

    // Return the next node.
    public SparseGridNode getNext() {
        return next;
    }

    // Set the object stored.
    public void setObj(Object obj) {
        occupant = obj;
    }

    // Return the object stored.
    public Object getObj() {
        return occupant;
    }

    // Set the column.
    public int getCol() {
        return col;
    }
}
