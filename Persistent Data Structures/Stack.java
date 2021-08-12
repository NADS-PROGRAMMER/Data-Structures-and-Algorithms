public class Stack {
    
    private Node headOfHistory; // The head of history nodes of this stack.
    private Node head;
    private Node tail;

    public void push(int data) {

        // Check if head is null
        if (head == null) {

            this.head = new Node(data);
            this.tail = this.head;
            return;
        }
        
        Node previousTail = this.tail;
        this.tail = new Node(data);
        previousTail.setNext(this.tail);
    }

    public int pop() {

        int deletedData = 0;

        if (!this.isEmpty()) {

            /** If the next node of head is null
             * then we set the head node of history
             * to copy the head of this stack.
             */
            if (this.head.getNext() == null) {

                deletedData = this.head.getData(); // this is the data of the data we want to delete.

                // If the head of history is null then we only copy the value of the address of head
                if (this.headOfHistory == null)
                    this.headOfHistory = this.head;
                else {

                    /**
                     * Get the last node of histories and set the next
                     * address to the node of the current history
                     */
                    Node currentHistory = this.head;
                    Node lastNodeOfHistories = getLastNode(this.headOfHistory);
                    lastNodeOfHistories.setNext(currentHistory);
                }
                this.head = null;
                return deletedData;
            }

            // This will execute if the stack size is more than 1
            Node currentNode = this.head;

            while (currentNode.getNext() != this.tail) {

                currentNode = currentNode.getNext();
            }
            currentNode.addToHistory(this.tail);
            deletedData = currentNode.getNext().getData();
            this.tail = currentNode;
            currentNode.setNext(null);
        }
        return deletedData;
    }

    /**
     * Get the top value of the stack
     * without removing it.
     * @return
     * - the value of the top of the stack.
     */
    public int peek() {

        if (!this.isEmpty()) {

            return this.tail.getData();
        }
        return -1;
    }

    /** Display the current values of the list. */
    public String Display() {

        String elements = "";
        
        if (!this.isEmpty()) {
            
            Node currentNode = this.head;
            
            while (currentNode != null) {

                elements += currentNode.getData() + " ";
                currentNode = currentNode.getNext();
            }
        }
        return elements;
    }

    /**
     * Get the last node of the 
     * head of histories of nodes
     * @param thisNode
     * - head of history nodes
     * @return
     * - the last node of the history.
     */
    private Node getLastNode(Node thisNode) {

        Node current = thisNode;

        while (current.getNext() != null) {

            current = current.getNext();
        }
        return current;
    }

    public String DisplayHistory() {

        Node currentNode = null;
        String elements = "";

        /** If the head of history is not equal to null
         * then we first get it before the current values of the list.
         */
        if (this.headOfHistory != null) {

            currentNode = this.headOfHistory;

            while (currentNode != null) {

                elements += " [ " + currentNode.getData() + " ] -> ";
                elements += currentNode.getAllHistories();
                currentNode = currentNode.getNext();
            }
        }

        /** If the head of the current list is not empty
         * then we also need to traverse it to display in the 
         * console.
         */
        if (!this.isEmpty())
         currentNode = this.head;

        while (currentNode != null) {

            elements += currentNode.getData() + " -> ";
            elements += currentNode.getAllHistories();
            currentNode = currentNode.getNext();
        }
        return elements;
    }

    /** Determines if the this list is empty */
    public boolean isEmpty() {

        return this.head == null;
    }
}