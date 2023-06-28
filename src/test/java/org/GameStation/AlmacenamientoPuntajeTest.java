package org.GameStation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class AlmacenamientoPuntajeTest {

    private String ruta;

    @BeforeEach
    void setUp(){
        ruta = "archivos/puntajes.json";
    }

    @Test
    @DisplayName("comprueba la creacion del archivo")
    void crearArchivo(){
        AlmacenamientoPuntaje.crearArchivo(ruta, "Juego1", 20);
        File archivo = new File(ruta);
        assertTrue(archivo.exists());
    }

    @Test
    @DisplayName("comprueba que puede recuperar el puntaje segun el nombre de la clase")
    void recuperarPuntajeArchivo(){
        AlmacenamientoPuntaje.crearArchivo(ruta, "juego1", 20);
        float puntaje = AlmacenamientoPuntaje.ObtenerPuntaje(ruta, "juego1");
        assertEquals(20, puntaje);
    }

    @Test
    @DisplayName("comprueba que se sobreescriba un nuevo puntaje en el DatoJuego que coincida el nombre")
    void sobreEscribirArchivo(){
        AlmacenamientoPuntaje.crearArchivo(ruta, "juego1", 20);
        AlmacenamientoPuntaje.sobreescribirArchivo(ruta, "juego1", 30);
        float puntaje = AlmacenamientoPuntaje.ObtenerPuntaje(ruta, "juego1");
        assertEquals(30, puntaje);
    }

    @Test
    @DisplayName("Agrega un nuevo DatoJuego al archivo")
    void agregarNuevoDatoAlArchivo(){
        AlmacenamientoPuntaje.crearArchivo(ruta, "juego1", 20);
        AlmacenamientoPuntaje.agregarDatoAlArchivo(ruta, "juego2", 40);
        float puntaje = AlmacenamientoPuntaje.ObtenerPuntaje(ruta, "juego2");
        assertEquals(40, puntaje);
    }

}