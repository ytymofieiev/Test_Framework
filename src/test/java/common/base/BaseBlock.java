package common.base;

import common.DriverHelper;
import common.utils.Log;
import io.qameta.allure.Step;
import org.openqa.selenium.support.PageFactory;
import ru.yandex.qatools.htmlelements.element.HtmlElement;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementDecorator;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementLocatorFactory;


public abstract class BaseBlock extends HtmlElement {

    protected  DriverHelper h;
    protected  DriverHelper hLong;
    protected  DriverHelper hShort;

    public BaseBlock() {
        h = new DriverHelper(this, DriverHelper.TIMEOUT_LONG);
        hLong = new DriverHelper(this, DriverHelper.TIMEOUT_LONG);
        hShort = new DriverHelper(this, DriverHelper.TIMEOUT_SHORT);
        HtmlElementLocatorFactory factory = new HtmlElementLocatorFactory(DriverHelper.getDriver());
        PageFactory.initElements(new HtmlElementDecorator(factory), this);
    }

    @Step("Is block displayed")
    public boolean isDisplayed() {
        boolean block = h.isElementDisplayed(getWrappedElement());
        Log.info("Verify is the "+ getName() +" block displays" );
        return block;
    }

}
