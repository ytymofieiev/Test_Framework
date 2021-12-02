package common.baseMobile;

import common.DriverHelper;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementDecorator;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementLocatorFactory;

public class BasePageMobile {

    public DriverHelper h;
    public DriverHelper hMinTimeout;
    public DriverHelper hMaxTimeout;

    public BasePageMobile() {
        DriverHelper.waitForPageLoad();
        HtmlElementLocatorFactory factory = new HtmlElementLocatorFactory(DriverHelper.getDriver());
        PageFactory.initElements(new HtmlElementDecorator(factory), this);
        h = new DriverHelper(DriverHelper.TimeOutMob.DEFAULT);
        hMinTimeout = new DriverHelper(DriverHelper.TimeOutMob.SHORT);
        hMaxTimeout = new DriverHelper(DriverHelper.TimeOutMob.LONG);
    }

    public void switchToFrame(WebElement element) {
        DriverHelper.getDriver().switchTo().frame(element);
    }

    public void switchToDefaultContent() {
        DriverHelper.getDriver().switchTo().defaultContent();
    }
}