package com.example.northwindserver.logger;

import com.example.northwindserver.NorhtwindWebApplication;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoggerClass {

    private static final Logger logger = LogManager.getLogger(NorhtwindWebApplication.class);

    public static void logTrace(String message){
        logger.trace(message);
    }
    public static void logError(String message){
        logger.error(message);
    }
}
