package common.utils.listeners;

import common.DriverHelper;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

public class ScreenshotListener extends TestListenerAdapter {
    @Override
    public void onTestSuccess(ITestResult tr){
        getScreenshot(tr.getMethod().getMethodName());
    }

    @Override
    public void onTestFailure(ITestResult tr){
        getScreenshot(tr.getMethod().getMethodName());
    }

    @Override
    public void onTestSkipped(ITestResult tr){
        getScreenshot(tr.getMethod().getMethodName());
    }

    @Override
    public void onTestFailedWithTimeout(ITestResult tr){
        getScreenshot(tr.getMethod().getMethodName());
    }

    @Attachment(value = "Page ScreenShot - {name}", type = "image/png")
    public byte[] getScreenshot(String name){
        return ((TakesScreenshot) DriverHelper.getDriver()).getScreenshotAs(OutputType.BYTES);
    }

}
