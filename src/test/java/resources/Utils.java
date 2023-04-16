package resources;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

public class Utils {
     public static RequestSpecification req;
    public  RequestSpecification requestSpecification() throws IOException {
        if(req==null) {

            PrintStream stream = new PrintStream(Files.newOutputStream(Paths.get("login.txt")));
            req = new RequestSpecBuilder().setBaseUri(getGlobalValue("baseUrl")).addQueryParam("key", "qaclick123")
                    .addFilter(RequestLoggingFilter.logRequestTo(stream))
                    .addFilter(ResponseLoggingFilter.logResponseTo(stream))
                    .setContentType(ContentType.JSON).build();
            return req;
        }
            return req;

    }

    public String getGlobalValue(String key) throws IOException {
        Properties prop = new Properties();
        FileInputStream file = new FileInputStream("C:\\Users\\asena\\eclipse-workspace\\APIFramework\\src\\test\\java\\resources\\global.properties");
        prop.load(file);
        return prop.getProperty(key);
    }

    public String getJsonPath(Response response, String key){

        String output = response.asString();
        JsonPath jsonPath = new JsonPath(output);
        return jsonPath.get(key).toString();
    }


}
