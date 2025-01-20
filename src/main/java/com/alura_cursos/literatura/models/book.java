package com.alura_cursos.literatura.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "book")
public class book {
    @Id
    private Long id;
    @Column(unique = true, name = "nombre")
    private String titulo;
    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<autor> autores;
    private String tema;
    @Enumerated(EnumType.STRING)
    private Idioma idiomas;
    private Integer descargas;

    public book() {

    }

    public book(List<dataBook> data_books) {
        this.id = data_books.get(0).id();
        this.titulo = data_books.get(0).titulo();
        this.tema = String.valueOf(data_books.get(0).tema());
        if (data_books.get(0).idiomas() != null && !data_books.get(0).idiomas().isEmpty()) {
            this.idiomas = Idioma.fromCodigoAPI(data_books.get(0).idiomas().get(0).trim());
        } else {
            // Manejar el caso en que la lista de idiomas esté vacía o sea nula
            this.idiomas = null; // O algún valor predeterminado
        }
        this.descargas = data_books.get(0).descargas();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<autor> getAutores() {
        return autores;
    }

    public void setAutores(List<autor> autores) {
        this.autores = autores;
    }

    public String getTema() {
        return tema;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }

    public Idioma getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(Idioma idiomas) {
        this.idiomas = idiomas;
    }

    public Integer getDescargas() {
        return descargas;
    }

    public void setDescargas(Integer descargas) {
        this.descargas = descargas;
    }

    @Override
    public String toString() {
        return "LIBRO: \n" +
                "Id= " + id +"\n"+
                "Nombre= " + titulo + "\n"+
                "Autores = " + autores.get(0).getTitulo() +"\n"+
                "Tema= " + tema + "\n"+
                "Idiomas= " + idiomas +"\n"+
                "Descargas= " + descargas+"\n"+"************************";
    }
}
