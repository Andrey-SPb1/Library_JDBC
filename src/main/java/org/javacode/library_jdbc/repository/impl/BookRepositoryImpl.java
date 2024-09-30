package org.javacode.library_jdbc.repository.impl;

import lombok.RequiredArgsConstructor;
import org.javacode.library_jdbc.model.entity.Book;
import org.javacode.library_jdbc.repository.BookRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class BookRepositoryImpl implements BookRepository {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public Book save(Book book) {
        String sql;
        if (findById(book.getId()).isEmpty()) {
            sql = "INSERT INTO books (title, author, publication_year) VALUES (?, ?, ?)";
            jdbcTemplate.update(sql, book.getTitle(), book.getAuthor(), book.getPublicationYear());
        } else {
            sql = "UPDATE books SET title = ?, author = ?, publication_year = ? WHERE id = ?";
            jdbcTemplate.update(sql, book.getTitle(), book.getAuthor(), book.getPublicationYear(), book.getId());
        }
        return book;
    }

    @Override
    public Optional<Book> findById(Long id) {
        String sql = "SELECT * FROM books WHERE id = ?";
        return jdbcTemplate.query(sql, resultSetExtractor, id).stream().findFirst();
    }

    @Override
    public List<Book> findAll(Pageable pageable) {
        String sql = "SELECT * FROM books LIMIT ? OFFSET ?";
        int limit = pageable.getPageSize();
        int offset = pageable.getPageNumber() * limit;
        return jdbcTemplate.query(sql, resultSetExtractor, limit, offset);
    }

    @Override
    public void deleteById(Long id) {
        String sql = "DELETE FROM books WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    private final RowMapper<Book> resultSetExtractor = (resultSet, rowNum) -> new Book(
            resultSet.getLong("id"),
            resultSet.getString("title"),
            resultSet.getString("author"),
            resultSet.getInt("publication_year")
    );
}

