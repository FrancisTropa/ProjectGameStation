package org.GameStation;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class AlmacenamientoPuntaje {//almacenamiento temporal, se evalua el uso de gson

    public void crearArchivo(String ruta) {
        File archivo = new File(ruta);
        try {
            archivo.createNewFile();
        } catch (IOException e) {
            System.out.println("No se pudo crear el archivo: " + e.getMessage());
        }
    }

    public void sobrescribirArchivo(String ruta, int puntaje) {
        File archivo = new File(ruta);
        try {
            FileWriter escritor = new FileWriter(archivo);
            escritor.write(String.valueOf(puntaje));
            escritor.close();
        } catch (IOException e) {
            System.out.println("No se pudo sobrescribir el archivo: " + e.getMessage());
        }
    }

    public void eliminarArchivo(String ruta) {
        File archivo = new File(ruta);
        archivo.delete();
    }

}