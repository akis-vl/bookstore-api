package com.bookstore.api;

import static io.restassured.RestAssured.given;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import com.bookstore.utils.RequestSpecFactory;

public abstract class BaseClient {

    protected RequestSpecification spec() {
        return RequestSpecFactory.get();
    }
    protected Response get(String path) {
        return given().spec(spec()).when().get(path);
    }
    protected Response post(String path, Object body) {
        return given().spec(spec()).body(body).when().post(path);
    }
    protected Response put(String path, Object body) {
        return given().spec(spec()).body(body).when().put(path);
    }
    protected Response delete(String path) {
        return given().spec(spec()).when().delete(path);
    }
}