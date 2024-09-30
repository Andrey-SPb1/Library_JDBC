package org.javacode.library_jdbc.mapper.create;

import org.javacode.library_jdbc.mapper.Mapper;
import org.javacode.library_jdbc.model.dto.create.BookCreateEditDto;
import org.javacode.library_jdbc.model.entity.Book;
import org.springframework.stereotype.Component;

@Component
public class BookCreateEditMapper implements Mapper<BookCreateEditDto, Book> {

    @Override
    public Book map(BookCreateEditDto bookDto) {
        return Book.builder()
                .title(bookDto.title())
                .author(bookDto.author())
                .publicationYear(bookDto.publicationYear())
                .build();
    }

    @Override
    public Book map(BookCreateEditDto bookDto, Book book) {
        book.setTitle(bookDto.title());
        book.setAuthor(bookDto.author());
        book.setPublicationYear(bookDto.publicationYear());
        return book;
    }
}
