package stepDefinition;
import io.cucumber.datatable.DataTable;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.*;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class StepDefinition extends Utils{
    ResponseSpecification res;
    RequestSpecification req;
    Response response;
    static String place_id;

    TestDataBuild data = new TestDataBuild();

    @Given("Add Place Payload with {string},{string},{string}")
    public void add_place_payload_with(String name, String language, String address) throws IOException {
        req = given().spec(requestSpecification()).body(data.addPlacePayLoad(name,language,address));
    }
    @When("user calls {string} with {string} http request")
    public void user_calls_with_post_http_request(String resource, String httpMethod) {
        APIResources resources = APIResources.valueOf(resource);
        System.out.println(resources.getResource());
        res = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();

        if(httpMethod.equalsIgnoreCase("POST")){
            response = req.when().post(resources.getResource())
                    .then().spec(res).extract().response();
        }
        else if (httpMethod.equalsIgnoreCase("GET")) {
            response = req.when().get(resources.getResource())
                    .then().spec(res).extract().response();
        }

    }
    @Then("the API call is success with status code {int}")
    public void the_api_call_is_success_with_status_code(Integer int1) {
        assertEquals(200,response.statusCode());
    }
    @Then("{string} is response body is {string}")
    public void is_response_body_is(String key, String value) {
        assertEquals(value,getJsonPath(response,key));
    }

    @Then("verify place_id created maps to {string} using {string}")
    public void verify_place_id_created_maps_to_using(String expectedName, String resource) throws IOException {

        //getApiCall
      place_id = getJsonPath(response,"place_id");
        req = given().spec(requestSpecification()).queryParam("place_id",place_id);
        user_calls_with_post_http_request(resource,"GET");
        String actualName =getJsonPath(response,"name");
        assertEquals(expectedName,actualName);

    }
    @Given("DeletePlace Payload")
    public void delete_place_payload() throws IOException {
        req = given().spec(requestSpecification()).body(data.deletePlacePayload(place_id));
    }

}
