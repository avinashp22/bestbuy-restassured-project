package com.bestbuy.crudtest;


import com.bestbuy.model.ProductPojo;
import com.bestbuy.testbase.ProductsTestBase;
import com.bestbuy.utils.TestUtils;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;


public class ProductsCRUDTest extends ProductsTestBase {

    static String name="Macbook"+ TestUtils.getRandomValue();
    static String type="laptop";
    static double price=1999.99;
    static double shipping=9.99;
    static String upc=TestUtils.getRandomValue();
    static String description= "Latest Model";
    static String manufacturer="Apple";
    static String model=TestUtils.getRandomValue();
    static String url="https://pisces.bbystatic.com/"+TestUtils.getRandomValue()+".jpg;maxHeight=640;maxWidth=550";
    static String image=TestUtils.getRandomValue();
    static int id;


    @Test
    public void averifyProductCreatedSuccessfully() {
        ProductPojo productPojo=new ProductPojo();
        productPojo.setName(name);
        productPojo.setType(type);
        productPojo.setPrice(price);
        productPojo.setShipping(shipping);
        productPojo.setUpc(upc);
        productPojo.setDescription(description);
        productPojo.setManufacturer(manufacturer);
        productPojo.setModel(model);
        productPojo.setUrl(url);
        productPojo.setImage(image);
        ValidatableResponse response= given()
                .contentType(ContentType.JSON)
                .header("Accept","application/json")
                .when()
                .body(productPojo)
                .post()
                .then().log().body().statusCode(201);
        id=response.extract().path("id");

    }


    @Test
    public void bVerifyProductReadSuccessfully() {

        int pId=given()
                .pathParams("id",id)
                .when()
                .get("/{id}")
                .then().statusCode(200)
                .extract()
                .path("id");
        Assert.assertEquals(pId,id);

    }

    @Test
    public void cverifyProductUpdateSuccessfully() {
        ProductPojo productPojo=new ProductPojo();
        productPojo.setName("Iphone");
        productPojo.setType("Mobile");
        productPojo.setPrice(699.00);
        productPojo.setShipping(4.50);
        productPojo.setUpc(TestUtils.getRandomValue());
        productPojo.setDescription("Oldest Model");
        productPojo.setManufacturer("Apple");
        productPojo.setModel(TestUtils.getRandomValue());
        productPojo.setUrl("https://pisces.bbystatic.com/.jpg;maxHeight=640;maxWidth=550");
        productPojo.setImage("https://pisces.bbystatic.com");
        Response response= given()
                .header("Content-Type", "application/json")
                .pathParams("id", id)
                .when()
                .body(productPojo)
                .put("/{id}");
        response.then().log().body().statusCode(200);

    }

    @Test
    public void zVerifyProductDeleteSuccessfully() {

        given()
                .pathParam("id", id)
                .when()
                .delete("/{id}")
                .then()
                .statusCode(200);

    }

}
