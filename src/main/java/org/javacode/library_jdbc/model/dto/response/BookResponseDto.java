package org.javacode.library_jdbc.model.dto.response;

public record BookResponseDto(
        String title,
        String author,
        Integer publicationYear) {
}
