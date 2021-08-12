import java.util.*;

public class Treap {
    
    private TreeNode root;

    /**
     * Inserts a new node following the
     * BST insertion rule and heap invariant.
     * 
     * @param data - data to be inserted.
     */
    public void insert(int data) {

        Random rand = new Random(); // Instance of a Random class that generates random number.
        int priority = rand.nextInt(50); // Generates the random number up to 50

        TreeNode newNode = new TreeNode(data);
        newNode.setPriority(priority);

        if (this.root == null) {

            this.root = new TreeNode(data);
            this.root.setPriority(priority);
            return;
        }

        TreeNode testRoot = insertImplementation(this.root, newNode);
        rotation(testRoot); // See the implementations below.
    }

    /**
     * The implementation of the insert operation.
     * @param root
     * - The root of the tree.
     *
     * @param newNode
     * - The new node to be inserted.
     * 
     * @return
     * - Returns the new position of the new inserted node to check if it is satisfy the heap invariant.
     */
    private TreeNode insertImplementation(TreeNode root, TreeNode newNode) {

        if (newNode.getData() <= root.getData()) {

            if (root.getLeft() == null) {

                root.setLeft(newNode);
                root.getLeft().setParent(root);
            }
            else
                insertImplementation(root.getLeft(), newNode);

        } else if (newNode.getData() > root.getData()) {

            if (root.getRight() == null) {
                root.setRight(newNode);
                root.getRight().setParent(root);
            }
            else
                insertImplementation(root.getRight(), newNode);
        }
        return newNode;
    }

    public String Display() {

        Queue<TreeNode> q = new LinkedList<>();
        String nodes = "";

        if (this.root != null)
            q.offer(this.root);

        return displayImplementation(q, nodes);
    }

    /**
     * The implementation of display operation of the tree.
     * @param q
     * - a Queue that prioritize first the root and its two children.
     * @param nodes
     * - A string that concatenates the value of the nodes.
     * @return
     * - the string representation of the root value.
     */
    private String displayImplementation(Queue<TreeNode> q, String nodes) {

        if (q.isEmpty())
            return nodes;

            if (!q.isEmpty() && q.peek().getParent() != null)
                nodes +=  "\nPARENT: " + q.peek().getData() + String.format("(%d)", q.peek().getPriority());
            else
                nodes +=  "\nPARENT: " + q.peek().getData() + String.format("(%d)", q.peek().getPriority());

            if (q.peek().getLeft() != null) {

                q.offer(q.peek().getLeft());
                nodes += "\nLEFT CHILD: " + q.peek().getLeft().getData() + String.format("(%d)", q.peek().getLeft().getPriority());
            }
            else
                nodes += "\nLEFT CHILD: NULL"; 

            if (q.peek().getRight() != null) {

                q.offer(q.peek().getRight());
                nodes += "\nRIGHT CHILD: " + q.peek().getRight().getData() + String.format("(%d)", q.peek().getRight().getPriority()) + "\n";
            }
            else
                nodes += "\nRIGHT CHILD: NULL\n";
        q.poll();

        return displayImplementation(q, nodes);
    }

    /**
     * A function that rotate the tree clockwise.
     * @param root
     * - the root to be rotated
     * @return
     * - a TreeNode with its new position and be also checked if
     * the heap property is still violated.
     */
    private TreeNode rightRotate(TreeNode root) {

        if (root.getRight() != null) {
            root.getParent().setLeft(root.getRight());
            root.getRight().setParent(root.getParent());
            root.setRight(null);
        }
        else
            root.getParent().setLeft(null);

        // set the parent as a right child of current root.
        root.setRight(root.getParent());
        // set the parent of parent's current root as the parent of its new right child.
        root.setParent(root.getRight().getParent());

        if (root.getParent() != null && root.getParent().getData() < root.getData())
            root.getParent().setRight(root);
        else if (root.getParent() != null && root.getParent().getData() >= root.getData())
            root.getParent().setLeft(root);

        root.getRight().setParent(root);

        return root;
    }

    /**
     * A function that rotate the tree counter-clockwise.
     * @param root
     * - the root to be rotated
     * @return
     * - a TreeNode with its new position and be also checked if
     * the heap property is still violated.
     */
    private TreeNode leftRotate(TreeNode root) {

        if (root.getLeft() != null) {
            root.getParent().setRight(root.getLeft());
            root.getLeft().setParent(root.getParent());
            root.setLeft(null);
        }
        else
            root.getParent().setRight(null);

        // set the parent as a left child of current root.
        root.setLeft(root.getParent());
        // set the parent of parent's current root as the parent of its new left child.
        root.setParent(root.getLeft().getParent());

        if (root.getParent() != null && root.getParent().getData() < root.getData())
            root.getParent().setRight(root);
        else if (root.getParent() != null && root.getParent().getData() >= root.getData())
            root.getParent().setLeft(root);

        root.getLeft().setParent(root);

        return root;
    }

    /**
     * A function that combines the two rotations (left and right rotations)
     * @param root
     * - a root that to be evaluate and rotate.
     * @return
     * - the new position of root for new rotation, if the heap property is still violated.
     */
    private TreeNode rotation(TreeNode root) {

        if (root.getParent() == null) {

            this.root = root;
            return this.root;
        }

        if (root.getParent().getLeft() == root && root.getPriority() > root.getParent().getPriority())
            root = rightRotate(root);
        else if (root.getParent().getRight() == root && root.getPriority() > root.getParent().getPriority())
            root = leftRotate(root);
        else
            return root;

        return rotation(root);
    }

    /**
     * Search the position of the node to be deleted if it is exisiting.
     * @param currentRoot
     * - The current root to be evaluated if its data is equal to the @param valueToBeDeleted.
     * @param valueToBeDeleted
     * - The node that has a value that is equal to this.
     * @return
     * - The position of the TreeNode for rotations, otherwise, it will return null.
     */
    private TreeNode searchNodeToBeDeleted(TreeNode currentRoot, int valueToBeDeleted) {

        if (currentRoot == null)
            return null; // It means value is not found

        if (currentRoot.getData() == valueToBeDeleted)
            return currentRoot;
        else if (currentRoot.getData() >= valueToBeDeleted)
            return searchNodeToBeDeleted(currentRoot.getLeft(), valueToBeDeleted);
        else
            return searchNodeToBeDeleted(currentRoot.getRight(), valueToBeDeleted);
    }

    /**
     * A function that deletes a node to the tree.
     * @param valueToBeDeleted
     * - a value to be deleted.
     * @param nodeToBeDeleted
     * - a node that contains the value that equal to @param valueToBeDeleted
     */
    public void deleteNode(int valueToBeDeleted, TreeNode nodeToBeDeleted) {

        // if the first value of nodeToBeDeleted is equal to null then we start to the root of the tree.
        if (nodeToBeDeleted == null)
            nodeToBeDeleted = searchNodeToBeDeleted(this.root, valueToBeDeleted);
        
        // If it is null after the searching then we know that the element is not found
        if (nodeToBeDeleted == null) {

            System.out.println("Key does not exist!");
            return;
        }
            

        /** This will execute if the node that we want to delete is a leaf node. */
        if (nodeToBeDeleted.getLeft() == null && nodeToBeDeleted.getRight() == null) {

            // if it is equal to the root of the tree then set the root as null if it has no right and left child
            if (nodeToBeDeleted == this.root && this.root.getLeft() == null && this.root.getRight() == null)
                this.root = null;
            else if (nodeToBeDeleted.getParent().getLeft() == nodeToBeDeleted) 
                nodeToBeDeleted.getParent().setLeft(null);
            else if (nodeToBeDeleted.getParent().getRight() == nodeToBeDeleted)
                nodeToBeDeleted.getParent().setRight(null);

            System.out.println("Successfully Deleted!");
            return;
        }

        nodeToBeDeleted.setPriority(Integer.MIN_VALUE);

        if (nodeToBeDeleted.getLeft() != null && nodeToBeDeleted.getRight() != null) {

            if (nodeToBeDeleted.getLeft().getPriority() > nodeToBeDeleted.getRight().getPriority())
                deleteNode(valueToBeDeleted, rotation(nodeToBeDeleted.getLeft()).getRight());
                // deleteNode(valueToBeDeleted, rightRotate(nodeToBeDeleted.getLeft()).getRight());
            else
                deleteNode(valueToBeDeleted, rotation(nodeToBeDeleted.getRight()).getLeft());
                // deleteNode(valueToBeDeleted, leftRotate(nodeToBeDeleted.getRight()).getLeft());
        }
        else if (nodeToBeDeleted.getLeft() != null)
            deleteNode(valueToBeDeleted, rotation(nodeToBeDeleted.getLeft()).getRight());
        else if (nodeToBeDeleted.getRight() != null)
            deleteNode(valueToBeDeleted, rotation(nodeToBeDeleted.getRight()).getLeft());
    }
}