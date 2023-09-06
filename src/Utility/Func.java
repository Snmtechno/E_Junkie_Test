package Utility;

public class Func {

    public static void Wait(int sec){
        try {
           Thread.sleep(sec*1000l);
        }catch (InterruptedException e){
            throw new RuntimeException(e);
        }
    }
}
