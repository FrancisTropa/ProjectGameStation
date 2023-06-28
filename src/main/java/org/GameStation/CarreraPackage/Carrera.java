package org.GameStation.CarreraPackage;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import org.GameStation.Juego;
import org.GameStation.OpcionesJuego;
import org.GameStation.PantallaGameOver;

public class Carrera extends Juego.PantallaJuego implements OpcionesJuego {

    private SpriteBatch batch;
    private Texture texturaCarro;
    private Texture texturaObstaculo;
    private Carro carro;
    private Barrera barreraIzq, barreraDer;
    private ShapeRenderer shapeRenderer;
    private int colisiones;
    private float puntuacion;
    private BitmapFont fuentePuntuacion;
    private float tiempoSinColision;
    private boolean juegoTerminado = false;
    private Obstaculo obstaculo;


    public Carrera(Juego juego) {
        super(juego);
    }

    @Override
    public void show() {
        batch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();
        texturaCarro = new Texture("src/main/java/org/GameStation/imagenes/carro.png");
        texturaObstaculo = new Texture("src/main/java/org/GameStation/imagenes/cono.png");
        carro = new Carro(texturaCarro);
        obstaculo = new Obstaculo(texturaObstaculo);
        barreraIzq = new Barrera();
        barreraDer = new Barrera();
        colisiones = 3;
        puntuacion = 0;

        fuentePuntuacion = new BitmapFont();
        fuentePuntuacion.getData().setScale(2);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        definirBarreras();

        manejoTeclado();
        carro.update(delta);

        barreraIzq.render(shapeRenderer);
        barreraDer.render(shapeRenderer);

        batch.begin();

        fuentePuntuacion.setColor(Color.WHITE);
        fuentePuntuacion.draw(batch, "Puntuación: " + puntuacion, 10, Gdx.graphics.getHeight() - 10);

        obstaculo.update(delta);
        obstaculo.render(batch);

        carro.render(batch);

        batch.end();

        detectarColisionObstaculo(obstaculo);
        detectarColision(delta);

        comprobarTermino();
    }

    private void detectarColisionObstaculo(Obstaculo obstaculo){
        if (carro.getRectanguloColision().overlaps(obstaculo.getRectanguloColision())){
            verificarTiempo();
        }
    }

    private void comprobarTermino() {
        if (juegoTerminado){
            Juego.PantallaJuego pantallaGameOver = new PantallaGameOver(juego, this);
            juego.setScreen(pantallaGameOver);
        }
    }

    private void detectarColision(float delta) {
        if (carro.getRectanguloColision().overlaps(barreraIzq.getRectanguloColision()) ||
                carro.getRectanguloColision().overlaps(barreraDer.getRectanguloColision())) {
            verificarTiempo();
        }else {
            tiempoSinColision += delta;
            puntuacion += delta;
        }
    }

    private void verificarTiempo() {
        if (tiempoSinColision > 1.0f){
            colisiones--;
            tiempoSinColision = 0;
            comprobarColisiones();
        }
    }

    private void comprobarColisiones() {
        if (colisiones <= 0){
            juegoTerminado = true;
        }
    }

    private void definirBarreras() {
        barreraIzq.definirBarreraIzq();
        barreraDer.definirBarreraDer();
    }

    private void manejoTeclado() {
        carro.setMoverIzquierda(Gdx.input.isKeyPressed(Input.Keys.LEFT));
        carro.setMoverDerecha(Gdx.input.isKeyPressed(Input.Keys.RIGHT));
    }

    @Override
    public void dispose() {
        batch.dispose();
        texturaCarro.dispose();
        shapeRenderer.dispose();
        fuentePuntuacion.dispose();
        obstaculo.dispose();
        texturaObstaculo.dispose();
    }

    @Override
    public void reiniciar() {
        Juego.PantallaJuego carrera = new Carrera(juego);
        juego.setScreen(carrera);
    }

    @Override
    public void volverAlMenu(Juego.PantallaJuego menuScreen) {
        juego.setScreen(menuScreen);
    }
}
