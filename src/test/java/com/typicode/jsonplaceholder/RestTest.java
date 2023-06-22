package com.typicode.jsonplaceholder;

import org.testng.annotations.Test;


import static io.restassured.RestAssured.given;

public class RestTest {
    @Test
    public void testGetPostById(){
       given().when().get("https://jsonplaceholder.typicode.com/posts/1").then().log().body();
    }

    @Test
    public void testCreatePost(){
        PostObject po = new PostObject(1000, 1, "Hello", "Hello from rest");
        given().header("Content-type","application/json; charset=UTF-8")
                .body(po).post("https://jsonplaceholder.typicode.com/posts")
                .then().log().body();
    }

}
