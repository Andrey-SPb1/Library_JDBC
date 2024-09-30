package org.javacode.library_jdbc.service.impl;

import lombok.RequiredArgsConstructor;
import org.javacode.library_jdbc.mapper.create.BookCreateEditMapper;
import org.javacode.library_jdbc.mapper.response.BookResponseMapper;
import org.javacode.library_jdbc.model.dto.create.BookCreateEditDto;
import org.javacode.library_jdbc.model.dto.response.BookResponseDto;
import org.javacode.library_jdbc.repository.BookRepository;
import org.javacode.library_jdbc.service.BookService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final BookResponseMapper bookResponseMapper;
    private final BookCreateEditMapper bookCreateEditMapper;

    @Override
    public Optional<BookResponseDto> findById(Long id) {
        return bookRepository.findById(id)
                .map(bookResponseMapper::map);
    }

    @Override
    public List<BookResponseDto> findAll(Pageable pageable) {
        return bookRepository.findAll(pageable).stream()
                .map(bookResponseMapper::map)
                .toList();
    }

    @Override
    public BookResponseDto create(BookCreateEditDto bookDto) {
        return Optional.of(bookDto)
                .map(bookCreateEditMapper::map)
                .map(bookRepository::save)
                .map(bookResponseMapper::map)
                .orElseThrow();
    }

    @Override
    public Optional<BookResponseDto> update(Long id, BookCreateEditDto bookDto) {
        return bookRepository.findById(id)
                .map(book -> bookCreateEditMapper.map(bookDto, book))
                .map(bookRepository::save)
                .map(bookResponseMapper::map);
    }

    @Override
    public void delete(Long id) {
        bookRepository.deleteById(id);
    }
}
