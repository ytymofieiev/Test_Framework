package common;

import common.base.BaseTest;
import common.baseMobile.BaseMobile;
import common.utils.Log;
import common.utils.Utils;
import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class DriverHelper {

    public static final int TIMEOUT_SHORT = 10;
    public static final int TIMEOUT_LONG = 60;
    public static final int TIMEOUT_DEFAULT = 30;


    /**
     *  Web-site URLS
     */

    private SearchContext searchContext;
    private JavascriptExecutor js;
    private WebDriverWait webDriverWait;

    public DriverHelper(SearchContext searchContext, int timeout){
        this.searchContext = searchContext;
        webDriverWait = new WebDriverWait(getDriver(), timeout);
        js = (JavascriptExecutor) getDriver();
    }

    public static WebDriver getDriver(){
        return BaseTest.getDriver();
    }

    public static WebDriver getDriverMobile(){
        return BaseMobile.getDriver();
    }

    public DriverHelper(int timeout) {
        this(getDriver(), timeout);
    }

    public DriverHelper(TimeOutMob timeout) {
        this(getDriverMobile(), timeout.time);
    }



    DriverHelper() {
        this(TIMEOUT_DEFAULT);
    }

    /**
     * Waiting for something
     */

    public static void waitForPageLoad(){
        Log.info("Waiting for page load");
        WebDriverWait webDriverWait = new WebDriverWait(getDriver(), 30);
        webDriverWait.until(wait ->
        String.valueOf(((JavascriptExecutor) getDriver()).executeScript("return document.readyState"))
                .equals("complete"));
    }

    @Step("Wait until url contains - {text}")
    public static boolean waitForUrlContains(String text){
        Log.info("Waiting while page URL contains : " + text);
        WebDriverWait webDriverWait = new WebDriverWait(getDriver(), 30);
        webDriverWait.until(ExpectedConditions.urlContains(text));
        return getDriver().getCurrentUrl().contains(text);
    }

    public WebElement waitForVisibility(WebElement element){
        Log.info("Waiting for visibility of" + element);
        return webDriverWait.until(ExpectedConditions.visibilityOf(element));
    }
    public WebElement waitForVisibility(By locator){
        Log.info("Waiting for visibility of" + locator);
        return webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    /**
     * Opening the url
     * @param url
     */

    @Step("Open url -{url}")
    public static void openUrl(String url){
        getDriver().get(url);
        waitForPageLoad();
        Log.info("The " + url + " is opened");
    }

    @Step("Open url -{url}")
    public static <T> T openUrl(String url, Class <T> clazz){
        getDriver().get(url);
        waitForPageLoad();
        Log.info("The " + url + " is opened");
        return (T) clazz;
    }

    /**
     * Refreshing page
     */

    @Step("Refreshing page")
    public static void refreshPage(){
        getDriver().navigate().refresh();
        Log.info("The page is refreshed");
    }

    @Step("Refreshing page and wait until load")
    public static void refreshPageAndWait(){
        refreshPage();
        waitForPageLoad();
    }
    /**
     * Helpful functions
     */

    public void scrollToElement(WebElement element){
        js.executeScript("argument[0].scrollIntoView(true)", element);
        Log.info("Scrolled to the element" + element);
    }
    public void scrollToElement(By locator){
        WebElement element = findElement(locator);
        js.executeScript("argument[0].scrollIntoView(true)", element);
        Log.info("Scrolled to the element" + element);
    }


    public void clearCookiesAndCached(){
        getDriver().get("chrome://settings/clearBrowserData");
        Utils.delayMillisec(5000);
        Actions actions = new Actions(getDriver());
        for (int i = 0; i < 7; i++) {
            actions.sendKeys(Keys.TAB).build().perform();
            Utils.delayMillisec(1000);
        }
        actions.sendKeys(Keys.ENTER).build().perform();
        Utils.delayMillisec(10000);
        Log.info("Cleared the cookies and cache");
    }

    public void sendESC(){
        new Actions(getDriver()).sendKeys(Keys.ESCAPE).perform();
        Log.info("The ESC button is clicked");
    }

    /**
     * Driver Functions
     */

    public WebElement findElement(By xpath){
        return getDriver().findElement(xpath);
    }

    public List<WebElement> findElements(By xpath){
        List<WebElement> elements  =  getDriver().findElements(xpath);
        Log.info("Total amount of founded elements: " + elements.size());
        return elements;
    }

    public boolean isElementDisplayed(WebElement element){
        try {
            return waitForVisibility(element).isDisplayed();
        } catch (WebDriverException e){
            Log.info("The element" + element + " isn't displayed");
            return false;
        }
    }

    public boolean isElementDisplayed(By locator){
        try {
            return waitForVisibility(locator).isDisplayed();
        } catch (WebDriverException e){
            Log.info("The element" + locator + " isn't displayed");
            return false;
        }
    }

    public void clickWebElement(WebElement element){
        webDriverWait.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
        Log.info("The " + element + " is clicked");
    }

    public void clickWebElement(By locator){
        WebElement element = webDriverWait.until(ExpectedConditions.elementToBeClickable(locator));
        clickWebElement(element);
    }

    public void typeIntoElement(WebElement element, String text){
        element.sendKeys(text);
        Log.info("The text is inserted to the element " + element);
    }

    public void getElementAttribute(WebElement element, String attribute){
        waitForVisibility(element);
        try {
            element.getAttribute(attribute);
        } catch (WebDriverException e) {
            Log.error("The attribute isn't founded " + attribute + " | " + element  );
        }
    }

    public enum TimeOutMob{
        SHORT(10),
        LONG(60),
        DEFAULT(30);

        public  int time;

        TimeOutMob(int time){
            this.time=time;
        }

    }
}
