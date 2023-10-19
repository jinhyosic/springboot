package com.example1.demo.controller;

import com.example1.demo.entity.Book;
import com.example1.demo.exception.BusinessException;
import com.example1.demo.repository.BookRepository;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/books")
public class BookBasicRestController {
    @Autowired
    private BookRepository bookRepository;

    @PostMapping
    public Book create(@RequestBody Book book){
        return bookRepository.save(book);
    }

    @RequestMapping(produces = {"application/json"})
    public List<Book> getBooks(){
        return bookRepository.findAll();
    }

    @RequestMapping(value = "/{id}")
    public Book getBook(@PathVariable Long id){
        return bookRepository.findById(id).orElseThrow(()->new BusinessException("Book Not Found", HttpStatus.NOT_FOUND));
    }
    @RequestMapping(value = "/isbn/{isbn}",produces = {"application/json"})
    public Book getBookByIsbn(@PathVariable String isbn){
        //문제라인
        return bookRepository.findByIsbn(isbn).orElseThrow(()->new BusinessException("Book Not Found",HttpStatus.NOT_FOUND));
    }
    //삭제
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable Long id){
        Book book = bookRepository.findById(id).orElseThrow(()->new BusinessException("Book Not Found",HttpStatus.NOT_FOUND));
        bookRepository.delete(book);
        return ResponseEntity.ok("delete Book success");
    }

   @PutMapping("/{id}")
        public ResponseEntity<?> updateBook(@PathVariable("id") long id, @RequestBody Book editBook) {
            Book book = bookRepository.findById(id).orElseThrow(()->new BusinessException("Book Not Found",HttpStatus.NOT_FOUND));
            book.setAuthor(editBook.getAuthor());
            book.setGenre(editBook.getGenre());
            book.setTitle(editBook.getTitle());
            bookRepository.save(book);
            return ResponseEntity.ok("alter Book success");
   }

}
