package com.alura_cursos.literatura.models;

public enum Idioma {
    INGLES("en", "Inglés"),
    ESPANOL("es", "Español"),
    ALEMAN("de", "Alemán"),
    ITALIANO("it", "Italiano"),
    MANDARIN("zh", "Mandarín"),
    PORTUGUES("pt", "Portugués");

    private String codigoAPI;
    private String nombreEspanol;

    Idioma(String codigoAPI, String nombreEspanol) {
        this.codigoAPI = codigoAPI;
        this.nombreEspanol = nombreEspanol;
    }

    public static Idioma fromCodigoAPI(String text) {
        for (Idioma idioma : Idioma.values()) {
            if (idioma.codigoAPI.equalsIgnoreCase(text)) {
                return idioma;
            }
        }
        throw new IllegalArgumentException("Ningún idioma encontrado con el código: " + text);
    }

    public static Idioma fromNombreEspanol(String text) {
        for (Idioma idioma : Idioma.values()) {
            if (idioma.nombreEspanol.equalsIgnoreCase(text)) {
                return idioma;
            }
        }
        throw new IllegalArgumentException("Ningún idioma encontrado con el nombre: " + text);
    }
}