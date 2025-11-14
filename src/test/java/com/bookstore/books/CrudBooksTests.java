package com.bookstore.books;

import com.bookstore.api.BookClient;
import com.bookstore.base.BaseTest;
import com.bookstore.data.TestData;
import com.bookstore.models.Book;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.equalTo;

@Test(groups = {"smoke", "regression"})
public class CrudBooksTests extends BaseTest {

    private BookClient book;
    private int bookId;

    @BeforeClass
    public void init() {
        book = new BookClient();
    }

    @Test(priority = 1, description = "Create new book")
    @Description("Create a new book and accept 200 or 201 status code")
    public void createBook_shouldSucceed() {

        int id = (int) (System.currentTimeMillis() % 1_000_000);
        Book payload = TestData.randomBookWithId(id);

        Allure.step("Creating Book payload with ID: " + id + " and title: " + payload.getTitle());

        var response = book.create(payload);
        logAndAttach(response, "POST", "/Books");

        response.then().statusCode(200)
                .body("id", equalTo(id))
                .body("title", equalTo(payload.getTitle()));
    }

    @Test(priority = 2, description = "Retrieve created book", dependsOnMethods = "createBook_shouldSucceed")
    @Description("Retrieve created book")
    public void getCreatedBook_shouldReturn200() {

        var response = book.getById(bookId);
        logAndAttach(response, "GET", "/Books/" + bookId);

        response.then().statusCode(200);
    }

    @Test(priority = 3, description = "Update created book", dependsOnMethods = "createBook_shouldSucceed")
    @Description("Update created book")
    public void updateBook_shouldSucceed() {

        Book updated = TestData.randomBookWithId(bookId);
        updated.setTitle(updated.getTitle() + " (Updated)");

        Allure.step("Preparing updated Book payload for ID: " + bookId +
                " with title: " + updated.getTitle());

        var putResponse = book.update(bookId, updated);
        logAndAttach(putResponse, "PUT", "/Books/" + bookId);
        putResponse.then().statusCode(200);

        var getResponse = book.getById(bookId);
        logAndAttach(getResponse, "GET", "/Books/" + bookId + " (after update)");
        getResponse.then().statusCode(200);
    }

    @Test(priority = 4, description = "Delete created book", dependsOnMethods = "createBook_shouldSucceed")
    @Description("Delete created book")
    public void deleteBook_shouldSucceed() {

        var deleteResponse = book.delete(bookId);
        logAndAttach(deleteResponse, "DELETE", "/Books/" + bookId);
        deleteResponse.then().statusCode(200);

        var getResponse = book.getById(bookId);
        logAndAttach(getResponse, "GET", "/Books/" + bookId + " (after delete)");
        getResponse.then().statusCode(404);
    }
}