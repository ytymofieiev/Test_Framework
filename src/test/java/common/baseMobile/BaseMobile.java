package common.baseMobile;


import org.openqa.selenium.WebDriver;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class BaseMobile {

    protected static ThreadLocal<WebDriver> driverPool = new ThreadLocal<>();

    public static String getUsername(){
        return readPropertiesFile().getProperty("name");
    }

    public static String getPass(){
        return readPropertiesFile().getProperty("pass");
    }

    public static String getSiteURL(){
        return readPropertiesFile().getProperty("url");
    }

    public static String getUrl(){
        return "https://" + getUsername() + ":" + getPass() + "@hub-cloud.browserstack.com/wd/hub";
    }

    public static Properties readPropertiesFile(){
        FileInputStream fis = null;
        Properties properties = new Properties();
        try {
            fis = new FileInputStream("D:\\Project_PPC\\ClickCease_BrowserStack\\src\\test\\resources\\browserStack.properties");
            properties.load(fis);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return properties;
    }

    public static WebDriver getDriver(){
        return driverPool.get();
    }

}
