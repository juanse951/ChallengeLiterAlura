package com.alura.literalura.service;

import com.alura.literalura.model.Autor;
import com.alura.literalura.repository.AutorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AutorService {

    private final AutorRepository autorRepository;

    public AutorService(AutorRepository autorRepository) {
        this.autorRepository = autorRepository;
    }

    public Optional<Autor> buscarAutorPorNombre(String nombre) {
        return autorRepository.findByNombre(nombre);
    }

    public List<Autor> obtenerAutoresVivosEn(int anio) {
        return autorRepository.findAutoresVivosEnAnio(anio);
    }

    public List<Autor> obtenerTodosLosAutores() {
        return autorRepository.findAll();
    }

    public Autor guardarAutor(Autor autor) {
        return autorRepository.save(autor);
    }
}
