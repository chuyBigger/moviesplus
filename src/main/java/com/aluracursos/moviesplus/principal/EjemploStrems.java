package com.aluracursos.moviesplus.principal;

import java.util.Arrays;
import java.util.List;

public class EjemploStrems {

    public void muestraEjemplo(){
        List<String> nombres = Arrays.asList("Maria","Dereck","Genesis", "Marco", "Jesùs","Maria", "Abram");

        nombres.stream()
                .sorted()
                //.limit(3)
                .filter(n -> n.startsWith("M"))
                .map(n ->n.toUpperCase())
                .forEach(System.out::println);
    }
}
