package common.base;

import common.DriverHelper;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;


public abstract class Base {

    @Attachment(type = "text/plain")
    public String saveOutput(String data) {
        return data;
    }

    @Attachment(value = "Output - {outPutName}", type = "text/plain")
    public String saveOutput(String outPutName, String data) {
        return data;
    }

    @Attachment(value = "Page screenshot - {screenshotName}", type = "image/png")
    public byte[] saveScreenshot(String screenshotName) {
        return ((TakesScreenshot) DriverHelper.getDriver()).getScreenshotAs(OutputType.BYTES);
    }

}

