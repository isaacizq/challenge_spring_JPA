package com.alura_cursos.literatura.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "authors")
public class autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, name = "nombre")
    private String titulo;
    @ManyToOne
    private book book;
    private Integer anoNacimiento;
    private Integer anofallecimiento;

    public autor() {

    }

    public autor(List<dataAuthor> dataAuthorList) {
        this.titulo = dataAuthorList.get(0).nombre();
        this.anoNacimiento = dataAuthorList.get(0).anoNacimiento();
        this.anofallecimiento = dataAuthorList.get(0).anofallecimiento();
    }
//geters y setpters y el tostring
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

    public Integer getAnoNacimiento() {
        return anoNacimiento;
    }

    public void setAnoNacimiento(Integer anoNacimiento) {
        this.anoNacimiento = anoNacimiento;
    }

    public Integer getAnofallecimiento() {
        return anofallecimiento;
    }

    public book getBooks() {
        return book;
    }

    public void setBooks(book books) {
        this.book = books;
    }

    public void setAnofallecimiento(Integer anofallecimiento) {
        this.anofallecimiento = anofallecimiento;
    }

    @Override
    public String toString() {
        return  "AUTOR: " +
                "nombre= " + titulo +"\n"+
                "Año de Nacimiento= " + anoNacimiento +"\n"+
                "Año de fallecimiento= " + anofallecimiento+"\n"+"************************";
    }
}
