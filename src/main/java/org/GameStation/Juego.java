package org.GameStation;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;

public abstract class Juego extends ApplicationAdapter {
    private Stage stage;
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private FitViewport viewport;

    public Juego() {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.title = "Juego";
        config.width = 640;
        config.height = 480;
        new LwjglApplication(this, config);
    }

    @Override
    public void create() {
        camera = new OrthographicCamera();
        batch = new SpriteBatch();
        stage = new Stage();
        viewport = new FitViewport(640, 480, camera);

        Gdx.gl.glClearColor(0, 0, 0, 1);
    }

    @Override
    public void render() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.end();
        stage.act();
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
        camera.position.set(camera.viewportWidth / 2f, camera.viewportHeight / 2f, 0);
        camera.update();
    }

    @Override
    public void dispose() {
        stage.dispose();
        batch.dispose();
    }
}
