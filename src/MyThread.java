import java.util.Timer;
import java.util.TimerTask;

public class MyThread extends Thread {

    String control = "";
    Timer myTimer = new Timer();

    TimerTask myTask = new TimerTask() {
        @Override
        public void run() {
            if (control.equals("finish")) {
                control = "";
                myTimer.cancel();
            } else {
                System.out.print(".........................");
            }
        }
    };

    public void run() {
        System.out.print("Loading...");
        myTimer.schedule(myTask, 0, 3000);
    }

    public void finish() {
        control = "finish";
    }
}