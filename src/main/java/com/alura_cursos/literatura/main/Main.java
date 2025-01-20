package com.alura_cursos.literatura.main;

import com.alura_cursos.literatura.models.*;
import com.alura_cursos.literatura.repository.bookRepository;
import com.alura_cursos.literatura.services.converData;
import com.alura_cursos.literatura.services.getAPI;

import java.util.*;

public class Main {
    private final String api = "https://gutendex.com/books/?search=";
    private Scanner teclado = new Scanner(System.in);
    private getAPI get_API = new getAPI();
    private converData conversor = new converData();
    private List<dataBook> dataBookList = new ArrayList<>();
    private bookRepository repositorio;
    private List<book> bookList;
    private Optional<book> bookFound;

    public Main(bookRepository repositorio) {
        this.repositorio = repositorio;
    }

    public void showMenu() {
        var opcion = -1;
        while (opcion != 0) {
            var menu = """
                    === Menú de LiterAlura ===
                    1. Buscar libro por título
                    2. Listar libros registrados
                    3. Listar autores registrados
                    4. Listar autores vivos en un año específico
                    5. Listar libros por idioma
                    6. Salir
                    ===========================
                    Por favor, elige una opción:
                    """;
            System.out.println(menu);
            opcion = getNum(teclado);
            switch (opcion) {
                case 1:
                    searchBookTitle();
                    break;
                case 2:
                    getBooks();
                    break;
                case 3:
                    getAuthors();
                    break;
                case 4:
                    getAuthorsAliveYear();
                    break;
                case 5:
                    getBooksLanguage();
                    break;
                case 6:
                    System.out.println("Cerrando la aplicación...");
                    opcion=0;
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        }

    }

    private void getBooksLanguage() {
        String lenguages =
                "(\"en-\", \"Inglés\"),\n" +
                "(\"es-\", \"Español\"),\n" +
                "(\"de-\", \"Alemán\"),\n" +
                "(\"it-\", \"Italiano\"),\n" +
                "(\"zh-\", \"Mandarín\"),\n" +
                "(\"pt-\", \"Portugués\",\n)";
        System.out.println(lenguages);
        System.out.println("Escriba el idioma  para buscar los libro: ");
        var name_idioma = teclado.nextLine();
        var idiomas = Idioma.fromCodigoAPI(name_idioma);
        List<book> lista = repositorio.findByIdiomas(idiomas);
        System.out.println("Libros del idioma : " + name_idioma);
        lista.forEach(System.out::println);
    }

    private void getAuthorsAliveYear() {
        System.out.println("Escriba el año para traer lo autores vivos en dicho año: ");
        var ano = teclado.nextInt();
        List<autor> autorList = repositorio.get_authors_deathday(ano);
        autorList.forEach(a-> System.out.println(a.toString()));
    }

    private void getAuthors() {
        List<autor> autorList = repositorio.get_authors();
        autorList.forEach(s-> System.out.println(s.toString()));
    }

    private void getBooks() {
        bookList = repositorio.findAll();
        bookList.stream()
                .forEach(System.out::println);
    }

    private void searchBookTitle() {
        List<dataBook> data = getDataBook();
        book books = new book(data);
        System.out.println(data);
        bookFound= Optional.of(books);
        if (bookFound.isPresent()) {
            System.out.println("Libro encontrado : " + bookFound.get());
            // Crear y asociar autores
            List<autor> authors = new ArrayList<>();
            for (dataAuthor authorName : data.get(0).autores()) {
                autor author = new autor();
                author.setTitulo(data.get(0).autores().get(0).nombre());
                author.setAnofallecimiento(data.get(0).autores().get(0).anofallecimiento());
                author.setAnoNacimiento(data.get(0).autores().get(0).anoNacimiento());
                author.setBooks(books);
                authors.add(author);
            }
            books.setAutores(authors);
            repositorio.save(books);
            books.toString();
        } else {
            System.out.println("El libro  no existe en la BD");
        }

    }

    private List<dataBook> getDataBook() {
        var name = getLetter(teclado);
        var json = get_API.get_datos(api+name.replace(" ","+"));
        results datos = conversor.getData(json, results.class);
        List<dataBook> datosn = datos.resultados();
        return datosn;
    }


    private static int getNum(Scanner scanner) {
        System.out.print("Por favor, ingrese un número: ");
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Entrada inválida. Solo se permiten números.");
            return getNum(scanner);
        }
    }

    private static String getLetter(Scanner scanner) {
        System.out.print("Por favor, ingrese una frase: ");
        String entrada = scanner.nextLine();
        // Permitir solo letras y espacios
        if (entrada.matches("[a-zA-ZÀ-ÿ\\u00f1\\u00d1\\s]+")) {
            return entrada;
        } else {
            System.out.println("Entrada inválida. Solo se permiten letras y espacios.");
            return getLetter(scanner);
        }
    }
}

