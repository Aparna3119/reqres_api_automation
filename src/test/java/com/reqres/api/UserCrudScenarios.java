package com.reqres.api;

import io.restassured.*;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.testng.annotations.*;
import static com.google.common.truth.Truth.assertThat;

public class UserCrudScenarios {
    private String userId = null;
    private RequestSpecification request;

    @BeforeTest
    public void setRequest() {
        RestAssured.baseURI = "https://reqres.in/";
        request = RestAssured.given();
    }

    @Test
    public void createUser(){
        JSONObject requestParam = new JSONObject();
        requestParam.put("name","Aparna");
        requestParam.put("job","QA");
        request.header("Content-Type", "application/json")
                .body(requestParam.toJSONString());
        Response response = request
            .post("/api/users");
        userId = response.getBody().jsonPath().getString("id");
        assertThat(response.statusCode()).isEqualTo(201);
        assertThat(response.getBody().jsonPath().getString("name")).isEqualTo("Aparna");
        assertThat(response.getBody().jsonPath().getString("job")).isEqualTo("QA");
        assertThat(response.getBody().jsonPath().getString("id")).isNotNull();
        assertThat(response.getBody().jsonPath().getString("createdAt")).isNotNull();
    }

    @Test(dependsOnMethods = {"createUser"})
    public void deleteUser(){
        Response response = request
                .delete("/api/users/"+userId);
        assertThat(response.statusCode()).isEqualTo(204);
    }

    @Test
    public void getListOfUsers(){
        Response response = request
                .get("/api/users?page=2");
        assertThat(response.statusCode()).isEqualTo(200);
        assertThat(response.getBody().jsonPath().getString("page")).isEqualTo("2");
        assertThat(response.getBody().jsonPath().getString("per_page")).isEqualTo("6");
        assertThat(response.getBody().jsonPath().getString("total")).isNotNull();
        assertThat(response.getBody().jsonPath().getString("total_pages")).isNotNull();
        assertThat(response.getBody().jsonPath().getList("data")).hasSize(6);
    }

    @Test
    public void getSingleUser(){
        Response response = request
                .get("/api/users/2");
        assertThat(response.statusCode()).isEqualTo(200);
        assertThat(response.getBody().jsonPath().getString("data.id")).isEqualTo("2");
        assertThat(response.getBody().jsonPath().getString("data.email")).isNotNull();
        assertThat(response.getBody().jsonPath().getString("data.first_name")).isNotNull();
        assertThat(response.getBody().jsonPath().getString("data.last_name")).isNotNull();
        assertThat(response.getBody().jsonPath().getString("data.avatar")).isNotNull();
    }
}
