package com.example.demo.repository;

import com.example.demo.domain.Book;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class BookRepositoryTest {

    @Autowired
    BookRepository repository;

    @Test
    void validate() {

        Book book = Book.builder().isbn("234324").bookName(null).build();
        Book savedBook = repository.save(book);

        repository.flush();

        Assertions.assertThat(savedBook).isNotNull();
        Assertions.assertThat(savedBook.getBookId()).isNotNull();
    }
}