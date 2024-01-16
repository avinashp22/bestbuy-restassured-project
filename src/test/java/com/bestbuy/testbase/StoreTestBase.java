package com.bestbuy.testbase;

import com.bestbuy.utils.PropertyReader;
import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;

public class StoreTestBase {

    @BeforeClass
    public void inIt() {
       // RestAssured.baseURI = "http://localhost";
      //  RestAssured.basePath = "/stores";

        RestAssured.baseURI = PropertyReader.getInstance().getProperty("baseUrl");
        RestAssured.port = 3030;
        RestAssured.basePath = PropertyReader.getInstance().getProperty("storesPath");

    }
}
