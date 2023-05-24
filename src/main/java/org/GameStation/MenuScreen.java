package org.GameStation;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class MenuScreen extends ApplicationAdapter {
    private Stage stage;
    private Table table;
    private Label titleLabel;
    private TextButton tetrisButton, carrerasButton;

    @Override
    public void create () {
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        table = new Table();
        table.setFillParent(true);
        stage.addActor(table);

        // Configurar fuente y estilo de los botones
        BitmapFont font = new BitmapFont();
        TextButtonStyle buttonStyle = new TextButtonStyle();
        buttonStyle.font = font;
        buttonStyle.fontColor = Color.CYAN;

        // Crear elementos de la interfaz gráfica
        titleLabel = new Label("GameStation", new Label.LabelStyle(font, Color.WHITE));
        tetrisButton = new TextButton("Tetris", buttonStyle);
        carrerasButton = new TextButton("Carreras", buttonStyle);

        // Agregar listeners a los botones
        tetrisButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Botón Tetris presionado.");
            }
        });

        carrerasButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Botón Carrera presionado.");
            }
        });

        // Agregar elementos a la tabla
        table.add(titleLabel).colspan(2).padTop(50).expandY().center();
        table.row();
        table.add(tetrisButton).width(200).height(80).padBottom(20).center();
        table.row();
        table.add(carrerasButton).width(200).height(80).center();
    }

    @Override
    public void render () {
        Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.draw();
    }

    @Override
    public void dispose () {
        stage.dispose();
    }
}