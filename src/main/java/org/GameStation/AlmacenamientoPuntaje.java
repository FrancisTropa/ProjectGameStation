package org.GameStation;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AlmacenamientoPuntaje {


    public static void crearArchivo(String ruta, String nombreClase, float puntaje) {
        DatoJuego puntajeObj = new DatoJuego(nombreClase, puntaje);
        DatoJuego[] datosJuego = { puntajeObj };
        Gson gson = new Gson();
        String json = gson.toJson(datosJuego);

        try (FileWriter fileWriter = new FileWriter(ruta)) {
            fileWriter.write(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static float ObtenerPuntaje(String ruta, String nombreClase) {
        try {
            Gson gson = new Gson();
            JsonReader jsonReader = obtenerJsonReader(ruta);

            DatoJuego[] datosJuego = deserializar(gson, jsonReader);
            for (DatoJuego datoJuego : datosJuego) {
                if (datoJuego.getNombreClase().equals(nombreClase)) {
                    return datoJuego.getPuntaje();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return 0;
    }

    public static void sobreescribirArchivo(String ruta, String nombreClase, float puntaje) {
        try {
            Gson gson = new Gson();
            JsonReader jsonReader = obtenerJsonReader(ruta);

            DatoJuego[] datosJuego = deserializar(gson, jsonReader);
            for (DatoJuego datoJuego : datosJuego) {
                if (datoJuego.getNombreClase().equals(nombreClase)) {
                    datoJuego.setPuntaje(puntaje);
                    break;
                }
            }

            String json = gson.toJson(datosJuego);
            try (FileWriter fileWriter = new FileWriter(ruta)) {
                fileWriter.write(json);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void agregarDatoAlArchivo(String ruta, String nombreClase, float puntaje) {
        try {
            Gson gson = new Gson();
            JsonReader jsonReader = obtenerJsonReader(ruta);

            DatoJuego[] datosJuego = deserializar(gson, jsonReader);
            List<DatoJuego> listaDatosJuego = new ArrayList<>(Arrays.asList(datosJuego));

            DatoJuego nuevoDatoJuego = new DatoJuego(nombreClase, puntaje);

            listaDatosJuego.add(nuevoDatoJuego);

            String json = gson.toJson(listaDatosJuego);

            try (FileWriter fileWriter = new FileWriter(ruta)) {
                fileWriter.write(json);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static JsonReader obtenerJsonReader(String ruta) throws IOException {
        FileReader fileReader = new FileReader(ruta);
        JsonReader jsonReader = new JsonReader(fileReader);
        jsonReader.setLenient(true);
        return jsonReader;
    }

    private static DatoJuego[] deserializar(Gson gson, JsonReader jsonReader){
        return gson.fromJson(jsonReader, DatoJuego[].class);
    }

    public static boolean comprobarExistenciaDato(String ruta, String nombreClase) {
        boolean existe = false;
        try {
            Gson gson = new Gson();
            JsonReader jsonReader = obtenerJsonReader(ruta);

            DatoJuego[] datosJuego = deserializar(gson, jsonReader);
            for (DatoJuego datoJuego : datosJuego) {
                if (datoJuego.getNombreClase().equals(nombreClase)) {
                    existe = true;
                    break;
                }
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        return existe;
    }
}