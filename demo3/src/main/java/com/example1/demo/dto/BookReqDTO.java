package com.example1.demo.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class BookReqDTO {
    private long id;
    @NotNull(message = "제목은 필수 입력 항목입니다")
    private String title;
    @NotNull(message = "저자는 필수 입력 항목입니다")
    private String author;
    @NotNull(message = "번호는 필수 입력 항목입니다")
    private String isbn;
    @NotNull(message = "장르는 필수 입력 항목입니다")
    private String genre;
    private LocalDateTime created_at;
}
