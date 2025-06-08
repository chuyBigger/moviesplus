package com.aluracursos.moviesplus.principal;

import com.aluracursos.moviesplus.service.ConsumoApi;
import com.aluracursos.moviesplus.service.ConvierteDatos;

import java.util.Scanner;

public class Principal {
    Scanner scanner = new Scanner(System.in);
    ConsumoApi consumoApi = new ConsumoApi();
    ConvierteDatos convierteDatos = new ConvierteDatos();
    private static final String API_BASE = "https://www.omdbapi.com/?t=";
    private static final String API_KEY = "&apikey=58237260";


    public void muestrElMenu() {
        System.out.println("Escribe el nombre de la serie que buscas: ");
        var busquedaSerie = scanner.nextLine();
        var json = consumoApi.obtenerDatos(API_BASE + busquedaSerie.replace(" ", "+") + API_KEY);
        



    }


}
