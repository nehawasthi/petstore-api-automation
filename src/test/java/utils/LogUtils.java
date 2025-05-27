package utils;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
public class LogUtils {
	  private static final Logger logger = Logger.getLogger(LogUtils.class);

	    static {
	        try {
	            PropertyConfigurator.configure("src/test/resources/log4j.properties");
	        } catch (Exception e) {
	            System.err.println("Log4j configuration failed: " + e.getMessage());
	        }
	    }

	    public static void info(String message) {
	        logger.info(message);
	    }

	    public static void error(String message) {
	        logger.error(message);
	    }

	    public static void warn(String message) {
	        logger.warn(message);
	    }
	}
