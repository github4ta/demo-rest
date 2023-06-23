package com.typicode.jsonplaceholder;

import io.restassured.response.ValidatableResponse;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class RestTest {
    @Test
    public void testGetPostById() {
        given().when().get("https://jsonplaceholder.typicode.com/posts/1").then().log().body();
    }

    @Test
    public void testCreatePost() {
        PostObject po = new PostObject(1000, 1, "Hello", "Hello from rest");
        given().header("Content-type", "application/json; charset=UTF-8").body(po)
                .when().post("https://jsonplaceholder.typicode.com/posts").then();

        ValidatableResponse responce = given().header("Content-type", "application/json; charset=UTF-8").body(po)
                .when().post("https://jsonplaceholder.typicode.com/posts").then();

        PostObject poActual = responce.extract().as(PostObject.class);

        Assert.assertEquals(responce.extract().statusCode(), 200);
        Assert.assertEquals(poActual, po);
    }

    @Test
    public void testEdostavka() {
        String body = "{\n" +
                "    \"CRC\": \"\",\n" +
                "    \"Packet\": {\n" +
                "        \"JWT\": null,\n" +
                "        \"MethodName\": \"GetJWT\",\n" +
                "        \"ServiceNumber\": \"01093ABC-6B36-450D-8FAF-EA32BCC2EAE8\",\n" +
                "        \"Data\": {\n" +
                "            \"LoginName\": \"375296550009\",\n" +
                "            \"Password\": \"1q2w3e4r\",\n" +
                "            \"LoginNameTypeId\": 2,\n" +
                "            \"SourceName\": \"edostavka.by\",\n" +
                "            \"UUID\": \"54d97ed9-ad81-4006-b7e6-811fbe4a993a\",\n" +
                "            \"UserAgent\": \"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36\",\n" +
                "            \"IpAddress\": \"86.57.143.244\"\n" +
                "        }\n" +
                "    }\n" +
                "}";
        given().when().body(body).post("https://api.static.edostavka.by/rest/Json").then().log().body();
    }

    @Test
    public void testRedfin() {
        HashMap<String, String> formParams = new HashMap<>();
        formParams.put("email", "test@test.com");
        formParams.put("pwd", "1q2w3e4r");
        formParams.put("authenticationAuthority", "Redfin");
        given().when().formParams(formParams).post("https://www.redfin.com/stingray/do/api-login").then().log().body();
    }

    @Test
    public void testDominos() {
        HashMap<String, String> queryParams = new HashMap<>();
        queryParams.put("language", "ru");
        queryParams.put("cityId", "2");
        String body = "{\n" +
                "   \"login\":\"\",\n" +
                "   \"password\":\"\"\n" +
                "}";
        given().when().queryParams(queryParams).body(body).post("https://www.dominos.by/api/web/user/session").then().log().body();
    }

    @Test
    public void testMarkformelle() {
        HashMap<String, String> formParams = new HashMap<>();
        formParams.put("backurl", "/");
        formParams.put("AUTH_FORM", "Y");
        formParams.put("TYPE", "AUTH");
        formParams.put("USER_LOGIN", "test@test.com");
        formParams.put("USER_PASSWORD", "1q2w3e4r");
        formParams.put("USER_REMEMBER", "Y");
        given().when().formParams(formParams).post("https://markformelle.by/ajax/auth_ajax.php").then().log().body();
    }
}
