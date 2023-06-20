package com.typicode.jsonplaceholder;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class RestTest {

    @Test
    public void testGetPostById(){
        given().when().get("https://jsonplaceholder.typicode.com/posts/1").then().log().body();

    }
}
