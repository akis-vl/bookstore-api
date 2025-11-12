package com.bookstore.data;

import com.bookstore.models.Author;
import com.bookstore.models.Book;
import com.github.javafaker.Faker;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

public final class TestData {

    private static final Faker FAKER = new Faker();

    private TestData() {
    }

    public static Book randomBookWithId(int id) {
        return Book.builder()
                .id(id)
                .title(FAKER.book().title())
                .description(FAKER.lorem().sentence())
                .pageCount(FAKER.number().numberBetween(50, 1000))
                .excerpt(FAKER.lorem().paragraph())
                .publishDate(Instant.now().plus(1, ChronoUnit.DAYS).toString())
                .build();
    }

    public static Author randomAuthorWithId(int id) {
        return Author.builder()
                .id(id)
                .idBook(String.valueOf(FAKER.number().numberBetween(1, 10)))
                .firstName(FAKER.name().firstName())
                .lastName(FAKER.name().lastName())
                .build();
    }
}
