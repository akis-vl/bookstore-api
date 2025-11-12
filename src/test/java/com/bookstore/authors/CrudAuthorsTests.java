package com.bookstore.authors;

import com.bookstore.api.AuthorClient;
import com.bookstore.base.BaseTest;
import com.bookstore.data.TestData;
import com.bookstore.models.Author;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.*;

public class CrudAuthorsTests extends BaseTest {

    private AuthorClient author;
    private int authorId;

    @BeforeClass
    public void init() {
        author = new AuthorClient();
    }

    @Test(priority = 1, description = "Create new author")
    @Description("Create a new author and accept 200 status code")
    public void createAuthor_shouldSucceed() {

        int id = (int) (System.currentTimeMillis() % 1_000_000);
        Author payload = TestData.randomAuthorWithId(id);

        Allure.step("Creating Author payload with ID: " + id +
                " (" + payload.getFirstName() + " " + payload.getLastName() + ")");

        var response = author.create(payload);
        logAndAttach(response, "POST", "/Authors");

        response.then().statusCode(anyOf(is(200)))
                .body("id", equalTo(id))
                .body("firstName", equalTo(payload.getFirstName()))
                .body("lastName", equalTo(payload.getLastName()));

        authorId = id;
        Assert.assertTrue(authorId > 0, "Created Author ID should be set");
        Allure.step("Author created successfully with ID: " + authorId);
    }

    @Test(priority = 2, description = "Retrieve created author", dependsOnMethods = "createAuthor_shouldSucceed")
    @Description("Retrieve created author")
    public void getCreatedAuthor_shouldReturn200or404() {

        var response = author.getById(authorId);
        logAndAttach(response, "GET", "/Authors/" + authorId);

        // FakeRestAPI returns 404 for non-persistent data, so allow both
        response.then().statusCode(anyOf(is(200), is(404)));
    }

    @Test(priority = 3, description = "Update created author", dependsOnMethods = "createAuthor_shouldSucceed")
    @Description("Update created author")
    public void updateAuthor_shouldSucceed() {

        Author updated = TestData.randomAuthorWithId(authorId);
        updated.setLastName(updated.getLastName() + " (Updated)");

        Allure.step("Preparing updated Author payload for ID: " + authorId +
                " with last name: " + updated.getLastName());

        var putResponse = author.update(authorId, updated);
        logAndAttach(putResponse, "PUT", "/Authors/" + authorId);
        putResponse.then().statusCode(anyOf(is(200)));

        var getResponse = author.getById(authorId);
        logAndAttach(getResponse, "GET", "/Authors/" + authorId + " (after update)");
        // FakeRestAPI returns 404 for non-persistent data, so allow both
        getResponse.then().statusCode(anyOf(is(200), is(404)));
    }

    @Test(priority = 4, description = "Delete created author", dependsOnMethods = "createAuthor_shouldSucceed")
    @Description("Delete created author")
    public void deleteAuthor_shouldSucceed() {

        var deleteResponse = author.delete(authorId);
        logAndAttach(deleteResponse, "DELETE", "/Authors/" + authorId);
        deleteResponse.then().statusCode(anyOf(is(200)));

        var getResponse = author.getById(authorId);
        logAndAttach(getResponse, "GET", "/Authors/" + authorId + " (after delete)");
        getResponse.then().statusCode(anyOf(is(404)));
    }
}