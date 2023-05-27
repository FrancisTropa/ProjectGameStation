package org.GameStation;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.GameStation.CarreraPackage.Carro;

public class Carrera extends Juego.PantallaJuego {

    private SpriteBatch batch;
    private Texture textura;
    private Carro carro;

    public Carrera(Juego juego) {
        super(juego);
    }

    @Override
    public void show() {
        batch = new SpriteBatch();
        textura = new Texture("C:/Users/acer/Documents/ProjectGameStation/ParaProbar/src/main/java/org/imagen/carro.png");
        carro = new Carro(textura);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        manejoTeclado();
        carro.update(delta);

        batch.begin();
        carro.render(batch);
        batch.end();
    }

    private void manejoTeclado() {
        carro.setMoverIzquierda(Gdx.input.isKeyPressed(Input.Keys.LEFT));
        carro.setMoverDerecha(Gdx.input.isKeyPressed(Input.Keys.RIGHT));
    }

    @Override
    public void dispose() {
        batch.dispose();
        textura.dispose();
    }
}
