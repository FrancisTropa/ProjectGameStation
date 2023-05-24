package org.GameStation;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class Menu extends ApplicationAdapter {
    private Stage stage;
    private Texture backgroundTexture;
    private Button button1;
    private Button button2;

    @Override
    public void create() {
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        backgroundTexture = new Texture(Gdx.files.internal("imagenes/Espacio.png")); //por el momento no puede cargar la imagen; motivo: "desconocido"

        TextButtonStyle style = new TextButtonStyle();
        style.font = new BitmapFont(); // Establece la fuente
        style.fontColor = Color.WHITE; // Establece el color de la fuente
        style.up = new TextureRegionDrawable(new TextureRegion(new Texture("imagenes/Seleccion.png")));
        style.down = new TextureRegionDrawable(new TextureRegion(new Texture("imagenes/Vacio.png")));

        // Crear los botones
        button1 = new TextButton("Juego1", style);
        button2 = new TextButton("Juego2", style);

        Table table = new Table();
        table.setFillParent(true);
        table.add(button1).padBottom(20f);
        table.row();
        table.add(button2);

        stage.addActor(table);
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        SpriteBatch spriteBatch = new SpriteBatch();
        spriteBatch.begin();
        spriteBatch.draw(backgroundTexture, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        spriteBatch.end();

        stage.act();
        stage.draw();
    }

    @Override
    public void dispose() {
        stage.dispose();
        backgroundTexture.dispose();
    }
}
