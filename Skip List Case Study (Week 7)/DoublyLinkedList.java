public class DoublyLinkedList {

    private Node head;
    private Node tail;

    /**
     * Returns the first node of this list.
     * 
     * @return - the head of this list.
     */
    public Node getHead() {

        return this.head;
    }

    /**
     * Returns the last node of this list.
     * 
     * @return - the tail of this list.
     */
    public Node getTail() {

        return this.tail;
    }

    /**
     * Adds new element in the list.
     * 
     * @param data - an integer value to be added.
     */
    public void add(int data) {

        // Check if head is null
        if (head == null) {

            head = new Node(data);
            tail = head;
            return;
        }

        Node previousTail = tail;
        tail = new Node(data);
        previousTail.setNext(tail);
        tail.setPrevious(previousTail);
    }

    /**
     * Display the list from head.
     * 
     * @return - the string representation of the elements in the list.
     */
    public String DisplayFromHead() {

        Node currentNode = this.head;
        String elements = "[ ";

        while (currentNode != null) {

            elements += currentNode.getData() + " ";
            currentNode = currentNode.getNext();
        }

        return "LIST: " + (elements) + "]";
    }

    /**
     * Display the list from tail
     * 
     * @return - the string representation of the elements in the list.
     */
    public String DisplayFromTail() {

        Node currentNode = this.tail;
        String elements = "[ ";

        while (currentNode != null) {

            elements += currentNode.getData() + " ";
            currentNode = currentNode.getPrevious();
        }

        return "LIST: " + (elements) + "]";
    }

    /**
     * Search the element from the head of the list
     * @param elementToFind
     * - element to find in the list.
     * @return
     * - it will print the number of searched and skipped nodes after the traversal,
     * otherwise, print ELEMENT NOT FOUND!
     */
    public String searchFromHead(int elementToFind) {

        Node currentNode = this.head;
        String result = ""; // variable for storing the information of number of searched and skipped nodes.

        int sizeOfSkipList = (int) Math.sqrt(this.size()); // size of skiplist to determine the number of skip nodes.
        int noOfSearchedNodes = 0; // number of searched nodes after the traversal.
        int noOfSkipNodes = 0; // number of skipped nodes after the traversal.

        /**Check the size of skip list to avoid null pointer exception when accessing the data of the next node address. */
        if (sizeOfSkipList > 1 && currentNode.getTop().getNext().getData() <= elementToFind) {

            ++noOfSearchedNodes;
            currentNode = currentNode.getTop();

            while (currentNode != null) {

                ++noOfSearchedNodes;

                if (currentNode.getNext() != null) {

                /** Check if the next element of the current node in the skip list is greater than the element that we are finding
                    so that we can stop the traversal. */ 
                    if (currentNode.getNext().getData() > elementToFind) {

                        currentNode = currentNode.getBottom();
                        break;
                    }

                    currentNode = currentNode.getNext();
                    ++noOfSkipNodes;
                } else {
                    currentNode = currentNode.getBottom();
                    break;
                }
            }
        }
        // Find the element to the normal lane.
        result = find(currentNode, elementToFind, noOfSearchedNodes, true);

        if (!result.isEmpty())
            return result + "NUMBER OF SKIPPED NODES: " + (noOfSkipNodes * sizeOfSkipList);

        return "ELEMENT NOT FOUND!";
    }

    /**
     * A method for traversing the normal lane.
     * @param currentNode
     * - currentNode that may hold the element that we are finding.
     * @param elementToFind
     * - element to find.
     * @param noOfSearchedNodes
     * - noOfSearched nodes of traversal from express lane.
     * @param isFromHead
     * - true if the traversal is from head, otherwise, false.
     * @return
     * - the number of search nodes, null if the element is not found.
     */
    private String find(Node currentNode, int elementToFind, int noOfSearchedNodes, boolean isFromHead) {

        ++noOfSearchedNodes; // INCREMENT the noOfSearchedNodes variable.

        /**
         * We return an empty string if the current node is null, the current node data
         * is greater than (for SEARCHING FROM HEAD) or less than (for SEARCHING FROM
         * TAIL) to the element that we are finding.
         */
        if (currentNode == null || (currentNode.getData() > elementToFind && isFromHead)
                || (currentNode.getData() < elementToFind && !isFromHead))
            return "";

        if (currentNode.getData() == elementToFind)
            return String.format("%n%nNUMBER OF SEARCHED NODES: %d%n", noOfSearchedNodes);

        if (isFromHead)
            // This recursive call is for searching from head.
            return find(currentNode.getNext(), elementToFind, noOfSearchedNodes, isFromHead);

        // This recursive call is for searching from tail.
        return find(currentNode.getPrevious(), elementToFind, noOfSearchedNodes, isFromHead);
    }

    /**
     * Search the element from the tail of the list.
     * @param elementToFind
     * - element to find.
     * @return
     * - the number of searched and skipped nodes after the traversal.
     */
    public String searchFromTail(int elementToFind) {

        Node currentNode = this.tail;
        String result = "";
        int sizeOfSkipList = (int) Math.sqrt(this.size());
        int noOfSearchedNodes = 0;
        int noOfSkipNodes = 0;
        boolean topNotFound = true; // A boolean indivation if the current node has a top node.

        /** This part is checking if the value of current node 
         * is equal to the element that we are finding, and also
         * for checking if the current node has a top address so that we 
         * can go the express lane.
         */
        while (topNotFound) {

            ++noOfSearchedNodes;

            if (currentNode.getData() == elementToFind) 
                return String.format("NUMBER OF SEARCHED NODES: %d%nNUMBER OF SKIP NODES: %d%n", noOfSearchedNodes,
                        noOfSkipNodes);
            
            if (currentNode.getTop() != null)
                break;

            currentNode = currentNode.getPrevious();
        }

        /**
         * Check if the next element in the express lane is greater than the element
         * that we are finding
         */
        if (sizeOfSkipList > 1 &&  currentNode.getTop().getPrevious().getData() >= elementToFind) {

            // Go to the express lane.
            currentNode = currentNode.getTop();

            while (currentNode != null) {

                ++noOfSearchedNodes;
                if (currentNode.getPrevious() != null) {

                    if (elementToFind > currentNode.getPrevious().getData()) {

                        currentNode = currentNode.getBottom();
                        break;
                    }

                    currentNode = currentNode.getPrevious();
                    ++noOfSkipNodes;
                } else {

                    currentNode = currentNode.getBottom();
                    break;
                }
            }
        } else
            currentNode = currentNode.getPrevious();

        // Get the result from the normal lane.
        result = find(currentNode, elementToFind, noOfSearchedNodes, false);

        if (!result.isEmpty()) {

            return String.format("%sNUMBER OF SKIPPED NODES: %d%n", result, noOfSkipNodes * sizeOfSkipList);
        }
        return ("ELEMENT NOT FOUND!");
    }

    /** Bubble sort algorithm 
     * that sorts the list in ASCENDING ORDER.
     */
    public void sortInAsc() {

        Node currentNode = this.head;
        boolean isNotSatisfied = false;

        while (currentNode.getNext() != null) {

            if (currentNode.getData() > currentNode.getNext().getData()) {

                int temp = currentNode.getData();
                currentNode.setData(currentNode.getNext().getData());
                currentNode.getNext().setData(temp);
                isNotSatisfied = true;
            } else {
                currentNode = currentNode.getNext();
            }
        }
        // If there are still greater than element on the left side, recurse again.
        if (isNotSatisfied)
            sortInAsc(); // recurse again.
    }

    public boolean isEmpty() {

        return this.head == null;
    }
    /**
     * Counts the number of elements in the list.
     * 
     * @return - the number of elements in the list
     */
    public int size() {

        Node currentNode = this.head;
        int size = 0;

        while (currentNode != null) {

            currentNode = currentNode.getNext();
            size++;
        }

        return size;
    }
}
