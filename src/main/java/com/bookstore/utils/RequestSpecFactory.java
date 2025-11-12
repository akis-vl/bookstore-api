package com.bookstore.utils;

import com.bookstore.config.TestConfig;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public final class RequestSpecFactory {

    private static RequestSpecification spec;

    private RequestSpecFactory() { }

    public static RequestSpecification get() {
        if (spec == null) {
            spec = new RequestSpecBuilder()
                    .setBaseUri(TestConfig.baseUri())
                    .setBasePath(TestConfig.apiVersion())
                    .setContentType(ContentType.JSON)
                    .log(LogDetail.URI)
                    .log(LogDetail.METHOD)
                    .log(LogDetail.BODY)
                    .build();
        }
        return spec;
    }
}