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

    public static TipoIdioma fromString(String text) {
        for (TipoIdioma tipoIdioma : TipoIdioma.values()) {
            if (tipoIdioma.tipoIdiomaOmdb.equalsIgnoreCase(text)) {
                return tipoIdioma;
            }
        }
        throw new IllegalArgumentException("Ninguna idioma encontrada: " + text);
    }
}
