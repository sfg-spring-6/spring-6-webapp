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
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "AUTHOR_ID", nullable = false)
    private Long authorId;

    @Column(name = "AUTHOR_NAME")
    private String authorName;

    @ManyToMany
    @JoinTable(name = "XREF_BOOK_AUTHOR", joinColumns = @JoinColumn(name = "AUTHOR_ID"), inverseJoinColumns = @JoinColumn(name = "BOOK_ID"))
    @ToString.Exclude
    private Set<Book> setOfBooks;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Author author = (Author) o;
        return Objects.equals(authorId, author.authorId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(authorId);
    }
}
