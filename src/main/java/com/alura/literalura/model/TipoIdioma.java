package com.alura.literalura.model;

public enum TipoIdioma {
    espanol("es"),

    ingles("en"),

    frances("fr"),

    portugues("pt");

    private String codigoIdioma;

    TipoIdioma(String codigoIdioma){
        this.codigoIdioma = codigoIdioma;
    }

    public static TipoIdioma fromString(String text) {
        for (TipoIdioma tipoIdioma : TipoIdioma.values()) {
            if (tipoIdioma.codigoIdioma.equalsIgnoreCase(text)) {
                return tipoIdioma;
            }
        }
        throw new IllegalArgumentException("Ninguna idioma encontrada: " + text);
    }

    public String getCodigoIdioma() {
        return codigoIdioma;
    }
}
