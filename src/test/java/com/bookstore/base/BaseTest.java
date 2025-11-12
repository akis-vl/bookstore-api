package com.bookstore.base;

import io.qameta.allure.Step;
import org.testng.annotations.BeforeSuite;

import static io.restassured.RestAssured.enableLoggingOfRequestAndResponseIfValidationFails;

public abstract class BaseTest {
    @BeforeSuite
    @Step("Enable request/response logging on validation failures")
    public void setupSuite() {
        enableLoggingOfRequestAndResponseIfValidationFails();
    }
}