package common.utils.API;

import common.utils.Log;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.Map;

public abstract class BaseAPI {

    protected RequestSpecification setSpecification(){
        RequestSpecification requestSpecification =
                RestAssured.given().contentType(Headers.CONTENT_TYPE.spec).accept(Headers.ACCEPT.spec);
        Log.info("Specification is set");
        return requestSpecification;
    }

    protected RequestSpecification setBody(Map<String, String> body){
        RequestSpecification requestSpecification = setSpecification().log().all()
                .body(body);
        Log.info("The body is set");
        return requestSpecification;
    }

    protected Response makePostRequest(Map<String, String> body, RequestURLs url){
        Response response = setBody(body).when().post(url.url);
        Log.info("The body is set");
        return response;
    }



        public enum Headers{
                ACCEPT(""),
                CONTENT_TYPE("");

                public String spec;

                Headers(String spec){
                    this.spec = spec;
                }
        }
}
