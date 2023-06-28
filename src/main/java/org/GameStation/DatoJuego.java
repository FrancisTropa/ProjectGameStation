package org.GameStation;

public class DatoJuego {
    private String nombreClase;
    private float puntaje;

    public DatoJuego(String nombre, float puntaje){
        this.nombreClase = nombre;
        this.puntaje = puntaje;
    }

    public String getNombreClase() {
        return this.nombreClase;
    }

    public float getPuntaje() {
        return this.puntaje;
    }

    public void setPuntaje(float puntaje) {
        this.puntaje = puntaje;
    }
}
