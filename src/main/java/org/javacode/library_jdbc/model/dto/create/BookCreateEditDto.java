package org.javacode.library_jdbc.model.dto.create;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record BookCreateEditDto(
        @NotBlank
        String title,
        @NotBlank
        String author,
        @NotNull
        Integer publicationYear
) {
}
