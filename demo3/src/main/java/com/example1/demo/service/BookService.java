package com.example1.demo.service;

import com.example1.demo.dto.BookReqDTO;
import com.example1.demo.dto.BookResDTO;
import com.example1.demo.entity.Book;
import com.example1.demo.exception.BusinessException;
import com.example1.demo.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class BookService {
    private final BookRepository bookRepository;
    private final ModelMapper modelMapper;

    //객체등록
    public BookResDTO create(BookReqDTO bookReqDTO) {
        Book book = modelMapper.map(bookReqDTO, Book.class);

        return modelMapper.map(bookRepository.save(book), BookResDTO.class);
    }

    //책 객체 전체조회
    public List<BookResDTO> getBooks() {
        return bookRepository
                .findAll()
                .stream()
                .map(bookEntity -> modelMapper.map(bookEntity, BookResDTO.class))
                .collect(Collectors.toList());
    }

    //id로 단일 책 조회
    public BookResDTO getBookById(Long id){
        Book book = bookRepository.findById(id).orElseThrow(()->new BusinessException("book Not Found", HttpStatus.NOT_FOUND));
        return modelMapper.map(book, BookResDTO.class);
    }

    //isbn으로 단일 책조회
    public BookResDTO getBookByIsbn(String isbn){
        Book book = bookRepository.findByIsbn(isbn).orElseThrow(()->new BusinessException("book Not Found", HttpStatus.NOT_FOUND));
        return modelMapper.map(book, BookResDTO.class);
    }

    //삭제처리 (id로)
    public ResponseEntity<?> deleteBook(Long id){
        Book book = bookRepository.findById(id).orElseThrow(()->new BusinessException("book Not Found", HttpStatus.NOT_FOUND));
        bookRepository.delete(book);
        return ResponseEntity.ok("delete book success!");
    }
    //id로 수정
    public BookResDTO updateBook(Long id, BookReqDTO bookReqDTO){
        Book book = bookRepository.findById(id).orElseThrow(()->new BusinessException("book Not Found", HttpStatus.NOT_FOUND));
        book.setTitle(bookReqDTO.getTitle());
        book.setAuthor(bookReqDTO.getAuthor());
        book.setGenre(bookReqDTO.getGenre());

        return modelMapper.map(book,BookResDTO.class);

    }

    public BookResDTO saveBook(BookReqDTO bookReqDTO){
        Book book = modelMapper.map(bookReqDTO, Book.class);
        Book saveBook = bookRepository.save(book);

        return modelMapper.map(saveBook, BookResDTO.class);
    }


}

