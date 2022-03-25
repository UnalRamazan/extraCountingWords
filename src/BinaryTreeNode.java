public class BinaryTreeNode {
    private String data;
    private int frequency;
    private Node head;
    private BinaryTreeNode right;
    private BinaryTreeNode left;

    public BinaryTreeNode(String data) {
        this.setData(data);
        this.setFrequency(1);
        setHead(null);
        right = null;
        left = null;
    }

    public BinaryTreeNode() {
        this.setData("doftdare");
        this.setFrequency(1000);
        setHead(null);
        right = null;
        left = null;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    public BinaryTreeNode getRight() {
        return right;
    }

    public void setRight(BinaryTreeNode right) {
        this.right = right;
    }

    public BinaryTreeNode getLeft() {
        return left;
    }

    public void setLeft(BinaryTreeNode left) {
        this.left = left;
    }

    public Node getHead() {
        return head;
    }

    public void setHead(Node head) {
        this.head = head;
    }
}