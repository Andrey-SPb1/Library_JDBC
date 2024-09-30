package org.javacode.library_jdbc.mapper.response;

import org.javacode.library_jdbc.mapper.Mapper;
import org.javacode.library_jdbc.model.dto.response.BookResponseDto;
import org.javacode.library_jdbc.model.entity.Book;
import org.springframework.stereotype.Component;

@Component
public class BookResponseMapper implements Mapper<Book, BookResponseDto> {

    @Override
    public BookResponseDto map(Book book) {
        return new BookResponseDto(
                book.getTitle(),
                book.getAuthor(),
                book.getPublicationYear()
        );
    }
}
