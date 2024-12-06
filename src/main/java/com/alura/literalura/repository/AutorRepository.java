package com.alura.literalura.repository;

import com.alura.literalura.model.Autor;
import com.alura.literalura.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface AutorRepository extends JpaRepository<Autor, Long> {

    @Query("SELECT l FROM Libro l WHERE UPPER(l.titulo) LIKE CONCAT('%', UPPER(:titulo), '%')")
    Optional<Libro> buscarLibroPorTitulo(@Param("titulo") String titulo);
}
