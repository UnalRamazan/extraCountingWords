public class LinkedListOperations {

    Node head;

    public LinkedListOperations() {
        head = null;
    }

    public Node add(Node head, String fileName) {
        Node newNode = new Node(fileName);

        if (!contains(head, fileName)) {
            if (head == null) {
                head = newNode;
                head.setNext(null);
            } else {
                Node walk = head;

                while (walk.getNext() != null) {
                    walk = walk.getNext();
                }
                walk.setNext(newNode);
                newNode.setNext(null);
            }
        }
        return head;
    }

    private boolean contains(Node head, String fileName) {

        if (head == null) {//linkedList boş ise
            return false;
        }

        Node walk = head;
        while (walk != null) {
            if (walk.getFileName().equals(fileName)) {
                int counter = walk.getCounter();
                walk.setCounter(counter + 1);
                return true;
            }
            walk = walk.getNext();
        }

        return false;
    }
}