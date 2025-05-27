
package reports;

import com.aventstack.extentreports.ExtentTest;

import java.util.HashMap;
import java.util.Map;

public class ExtentTestManager {
    private static final Map<Integer, ExtentTest> testMap = new HashMap<>();

    public static synchronized ExtentTest getTest() {
        return testMap.get((int) Thread.currentThread().getId());
    }

    public static synchronized void createTest(String name) {
        ExtentTest test = ExtentManager.getInstance().createTest(name);
        testMap.put((int) Thread.currentThread().getId(), test);
    }
}