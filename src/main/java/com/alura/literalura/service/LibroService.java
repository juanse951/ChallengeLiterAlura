package com.alura.literalura.service;

import com.alura.literalura.model.*;
import com.alura.literalura.repository.AutorRepository;
import com.alura.literalura.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;


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

        // Buscar al autor en la base de datos
        Autor autor = autorRepository.findByNombre(datosAutor.nombre())
                .map(autorRepository::save)
                .orElseGet(() ->{
                    // Si el autor ya existe, lo gestionamos con merge
                    Autor nuevoAutor = new Autor(datosAutor);
                    return autorRepository.save(nuevoAutor); // Persistir el autor y asegurarnos de que esté gestionado
                });

        // Buscar el libro por titulo
        Optional<Libro> libroExistente = libroRepository.findByTitulo(datosLibro.titulo());

        // Si el libro existe, lo actualizamos, si no, lo creamos
        Libro libro = libroExistente.orElseGet(() -> new Libro(datosLibro));

        // Asociar el autor al libro
        libro.setAutor(autor);

        // Evitar duplicados en la lista del autor
        if (autor.getLibros() == null) {
            autor.setLibros(new ArrayList<>());
        }

        if (!autor.getLibros().contains(libro)) {
            autor.getLibros().add(libro);
        }

        // Guardar el libro en la base de datos
        return libroRepository.save(libro);
    }

}
