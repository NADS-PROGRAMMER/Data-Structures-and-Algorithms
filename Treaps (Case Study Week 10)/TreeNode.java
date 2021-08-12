public class TreeNode {
    
    private TreeNode right;
    private TreeNode left;
    private TreeNode parent;
    private int data;
    private int priority;
    
    public TreeNode(int data) {

        this.data = data;
    }

    public TreeNode getRight() {
        return this.right;
    }

    public void setRight(TreeNode right) {
        this.right = right;
    }

    public TreeNode getLeft() {
        return this.left;
    }

    public void setLeft(TreeNode left) {
        this.left = left;
    }

    public TreeNode getParent() {
        return this.parent;
    }

    public void setParent(TreeNode parent) {
        this.parent = parent;
    }

    public int getData() {
        return this.data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public void setPriority(int priority) {

        this.priority = priority;
    }

    public int getPriority() {

        return this.priority;
    }
}