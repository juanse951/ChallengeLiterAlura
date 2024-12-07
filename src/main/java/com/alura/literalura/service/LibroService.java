package com.alura.literalura.service;

import com.alura.literalura.model.*;
import com.alura.literalura.repository.AutorRepository;
import com.alura.literalura.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;


@Service
public class LibroService {

    @Autowired
    private final LibroRepository libroRepository;
    @Autowired
    private final AutorRepository autorRepository;

    public LibroService(LibroRepository libroRepository, AutorRepository autorRepository) {
        this.libroRepository = libroRepository;
        this.autorRepository = autorRepository;
    }

    public Libro guardarLibroConAutor(String tituloLibro, Datos datos) {
        DatosLibro datosLibro = datos.resultados().get(0);
        DatosAutor datosAutor = datosLibro.autores().get(0);

        Autor autor = autorRepository.findByNombre(datosAutor.nombre())
                .orElseGet(() -> autorRepository.save(new Autor(datosAutor)));

        // Crear el libro
        Libro libro = new Libro(datosLibro);
        libro.setAutor(autor);

        // Evitar duplicados en la lista del autor
        if (autor.getLibros() == null) {
            autor.setLibros(new ArrayList<>());
        }

        if (!autor.getLibros().contains(libro)) {
            autor.getLibros().add(libro);
        }

        return libroRepository.save(libro);
    }

}
