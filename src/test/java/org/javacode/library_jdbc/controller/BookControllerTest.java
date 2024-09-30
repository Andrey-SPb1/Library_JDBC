package org.javacode.library_jdbc.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.javacode.library_jdbc.model.dto.create.BookCreateEditDto;
import org.javacode.library_jdbc.repository.BookRepository;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class BookControllerTest {

    private final MockMvc mockMvc;
    private final ObjectMapper objectMapper;
    private final BookRepository bookRepository;

    public BookControllerTest(MockMvc mockMvc, ObjectMapper objectMapper, BookRepository bookRepository) {
        this.mockMvc = mockMvc;
        this.objectMapper = objectMapper;
        this.bookRepository = bookRepository;
    }

    @Test
    @Order(1)
    void getById() throws Exception {
        mockMvc.perform(get("/api/v1/book/1"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("title").value("Test Book 1"))
                .andExpect(jsonPath("author").value("Author 1"))
                .andExpect(jsonPath("publicationYear").value(2021));
    }

    @Test
    @Order(2)
    void getAll() throws Exception {
        mockMvc.perform(get("/api/v1/book/all?page=1&size=2"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("size()").value(2))
                .andExpect(jsonPath("[0].title").value("Test Book 3"))
                .andExpect(jsonPath("[0].author").value("Author 2"))
                .andExpect(jsonPath("[1].title").value("Test Book 4"));
    }

    @Test
    void create() throws Exception {
        BookCreateEditDto book =
                new BookCreateEditDto("Test Book 7", "Author 3", 2021);

        mockMvc.perform(post("/api/v1/book")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(book)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("title").value("Test Book 7"));
    }

    @Test
    void updateBook() throws Exception {
        BookCreateEditDto book =
                new BookCreateEditDto("Test Book 1", "Author 1", 1999);

        mockMvc.perform(put("/api/v1/book/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(book)))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("title").value("Test Book 1"))
                .andExpect(jsonPath("author").value("Author 1"))
                .andExpect(jsonPath("publicationYear").value(1999));
    }

    @Test
    void deleteBook() throws Exception {
        mockMvc.perform(delete("/api/v1/book/1"))
                .andExpect(status().isNoContent());
    }
}