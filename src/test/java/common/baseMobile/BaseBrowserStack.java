package common.baseMobile;


import common.DriverHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import java.net.MalformedURLException;
import java.net.URL;

public class BaseBrowserStack extends BaseMobile {

    protected WebDriver driver;
    protected DriverHelper driverHelper;
    protected String device;

    @BeforeTest
    @Parameters({"browserName","device","os_version"})
    public void setCapabilitiesAndInitDriver(String browserName, String device, String os_version) throws MalformedURLException {
        DesiredCapabilities caps = new DesiredCapabilities();
        this.device = device;

        String buildName = System.getenv("BROWSERSTACK_BUILD_NAME");
        String username = System.getenv("BROWSERSTACK_USERNAME");
        String accessKey = System.getenv("BROWSERSTACK_ACCESS_KEY");

        caps.setCapability("browserName", browserName);
        caps.setCapability("device", device);
        caps.setCapability("realMobile", "true");
        caps.setCapability("os_version", os_version);
        caps.setCapability("name", "Test for " + device + " version " + os_version); // test name
        caps.setCapability("build", buildName); // CI/CD job or build name

        String  url = "https://" + username + ":" + accessKey + "@hub.browserstack.com/wd/hub";

        System.out.println(url);
        driver = new RemoteWebDriver(new URL(
                /*getUrl()*/url
        ), caps);
        driverPool.set(driver);
    }

    @AfterTest
    public void quit(){
        driver.quit();
    }
}
