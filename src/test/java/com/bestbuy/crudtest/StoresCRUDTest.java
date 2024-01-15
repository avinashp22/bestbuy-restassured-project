package com.bestbuy.crudtest;


import com.bestbuy.model.StorePojo;
import com.bestbuy.testbase.StoreTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;


public class StoresCRUDTest extends StoreTestBase {

    static int id;
    static String name="Brent Cross";
    static String type="Mall";
    static String address="123 Prime Road";
    static String address2="Wembley";
    static String city="Brent";
    static String state="NV";
    static String zip="22183";
    static double lat=51.57620;
    static  double lng=11.2234;
    static String hours="Mon: 9-9; Tue: 9-9; Wed: 9-9; Thurs: 9-9; Fri: 9-9; Sat: 9-9; Sun: 11-4";


    @Test
    public void averifyStoreCreatedSuccessfully() {
        Map<String,Object> services = new HashMap<>();
        services.put("name","Geek Squad Services");
        services.put("id","01");
        StorePojo storePojo=new StorePojo();
        storePojo.setName(name);
        storePojo.setType(type);
        storePojo.setAddress(address);
        storePojo.setAddress2(address2);
        storePojo.setCity(city);
        storePojo.setState(state);
        storePojo.setZip(zip);
        storePojo.setLat(lat);
        storePojo.setLng(lng);
        storePojo.setHours(hours);
        storePojo.setServices(services);

        ValidatableResponse response= given()
                .contentType(ContentType.JSON)
                .header("Accept","application/json")
                .when()
                .body(storePojo)
                .post()
                .then().log().body().statusCode(201);
        id=response.extract().path("id");
    }

    @Test
    public void bVerifyStoreReadSuccessfully() {

        int sId=given()
                .pathParams("id",id)
                .when()
                .get("/{id}")
                .then().statusCode(200)
                .extract()
                .path("id");
        Assert.assertEquals(sId,id);

    }


    @Test
    public void cverifyStoreUpdateSuccessfully() {
        StorePojo storePojo=new StorePojo();
        storePojo.setName("Westfield");
        storePojo.setType(type);
        storePojo.setAddress(address);
        storePojo.setAddress2(address2);
        storePojo.setCity(city);
        storePojo.setState(state);
        storePojo.setZip(zip);
        storePojo.setLat(lat);
        storePojo.setLng(lng);
        storePojo.setHours("Mon: 8-5; Tue: 8-5; Wed: 8-5; Thurs: 8-5; Fri: 8-5; Sat: 8-5; Sun: 8-5");
        Response response= given()
                .header("Content-Type", "application/json")
                .pathParams("id", id)
                .when()
                .body(storePojo)
                .patch("/{id}");
        response.then().log().body().statusCode(200);

    }

    @Test
    public void zVerifyStoreDeleteSuccessfully() {

        given()
                .pathParam("id", id)
                .when()
                .delete("/{id}")
                .then()
                .statusCode(200);

    }


}
