package org.GameStation.CarreraPackage;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Obstaculo {

    private Texture textura;
    private Vector2 posicion;
    private Vector2 velocidad;
    private int ancho;
    private int alto;

     public Obstaculo(Texture textura){
         this.textura = textura;
         calcularProporciones();
         generarPosicion();
         this.velocidad = new Vector2(0, 200);
     }

    private void calcularProporciones() {
         definirAncho();
         definirAlto();
    }

    private void definirAncho() {
         ancho = Gdx.graphics.getWidth() / 10;
    }

    private void definirAlto() {
        alto = Gdx.graphics.getHeight() /15;
    }

    public void generarPosicion() {
        float coordenadaXRandom = generarX();
        posicion = new Vector2(coordenadaXRandom, Gdx.graphics.getHeight());
    }

    private float generarX() {
         float porcion = Gdx.graphics.getWidth() / 10f;
         float rango = Gdx.graphics.getHeight() - porcion - ancho;
         return  porcion + MathUtils.random(rango);
    }

    public Rectangle getRectanguloColision() {
        return new Rectangle(posicion.x, posicion.y, ancho, alto);
    }

    public void update(float delta) {
        mover(delta);
        if (posicion.y + alto < 0) {
            generarPosicion();
        }
    }

    public void render(SpriteBatch batch) {
        batch.draw(textura, posicion.x, posicion.y, ancho, alto);
    }

    public void mover(float delta){
        float nuevaPosY = posicion.y - velocidad.y * delta;
        posicion.y = nuevaPosY;
    }

    public void dispose() {
        textura.dispose();
    }
}
