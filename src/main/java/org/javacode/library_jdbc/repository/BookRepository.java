package org.javacode.library_jdbc.repository;

import org.javacode.library_jdbc.model.entity.Book;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface BookRepository {

    Book save(Book book);
    Optional<Book> findById(Long id);
    List<Book> findAll(Pageable pageable);
    void deleteById(Long id);

}
