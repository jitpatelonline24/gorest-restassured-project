package com.gorest.testsuite;



import com.gorest.testbase.TestBase;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class UserAssertionTest extends TestBase {

    static ValidatableResponse response;

    /*curl --location --request GET
'https://gorest.co.in/public/v2/users?page=1&per_page=20'*/

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

    //    Assert the following:
//      1. Verify the if the total record is 20

    @Test
    public void test001(){
        List<Integer> total = response.extract().path("id");
        Assert.assertEquals(total.size(),20);
        System.out.println("Total records are: "+total.size());
    }

//      2. Verify the if the name of id = 5914050 is equal to ”Rahul Iyengar”
    @Test
    public void test002(){
        response.body("find{it.id== 5914050}.name",equalTo("Rahul Iyengar"));
        System.out.println("ID : 5914050 belongs to : 'Rahul Iyengar' ");
    }

//      3. Check the single ‘Name’ in the Array list (Deeptendu Menon)
@Test
public void test003(){
   response.body("name",hasItem("Deeptendu Menon"));
}
//      4. Check the multiple ‘Names’ in the ArrayList ("Ramaa Banerjee","Brahmdev Devar", "Rahul Iyengar" )
@Test
public void test004(){
    response.body("name",hasItems("Ramaa Banerjee","Brahmdev Devar", "Rahul Iyengar"));
}
//      5. Verify the email of userid = 5914129 is equal “tara_panicker@hansen.example”
@Test
public void test005(){
    response.body("find{it.id='5914129'}.email",equalTo("tara_panicker@hansen.example"));
}
//      6. Verify the status is “Active” of user name is “Paramartha Kocchar”
@Test
public void test006(){
    response.body("find{it.name= 'Paramartha Kocchar'}.status",equalTo("active"));
}
//      7. Verify the Gender = male of user name is “Ghanaanand Verma”
@Test
public void test007(){
    response.body("find{it.gender== 'male'}.name",equalTo("Ghanaanand Verma"));
}
}
