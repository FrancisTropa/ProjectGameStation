package org.GameStation.CarreraPackage;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class Carro {
    private Texture texturaCarro;
    private Sprite spriteCarro;
    private FitViewport viewport;
    private Vector2 posicion;
    private float velocidad = 200f;

    public Carro(FitViewport viewport) {
        this.viewport = viewport;
        texturaCarro = new Texture("C:/Users/acer/Documents/ProjectGameStation/GameStation/src/main/java/org/GameStation/imagenes/carro.png");
        spriteCarro = new Sprite(texturaCarro);
        spriteCarro.setSize(viewport.getWorldWidth() / 4f, viewport.getWorldHeight() / 4f);
        spriteCarro.setPosition(viewport.getWorldWidth() / 2f - spriteCarro.getWidth() / 2f, 0);
        spriteCarro.flip(false, true); // Voltea el sprite verticalmente
        posicion = new Vector2(spriteCarro.getX(), spriteCarro.getY());
    }

    public void actualizaPosicion(float delta) {
        float movementAmount = velocidad * delta;
        // Mueve el carro a la izquierda o a la derecha
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            posicion.x -= movementAmount;
        } else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            posicion.x += movementAmount;
        }
        // Limita la posición del carro para que se mantenga dentro de la ventana
        if (posicion.x < 0) {
            posicion.x = 0;
        } else if (posicion.x > viewport.getWorldWidth() - spriteCarro.getWidth()) {
            posicion.x = viewport.getWorldWidth() - spriteCarro.getWidth();
        }
        // Actualiza la posición
        spriteCarro.setPosition(posicion.x, posicion.y);
    }

    public void render(SpriteBatch batch) {
        // Renderizar el carro en su posición actual
        spriteCarro.draw(batch);
    }

    public FitViewport getViewport() {
        return viewport;
    }

    public void dispose() {
        texturaCarro.dispose();
    }
}
