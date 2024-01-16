package com.gorest.testbase;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;

import static io.restassured.RestAssured.given;

public class TestBase {

    @BeforeClass
    public void inIt() {
        RestAssured.baseURI = "https://gorest.co.in";
        //RestAssured.port = 8081;
        RestAssured.basePath = "/public/v2";

        Response response = given()
                .when()
                .get("/users?page=1&per_page=20");
        response.then().statusCode(200);
        response.prettyPrint();
    }
}
