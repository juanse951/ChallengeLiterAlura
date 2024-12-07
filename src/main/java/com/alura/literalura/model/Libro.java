package com.alura.literalura.model;

import jakarta.persistence.*;

@Entity
@Table(name="libros")
public class Libro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private String titulo;

    @Enumerated(EnumType.STRING)
    private TipoIdioma idioma;

    private Double numeroDeDescargas;

    @ManyToOne(cascade = CascadeType.PERSIST,fetch = FetchType.LAZY)
    @JoinColumn(name = "autor_id")
    private Autor autor;

    public Libro(){}

    public Libro(DatosLibro datosLibro) {
        this.titulo = datosLibro.titulo();
       // this.autor = new Autor(datosLibro.autores().get(0));
        this.idioma = TipoIdioma.fromString(datosLibro.idioma().get(0));
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

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
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
        return "--------Libro--------\n" +
                "Titulo: " + titulo + "\n" +
                "Autor: " + (autor != null ? autor.getNombre(): "Sin autor") + "\n" +
                "Idioma: " + idioma + "\n" +
                "Numero de descargas: " + numeroDeDescargas + "\n"+
                "---------------------";
    }
}
