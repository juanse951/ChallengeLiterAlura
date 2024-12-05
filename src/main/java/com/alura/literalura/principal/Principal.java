package com.alura.literalura.principal;

import com.alura.literalura.model.DatosLibro;
import com.alura.literalura.repository.LibroRepository;
import com.alura.literalura.service.ConsumoAPI;
import com.alura.literalura.service.ConvierteDatos;

import java.util.Scanner;

public class Principal {

    private static final String URL_BASE = "https://gutendex.com/books/";
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private ConvierteDatos conversor = new ConvierteDatos();
    private Scanner teclado = new Scanner(System.in);
    private LibroRepository repositorio;

    public Principal(LibroRepository repository){
        this.repositorio = repository;
    }


    public void muestraElMenu(){
        var opcion = -1;
        while (opcion !=0){
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

        }


        var json = consumoAPI.obtenerDatos(URL_BASE);
        System.out.println(json);
        opcion = teclado.nextInt();
        teclado.nextLine();

        switch (opcion){
            case 1:
                buscarLibroPorTitulo();
                break;
            case 0:
                System.out.println("Cerrando la aplicación...");
                break;
            default:
                System.out.println("Opción inválida");
        }
    }

    private DatosLibro getDatosLibro(){
        System.out.println("Escribe el nombre del libro que deseas buscar: ");
        var nombreLibro
    }
    private void buscarLibroPorTitulo() {

    }
}


