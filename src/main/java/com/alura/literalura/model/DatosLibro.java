package com.alura.literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DatosLibro {

    @JsonAlias("title") String titulo;

    @JsonAlias("authors")  List<Autor> autor;

    @JsonAlias("languages") List<String> idioma;

    @JsonAlias("download_count") Double numeroDeDescargas;
}
