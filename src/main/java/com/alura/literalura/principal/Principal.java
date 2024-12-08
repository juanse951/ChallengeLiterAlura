package com.alura.literalura.principal;

import com.alura.literalura.model.*;
import com.alura.literalura.repository.AutorRepository;
import com.alura.literalura.repository.LibroRepository;
import com.alura.literalura.service.ConsumoAPI;
import com.alura.literalura.service.ConvierteDatos;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Principal {

    private static final String URL_BASE = "https://gutendex.com/books/";
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private ConvierteDatos conversor = new ConvierteDatos();
    private Scanner teclado = new Scanner(System.in);
    private LibroRepository libroRepository;
    private AutorRepository autorRepository;

    public Principal(LibroRepository libroRepository,AutorRepository autorRepository) {
        this.libroRepository = libroRepository;
        this.autorRepository = autorRepository;
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

            System.out.println("Ingrese una opción valida: ");
            String entrada = teclado.nextLine();

            try {
                opcion = Integer.parseInt(entrada);

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
            }catch (NumberFormatException e){
                System.out.println("Error: Debe ingresar un número válido.");
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
            DatosLibro datosLibro = datos.resultados().get(0);// Tomamos el primer libro
            DatosAutor datosAutor = datosLibro.autores().get(0);// Tomamos el primer autor

            // Crear el libro con los datos obtenidos
            Libro libro = new Libro(datosLibro);

            // Buscar al autor por su nombre en la base de datos
            Autor autor = autorRepository.findByNombre(datosAutor.nombre())
                    .orElseGet(() ->{
                        // Si no existe, crear un nuevo autor
                        Autor nuevoAutor = new Autor(datosAutor);
                        return autorRepository.save(nuevoAutor);// Guardamos el nuevo autor
                    });

            // Asignar el autor al libro
            libro.setAutor(autor);



            // Evitar duplicados en la lista del autor
            if (autor.getLibros() == null) {
                autor.setLibros(new ArrayList<>());
            }

            if (!autor.getLibros().contains(libro)) {
                autor.getLibros().add(libro);
            }

            // Guardar el libro en el libroRepository
            libroRepository.save(libro);

            // Mostrar el libro guardado
            System.out.println("Libro guardado: " + "\n" + libro);
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






