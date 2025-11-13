package com.bookstore.books;

import com.bookstore.api.BookClient;
import com.bookstore.base.BaseTest;
import io.qameta.allure.Description;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@Test(groups = {"smoke", "regression"})
public class GetBooksTests extends BaseTest {

    private final BookClient books = new BookClient();

    @Test(description = "List all books and validate basic schema and non-empty response")
    @Description("List all books and validate basic schema and non-empty response")
    public void listBooks_shouldReturn200_withSchema() {
        var response = books.list();
        logAndAttach(response, "GET", "/Books");

        response.then()
                .statusCode(200)
                .body(matchesJsonSchemaInClasspath("schemas/books-array.json"));

        List<String> titles = response.jsonPath().getList("title");
        assertThat("Books list should not be empty", titles, is(not(empty())));
    }

    @Test(description = "Get existing book by known ID")
    @Description("Get existing book by known ID and validate JSON schema")
    public void getBookById_shouldReturn200_withSchema() {
        int id = 1;
        var response = books.getById(id);
        logAndAttach(response, "GET", "/Books/" + id);

        response.then()
                .statusCode(200)
                .body(matchesJsonSchemaInClasspath("schemas/book.json"))
                .body("id", equalTo(id));
    }
}