package com.example.demo.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "BOOK_ID", nullable = false)
    private Long bookId;

    @Column(name = "BOOK_NAME")
    private String bookName;

    @Column(name = "ISBN")
    private String isbn;

    @ManyToMany(mappedBy = "setOfBooks")
    @ToString.Exclude
    private Set<Author> setOfAuthors;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(bookId, book.bookId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookId);
    }
}
