package com.alura.literalura.principal;

import com.alura.literalura.model.*;
import com.alura.literalura.repository.AutorRepository;
import com.alura.literalura.repository.LibroRepository;
import com.alura.literalura.service.AutorService;
import com.alura.literalura.service.ConsumoAPI;
import com.alura.literalura.service.ConvierteDatos;
import com.alura.literalura.service.LibroService;

import java.time.LocalDate;
import java.util.*;

public class Principal {

    private static final String URL_BASE = "https://gutendex.com/books/";
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private ConvierteDatos conversor = new ConvierteDatos();
    private Scanner teclado = new Scanner(System.in);
    private AutorService autorService;
    private LibroService libroService;

    public Principal(AutorService autorService, LibroService libroService) {
        this.autorService = autorService;
        this.libroService = libroService;
    }

    public void muestraElMenu() {
        var opcion = -1;
        while (opcion != 0) {
            var menu = """
                     Elíje la opción a través de su número:
                     \s
                     1- Buscar libro por titulo
                     2- Listar libros registrados
                     3- Listar autores registrados
                     4- Listar autores vivos en un determinado año
                     5- Listar libros por idioma
                     6- Mostrar estadísticas de libros por idioma
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
                        listarTodosLosLibros();
                        break;
                    case 3:
                        autoresRegistrados();
                        break;
                    case 4:
                        obtenerAutoresVivosEn();
                        break;
                    case 5:
                        buscarLibrosPorIdioma();
                        break;
                    case 6:
                        mostrarEstadisticasDeLibros();
                        break;
                    case 0:
                        System.out.println("Cerrando la aplicación...");
                        break;
                    default:
                        System.out.println("Opción inválida");
                }
            }catch (NumberFormatException e){
                System.out.println("Error: Debe ingresar un número válido.\n");
            }
        }

    }

    private void mostrarEstadisticasDeLibros() {
        List<Object[]> estadisticas = libroService.obtenerEstadisticasPorIdioma();

        if(estadisticas.isEmpty()){
            System.out.println("No hay estadísticas disponibles sobre los libros por idioma T.T\n");
        }else {
            System.out.println("Estadísticas de libros por idioma en la base de datos: \n");

            estadisticas.stream()
                    .map(estadistica ->{
                        TipoIdioma idioma =(TipoIdioma) estadistica[0];
                        Long cantidad = (Long) estadistica[1];
                        return "Idioma: " + idioma.name() + " - Cantidad: " + cantidad;
                    })
                    .forEach(System.out::println);
        }
        System.out.println();

    }

    private void obtenerAutoresVivosEn() {
        System.out.println("Indica el año, para verificar los autores vivos: ");
        int anio = -1;
        int anioActual = LocalDate.now().getYear();

        while (anio < 0 || anio > anioActual){
            try {
                anio = Integer.parseInt(teclado.nextLine());
                if (anio < 0 || anio > anioActual) {
                    System.out.println("Error T.T El año debe estar entre 0 y " + anioActual + ". Inténtalo nuevamente.\n");
                }
            }catch (NumberFormatException e){
                System.out.println("Error T.T Debes ingresar un año válido :D\n");
            }
        }
    List<Autor> autoresVivos = autorService.obtenerAutoresVivosEn(anio);

        if(autoresVivos.isEmpty()){
            System.out.println("No hay autores vivos en el año: " + anio + "\n");
        }else {
            System.out.println("Autores vivos en el año " + anio + ":\n");
            autoresVivos.forEach(autor -> {
                System.out.println(autor);
            });
        }
    }

    private void buscarLibrosPorIdioma() {
        System.out.println("Seleccione un idioma: ");

        for (int i = 0; i < TipoIdioma.values().length; i++) {
            System.out.println((i + 1) + " - " + TipoIdioma.values()[i].name());
        }
        System.out.println("0 - Volver al menú principal\n");

        int opcion = -1;

        while(opcion < 0 || opcion > TipoIdioma.values().length){
            try{
                opcion = Integer.parseInt(teclado.nextLine());

                if(opcion == 0){
                    return;
                }

                if(opcion < 0 || opcion > TipoIdioma.values().length){
                    System.out.println("Opción invalida. Por favor ingresa un número entre 0 y " + TipoIdioma.values().length);
                }
            }catch (NumberFormatException e){
                System.out.println("Entrada invalida T.T Por favor ingresa un número entre 0 y " + TipoIdioma.values().length);
            }
        }
        TipoIdioma idiomaSeleccionado = TipoIdioma.values()[opcion - 1];
        listarLibrosPorIdioma(idiomaSeleccionado);
    }

    private void listarLibrosPorIdioma(TipoIdioma idioma){
        List<Libro> libros = libroService.obtenerLibrosPorIdioma(idioma);
        if(libros.isEmpty()){
            System.out.println("No hay libros disponibles en el idioma: " + idioma.name() + " T.T\n");
        }else {
            System.out.println("Libros en " + idioma.name() + ":\n");
            libros.forEach(System.out::println);
        }
    }

    private void listarTodosLosLibros() {
        List<Libro> libros = libroService.obtenerTodosLosLibros();

        if(libros.isEmpty()){
            System.out.println("No hay libros registrados T.T\n");
        }else {
            System.out.println("Libros REGISTRADOS: \n");
            libros.forEach(libro -> {
                System.out.println("--------LIBRO--------");
                System.out.println("Título: " + libro.getTitulo());
                System.out.println("Autor: " + libro.getAutor().getNombre());
                System.out.println("Idioma: " + libro.getIdioma().name());
                System.out.println("Número de descargas: " + libro.getNumeroDeDescargas());
                System.out.println("---------------------\n");
            });
        }
    }

    private void autoresRegistrados() {
        List<Autor> autores = autorService.obtenerTodosLosAutores();

        if (autores.isEmpty()) {
            System.out.println("No hay autores registrados.\n");
        } else {
            System.out.println("Autores disponibles: \n");
            autores.forEach(System.out::println);
        }
    }

    private void buscarLibroPorTitulo() {
        Datos datos = getDatos();// Obtienes la lista de libros

        if (!datos.resultados().isEmpty()) {
            DatosLibro datosLibro = datos.resultados().get(0);// Tomamos el primer libro
            DatosAutor datosAutor = datosLibro.autores().get(0);// Tomamos el primer autor

            // Validamos el primer idioma del libro
            String codigoIdioma = datosLibro.idioma().get(0);//// Solo tomamos el primer idioma
            TipoIdioma idiomaPrincipal;
            try{
                idiomaPrincipal = TipoIdioma.fromString(codigoIdioma);// Validamos el idioma
            } catch (IllegalArgumentException e){
                System.out.println("Idioma original del libro no permitido.\nIdiomas válidos: español(es), ingles(en), frances(fr), portuges(pt).");
                System.out.println("Idioma ingresado: " + codigoIdioma + "\n");
                return; // Salimos del metodo si el idioma no es valido
            }

            // Crear el libro con los datos obtenidos
            Libro libro = new Libro(datosLibro);
            libro.setIdioma(idiomaPrincipal);

            // Buscar al autor por su nombre en la base de datos
            Autor autor = autorService.buscarAutorPorNombre(datosAutor.nombre())
                    .orElseGet(() -> {
                        // Si no existe, crear un nuevo autor
                        Autor nuevoAutor = new Autor(datosAutor);
                        return autorService.guardarAutor(nuevoAutor);// Guardamos el nuevo autor
                    });

            // Asignar el autor al libro
            libro.setAutor(autor);

            // Evitar duplicados en la lista del autor
            if (autor.getLibros() == null) {
                autor.setLibros(new ArrayList<>());
            }

            // Verificar si el libro ya está registrado para este autor
            boolean libroExiste = autor.getLibros().stream()
                    .anyMatch(l -> l.getTitulo().equalsIgnoreCase(libro.getTitulo()));

            if (libroExiste) {
                System.out.println("El titulo del libro ya esta registrado :D\n");
            } else {
                //si no esta registrado, agregar el libro
                autor.getLibros().add(libro);
                libroService.guardarLibro(libro);
                System.out.println("Libro guardado: " + "\n" + libro);
            }
        } else {
            System.out.println("No se encontraron libros T.T\n");
        }
    }

    private Datos getDatos(){
        System.out.println("Escribe el titulo de tu libro: ");
        var tituloLibro = teclado.nextLine();
        var json = consumoAPI.obtenerDatos(URL_BASE + "?search=" + tituloLibro.replace(" ","+"));
        return conversor.obtenerDatos(json, Datos.class);
    }

}












