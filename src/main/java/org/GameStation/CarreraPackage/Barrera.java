package org.GameStation.CarreraPackage;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Rectangle;

public class Barrera {
    private float ancho;
    private float alto;
    private Vector2 posicion;

    public Barrera(){
        definirDimensiones();
    }

    private void definirDimensiones() {
        int ventanaAncho = Gdx.graphics.getWidth();
        int ventanaAlto = Gdx.graphics.getHeight();

        ancho = ventanaAncho / 10f;
        alto = ventanaAlto;
    }

    public void definirBarreraIzq() {
        posicion = new Vector2(0, 0);
    }

    public void definirBarreraDer(){
        posicion = new Vector2(Gdx.graphics.getWidth() - ancho, 0);
    }

    public Rectangle getRectanguloColision() {
        return new Rectangle(posicion.x, posicion.y, ancho, alto);
    }

    public void render(ShapeRenderer shapeRenderer) {
        shapeRenderer.begin(ShapeType.Filled);
        shapeRenderer.setColor(Color.BLACK);
        shapeRenderer.rect(posicion.x, posicion.y, ancho, alto);
        shapeRenderer.end();
    }
}
