import java.util.*;

public class Node {

    private LinkedList<Node> previousNodes = new LinkedList<Node>(); // Container of the history nodes for every node.
    private Node next;
    private int data;

     public Node (int data) {

        this.data = data;
    }

    /**
     * Checks if the history of this node is
     * empty.
     * @return
     * - returns true if the history list is empty, otherwise, false.
     */
    public boolean isHistoryEmpty() {

        return previousNodes.isEmpty();
    }

    /**
     * Adds a new history node to the
     * list of this node.
     * @param node
     * - Node that deleted on the stack.
     */
    public void addToHistory(Node node) {

        previousNodes.add(node);
    }

    /**
     * Get all the history nodes of the specific node.
     * @return
     */
    public String getAllHistories() {

        String elements = "";

        if (!isHistoryEmpty()) {

            for (int i = 0; i < previousNodes.size(); i++) {

            elements += " [ " + previousNodes.get(i).getData() + " ] -> ";

            /**
             * Recurse to the particular node if the current node has a node
             * that also has a history node. */
            if (!previousNodes.get(i).isHistoryEmpty())
                elements += previousNodes.get(i).getAllHistories(); 
            }
        }
        return elements;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }
}