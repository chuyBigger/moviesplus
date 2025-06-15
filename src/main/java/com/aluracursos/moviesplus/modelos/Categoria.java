package com.aluracursos.moviesplus.modelos;

public enum Categoria {
    ACCION("Action", "Acción"),
    AVENTURA("Adventure","Aventura"),
    ROMACE("Romance","Romance"),
    COMEDIA("Comedy","Comedia"),
    DRAMA("Drama", "Drama"),
    CRIMEN("Crime","Crimen");
    private String categoriaOmdb;
    private String categoriaEspanol;

    Categoria(String categoriaOmdb, String  categoriaEspanol) {
        this.categoriaOmdb = categoriaOmdb;
        this.categoriaEspanol = categoriaEspanol;
    }

    public static Categoria fromString(String text) {
        for (Categoria categoria : Categoria.values()) {
            if (categoria.categoriaOmdb.equalsIgnoreCase(text)) {
                return categoria;
            }
        }
        throw new IllegalArgumentException("Ninguna Categoria encontrada ..." + text);
    }

    public static Categoria fromEspanol(String text) {
        for (Categoria categoria : Categoria.values()) {
            if (categoria.categoriaEspanol.equalsIgnoreCase(text)) {
                return categoria;
            }
        }
        throw new IllegalArgumentException("Ninguna Categoria encontrada ..." + text);
    }
}
