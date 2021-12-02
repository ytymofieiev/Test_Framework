package common.utils.dataFromXML;

import org.jdom2.Element;

import java.util.List;


public class UserData {

    private String id;
    private String name;
    private String pass;
    private String username;

    public UserData (Element element){
        id = element.getAttributeValue("id");
        List<Element> children = element.getChildren();

        for (Element tag : children){
            switch (tag.getName()){
                case "name":{
                    this.name = tag.getText();
                    break;
                }
                case "pass":{
                    this.pass = tag.getText();
                    break;
                }
                case "username":{
                    this.username = tag.getText();
                    break;
                }
            }
        }
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }
}
