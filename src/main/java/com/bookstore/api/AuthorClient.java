package com.bookstore.api;

import io.restassured.response.Response;

public class AuthorClient extends BaseClient {

    private static final String ROOT = "/Authors";

    public Response list() {
        return get(ROOT);
    }

    public Response getById(int id) {
        return get(ROOT + "/" + id);
    }

    public Response create(Object body) {
        return post(ROOT, body);
    }

    public Response update(int id, Object body) {
        return put(ROOT + "/" + id, body);
    }

    public Response delete(int id) {
        return delete(ROOT + "/" + id);
    }
}