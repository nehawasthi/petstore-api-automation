package base;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentTest;
import com.microsoft.playwright.Page;

import reports.ExtentManager;
import reports.ExtentTestManager;

public class TestListener implements ITestListener {

    @Override
    public void onTestStart(ITestResult result) {
        ExtentTestManager.createTest(result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ExtentTestManager.getTest().pass("Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
    	ExtentTest test = ExtentTestManager.getTest();
        test.fail(result.getThrowable());

        try {
            Object testClass = result.getInstance();
            Class<?> clazz = result.getTestClass().getRealClass();

            Field pageField = clazz.getDeclaredField("page");
            pageField.setAccessible(true);
            Page page = (Page) pageField.get(testClass);

            Method method = clazz.getSuperclass().getDeclaredMethod("captureScreenshot", String.class, Page.class);
            method.setAccessible(true);
            String path = (String) method.invoke(testClass, result.getMethod().getMethodName(), page);

            test.addScreenCaptureFromPath(path);
        } catch (Exception e) {
            test.warning("Screenshot not captured: " + e.getMessage());
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ExtentTestManager.getTest().skip("Test Skipped");
    }

    @Override
    public void onFinish(ITestContext context) {
        ExtentManager.getInstance().flush();
    }
}