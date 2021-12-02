package common.base;

import common.DriverHelper;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementDecorator;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementLocatorFactory;

public abstract class BasePage extends Base{

    protected DriverHelper h;
    protected DriverHelper hLong;
    protected DriverHelper hShort;

    public BasePage() {
        DriverHelper.waitForPageLoad();
        HtmlElementLocatorFactory factory = new HtmlElementLocatorFactory(DriverHelper.getDriver());
        PageFactory.initElements(new HtmlElementDecorator(factory), this);
        hShort = new DriverHelper(DriverHelper.TIMEOUT_SHORT);
        h = new DriverHelper(DriverHelper.TIMEOUT_DEFAULT);
        hLong = new DriverHelper(DriverHelper.TIMEOUT_LONG);
    }

    public void switchToFrame(WebElement element) {
        DriverHelper.getDriver().switchTo().frame(element);
    }

    public void switchToDefaultContent() {
        DriverHelper.getDriver().switchTo().defaultContent();
    }

    public void waitForPageLoad(){
        DriverHelper.waitForPageLoad();
    }
}
