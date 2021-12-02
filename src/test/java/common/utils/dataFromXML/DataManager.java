package common.utils.dataFromXML;

import org.jdom2.Document;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public enum DataManager {

    INSTANCE;

    private final String userDataFile = "UserData.xml";
    private List<UserData> userData;

    DataManager (){
        File path = new File("src/test/resources/" + userDataFile);
        userData = new ArrayList<>();
            try {
                Document document = new SAXBuilder().build(path);
                document.getRootElement().getChildren().forEach(e -> userData.add(new UserData(e)));
            } catch (JDOMException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
    }

    public UserData getByID(String id){
        return userData.stream().filter(x -> x.getId().equals(id)).findFirst().orElseThrow(NoSuchElementException::new);
    }
}
