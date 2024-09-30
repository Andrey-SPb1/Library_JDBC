package org.javacode.library_jdbc.controller;

import lombok.RequiredArgsConstructor;
import org.javacode.library_jdbc.exception.ResourceNotFoundException;
import org.javacode.library_jdbc.model.dto.create.BookCreateEditDto;
import org.javacode.library_jdbc.model.dto.response.BookResponseDto;
import org.javacode.library_jdbc.service.BookService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/book")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping("/{id}")
    public BookResponseDto getById(@PathVariable Long id) {
        return bookService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book with id " + id + " not found"));
    }

    @GetMapping("/all")
    public List<BookResponseDto> getAll(Pageable pageable) {
        return bookService.findAll(pageable);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BookResponseDto create(@RequestBody @Validated BookCreateEditDto book) {
        return bookService.create(book);
    }

    @PutMapping("/{id}")
    public BookResponseDto updateBook(@PathVariable Long id,
                                                      @RequestBody @Validated BookCreateEditDto book) {
        return bookService.update(id, book)
                .orElseThrow(() -> new ResourceNotFoundException("Book with id " + id + " not found"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable Long id) {
        bookService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
