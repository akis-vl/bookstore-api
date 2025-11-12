package com.bookstore.models;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Author {
    private Integer id;
    private String idBook; // string/int variations
    private String firstName;
    private String lastName;
}