package org.GameStation;

public abstract class JuegoBase extends Juego.PantallaJuego implements OpcionesJuego{
    protected boolean juegoTerminado = false;
    protected float puntuacion = 0;

    protected JuegoBase(Juego juego) {
        super(juego);
    }

    protected void comprobarTermino() {
    }

    protected void irAlArchivo() {
    }

    protected void comprobarSiHayDatos() {
    }

    protected void agregarDatos() {
    }

    protected void comprobarPuntajeMayor() {
    }
}
