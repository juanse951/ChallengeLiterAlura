package com.alura.literalura.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "autores")
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private String nombre;

    private String fechaDeNacimiento;

    private String fechaDeFallecimiento;

    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Libro> libros;

    public Autor(){}

    public Autor(DatosAutor datosAutor) {
        this.nombre = datosAutor.nombre();
        this.fechaDeNacimiento = datosAutor.fechaDeNacimiento();
        this.fechaDeFallecimiento = datosAutor.fechaDeFallecimiento();
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFechaDeFallecimiento() {
        return fechaDeFallecimiento;
    }

    public void setFechaDeFallecimiento(String fechaDeFallecimiento) {
        this.fechaDeFallecimiento = fechaDeFallecimiento;
    }

    public String getFechaDeNacimiento() {
        return fechaDeNacimiento;
    }

    public void setFechaDeNacimiento(String fechaDeNacimiento) {
        this.fechaDeNacimiento = fechaDeNacimiento;
    }

    public List<Libro> getLibros() {
        return libros;
    }

    public void setLibros(List<Libro> libros) {
        this.libros = libros;
    }

    public String toString() {
        // Aquí imprimes solo los títulos de los libros, evitando la recursión
        StringBuilder sb = new StringBuilder();
        sb.append("\nAutor: ").append(nombre)
                .append("\nFecha de nacimiento: ").append(fechaDeNacimiento)
                .append("\nFecha de fallecimiento: ").append(fechaDeFallecimiento)
                .append("\nLibros: ");

        if (libros != null && !libros.isEmpty()) {
            for (Libro libro : libros) {
                sb.append(" ").append(libro.getTitulo());
            }
        } else {
            sb.append("No tiene libros registrados.");
        }

        return sb.toString();
    }
}

