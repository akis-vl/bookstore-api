package com.bookstore.authors;

import com.bookstore.api.AuthorClient;
import com.bookstore.base.BaseTest;
import io.qameta.allure.Description;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class GetAuthorsTests extends BaseTest {

    private final AuthorClient authors = new AuthorClient();

    @Test(description = "List all authors with schema validation and non-empty response")
    @Description("List all authors and validate basic schema and non-empty response")
    public void listAuthors_shouldReturn200_withSchema() {
        var response = authors.list();
        logAndAttach(response, "GET", "/Authors");

        response.then()
                .statusCode(200)
                .body(matchesJsonSchemaInClasspath("schemas/authors-array.json"));

        List<String> titles = response.jsonPath().getList("firstName");
        assertThat("Author list should not be empty", titles, is(not(empty())));
    }

    @Test(description = "Get existing author by known ID")
    @Description("Get existing author by known ID and validate JSON schema")
    public void getAuthorById_shouldReturn200_withSchema() {
        int id = 1;
        var response = authors.getById(id);
        logAndAttach(response, "GET", "/Authors/" + id);

        response.then()
                .statusCode(200)
                .body(matchesJsonSchemaInClasspath("schemas/author.json"))
                .body("id", equalTo(id));
    }
}