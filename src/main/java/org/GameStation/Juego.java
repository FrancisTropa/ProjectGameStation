package org.GameStation;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;

public abstract class Juego extends Game {
    private Stage stage;
    private OrthographicCamera camara;
    private SpriteBatch batch;
    private FitViewport viewport;

    @Override
    public void create() {
        camara = new OrthographicCamera();
        batch = new SpriteBatch();
        stage = new Stage();
        viewport = new FitViewport(640, 480, camara);

        Gdx.gl.glClearColor(0, 0, 0, 1);
    }

    @Override
    public void render() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.setProjectionMatrix(camara.combined);
        batch.begin();
        // Renderiza el contenido del juego
        renderGame();
        batch.end();

        stage.act();
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
        camara.position.set(camara.viewportWidth / 2f, camara.viewportHeight / 2f, 0);
        camara.update();
    }

    @Override
    public void dispose() {
        stage.dispose();
        batch.dispose();
    }

    public FitViewport getViewport(){
        return this.viewport;
    }

    public SpriteBatch getBatch() {
        return batch;
    }

    public OrthographicCamera getCamara() {
        return camara;
    }

    public abstract void renderGame();
}