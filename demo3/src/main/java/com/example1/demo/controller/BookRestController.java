package com.example1.demo.controller;

import com.example1.demo.dto.BookReqDTO;
import com.example1.demo.dto.BookResDTO;
import com.example1.demo.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/books")
public class BookRestController {
    private final BookService bookService;
//생성
    @PostMapping
    public BookResDTO create(@RequestBody BookReqDTO bookReqDTO){
        return bookService.create(bookReqDTO);
    }

    //전체 조회
    @RequestMapping
    public List<BookResDTO> getBook(){
        return bookService.getBooks();
    }

    //id로 책정보 단일조회
    @RequestMapping(value = "/{id}")
    public BookResDTO getBook(@PathVariable Long id){
        return bookService.getBookById(id);
    }
    //isbn으로 단일조회한다
    @RequestMapping(value = "/isbn/{isbn}")
    public BookResDTO getBookByIsbn(@PathVariable String isbn){
        return bookService.getBookByIsbn(isbn);
    }

    //id로 책 객체 삭제하기
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable Long id){
        bookService.deleteBook(id);
        return ResponseEntity.ok("delete book!");
    }
    //수정put매핑 (id로수정하기

    @PutMapping(value = "/{id}")
    public BookResDTO updateBook(@PathVariable Long id, @RequestBody BookReqDTO bookReqDTO){
        return bookService.updateBook(id, bookReqDTO);
    }
}
