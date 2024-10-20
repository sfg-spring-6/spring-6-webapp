package com.example.demo.web.controller;

import com.example.demo.domain.Book;
import com.example.demo.repository.BookRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class ValidationController {

    BookRepository bookRepository;

    public ValidationController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @GetMapping("/validateBook")
    public ResponseEntity<Book> validateBook(@RequestBody @Validated Book book) {

        Book savedBook = bookRepository.findByBookName(book.getBookName()).get();

        return ResponseEntity.ok(savedBook);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<Map<String, String>>> methodArgumentNotValidException(MethodArgumentNotValidException ex) {

        List<FieldError> lst = ex.getBindingResult().getFieldErrors();

        return ResponseEntity.ok(lst.stream()
                .map(e -> {
                    Map<String, String> map = new HashMap<>();
                    map.put(e.getField(), e.getDefaultMessage());
                    return map;
                }).collect(Collectors.toList()));
    }
}
