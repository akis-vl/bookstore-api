package com.bookstore.config;

public final class TestConfig {

    private TestConfig() { }

    public static String baseUri() {
        return System.getProperty("baseUri", "https://fakerestapi.azurewebsites.net");
    }

    public static String apiVersion() {
        return System.getProperty("apiVersion", "/api/v1");
    }
}