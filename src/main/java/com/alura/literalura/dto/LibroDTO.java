package com.alura.literalura.dto;

import com.alura.literalura.model.TipoIdioma;

public record LibroDTO(
         String titulo,

         String autorNombre,

         TipoIdioma idioma,

         Long numeroDeDescargas) {

}
