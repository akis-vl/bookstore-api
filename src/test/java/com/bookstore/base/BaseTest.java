package com.bookstore.base;

import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.testng.annotations.BeforeSuite;

import static io.restassured.RestAssured.enableLoggingOfRequestAndResponseIfValidationFails;

public abstract class BaseTest {

    @BeforeSuite
    @Step("Enable request/response logging on validation failures")
    public void setupSuite() {
        enableLoggingOfRequestAndResponseIfValidationFails();
    }

    protected void logAndAttach(Response response, String method, String path) {
        int status = response.getStatusCode();
        Allure.step(method + " " + path + " returned status code: " + status);
        Allure.addAttachment(method + " " + path + " Response Body", response.asPrettyString());
    }
}