import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        printMenu();
    }

    static void printMenu() {
        MyThread myThread = new MyThread();//başlangınç efekti için
        Operations operations = new Operations();//tüm işlemler için

        System.out.println("Please wait.....(15-20 sec)");

        myThread.start();

        long startTime = System.currentTimeMillis();
        operations.createBinaryTree();//ağacı oluşturdum
        long endTime = System.currentTimeMillis();

        myThread.finish();

        long estimatedTime = endTime - startTime;
        double seconds = (double) estimatedTime / 1000;//saniyeye çevirmek için 1000'e bölüyoruz.

        Scanner scanner = new Scanner(System.in);
        String choice;

        System.out.println("\nSimple Document Retrieval System");
        System.out.println(seconds + " second");
        do {
            System.out.println("--------------------------------------------------------------------------------------------");
            System.out.println("1-Enter a single keyword to list the document(s)(file names)");
            System.out.println("2-Print the top 10 words that appeared most frequently");
            System.out.println("3-Print the top 10 words that appeared least frequently");
            System.out.println("4-Print the whole tree");
            System.out.println("5-Total Word");
            System.out.println("6-Number of Files Read");
            System.out.println("7-Save the resulting tree in file alphabetically");
            System.out.println("8-Exist");
            System.out.print("Option: ");
            choice = scanner.next();
            System.out.println("--------------------------------------------------------------------------------------------");

            switch (choice) {
                case "1":
                    operations.search();
                    break;
                case "2":
                    operations.mostTop10();
                    break;
                case "3":
                    operations.leastTop10();
                    break;
                case "4":
                    operations.print();
                    break;
                case "5":
                    operations.totalWords();
                    break;
                case "6":
                    operations.totalFile();
                    break;
                case "7":
                    System.out.println("Please wait a few seconds for the writing process to finish.");
                    operations.writerToOutputFile();
                    System.out.println("The data has been saved to the file.(output.txt)");
                    break;
                case "8":
                    System.out.println("Thank you for choosing us. System shuts down...");
                    break;
                default:
                    System.out.println("You entered an incorrect value, please try again.(1-4)");
                    break;
            }

        } while (!choice.equals("8"));
    }
}