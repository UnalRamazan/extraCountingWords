import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Operations {

    ReaderFile readerFile = new ReaderFile();//dosyadan veri çekmek için
    WriterFile writerFile = new WriterFile();//dosyaya veri yazmak için
    BinaryTreeOperations binaryTreeOperations = new BinaryTreeOperations();//ağacı oluşturmak için
    GetFileNameFilter getFileNameFilter = new GetFileNameFilter();//klasör içindeki txt dosyası sayısını bulmak için

    public Operations() {

    }

    public void createBinaryTree() {//tüm dosyalardan gelen verileri ağaca ekledim
        int j = 0, k = 0;
        String newStr = "";

        int size = getFileNameFilter.findFiles("AllDocs");//kaç tane .txt dosyası olduğunu buldum

        for (int i = 1; i <= size; i++) {

            String str = readerFile.reader(i + ".txt");//her txt dosyasında bulunan veriler tek tek bir string değer olarak dönüyor

            for (j = 0; j < str.length(); j++) {//str'nin başındaki boşluklardan kurtuldum
                if (str.charAt(j) != ' ') {
                    break;
                }
            }

            for (k = j; k < str.length(); k++) {//str içinde işlemleri yapıp string leri belirleyip ağaca ekledim
                if (str.charAt(k) != ' ') {
                    newStr += str.charAt(k);
                } else if (str.charAt(k) == ' ' && newStr.length() != 0) {
                    binaryTreeOperations.root = binaryTreeOperations.insert(binaryTreeOperations.root, newStr, i + ".txt");
                    newStr = "";
                } else if (k == (str.length() - 1) && newStr.length() != 0) {
                    newStr += str.charAt(k);
                    binaryTreeOperations.root = binaryTreeOperations.insert(binaryTreeOperations.root, newStr, i + ".txt");
                    newStr = "";
                }
            }
        }
    }

    public void print() {
        binaryTreeOperations.inOrder(binaryTreeOperations.root);
    }

    //kulanıcının girdiği değeri arattım
    public void search() {
        boolean check = binaryTreeOperations.search(binaryTreeOperations.root, binaryTreeOperations.getData());

        if (!check) {
            System.out.println("Word not found in current binary search tree...");
        }
    }

    //en çok tekrar eden 10 kelime
    public void mostTop10() {
        binaryTreeOperations.createArraylist();
        binaryTreeOperations.printTheMostTop10(binaryTreeOperations.root);
    }

    //en az tekrar eden 10 kelime
    public void leastTop10() {
        binaryTreeOperations.createArraylist2();
        binaryTreeOperations.printTheLeastTop10(binaryTreeOperations.root);
    }

    //output.txt dosyasına veri yazmak için
    public void writerToOutputFile() {
        String fiLeName = "output.txt";
        try {//output.txt de olan verileri sildim
            BufferedWriter writer = new BufferedWriter(new FileWriter(fiLeName));
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        writerFile.writer(binaryTreeOperations.root);
    }

    public void totalWords() {
        binaryTreeOperations.printTotalWord(binaryTreeOperations.root);
    }

    public void totalFile() {
        System.out.println("Number of files read: " + readerFile.getCounterFileName());
    }
}