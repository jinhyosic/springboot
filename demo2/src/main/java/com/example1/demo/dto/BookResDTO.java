package com.example1.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BookResDTO {
    private long id;
    private String title;
    private String author;
    private String isbn;
    private String genre;
    private LocalDateTime created_at;
}
