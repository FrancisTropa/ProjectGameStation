package org.GameStation;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.GameStation.CarreraPackage.Carro;

public class Carrera extends Juego implements Screen {
    private Carro carro;

    public Carrera() {
        create();
        carro = new Carro(getViewport());
    }

    @Override
    public void renderGame() {
        carro.actualizaPosicion(Gdx.graphics.getDeltaTime());

        SpriteBatch batch = getBatch();
        batch.setProjectionMatrix(getCamara().combined);

        batch.begin();
        carro.render(batch);
        batch.end();
    }

    @Override
    public void create() {
        super.create(); // Agrega esta línea para inicializar el viewport en Juego
        carro = new Carro(getViewport());
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float v) {

    }

    @Override
    public void hide() {

    }
}
