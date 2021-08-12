public class Node {
    
    private Node parent; // representative or root of this node
    private int noOfNodes; // no of nodes of this node.
    private int data; // data of the node

    /** Constructor that accepts an integer value for this node. */
    public Node(int data) {

        this.data = data; // Assign the parameter to instance's data.
        this.noOfNodes = 1; // If we create new node the initial value of their number of nodes is equal to 1.
        this.parent = this; // We set the parent of new created node as itself.
    }


    // Sets the parent of this node.
    public void setParent(Node parent) {

        this.parent = parent;
    }

    // Retrieves the parent of this node.
    public Node getParent() {

        return this.parent;
    }

    // Retrieves the data of this node.
    public int getData() {

        return this.data;
    }

    /**
     * Adds the number of number of nodes of this node. */
    public void addNumberOfNodes() {

        this.noOfNodes += 1;
    }

    /** Returns the number of nodes of this node. */
    public int getNumberOfNodes() {

        return this.noOfNodes;
    }
}
