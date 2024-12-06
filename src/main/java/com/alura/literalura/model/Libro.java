package com.alura.literalura.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="libros")
public class Libro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titulo;

    private List<Autor> autor;

//    @ElementCollection
//    @CollectionTable(name = "idiomas", joinColumns = @JoinColumn(name = "libro_id"))
//    @Column(name = "idioma")
    private TipoIdioma idioma;

    private Double numeroDeDescargas;

    @ManyToOne
    private Autor autore;


    public Libro(){}

    public Libro(DatosLibro datosLibro) {
        this.titulo = datosLibro.titulo();
        this.idioma = datosLibro.idioma();
        this.numeroDeDescargas = datosLibro.numeroDeDescargas();
        this.autor = datosLibro.autor();
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

    public List<Autor> getAutor() {
        return autor;
    }

    public void setAutor(List<Autor> autor) {
        this.autor = autor;
    }

    public List<String> getIdioma() {
        return idioma;
    }

    public void setIdioma(List<String> idioma) {
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
