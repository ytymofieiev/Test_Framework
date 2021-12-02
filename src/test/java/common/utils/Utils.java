package common.utils;

public class Utils {


    public static boolean isEnvironmentRemote(){
        String env = System.getProperty("target.environment");
        return env != null && env.equals("remote");
    }

    public static void delayMillisec(int millSec){
        Long limit = System.currentTimeMillis() + millSec;
        while (limit < System.currentTimeMillis()){
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Log.error("Can't wait " + millSec + " milliseconds");
            }
        }
    }
}
