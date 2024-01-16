package com.gorest.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import static io.restassured.RestAssured.given;

public class ProductsExtractionTest {

    static ValidatableResponse response;


    @BeforeClass
    public void inIt() {
        RestAssured.baseURI = "https://gorest.co.in";
        //RestAssured.port = 8081;
        RestAssured.basePath = "/public/v2";

        response = given()
                .when()
                .queryParam("page", 1)
                .queryParam("per_page", 25)
                .get("/posts")
                .then().statusCode(200);
    }

//    Extraction Example
//  1. Extract the title
@Test
public void test001() {

    List<String> listOfTitles = response.extract().path("title");

    System.out.println("------------------StartingTest---------------------------");
    System.out.println("The Extract all Titles is : " + listOfTitles);
    System.out.println("------------------End of Test---------------------------");
}

//  2. Extract the total number of record
@Test
public void test002() {

    List<String> listOfTotalRecords = response.extract().path("id");

    System.out.println("------------------StartingTest---------------------------");
    System.out.println("The Extract all Total number of Records : " + listOfTotalRecords);
    System.out.println("------------------End of Test---------------------------");
}
//  3. Extract the body of 15th record
@Test
public void test003() {

    String listOfRecord = response.extract().path("[14].body");

    System.out.println("------------------StartingTest---------------------------");
    System.out.println("The Extract all Titles is : " + listOfRecord);
    System.out.println("------------------End of Test---------------------------");
}
//  4. Extract the user_id of all the records
@Test
public void test004() {

    List<String> listOfAllRecords = response.extract().path("user_id");

    System.out.println("------------------StartingTest---------------------------");
    System.out.println("The Extract the user_id of all the records : " + listOfAllRecords);
    System.out.println("------------------End of Test---------------------------");
}
//  5. Extract the title of all the records
@Test
public void test005() {

    List<String> listOfTitles = response.extract().path("title");

    System.out.println("------------------StartingTest---------------------------");
    System.out.println("Extract the title of all the records : " + listOfTitles);
    System.out.println("------------------End of Test---------------------------");
}
//  6. Extract the title of all records whose user_id = 5914200
@Test
public void test006() {

    List<?> listOfTitlesRecords = response.extract().path("findAll{it.user_id = '5914200'}.title");

    System.out.println("------------------StartingTest---------------------------");
    System.out.println("Extract the title of all records whose user_id : " + listOfTitlesRecords);
    System.out.println("------------------End of Test---------------------------");
}
//  7. Extract the body of all records whose id = 93957
@Test
public void test007() {

    List<?> listOfAllRecords = response.extract().path("findAll{it.body = '93957'}.title");

    System.out.println("------------------StartingTest---------------------------");
    System.out.println("Extract the body of all records whose id  : " + listOfAllRecords);
    System.out.println("------------------End of Test---------------------------");
}
}
