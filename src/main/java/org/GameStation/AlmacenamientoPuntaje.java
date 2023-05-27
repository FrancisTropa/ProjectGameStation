package org.GameStation;

import java.io.*;

public class AlmacenamientoPuntaje {

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
        if (archivo.exists()) {
            archivo.delete();
        } else {
            System.out.println("El archivo no existe: " + ruta);
        }
    }

    public String leerArchivo(String ruta) {
        File archivo = new File(ruta);
        StringBuilder contenido = new StringBuilder();

        if (archivo.exists()) {
            try {
                BufferedReader reader = new BufferedReader(new FileReader(archivo));
                String linea;

                while ((linea = reader.readLine()) != null) {
                    contenido.append(linea);
                }

                reader.close();
            } catch (IOException e) {
                System.out.println("Error al leer el archivo: " + e.getMessage());
            }
        }

        return contenido.toString();
    }

}