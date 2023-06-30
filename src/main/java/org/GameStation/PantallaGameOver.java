package org.GameStation;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class PantallaGameOver extends Juego.PantallaJuego{
    Stage stage;
    Table tabla;
    Label titulo;
    TextButton botonReiniciar;
    TextButton botonSalirMenu;
    private OpcionesJuego juegoActual;

    public PantallaGameOver(Juego juego, OpcionesJuego juegoActual){
        super(juego);
        this.juegoActual = juegoActual;
    }

    @Override
    public void show() {
        crearEsenario();
        crearElementos();
        agregarListeners();
        agregarATabla();
    }

    private void crearEsenario() {
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        tabla = new Table();
        tabla.setFillParent(true);
        stage.addActor(tabla);
    }

    private void crearElementos() {
        BitmapFont font = new BitmapFont();
        Label.LabelStyle labelStyle = new Label.LabelStyle(font, Color.WHITE);
        titulo = new Label("Game Over", labelStyle);

        TextButton.TextButtonStyle buttonStyle = new TextButton.TextButtonStyle();
        buttonStyle.font = font;
        buttonStyle.fontColor = Color.CYAN;

        botonReiniciar = new TextButton("Reiniciar", buttonStyle);
        botonSalirMenu = new TextButton("Salir al Menú", buttonStyle);
    }

    private void agregarListeners() {
        botonReiniciar.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                juegoActual.reiniciar();
            }
        });

        botonSalirMenu.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Juego.PantallaJuego menuScreen = new MenuScreen(juego);
                juegoActual.volverAlMenu(menuScreen);
            }
        });
    }

    private void agregarATabla() {
        tabla.add(titulo).colspan(2).padTop(50).expandY().center();
        tabla.row();
        tabla.add(botonReiniciar).width(Gdx.graphics.getWidth()/6.4f).height(Gdx.graphics.getWidth()/12f).padBottom(20).center();
        tabla.row();
        tabla.add(botonSalirMenu).width(Gdx.graphics.getWidth()/6.4f).height(Gdx.graphics.getWidth()/12f).center();
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
