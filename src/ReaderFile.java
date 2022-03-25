import java.io.FileReader;
import java.io.BufferedReader;

public class ReaderFile {

    private int counterFileName = 0;//kaç dosya okuduğumu tutmak için kullandım

    public ReaderFile() {

    }

    //gelen dosya isimi ile tüm dosya içindeki verileri okudum
    public String reader(String fileName) {
        String str = "";//gelen dosyadaki tüm değerleri str adında String de tuttum

        //C:\Users\dogan\IdeaProjects\ExtraCountingWords\AllDocs\
        String newFileName = "AllDocs\\" + fileName;
        try {
            String line = "";

            BufferedReader bufferedReader = new BufferedReader(new FileReader(newFileName));
            while ((line = bufferedReader.readLine()) != null) {
                line = loverCaseOperation(line);//Harfleri kendim oluşturduğum fonksiyon ile küçülttüm

                for (int i = 0; i < line.length(); i++) {
                    if ((line.charAt(i) <= 'Z' && line.charAt(i) >= 'A') || (line.charAt(i) <= 'z' && line.charAt(i) >= 'a')) {
                        str += line.charAt(i);
                    } else {
                        str += " ";
                    }
                }
                str += " ";//dosyada bulunan her satır arasına bir boşluk koydum
            }

            counterFileName++;
            return str;//oluşan string değeri ağaça eklemek için gönderdim
        } catch (Exception e) {
            System.out.println((counterFileName + 1) + ". dosya okunurken hata çıktı!(error)");
        }
        counterFileName++;
        return "error";
    }

    public String loverCaseOperation(String str) {//kelimeleri küçülttüm
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

    public int getCounterFileName() {
        return counterFileName;
    }

    public void setCounterFileName(int counterFileName) {
        this.counterFileName = counterFileName;
    }
}