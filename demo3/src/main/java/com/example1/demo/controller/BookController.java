package com.example1.demo.controller;

import com.example1.demo.dto.BookReqDTO;
import com.example1.demo.dto.BookResDTO;
import com.example1.demo.repository.BookRepository;
import com.example1.demo.service.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import org.springframework.ui.Model;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/bookWithThymeleaf")
public class BookController {
    private final BookService bookService;
    //객체저장
//    @PostMapping
//    public BookResDTO saveBook(@RequestBody BookReqDTO bookReqDTO){
//        return bookService.saveBook(bookReqDTO);
//    }

    @GetMapping(value="/bookList")
    public String getBookList(Model model){
        model.addAttribute("bookList",bookService.getBooks());
        return "book-list";
    }
//추가 페이지로 이동
    @GetMapping(value = "/add")
    public String gotoBookAddPage(BookReqDTO bookReqDTO) {

        return "book-add";
    }
    //추가 행위
    @PostMapping(value = "/addSave")
    public String addBook(@Valid BookReqDTO bookReqDTO, BindingResult result, Model model){
        if(result.hasErrors()){
            return "book-add";
        }
        System.out.println(bookReqDTO);
            bookService.saveBook(bookReqDTO);
           // model.addAttribute("books",bookService.getBooks());
            return "redirect:/";
    }

    //삭제 실행
    @GetMapping(value = "/delete/{id}")
    public String deleteBook(@PathVariable long id){
        bookService.deleteBook(id);
        return "redirect:/";
    }

    //수정 창으로 이동
    @GetMapping(value = "/update/{id}")
    public String gotoUpdateBook(@PathVariable long id, Model model){
        model.addAttribute("book",bookService.getBookById(id));
        return "book-update";
    }
    //수정 실행
    @PostMapping("/updatek/{id}")
    public String updateBook(@PathVariable("id") long id, @Valid BookReqDTO bookReqDTO, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("book", bookReqDTO);
            return "book-update";
        }
        bookService.updateBook(id,bookReqDTO);
        return "redirect:/";
    }

}

