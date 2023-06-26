package com.typicode.jsonplaceholder;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

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

    @Test
    public void test21vek() {
        String body = "{\n" +
                "    \"User\": {\n" +
                "        \"email\": \"test@test.com\",\n" +
                "        \"password\": \"1q2w3e4r\"\n" +
                "    }\n" +
                "}";
        given().when().header("Content-Type", "application/json").body(body).post("https://www.21vek.by/users/login/").then().log().body();
    }

    @Test
    public void testTous() {
        HashMap<String, String> queryParams = new HashMap<>();
        queryParams.put("client_id", "web_ey");
        queryParams.put("client_secret", "secret");
        queryParams.put("grant_type", "password");
        queryParams.put("username", "");
        queryParams.put("password", "");
        given().when().queryParams(queryParams).post("https://www.tous.com/authorizationserver/oauth/token").then().log().body();
    }

    @Test
    public void testMarko() {
        HashMap<String, String> formParams = new HashMap<>();
        formParams.put("form_data", "login=%2B375%20(29)%20655-00-09&password=1q2w3e4r");
        HashMap<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        headers.put("X-Requested-With", "XMLHttpRequest");
        given().when().headers(headers).formParams(formParams).post("https://www.marko.by/ajax/authClient.php").then().log().body();
    }

    @Test
    public void testDev() {
        String body = "{\"user\":{\"login\":\"test@test.com\",\"password\":\"1q2w3e4r\"}}";
        HashMap<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json;charset=UTF-8");
        headers.put("X-Requested-With", "XMLHttpRequest");
        headers.put("Origin", "https://id.devby.io");
        headers.put("Referer", "https://id.devby.io/@/hello");

        RestAssured.urlEncodingEnabled = false;

        given().when().headers(headers).body(body).post("https://id.devby.io/@/hello").then().log().body();
    }

    @Test
    public void testMegatop() {
        String body = "{\"email\":\"375296550009\",\"password\":\"1q2w3e4r\"}";

        given().when().body(body).header("Content-Type", "application/json").post("https://admin.megatop.by/api/v1/user/login").then().log().body();
    }

    @Test
    public void testVodafone() {
        String body = "{\n" +
                "    \"username\": \"\",\n" +
                "    \"password\": \"1q2w3e4r\",\n" +
                "    \"returnUrl\": \"/connect/authorize/callback?client_id=ecare-auth-prod1&scope=openid%20vf-profile%20vf-account%20vf-subscription%20vf-contact&response_type=code&redirect_uri=https%3A%2F%2Fmyaccount.vodafone.co.uk%2Fauth%2Flogin%2Fcallback&state=%7B%22successReturnUrl%22%3A%22%252Faccount%252Fsummary%253Ficmp%253Duk~1_consumer~topnav~useractions~1_login_to_my_vodafone%2526linkpos%253Dtopnav~1~1%22,%22errorReturnUrl%22%3A%22https%253A%252F%252Fwww.vodafone.co.uk%252F%22%7D&acr_values=LOA%3A3\",\n" +
                "    \"userIdentifier\": \"un\"\n" +
                "}";

        given().when().body(body).header("Content-Type", "application/json").post("https://login.vodafone.co.uk/api/authentication/password").then().log().body();
    }
}
