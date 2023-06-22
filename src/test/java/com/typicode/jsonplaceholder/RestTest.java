package com.typicode.jsonplaceholder;

import io.restassured.response.ValidatableResponse;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class RestTest {
    @Test
    public void testGetPostById(){
       given().when().get("https://jsonplaceholder.typicode.com/posts/1").then().log().body();
    }

    @Test
    public void testCreatePost(){
        PostObject po = new PostObject(1000, 1, "Hello", "Hello from rest");
        given().header("Content-type","application/json; charset=UTF-8").body(po)
                .when().post("https://jsonplaceholder.typicode.com/posts").then();

        ValidatableResponse responce = given().header("Content-type","application/json; charset=UTF-8").body(po)
                .when().post("https://jsonplaceholder.typicode.com/posts").then();

        PostObject poActual = responce.extract().as(PostObject.class);

        Assert.assertEquals(responce.extract().statusCode(), 200);
        Assert.assertEquals(poActual, po);
    }
}
