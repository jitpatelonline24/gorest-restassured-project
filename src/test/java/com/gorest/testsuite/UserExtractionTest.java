package com.gorest.testsuite;

import com.gorest.testbase.TestBase;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class UserExtractionTest extends TestBase {

    static ValidatableResponse response;

    @BeforeClass
    public void inIt() {
        RestAssured.baseURI = "https://gorest.co.in";
        //RestAssured.port = 8081;
        RestAssured.basePath = "/public/v2";

        response = given()
                .when()
                .queryParam("page", 1)
                .queryParam("per_page", 20)
                .get("/users")
                .then().statusCode(200);
    }

    //    Extraction Example
//  1. Extract the All Ids
    @Test
    public void test001() {

        List<Integer> listOfIds = response.extract().path("id");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The Extract all Ids is : " + listOfIds);
        System.out.println("------------------End of Test---------------------------");

    }
//  2. Extract the all Names
@Test
public void test002() {

    List<String> listOfNames = response.extract().path("name");

    System.out.println("------------------StartingTest---------------------------");
    System.out.println("The Extract all Names is : " + listOfNames);
    System.out.println("------------------End of Test---------------------------");
}
//  3. Extract the name of 5th object
@Test
public void test003() {

    String objectName = response.extract().path("[4].name");

    System.out.println("------------------StartingTest---------------------------");
    System.out.println("The Extract all Names is : " + objectName);
    System.out.println("------------------End of Test---------------------------");

}
//  4. Extract the names of all object whose status = inactive
@Test
public void test004() {

    List<String> listNameofStatus = response.extract().path("findAll{it.status=='inactive'}");

    System.out.println("------------------StartingTest---------------------------");
    System.out.println("The Extract all Names is : " + listNameofStatus);
    System.out.println("------------------End of Test---------------------------");
}
//  5. Extract the gender of all the object whose status = active
@Test
public void test005() {

    List<String> listActiveStatusOfGender = response.extract().path("findAll{it.status=='active'}.gender");

    System.out.println("------------------StartingTest---------------------------");
    System.out.println("The Extract all Gender whose status Active : " + listActiveStatusOfGender);
    System.out.println("------------------End of Test---------------------------");
}
//  6. Print the names of the object whose gender = female
@Test
public void test006() {

    List<String> listOfFemaleGender = response.extract().path("findAll{it.gender=='female'}.name");

    System.out.println("------------------StartingTest---------------------------");
    System.out.println("The Extract all Gender whose status Active : " + listOfFemaleGender);
    System.out.println("------------------End of Test---------------------------");
}
//  7. Get all the emails of the object where status = inactive
@Test
public void test007() {

    List<String> listOfInactiveEmails = response.extract().path("findAll{it.status=='inactive'}.email");

    System.out.println("------------------StartingTest---------------------------");
    System.out.println("Get all the emails of the object where status = inactive : " + listOfInactiveEmails);
    System.out.println("------------------End of Test---------------------------");
}
//  8. Get the ids of the object where gender = male
@Test
public void test008() {

    List<String> listOfMaleGender = response.extract().path("findAll{it.gender=='male'}.id");

    System.out.println("------------------StartingTest---------------------------");
    System.out.println("TGet the ids of the object where gender = male: " + listOfMaleGender);
    System.out.println("------------------End of Test---------------------------");
}
//  9. Get all the status
@Test
public void test009() {

    List<String> listOfAllStatus = response.extract().path("status");

    System.out.println("------------------StartingTest---------------------------");
    System.out.println("Get all the status : " + listOfAllStatus);
    System.out.println("------------------End of Test---------------------------");
}
//  10. Get email of the object where name = Heema Kaniyar
@Test
public void test010() {

    String getEmailOfName = response.extract().path("find{it.name=='Heema Kaniyar'}.email");

    System.out.println("------------------StartingTest---------------------------");
    System.out.println("The Email of the object where name : Arnesh Singh " + getEmailOfName);
    System.out.println("------------------End of Test---------------------------");
}
//  11. Get gender of id = 5914046
@Test
public void test011() {

    String getGenderOfId = response.extract().path("find{it.id==5914046}.gender");

    System.out.println("------------------StartingTest---------------------------");
    System.out.println("Get Gender of whose Id : " + getGenderOfId);
    System.out.println("------------------End of Test---------------------------");
}
}
