package Listeners;

import Tests.BaseTest;
import Utilities.ExtentReportsManager;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.gherkin.model.Feature;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.IOException;
import java.util.Arrays;

public class TestNGListener extends BaseTest implements ITestListener {

    int testNumber = 1;
    ExtentReports reportsManager = ExtentReportsManager.createInstance();
    public static ExtentTest test;
    ExtentTest test1;

    @Override
    public void onTestStart(ITestResult result) {

        System.out.println("Test " + testNumber + " - " + result.getName() + " start");
        test1 = reportsManager.createTest(Feature.class, result.getName() + testNumber).assignCategory("regression");
        test = test1.createNode(result.getName() + testNumber + " Test");


    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("Test " + testNumber + " - " + result.getName() + " Passed");
        try {
            test.log(Status.PASS, "pass", MediaEntityBuilder.createScreenCaptureFromPath
                    (ExtentReportsManager.capture(driver)).build());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        testNumber++;
    }

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("Test " + testNumber + " - " + result.getName() + "Failed");
        String exceptionMsg = Arrays.toString(result.getThrowable().getStackTrace());
        try {
            test.log(Status.FAIL, exceptionMsg, MediaEntityBuilder.createScreenCaptureFromPath
                    (ExtentReportsManager.capture(driver)).build());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        testNumber++;
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        try {
            test.log(Status.SKIP, result.getName() + " is skip", MediaEntityBuilder.createScreenCaptureFromPath
                    (ExtentReportsManager.capture(driver)).build());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {
        this.onTestFailure(result);
    }

    @Override
    public void onStart(ITestContext context) {

    }

    @Override
    public void onFinish(ITestContext context) {
        reportsManager.flush();

    }
}
