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
public class StoresExtractionTest {

    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 3030;
        response = given()
                .when()
                .get("/stores")
                .then().statusCode(200);
    }


//1. Extract the limit
//2. Extract the total
//3. Extract the name of 5th store
//4. Extract the names of all the store
//5. Extract the storeId of all the store
//6. Print the size of the data list
//7. Get all the value of the store where store name = St Cloud
//8. Get the address of the store where store name = Rochester
//9. Get all the services of 8th store
//10. Get storeservices of the store where service name = Windows Store
//11. Get all the storeId of all the store
//12. Get id of all the store
//13. Find the store names Where state = ND
//14. Find the Total number of services for the store where store name = Rochester
//15. Find the createdAt for all services whose name = “Windows Store”
//16. Find the name of all services Where store name = “Fargo”
//17. Find the zip of all the store
//18. Find the zip of store name = Roseville
//19. Find the storeservices details of the service name = Magnolia Home Theater
//20. Find the lat of all the stores




    // 1) Extract the value of limit
    @Test
    public void test001() {

        int limit = response.extract().path("limit");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of limit is : " + limit);
        System.out.println("------------------End of Test---------------------------");

    }

    // 2) Extract the list of IDs of all products
    @Test
    public void test002() {

        List<Integer> listOfIds = response.extract().path("data.id");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("List of Ids are : " + listOfIds);
        System.out.println("------------------End of Test---------------------------");

    }

    // 3) Extract first product name from data by providing list index value
    @Test
    public void test003() {
        String productName = response.extract().path("data[0].name");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The first product name is : " + productName);
        System.out.println("------------------End of Test---------------------------");
    }

    // 4) Get the categories list of the first data
    @Test
    public void test004() {
        // Homework

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The Categories list are : ");
        System.out.println("------------------End of Test---------------------------");

    }

    // 5)Print the size of data
    @Test
    public void test005() {
        //Homework
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The size of the data is : ");
        System.out.println("------------------End of Test---------------------------");
    }

    // 6) Get All the products Name from data
    @Test
    public void test006() {
        //Homework  (data.name)

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The names of the products are : ");
        System.out.println("------------------End of Test---------------------------");
    }

    // 7) Get all the values for Name == Duracell - AA Batteries (8-Pack)
    @Test
    public void test007() {
        List<HashMap<String, ?>> values = response.extract().path("data.findAll{it.name == 'Duracell - AA Batteries (8-Pack)'}");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The values for product name 'Duracell - AA Batteries (8-Pack)' are: " + values);
        System.out.println("------------------End of Test---------------------------");
    }

    // 8) Get the price for product Name == Duracell - D Batteries (4-Pack)
    @Test
    public void test008() {
        List<Double> price = response.extract().path("data.findAll{it.name == 'Duracell - D Batteries (4-Pack)'}.price");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The price of product name 'Duracell - D Batteries (4-Pack)' is : " + price);
        System.out.println("------------------End of Test---------------------------");
    }

    // 9) Get the Names of products which have price less than 16.99
    @Test
    public void test009() {
        List<String> productsName = response.extract().path("data.findAll{it.price < 16.99}.name");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The names of products that price is less than 16.99 are: " + productsName);
        System.out.println("------------------End of Test---------------------------");
    }

    // 10) Get the manufacturer of products whose name Start = Ene
    @Test
    public void test010() {
        List<?> menuList = response.extract().path("data.findAll{it.name.startsWith('Ene')}.manufacturer");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The manufacturer of products whose name Start = Ene are: " + menuList);
        System.out.println("------------------End of Test---------------------------");
    }

    // 11) Get the price of products whose name end with = Vehicles
    @Test
    public void test011() {
        List<?> priceList = response.extract().path("data.findAll{it.name.endsWith('Black')}.price");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The prices of products whose name end with = Vehicles are: " + priceList);
        System.out.println("------------------End of Test---------------------------");
    }

    // 12) Get the id of product whose name is 'Energizer - N Cell E90 Batteries (2-Pack)'
    @Test
    public void test012() {
        List<Integer> productListMap = response.extract().path("data.findAll{it.name == 'Energizer - N Cell E90 Batteries (2-Pack)'}.id");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The id of product whose name 'Energizer - N Cell E90 Batteries (2-Pack)' is : " +productListMap.get(0));
        System.out.println("------------------End of Test---------------------------");
    }

}
