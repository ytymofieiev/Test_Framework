package common.base;

import common.utils.Utils;
import common.utils.listeners.ScreenshotListener;
import common.utils.listeners.TestLogs;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.*;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

@Listeners({ScreenshotListener.class, TestLogs.class})
public abstract class BaseTest extends Base{

    private static ThreadLocal<WebDriver> driverPool = new ThreadLocal<>();

    private final String GRID_URL = "grid-url";
    private URL host;

    private Map<String, Object> capabilities = new HashMap<>();
    private ChromeOptions chromeOptions = new ChromeOptions();
    private FirefoxOptions fireFoxOptions = new FirefoxOptions();

    public void initChromeDriver(){
        if (Utils.isEnvironmentRemote()){
            //init Grid + remote web Driver
            initGridConnection();
            RemoteWebDriver remoteWebDriver = new RemoteWebDriver(host, chromeOptions);
            remoteWebDriver.setFileDetector(new LocalFileDetector());
            driverPool.set(remoteWebDriver);
        }else {
            System.setProperty("webdriver.chrome.driver", "src\\test\\resources\\chromedriver.exe");
            driverPool.set(new ChromeDriver(chromeOptions));
        }
    }


    private void initFireFoxDriver() {
        if (Utils.isEnvironmentRemote()) {
            //init Grid + remote web Driver
            initGridConnection();
            RemoteWebDriver driver = new RemoteWebDriver(host, fireFoxOptions);
            driver.setFileDetector(new LocalFileDetector());
            driverPool.set(driver);
        } else {
            System.setProperty("webdriver.gecko.driver", "/geckodriver.exe");
            driverPool.set(new FirefoxDriver((fireFoxOptions)));
        }
    }

    public void initGridConnection(){
        host = null;
        try {
            host = new URL(GRID_URL);
        }catch (MalformedURLException e){
            e.printStackTrace();
        }
    }

    public static WebDriver getDriver(){
        return driverPool.get();
    }

    @BeforeTest(alwaysRun = true)
    @Parameters("Browser")
    public void setUp(@Optional("chrome") String browser) {
        if (browser.equals("chrome")) {
            initChromeDriver();
        } else if (browser.equals("firefox")) {
            initFireFoxDriver();
        }
        WebDriver driver = getDriver();
        driver.manage().window().setSize(new Dimension(1920, 1080));
    }

    @AfterTest(alwaysRun = true)
    public void quit() {
        try {
            driverPool.get().close();
            driverPool.get().quit();
        } catch (WebDriverException e) {
            e.printStackTrace();
        }
    }
}
