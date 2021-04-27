import java.util.concurrent.TimeUnit;

public class TimeOutThread extends Thread {
    static int TIMEOUT_TIME = 5;
    boolean timeOut;

    public void run() {
        timeOut = false;
        try {
            TimeUnit.SECONDS.sleep(TIMEOUT_TIME);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        timeOut = true;
    }
}
