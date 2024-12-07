package com.alura.literalura.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "autores")
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(nullable = false, unique = true)
    private String nombre;

    private String fechaDeNacimiento;

    private String fechaDeFallecimiento;

    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Libro> libros = new ArrayList<>();

    public Autor() {
    }

    public Autor(DatosAutor datosAutor) {
        this.nombre = datosAutor.nombre();
        this.fechaDeNacimiento = datosAutor.fechaDeNacimiento();
        this.fechaDeFallecimiento = datosAutor.fechaDeFallecimiento();
        this.libros = new ArrayList<>();
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
        if(libros == null){
            libros = new ArrayList<>();
        }
        return libros;
    }

    public void setLibros(List<Libro> libros) {
        this.libros = libros;
    }

    @Override
    public String toString() {
        return "Autor: " + nombre + "\n" +
                "Fecha de nacimiento: " + fechaDeNacimiento + "\n" +
                "Fecha de fallecimiento: " + fechaDeFallecimiento + "\n" +
                "Libros: " + (libros.isEmpty() ? "Ninguno" :
                libros.stream()
                        .map(Libro::getTitulo)
                        .collect(Collectors.joining(", "))) + "\n";
    }
}





    //este toString lo habia creado por que me estaba generando recursion
//    public String toString() {
//        // Aquí imprimes solo los títulos de los libros, evitando la recursión
//        StringBuilder sb = new StringBuilder();
//        sb.append("\nAutor: ").append(nombre)
//                .append("\nFecha de nacimiento: ").append(fechaDeNacimiento)
//                .append("\nFecha de fallecimiento: ").append(fechaDeFallecimiento)
//                .append("\nLibros: ");
//
//        if (libros != null && !libros.isEmpty()) {
//            for (Libro libro : libros) {
//                sb.append(" ").append(libro.getTitulo());
//            }
//        } else {
//            sb.append("No tiene libros registrados.");
//        }
//
//        return sb.toString();
//    }
//}

