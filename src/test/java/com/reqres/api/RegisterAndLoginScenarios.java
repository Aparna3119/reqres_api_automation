package com.reqres.api;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.testng.annotations.*;
import static com.google.common.truth.Truth.assertThat;

public class RegisterAndLoginScenarios {
    private RequestSpecification request;
    private JSONObject requestParam = new JSONObject();

    @BeforeTest
    public void setRequest(){
        RestAssured.baseURI ="https://reqres.in/";
        request = RestAssured.given();
        requestParam.put("email","eve.holt@reqres.in");
        requestParam.put("password","pistol");
        request.header("Content-Type", "application/json")
                .body(requestParam.toJSONString());
    }
    @Test
    public void verifySuccessfulRegistration(){
        Response response = request
                .post("/api/register");
        assertThat(response.statusCode()).isEqualTo(200);
        assertThat(response.getBody().jsonPath().getString("token")).isNotNull();
        assertThat(response.getBody().jsonPath().getString("id")).isEqualTo("4");
        assertThat(response.getBody().jsonPath().getString("id")).isNotNull();
    }

    @Test(dependsOnMethods = "verifySuccessfulRegistration")
    public void verifySuccessfulLogin(){
        Response response = request
                .post("/api/login");
        assertThat(response.statusCode()).isEqualTo(200);
        assertThat(response.getBody().jsonPath().getString("token")).isNotNull();
    }
}
