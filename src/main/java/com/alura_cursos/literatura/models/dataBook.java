package com.alura_cursos.literatura.models;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record dataBook (
        @JsonAlias("id") Long id,
        @JsonAlias("title") String titulo,
        @JsonAlias("subjects") List<String> tema,
        @JsonAlias("authors") List<dataAuthor> autores,
        @JsonAlias("languages") List<String> idiomas,
        @JsonAlias("download_count") Integer descargas) {
}
