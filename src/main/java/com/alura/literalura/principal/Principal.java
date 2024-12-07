package com.alura.literalura.principal;

import com.alura.literalura.model.*;
import com.alura.literalura.repository.AutorRepository;
import com.alura.literalura.repository.LibroRepository;
import com.alura.literalura.service.ConsumoAPI;
import com.alura.literalura.service.ConvierteDatos;
import com.alura.literalura.service.LibroService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Principal {

    private static final String URL_BASE = "https://gutendex.com/books/";
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private ConvierteDatos conversor = new ConvierteDatos();
    private Scanner teclado = new Scanner(System.in);
    private LibroService libroService;
    private AutorRepository autorRepository;

    public Principal(LibroService libroService, AutorRepository autorRepository) {
        this.libroService = libroService;
        this.autorRepository = autorRepository;
    }

    public Principal(LibroService libroService) {
        this.libroService = libroService;
    }

    public void muestraElMenu() {
        var opcion = -1;
        while (opcion != 0) {
            var menu = """
                     Elíje la opción a través de su número:
                     1- Buscar libro por titulo
                     2- Listar libros registrados
                     3- Listar autores registrados
                     4- Listar autores vivos en un determinado año
                     5- Listar libros por idioma
                     \s
                     0 - Salir
                    \s""";
            System.out.println(menu);
            opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion) {
                case 1:
                    buscarLibroPorTitulo();
                    break;
                case 2:
                    System.out.println("hola");
                    break;
                case 3:
                    autoresRegistrados();
                    break;
                case 0:
                    System.out.println("Cerrando la aplicación...");
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        }

    }

    private void autoresRegistrados() {
        List<Autor> autores = autorRepository.findAll();

        if (autores.isEmpty()) {
            System.out.println("No hay autores registrados.");
        } else {
            System.out.println("Autores disponibles:");
            autores.forEach(System.out::println);
        }
    }

    private void buscarLibroPorTitulo() {
        Datos datos = getDatos();// Obtienes la lista de libros

        if(!datos.resultados().isEmpty()){
            String tituloLibro = datos.resultados().get(0).titulo();
            Libro libroGuardado = libroService.guardarLibroConAutor(tituloLibro, datos);

            // Mostrar el libro guardado
            System.out.println("Libro guardado: " + "\n" + libroGuardado);
        } else {
            System.out.println("No se encontraron libros :(");
        }

    }

    private Datos getDatos(){
        System.out.println("Escribe el titulo de tu libro: ");
        var tituloLibro = teclado.nextLine();
        var json = consumoAPI.obtenerDatos(URL_BASE + "?search=" + tituloLibro.replace(" ","+"));
        //System.out.println(json);
        Datos datos = conversor.obtenerDatos(json, Datos.class);
        return datos;
    }

}






