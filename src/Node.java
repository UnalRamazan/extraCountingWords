public class Node {
    private String fileName;
    private int counter;
    private Node next;

    public Node(String fileName) {
        this.setFileName(fileName);
        this.setCounter(1);
        setNext(null);
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }
}