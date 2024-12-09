package com.alura.literalura.service;

import com.alura.literalura.model.Libro;
import com.alura.literalura.model.TipoIdioma;
import com.alura.literalura.repository.LibroRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LibroService {

    private final LibroRepository libroRepository;

    public LibroService(LibroRepository libroRepository) {
        this.libroRepository = libroRepository;
    }

    public List<Libro> obtenerLibrosPorIdioma(TipoIdioma idioma) {
        return libroRepository.findByIdioma(idioma);
    }

    public List<Libro> obtenerTodosLosLibros() {
        return libroRepository.findAll();
    }

    public Libro guardarLibro(Libro libro) {
        return libroRepository.save(libro);
    }

    public List<Object[]> obtenerEstadisticasPorIdioma() {
        return libroRepository.obtenerEstadisticasPorIdioma();
    }

    public Optional<Libro> buscarLibroPorTitulo(String titulo) {
        return libroRepository.findByTituloIgnoreCase(titulo);
    }

    public List<Libro> buscarTop10librosDescargados(){
        return libroRepository.findTop10ByOrderByNumeroDeDescargasDesc();
    }
}
