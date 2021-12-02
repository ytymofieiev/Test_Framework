import common.DriverHelper;
import common.baseMobile.BaseBrowserStack;
import org.testng.annotations.Test;

public class InnerTestMobile extends BaseBrowserStack {


    @Test
    public void signInTest() {
        DriverHelper.openUrl("https://www.stage-dashboard.clickcease.com/");
    }
}
