package com.alura.literalura.model;

public enum TipoIdioma {
    espanol("es"),

    ingles("en"),

    frances("fr"),

    portugues("pt");

    private String tipoIdiomaOmdb;

    TipoIdioma(String tipoIdiomaOmdb){
        this.tipoIdiomaOmdb = tipoIdiomaOmdb;
    }
}
