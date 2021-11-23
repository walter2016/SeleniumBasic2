package org.walter.listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class RealTimeReport implements ITestListener {
    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("Tes Started: " + result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("Test Pass: " + result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("Test Failed: " + result.getName());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println("Test Skipped: " + result.getName());
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

    }

    @Override
    public void onStart(ITestContext context) {
        System.out.println("Star Execution (Test): " + context.getName());
    }

    @Override
    public void onFinish(ITestContext context) {
        System.out.println("end of Execution " + context.getName());
    }
}
