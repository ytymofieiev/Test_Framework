package common.utils.listeners;

import common.utils.Log;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestLogs implements ITestListener {

    public String getMethodName(ITestResult result){
        return result.getMethod().getConstructorOrMethod().getName();
    }

    @Override
    public void onTestStart(ITestResult result){
        Log.info("The " + getMethodName(result) + " is started");
    }
    @Override
    public void onTestSuccess(ITestResult result){
        Log.info("The " + getMethodName(result) + " is finished success \n");
    }
    @Override
    public void onTestFailure(ITestResult result){
        Log.error("The " + getMethodName(result) + " is failure \n");
    }
    @Override
    public void onTestSkipped(ITestResult result){
        Log.error("The " + getMethodName(result) + " is skipped \n");
    }
    @Override
    public void onTestFailedWithTimeout(ITestResult result){
        Log.error("The " + getMethodName(result) + " is failed with timeout exception \n");
    }

    @Override
    public void onStart(ITestContext result){
        Log.info("The " + result.getName() + " is started");
    }
    @Override
    public void onFinish(ITestContext result){
        Log.info("The " + result.getName() + " is finished");
    }
}
