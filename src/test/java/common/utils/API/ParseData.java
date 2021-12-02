package common.utils.API;

import com.google.gson.Gson;
import io.restassured.response.Response;

import java.util.ArrayList;
import java.util.List;

public abstract class ParseData {

    public <T> T getData(Response response, Class<T> clazz){
        String parse = getStringFromResponse(response);

        Gson gson = new Gson();
        T data = gson.fromJson(parse, clazz);
        return data;
    }

    public <T> List<T> getDataList(Response response, Class<T> clazz){
        String parse = getStringFromResponse(response);

        List<T> dataList = new ArrayList<>();
        Gson gson = new Gson();
        List data = gson.fromJson(parse, List.class);
        for (Object o: data){
            T dateOne = gson.fromJson(gson.toJson(o), clazz);
            dataList.add(dateOne);
        }
        return dataList;
    }


    public String getStringFromResponse(Response response){
        return response.then().extract().response().getBody().asString();
    }
}
