package org.GameStation.CarreraPackage;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Carro {
    private Texture textura;
    private Vector2 posicion;
    private Vector2 velocidad;
    private boolean moverIzquierda;
    private boolean moverDerecha;
    private int ancho;
    private int alto;

    public Carro(Texture textura) {
        this.textura = textura;
        posicion = new Vector2(100, 100);
        velocidad = new Vector2(100, 0);
        calcularProporciones();
    }

    public void update(float delta) {
        if (moverIzquierda) {
            velocidad.x = -100;
        } else if (moverDerecha) {
            velocidad.x = 100;
        } else {
            velocidad.x = 0;
        }

        float nuevaPosX = posicion.x + velocidad.x * delta;
        if (nuevaPosX >= 0 && nuevaPosX + ancho <= Gdx.graphics.getWidth()) {
            posicion.x = nuevaPosX;
        }

        posicion.x += velocidad.x * delta;
        posicion.y += velocidad.y * delta;
    }

    public void setMoverIzquierda(boolean moverIzquierda) {
        this.moverIzquierda = moverIzquierda;
    }

    public void setMoverDerecha(boolean moverDerecha) {
        this.moverDerecha = moverDerecha;
    }

    private void calcularProporciones() {
        int ventanaAncho = Gdx.graphics.getWidth();
        int ventanaAlto = Gdx.graphics.getHeight();

        ancho = ventanaAncho / 8;
        alto = ventanaAlto / 3;
    }

    public void render(SpriteBatch batch) {
        batch.draw(textura, posicion.x, posicion.y, ancho, alto, 0, 0, textura.getWidth(), textura.getHeight(), false, true);
    }
}
