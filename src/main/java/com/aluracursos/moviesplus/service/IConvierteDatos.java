package com.aluracursos.moviesplus.service;

public interface IConvierteDatos {

    <T> T obtenerDatos(String json, Class<T> clase);

}
