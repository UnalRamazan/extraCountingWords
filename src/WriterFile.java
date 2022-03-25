import java.io.BufferedWriter;
import java.io.FileWriter;

public class WriterFile {

    public WriterFile() {

    }

    final String fiLeName = "output.txt";

    private void writerToFile(String data) {

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(fiLeName, true));
            writer.write(data);
            writer.newLine();
            writer.close();
        } catch (Exception e) {
            System.out.println("output.txt dosyasına veri yazılırken sıkıntı çıktı.");
        }
    }

    String str = "";

    public void writer(BinaryTreeNode root) {//ortada Kök
        if (root != null) {
            writer(root.getLeft());
            str += "WORD: " + root.getData() + ", FREQUENCY: " + root.getFrequency() + "\n";
            str += printLinkedList(root.getHead());
            writerToFile(str);
            str = "";
            writer(root.getRight());
        }
    }

    private String printLinkedList(Node rootNode) {

        String str = "";
        Node walk = rootNode;
        while (walk.getNext() != null) {
            str += walk.getFileName() + "(" + walk.getCounter() + ")" + " --- ";
            walk = walk.getNext();
        }
        str += walk.getFileName() + "(" + walk.getCounter() + ")";
        return str;
    }
}