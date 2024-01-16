package com.gorest.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class PostsAssertionTest {

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

/*    curl --location --request GET
'https://gorest.co.in/public/v2/posts?page=1&per_page=25'*/

    //    Assert the following:
//  1. Verify the if the total record is 25
    @Test
    public void test001() {
        List<Integer> total = response.extract().path("id");
        Assert.assertEquals(total.size(), 25);
        System.out.println("Total records are: " + total.size());
    }

    //  2. Verify the if the title of id = 93997 is equal to ”Demitto conqueror atavus argumentum corrupti cohaero libero.”
    @Test
    public void test002() {
        response.body("find{it.id== 94000}.title", equalTo("Temeritas tardus omnis nam quia conspergo confero."));
        System.out.println("ID : the title of id = 93997 is equal to: Temeritas tardus omnis nam quia conspergo confero. ");
    }

    //  3. Check the single user_id in the Array list (5914181)
    @Test
    public void test003() {
        response.body("user_id", hasItem(5914181));
        //System.out.println("ID : single user_id in the Array list (5914249)");
    }

    //  4. Check the multiple ids in the ArrayList (5914156, 5914064, 5914070)
    @Test
    public void test004() {
        response.body("user_id",hasItems(5914156, 5914064, 5914070));

    }

    //  5. Verify the body of userid = 5914197 is equal “Vero solutio aperte. Occaecati dolor apto. Pauper canis utilis. Antiquus corpus amet. Cado sollers anser. Vesper vivo officiis. Non deripio degusto. Delibero vulgo viriliter. Derideo deficio vitium. Arcus damnatio est. Coniuratio tepidus theologus. Triumphus et damno. Et surculus vesper.”
    @Test
    public void test005() {
        response.body("find{it.user_id== 5914059}.body",equalTo("Vero solutio aperte. Occaecati dolor apto. Pauper canis utilis. Antiquus corpus amet. Cado sollers anser. Vesper vivo officiis. Non deripio degusto. Delibero vulgo viriliter. Derideo deficio vitium. Arcus damnatio est. Coniuratio tepidus theologus. Triumphus et damno. Et surculus vesper."));

    }

}
