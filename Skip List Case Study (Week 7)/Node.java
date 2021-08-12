public class Node {

    private Node previous;
    private Node bottom;
    private Node next;
    private Node top;
    private int data;

    public Node(int data) {

        this.data = data;
    }

    public Node getPrevious() {
        return previous;
    }

    public void setPrevious(Node previous) {
        this.previous = previous;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public Node getTop() {
        return top;
    }

    public void setTop(Node top) {
        this.top = top;
    }

    public Node getBottom() {
        return bottom;
    }

    public void setBottom(Node bottom) {
        this.bottom = bottom;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }
}