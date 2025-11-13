package com.bookstore.authors;

import com.bookstore.api.AuthorClient;
import com.bookstore.base.BaseTest;
import io.qameta.allure.Description;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.is;

public class NegativeAuthorsTests extends BaseTest {

    private final AuthorClient books = new AuthorClient();

    @Test(description = "GET non-existing author should return 404")
    @Description("GET non-existing book should return 404")
    public void getNonExisting_shouldReturn404() {
        int id = 99999999;

        var response = books.getById(id);
        logAndAttach(response, "GET", "/Authors/" + id);
        response.then().statusCode(404);
    }

    @Test(description = "POST with invalid payload should yield 400")
    @Description("POST with invalid payload should yield a 400 error")
    public void createInvalid_shouldReturn400() {
        var invalid = new Object() {
            public final Integer id = null;
        };

        var response = books.create(invalid);
        logAndAttach(response, "POST", "/Authors");
        response.then().statusCode(400);
    }
}
