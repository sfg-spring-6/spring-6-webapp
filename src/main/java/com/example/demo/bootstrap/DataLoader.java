package com.example.demo.bootstrap;

import com.example.demo.domain.Address;
import com.example.demo.domain.Author;
import com.example.demo.domain.Book;
import com.example.demo.domain.Publisher;
import com.example.demo.repository.AuthorRepository;
import com.example.demo.repository.BookRepository;
import com.example.demo.repository.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Set;

@Component
public class DataLoader implements CommandLineRunner {

    BookRepository bookRepository;
    AuthorRepository authorRepository;
    PublisherRepository publisherRepository;

    public DataLoader(BookRepository bookRepository, AuthorRepository authorRepository, PublisherRepository publisherRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        Author gcdas = Author.builder().authorName("GC Das").build();
        Author kevinmcg = Author.builder().authorName("Kevin McGrath").build();

        authorRepository.save(gcdas);
        authorRepository.save(kevinmcg);

        Address address1 = Address.builder().city("Mumbai").state("Maharashtra").zipcode("400096").build();
        Address address2 = Address.builder().city("BBSR").state("Odisha").zipcode("751006").build();

        Publisher penguin = Publisher.builder().publisherName("Penguin").publisherAddress(address1).build();
        Publisher darkhorse = Publisher.builder().publisherName("Dark Horse").publisherAddress(address2).build();

        publisherRepository.save(penguin);
        publisherRepository.save(darkhorse);

        Book beingGood = Book.builder().bookName("Difficulty of being good").isbn("32432434").publisher(penguin).setOfAuthors(Set.of(gcdas)).build();
        Book elephantParadigm = Book.builder().bookName("Elephant Paradigm").isbn("45959392").publisher(darkhorse).setOfAuthors(Set.of(gcdas, kevinmcg)).build();
        Book bheesma = Book.builder().bookName("Bheesma").isbn("35432182").publisher(darkhorse).setOfAuthors(Set.of(kevinmcg)).build();

        bookRepository.save(beingGood);
        bookRepository.save(elephantParadigm);
        bookRepository.save(bheesma);

        gcdas.setSetOfBooks(Set.of(beingGood, elephantParadigm));
        kevinmcg.setSetOfBooks(Set.of(bheesma));

        authorRepository.save(gcdas);
        authorRepository.save(kevinmcg);

        System.out.println("Total Authors : " + authorRepository.count());
        System.out.println("Total Publishers : " + publisherRepository.count());
        System.out.println("Total Books : " + bookRepository.count());
    }
}
