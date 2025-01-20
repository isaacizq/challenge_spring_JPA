package com.alura_cursos.literatura.repository;

import com.alura_cursos.literatura.models.Idioma;
import com.alura_cursos.literatura.models.autor;
import com.alura_cursos.literatura.models.book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface bookRepository extends JpaRepository<book,Long> {

    @Query("SELECT s from autor s")
    List<autor> get_authors();

    List<book> findByIdiomas(Idioma lg);

    @Query("SELECT a from autor a WHERE a.anoNacimiento<=:ano AND a.anofallecimiento>=:ano")
    List<autor> get_authors_deathday(int ano);

}
