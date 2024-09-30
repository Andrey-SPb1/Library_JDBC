package org.javacode.library_jdbc.service;

import org.javacode.library_jdbc.model.dto.create.BookCreateEditDto;
import org.javacode.library_jdbc.model.dto.response.BookResponseDto;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface BookService {

    BookResponseDto create(BookCreateEditDto book);

    Optional<BookResponseDto> findById(Long id);

    List<BookResponseDto> findAll(Pageable pageable);

    Optional<BookResponseDto> update(Long id, BookCreateEditDto book);

    void delete(Long id);

}
