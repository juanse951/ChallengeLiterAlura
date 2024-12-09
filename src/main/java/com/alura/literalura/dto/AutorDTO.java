package com.alura.literalura.dto;

import java.time.LocalDate;

public record AutorDTO(
         String nombre,

         LocalDate fechaDeNacimiento,

         LocalDate fechaDeFallecimiento) {
}
