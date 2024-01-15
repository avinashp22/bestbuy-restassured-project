package com.bestbuy.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;

/*
 *  Created by Jay
 */
public class ProductsExtractionTest {

    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 3030;
        response = given()
                .when()
                .get("/products")
                .then().statusCode(200);
    }

//21. Extract the limit
//22. Extract the total
//23. Extract the name of 5th product
//24. Extract the names of all the products
//25. Extract the productId of all the products
//26. Print the size of the data list
//27. Get all the value of the product where product name = Energizer - MAX Batteries AA (4-Pack)
//28. Get the model of the product where product name = Energizer - N Cell E90 Batteries (2-Pack)
//29. Get all the categories of 8th products
//30. Get categories of the store where product id = 150115
//31. Get all the descriptions of all the products
//32. Get id of all the all categories of all the products
//33. Find the product names Where type = HardGood
//34. Find the Total number of categories for the product where product name = Duracell - AA1.5V CopperTop Batteries (4-Pack)
//35. Find the createdAt for all products whose price < 5.49
//36. Find the name of all categories Where product name = “Energizer - MAX Batteries AA (4-Pack)”
//37. Find the manufacturer of all the products
//38. Find the imge of products whose manufacturer is = Energizer
//39. Find the createdAt for all categories products whose price > 5.99
//40. Find the uri of all the products




    //21. Extract the limit
    @Test
    public void test001() {
        int limit = response.extract().path("limit");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The limit is : " + limit);
        System.out.println("------------------End of Test---------------------------");

    }

    //22. Extract the total
    @Test
    public void test002() {
        int total = response.extract().path("total");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The total is : " + total);
        System.out.println("------------------End of Test---------------------------");

    }

    //23. Extract the name of 5th product
    @Test
    public void test003() {
        String name = response.extract().path("data[4].name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The name of 5th product is : " + name);
        System.out.println("------------------End of Test---------------------------");

    }

    //24. Extract the names of all the products
    @Test
    public void test004() {
        List<String> productNames = response.extract().path("data.name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Extract the names of all the products : " + productNames);
        System.out.println("------------------End of Test---------------------------");
    }

    //25. Extract the productId of all the products
    @Test
    public void test005() {
        List<String> productId = response.extract().path("data.id");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Extract the productId of all the products: " + productId);
        System.out.println("------------------End of Test---------------------------");

    }

    //26. Print the size of the data list
    @Test
    public void test006() {
        List<Integer> sizeD = response.extract().path("data");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The size of the data list is : " + sizeD.size());
        System.out.println("------------------End of Test---------------------------");
    }

    //27. Get all the value of the product where product name = Energizer - MAX Batteries AA (4-Pack)
    @Test
    public void test007() {
        List<HashMap<String, ?>> values = response.extract().path("data.findAll{it.name=='Energizer - MAX Batteries AA (4-Pack)'}");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Get all the value of the product where product name = Energizer - MAX Batteries AA (4-Pack) : " + values);
        System.out.println("------------------End of Test---------------------------");
    }

    //28. Get the model of the product where product name = Energizer - N Cell E90 Batteries (2-Pack)
    @Test
    public void test008() {
        String productModel = response.extract().path("data[8].model");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println(" Get the model of the product where product name = Energizer - N Cell E90 Batteries (2-Pack) : " + productModel);
        System.out.println("------------------End of Test---------------------------");
    }


    //29. Get all the categories of 8th products
    @Test
    public void test009() {
        List<HashMap<String, ?>> categories = response.extract().path("data[7].categories");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Get all the categories of 8th products: " + categories);
        System.out.println("------------------End of Test---------------------------");
    }

    //30. Get categories of the store where product id = 150115
    @Test
    public void test010() {
        List<HashMap<String, ?>> storeCategories = response.extract().path("data[3].categories");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Get categories of the store where product id = 150115 : " + storeCategories);
        System.out.println("------------------End of Test---------------------------");
    }

    //31. Get all the descriptions of all the products
    @Test
    public void test011() {
        List<String> productDescription = response.extract().path("data.description");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Get all the descriptions of all the products: " + productDescription);
        System.out.println("------------------End of Test---------------------------");
    }

    //32. Get id of all the all categories of all the products
    @Test
    public void test012() {
        List<Integer> categoryIds= response.extract().path("data.categories.id");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Get id of all the all categories of all the products : " + categoryIds);
        System.out.println("------------------End of Test---------------------------");
    }

    //33. Find the product names Where type = HardGood
    @Test
    public void test013() {
        List<String>productNames=response.extract().path("data.findAll{it.type=='HardGood'}.name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Find the product names Where type = HardGood: " + productNames);
        System.out.println("------------------End of Test---------------------------");
    }

    //34. Find the Total number of categories for the product where product name = Duracell - AA 1.5V CopperTop Batteries (4-Pack)
    @Test
    public void test014() {
        List<String>numberOfCategories=response.extract().path("data.findAll{it.productName='Duracell - AA 1.5V CopperTop Batteries (4-Pack)'}.categories");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Find the Total number of categories for the product where product name = Duracell - AA 1.5V CopperTop Batteries (4-Pack) : " + numberOfCategories);
        System.out.println("------------------End of Test---------------------------");
    }

    //35. Find the createdAt for all products whose price < 5.49
    @Test
    public void test015() {
        List<String>createdAt=response.extract().path("data.findAll{it.price<5.49}.createdAt");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Find the createdAt for all products whose price < 5.49 : " + createdAt );
        System.out.println("------------------End of Test---------------------------");
    }

    //36. Find the name of all categories Where product name = “Energizer - MAX Batteries AA (4-Pack)”
    @Test
    public void test016() {
        List<String>categories=response.extract().path("data.findAll{it.name=='Energizer - MAX Batteries AA (4-Pack)'}.categories");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Find the name of all categories Where product name = “Energizer - MAX Batteries AA (4-Pack): " +categories );
        System.out.println("------------------End of Test---------------------------");
    }

    //37. Find the manufacturer of all the products
    @Test
    public void test017() {
        List<String>manufacturer=response.extract().path("data.manufacturer");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Find the manufacturer of all the products : " + manufacturer);
        System.out.println("------------------End of Test---------------------------");
    }

    //38. Find the image of products whose manufacturer is = Energizer
    @Test
    public void test018() {
        List<String>productImage=response.extract().path("data.findAll{it.manufacturer=='Energizer'}.image");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Find the image of products whose manufacturer is = Energizer " +productImage);
        System.out.println("------------------End of Test---------------------------");
    }

    //39. Find the createdAt for all categories products whose price > 5.99
    @Test
    public void test019() {
        List<HashMap<String,?>> createdAt = response.extract().path("data.findAll{it.price > 5.99}.createdAt");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println(" Find the createdAt for all categories products whose price > 5.99" + createdAt);
        System.out.println("------------------End of Test---------------------------");
    }

    //40. Find the uri of all the products
    @Test
    public void test020() {
        List<String> url = response.extract().path("data.url");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Find the uri of all the products " +url);
        System.out.println("------------------End of Test---------------------------");
    }

}
