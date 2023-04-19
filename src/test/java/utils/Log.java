package utils;


import org.apache.log4j.*;

public class Log {

    //in order to add this, we had to add log4j dependency
    public static Logger log = Logger.getLogger(Log.class.getName());

    //when the test starts, I should print the logs
    //when the test stops, I should print the logs
    //if I want to print any message in between, I should print the logs

    public static void startTestcase(String testCaseName) {
        log.info("***************");
        log.info("***************");
        log.info("******** " + testCaseName + " ********");
    }
    public static void endTestcase(String testCaseName) {
        log.info("###############");
        log.info("###############");
        log.info("######## " + testCaseName + " ########");
    }

    public static void info(String message){
        log.info(message);
    }

    public static void warning(String warning){
        log.info(warning);
    }
}
