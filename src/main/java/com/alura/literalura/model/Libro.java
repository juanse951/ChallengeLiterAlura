package com.alura.literalura.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="libros")
public class Libro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private String titulo;

    private String autor;

    @Enumerated(EnumType.STRING)
    private TipoIdioma idioma;

    private Double numeroDeDescargas;

    public Libro(){}

    public Libro(DatosLibro datosLibro) {
        this.titulo = datosLibro.titulo();
        this.autor = datosLibro.autor();
        this.idioma = TipoIdioma.fromString(datosLibro.idioma());
        this.numeroDeDescargas = datosLibro.numeroDeDescargas();

    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public TipoIdioma getIdioma() {
        return idioma;
    }

    public void setIdioma(TipoIdioma idioma) {
        this.idioma = idioma;
    }

    public Double getNumeroDeDescargas() {
        return numeroDeDescargas;
    }

    public void setNumeroDeDescargas(Double numeroDeDescargas) {
        this.numeroDeDescargas = numeroDeDescargas;
    }

    @Override
    public String toString() {
        return "--------Libro--------" + '\''+
                "Titulo='" + titulo + '\'' +
                "Autor=" + autor + '\'' +
                "Idioma=" + idioma + '\''+
                "Numero de descargas=" + numeroDeDescargas + '\''+
                "---------------------";
    }
}
