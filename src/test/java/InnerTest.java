import common.DriverHelper;
import common.base.BaseTest;
import common.utils.Log;
import common.utils.Utils;
import common.utils.dataFromXML.DataManager;
import common.utils.dataFromXML.UserData;
import org.testng.annotations.Test;

public class InnerTest extends BaseTest {

    public FindLoh findLoh;
    @Test
    public void test(){
        DriverHelper.openUrl("https://mvnrepository.com/artifact/io.qameta.allure/allure-gradle/2.8.1");
        Log.info("SOSI");
        Utils.delayMillisec(5000);
    }

    @Test(description = "Hello")
    public void checkListeners(){
        Log.info("SOSI SAM");
        findLoh = new FindLoh();
        findLoh.asda();
        UserData user = DataManager.INSTANCE.getByID("first");
        Log.info(user.getName());
        Utils.delayMillisec(5000);
    }


}
