package com.alura.literalura.repository;

import com.alura.literalura.model.Libro;
import com.alura.literalura.model.TipoIdioma;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LibroRepository extends JpaRepository<Libro, Long> {

    List<Libro> findByIdioma(TipoIdioma idioma);

    @Query("SELECT l.idioma, COUNT(l) FROM Libro l GROUP BY l.idioma ORDER BY COUNT(l) DESC")
    List<Object[]> obtenerEstadisticasPorIdioma();


}

