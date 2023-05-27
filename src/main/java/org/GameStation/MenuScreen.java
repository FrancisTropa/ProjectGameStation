package org.GameStation;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class MenuScreen extends Juego.PantallaJuego {

    Stage stage;
    Table tabla;
    Label titulo;
    TextButton BotonTetris;
    TextButton BotonCarrera;

    public MenuScreen(Juego juego) {
        super(juego);
    }

    @Override
    public void show() {
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        tabla = new Table();
        tabla.setFillParent(true);
        stage.addActor(tabla);

        // Configurar fuente y estilo de los botones
        BitmapFont font = new BitmapFont();
        TextButton.TextButtonStyle buttonStyle = new TextButton.TextButtonStyle();
        confBoton(buttonStyle, font);

        // Crear elementos de la interfaz gráfica
        crearElementos(tabla, buttonStyle, font);

        // Agregar listeners a los botones
        agregarListerner();

        // Agregar elementos a la tabla
        agregarATabla();

    }

    private void agregarListerner() {
        BotonTetris.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Botón Tetris presionado.");
            }
        });

        BotonCarrera.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Juego.PantallaJuego carrera = new Carrera(juego);
                juego.setScreen(carrera);
            }
        });
    }

    private void agregarATabla() {
        tabla.add(titulo).colspan(2).padTop(50).expandY().center();
        tabla.row();
        tabla.add(BotonTetris).width(200).height(80).padBottom(20).center();
        tabla.row();
        tabla.add(BotonCarrera).width(200).height(80).center();
    }

    private void crearElementos(Table tabla, TextButton.TextButtonStyle buttonStyle, BitmapFont font) {
        titulo = new Label("GameStation", new Label.LabelStyle(font, Color.WHITE));
        BotonTetris = new TextButton("Tetris", buttonStyle);
        BotonCarrera = new TextButton("Carreras", buttonStyle);
    }

    public void confBoton(TextButton.TextButtonStyle buttonStyle, BitmapFont font){
        buttonStyle.font = font;
        buttonStyle.fontColor = Color.CYAN;
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(delta);
        stage.draw();
    }

    @Override
    public void dispose() {

        stage.dispose();
    }
}