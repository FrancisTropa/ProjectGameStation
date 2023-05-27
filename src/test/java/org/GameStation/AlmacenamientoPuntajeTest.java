package org.GameStation;

import org.junit.jupiter.api.*;
import java.io.File;
import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AlmacenamientoPuntajeTest {

    private String ruta = "archivos/archivoTest.txt";
    private AlmacenamientoPuntaje almacenamiento;

    @BeforeEach
    public void setup() {
        almacenamiento = new AlmacenamientoPuntaje();
    }

    @Test
    @Order(1)
    void testCrearArchivo() {
        almacenamiento.crearArchivo(ruta);
        File archivo = new File(ruta);
        assertTrue(archivo.exists(), "El archivo no se creo correctamente");
    }

    @Test
    @Order(2)
    void testSobrescribirArchivo() {
        almacenamiento.sobrescribirArchivo(ruta, 20);
        assertEquals(20, Integer.parseInt(almacenamiento.leerArchivo(ruta)), "El archivo no sobreescribio correctamente");
    }

    @Test
    @Order(3)
    void testLeerArchivo() {
        almacenamiento.leerArchivo(ruta);
        assertEquals(20, Integer.parseInt(almacenamiento.leerArchivo(ruta)), "El contenido no se leyo correctamente");
    }

    @Test
    @Order(4)
    void testEliminarArchivo() {
        almacenamiento.eliminarArchivo(ruta);
        File archivo = new File(ruta);
        assertFalse(archivo.exists(), "El archivo no se elimino correctamente");
    }
}