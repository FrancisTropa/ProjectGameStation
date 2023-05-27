package org.GameStation;

import com.badlogic.gdx.*;
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
    private Table tabla;
    private Label titulo;
    private TextButton botonTetris, botonCarrera;
    private Juego juego;

    @Override
    public void create() {
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        tabla = new Table();
        tabla.setFillParent(true);
        stage.addActor(tabla);

        // Configurar fuente y estilo de los botones
        BitmapFont font = new BitmapFont();
        TextButtonStyle buttonStyle = new TextButtonStyle();
        buttonStyle.font = font;
        buttonStyle.fontColor = Color.CYAN;

        // Crear elementos de la interfaz gráfica
        titulo = new Label("GameStation", new Label.LabelStyle(font, Color.WHITE));
        botonTetris = new TextButton("Tetris", buttonStyle);
        botonCarrera = new TextButton("Carreras", buttonStyle);

        // Agregar listeners a los botones
        botonTetris.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Botón Tetris presionado.");
            }
        });

        botonCarrera.addListener(
                new ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        juego = new Carrera();
                        juego.create();
                    }
                });

        // Agregar elementos a la tabla
        tabla.add(titulo).colspan(2).padTop(50).expandY().center();
        tabla.row();
        tabla.add(botonTetris).width(200).height(80).padBottom(20).center();
        tabla.row();
        tabla.add(botonCarrera).width(200).height(80).center();
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.draw();
    }

    @Override
    public void dispose() {
        stage.dispose();
        if (juego != null) {
            juego.dispose();
        }
    }
}