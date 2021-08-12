public class SkipList {

    private Node head;
    private Node tail;

    public void createSkipList(DoublyLinkedList list) {

        int nodesToSkip = (int) Math.sqrt(list.size()); // REQUIRED size of the skip list.

        /** Instantiating the head of the skip list */
        this.head = new Node(list.getHead().getData());
        this.head.setBottom(list.getHead());
        this.head.getBottom().setTop(this.head);
        this.tail = this.head;

        while (this.size() != nodesToSkip) {

            Node currentNode = this.tail.getBottom(); // Get the bottom of the current tail

            for (int count = 1; count <= nodesToSkip + 1; count++) {

                currentNode = currentNode.getNext();
            }

            if (currentNode == null) 
                break;

            Node previousTail = this.tail; // Store the address of the current tail node.
            this.tail = new Node(currentNode.getData()); // Instantiate the tail
            this.tail.setBottom(currentNode); // Set the bottom of this tail to the current node.
            this.tail.getBottom().setTop(this.tail); // Set the top of the bottom node to this tail.
            this.tail.setPrevious(previousTail); // Set the previous of this tail to the previous tail.
            previousTail.setNext(this.tail); // Set tne next of the previous tail to this new tail.
        }
    }

    /**
     * Count the number of elements in this list.
     * 
     * @return - the number of elements in the list.
     */
    public int size() {

        Node currentNode = this.head;
        int size = 0;

        while (currentNode != null) {

            currentNode = currentNode.getNext();
            ++size;
        }

        return (size);
    }

    /**
     * Check if this skip list is empty
     * @return
     * - true if it is emtpy, otherwise, false.
     */
    public boolean isEmpty() {

        return this.head == null;
    }
    /**
     * Display the elements of this skip list.
     * 
     * @return - the string representation of the elements in the list.
     */
    public String displayFromHead() {

        Node currentNode = this.head;
        String elements = "[ ";

        while (currentNode != null) {

            elements += currentNode.getData() + " ";
            currentNode = currentNode.getNext();
        }

        return "SKIP LIST FROM HEAD: " + (elements) + "]";
    }

    /**
     * Display the elements of this skip list in reverse order.
     * 
     * @return - the string representation of the elements in the list.
     */
    public String displayFromTail() {

        Node currentNode = this.tail;
        String elements = "[ ";

        while (currentNode != null) {

            elements += currentNode.getData() + " ";
            currentNode = currentNode.getPrevious();
        }

        return "SKIP LIST FROM TAIL: " +  (elements) + "]";
    }
}
