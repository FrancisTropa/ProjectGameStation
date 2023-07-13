package org.GameStation.CarreraPackage;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Rectangle;

public class Carro {
    private Texture textura;
    private Vector2 posicion;
    private Vector2 velocidad;
    private boolean moverIzquierda;
    private boolean moverDerecha;
    private int ancho;
    private int alto;
    private int velocidadMovimiento = 350;

    public Carro(Texture textura) {
        this.textura = textura;
        definirPosicion();
        velocidad = new Vector2(0, 0);
        calcularProporciones();
    }

    public void update(float delta) {
        if (moverIzquierda) {
            velocidad.x = (-1 * velocidadMovimiento); // Velocidad negativa para mover hacia la izquierda
        } else if (moverDerecha) {
            velocidad.x = (velocidadMovimiento); // Velocidad positiva para mover hacia la derecha
        } else {
            velocidad.x = 0; // No hay movimiento horizontal si ninguna tecla está presionada
        }

        float nuevaPosX = posicion.x + velocidad.x * delta;
        if (nuevaPosX >= 0 && nuevaPosX + ancho <= Gdx.graphics.getWidth()) {
            posicion.x = nuevaPosX;
        }
    }

    private void definirPosicion() {
        posicion = new Vector2(Gdx.graphics.getWidth()/2.4f, Gdx.graphics.getHeight()/3f);
    }

    private void calcularProporciones() {
        int ventanaAncho = Gdx.graphics.getWidth();
        int ventanaAlto = Gdx.graphics.getHeight();

        ancho = ventanaAncho / 8;
        alto = ventanaAlto / 3;
    }

    public void setMoverIzquierda(boolean moverIzquierda) {
        this.moverIzquierda = moverIzquierda;
    }

    public void setMoverDerecha(boolean moverDerecha) {
        this.moverDerecha = moverDerecha;
    }

    public Rectangle getRectanguloColision() {
        return new Rectangle(posicion.x, posicion.y, ancho, alto);
    }

    public void render(SpriteBatch batch) {
        batch.draw(textura, posicion.x, posicion.y, ancho, alto, 0, 0, textura.getWidth(), textura.getHeight(), false, true);
    }
}
