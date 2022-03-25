import java.util.ArrayList;
import java.util.Scanner;

public class BinaryTreeOperations {
    Scanner scanner = new Scanner(System.in);
    LinkedListOperations operations = new LinkedListOperations();//linkedList te işlem yapmak için

    BinaryTreeNode root;

    public BinaryTreeOperations() {
        root = null;
    }

    //ağaçta bulunan değerlerin hangi dosyalarda bulunduğunu tutmak için kullandım
    private Node addFileNameToLinkedList(Node node, String fileName) {
        return operations.add(node, fileName);
    }

    //ağaça yeni düğüm eklemek için kullandım
    private BinaryTreeNode createNewNode(String data) {
        root = new BinaryTreeNode(data);
        return root;
    }

    public BinaryTreeNode insert(BinaryTreeNode root, String data, String fileName) {

        if (!contains(data, fileName)) {

            if (root != null) {

                if (data.compareTo(root.getData()) < 0) {//kelimeleri alfabetik olarak karşılaştırmak için compareTo() kullandım
                    root.setLeft(insert(root.getLeft(), data, fileName));
                } else {
                    root.setRight(insert(root.getRight(), data, fileName));
                }

            } else {
                root = createNewNode(data);
                root.setHead(addFileNameToLinkedList(root.getHead(), fileName));
            }
            //return root;
        }
        return root;
    }

    //ağaçta eklenen değerin olup olmadığına baktım
    private boolean contains(String data, String fileName) {

        if (isEmpty()) {//ağaç boş ise
            return false;
        }

        BinaryTreeNode tempNode = root;

        while (tempNode != null) {
            int comparedValue = data.compareTo(tempNode.getData());//kelimeleri sıralamak için
            //int comparedValue = tempNode.getData.compareTo(data);

            if (comparedValue == 0) {
                int tmp = tempNode.getFrequency();
                tempNode.setFrequency((tmp + 1));//frekansı burada yükselttim
                root.setHead(addFileNameToLinkedList(tempNode.getHead(), fileName));
                return true;
            } else if (comparedValue < 0) {
                tempNode = tempNode.getLeft();
            } else {
                tempNode = tempNode.getRight();
            }
        }
        return false;
    }

    public boolean isEmpty() {
        return root == null;
    }

    //arama yapmak için
    public boolean search(BinaryTreeNode root, String data) {

        boolean check = false;
        if (root != null) {
            if (root.getData().equals(data)) {
                System.out.println(root.getData() + " = " + root.getFrequency());
                System.out.print("Files containing the word " + root.getData() + ": ");
                printLinkedList(root.getHead());
                check = true;
            } else if (data.compareTo(root.getData()) < 0) {
                check = search(root.getLeft(), data);
            } else if (data.compareTo(root.getData()) > 0) {
                check = search(root.getRight(), data);
            }
        }

        return check;
    }

    public String getData() {
        System.out.println("Please enter a string value: ");
        return loverCaseOperation(scanner.next());
    }

    private String loverCaseOperation(String str) {//kelimeleri küçülttüm
        String result = "";
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == 'I' || str.charAt(i) == 'İ') {
                result += "i";
            } else {
                result += str.toLowerCase().charAt(i);
            }
        }
        return result;
    }

    public void inOrder(BinaryTreeNode root) {//ortada Kök

        if (root != null) {
            inOrder(root.getLeft());
            System.out.println(root.getData() + " = " + root.getFrequency());
            //printLinkedList(root.getHead());//kelimelerin bulunduğu dosya isimlerini yazdırmak istersem
            inOrder(root.getRight());
        }
    }

    public void printLinkedList(Node rootNode) {

        Node walk = rootNode;
        while (walk.getNext() != null) {
            System.out.print(walk.getFileName() + "(" + walk.getCounter() + ")" + " --- ");
            walk = walk.getNext();
        }
        System.out.println(walk.getFileName() + "(" + walk.getCounter() + ")");
    }

    int totalWord = 0;

    private void totalWord(BinaryTreeNode root) {
        if (root != null) {
            totalWord(root.getLeft());
            totalWord += root.getFrequency();
            totalWord(root.getRight());
        }
    }

    public void printTotalWord(BinaryTreeNode root) {
        totalWord(root);

        System.out.println("Total word count: " + totalWord);
    }

    //En çok tekrar eden kelimeler için arraylist oluşturup ilk 10 elemanı atadım
    ArrayList<BinaryTreeNode> mostTop10 = new ArrayList<>();

    public void createArraylist() {
        BinaryTreeNode node = new BinaryTreeNode("doftdare");

        for (int i = 0; i < 10; i++) {
            mostTop10.add(node);
        }
    }

    //en çok tekrar eden değerleri düzeltme
    private void updateTheMostTop10(BinaryTreeNode root) {
        int min = mostTop10.get(0).getFrequency();//arrayList teki ilk frekans değerini min e atadım
        BinaryTreeNode box = mostTop10.get(0);//arrayList teki ilk BinaryTreeNode değerini box a atadım
        for (BinaryTreeNode n : mostTop10) {
            if (n.getFrequency() < min) {//burada arrayList tin içindeki en küçük değeri buldum
                min = n.getFrequency();
                box = n;
            }
        }

        if (root.getFrequency() > min) {//eğer gelen değer min den büyükse arrayList te yer değiştirme yaptım
            int index = mostTop10.indexOf(box);
            mostTop10.set(index, root);
        }
    }

    //agaçta bulunan tüm değerleri tek tek updateTheMostTop10 fonksiyonuna gönderdim
    private void createTheMostTop10(BinaryTreeNode root) {
        if (root != null) {
            createTheMostTop10(root.getLeft());
            updateTheMostTop10(root);
            createTheMostTop10(root.getRight());
        }
    }

    //mostTop10 arraylistini sıraladım
    private void compareTo(ArrayList<BinaryTreeNode> arrayList) {
        int min = 0;
        BinaryTreeNode box;

        for (int i = 0; i < arrayList.size(); i++) {
            min = arrayList.get(i).getFrequency();
            box = arrayList.get(i);

            for (int j = i; j < arrayList.size(); j++) {

                if (min > arrayList.get(j).getFrequency()) {
                    min = arrayList.get(j).getFrequency();
                    box = arrayList.get(j);
                }
            }

            int index = arrayList.indexOf(box);
            BinaryTreeNode temp = arrayList.get(i);

            arrayList.set(i, box);
            arrayList.set(index, temp);
        }
    }

    //mostTop10 arrayListini oluşturup yazdırdım
    public void printTheMostTop10(BinaryTreeNode root) {
        createTheMostTop10(root);
        compareTo(mostTop10);

        System.out.format("%15s %3s %5s", "name", "   ", "frequency\n");
        for (BinaryTreeNode binaryTreeNode : mostTop10) {
            System.out.format("%15s %3s %5d %1s", binaryTreeNode.getData(), " = ", binaryTreeNode.getFrequency(), "\n");
            //System.out.println(binaryTreeNode.getData() + " = " + binaryTreeNode.getFrequency());
        }
        mostTop10.clear();
    }

    //En az tekrar eden kelimeler için arraylist oluşturup ilk 10 elemanı atadım
    ArrayList<BinaryTreeNode> leastTop10 = new ArrayList<>();

    public void createArraylist2() {
        BinaryTreeNode node = new BinaryTreeNode();

        for (int i = 0; i < 10; i++) {
            leastTop10.add(node);
        }
    }

    //en az tekrar eden değerleri düzeltme
    private void updateTheLeastTop10(BinaryTreeNode root) {
        int max = leastTop10.get(0).getFrequency();//arrayList teki ilk frekans değerini max a atadım
        BinaryTreeNode box = leastTop10.get(0);//arrayList teki ilk BinaryTreeNode değerini box a atadım
        for (BinaryTreeNode n : leastTop10) {
            if (n.getFrequency() > max) {//burada arrayList tin içindeki en büyük değeri buldum
                max = n.getFrequency();
                box = n;
            }
        }

        if (root.getFrequency() < max) {//eğer gelen değer max den küçükse arrayList te yer değiştirme yaptım
            int index = leastTop10.indexOf(box);
            leastTop10.set(index, root);
        }
    }

    //agaçta bulunan tüm değerleri tek tek updateTheLeastTop10 fonksiyonuna gönderdim
    private void createTheLeastTop10(BinaryTreeNode root) {
        if (root != null) {
            createTheLeastTop10(root.getLeft());
            updateTheLeastTop10(root);
            createTheLeastTop10(root.getRight());
        }
    }

    //leastTop10 arrayListini oluşturup yazdırdım
    public void printTheLeastTop10(BinaryTreeNode root) {
        createTheLeastTop10(root);

        System.out.format("%15s %3s %5s", "name", "   ", "frequency\n");
        for (BinaryTreeNode binaryTreeNode : leastTop10) {
            System.out.format("%15s %3s %5d %1s", binaryTreeNode.getData(), " = ", binaryTreeNode.getFrequency(), "\n");
            //System.out.println(binaryTreeNode.getData() + " = " + binaryTreeNode.getFrequency());
        }
        leastTop10.clear();
    }
}