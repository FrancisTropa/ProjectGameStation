package org.GameStation.CarreraPackage;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import org.GameStation.AlmacenamientoPuntaje;
import org.GameStation.Juego;
import org.GameStation.JuegoBase;
import org.GameStation.PantallaGameOver;

import java.io.File;

public class Carrera extends JuegoBase {

    private SpriteBatch batch;
    private Texture texturaCarro;
    private Texture texturaObstaculo;
    private Carro carro;
    private Barrera[] barreras;
    private ShapeRenderer shapeRenderer;
    private int colisiones;
    private BitmapFont fuentePuntuacion;
    private float tiempoSinColision;
    private Obstaculo obstaculo;
    private String rutaSprites = "src/main/java/org/GameStation/imagenes/";


    public Carrera(Juego juego) {
        super(juego);
    }

    @Override
    public void show() {
        batch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();
        texturaCarro = new Texture(rutaSprites+"carro.png");
        texturaObstaculo = new Texture(rutaSprites+"cono.png");
        carro = new Carro(texturaCarro);
        obstaculo = new Obstaculo(texturaObstaculo);
        colisiones = 3;
        puntuacion = 0;
        barreras = new Barrera[2];

        crearFuente();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        crearBarreras();

        manejoTeclado();
        carro.update(delta);

        renderizarBarreras();

        batch.begin();

        fuentePuntuacion.setColor(Color.WHITE);
        fuentePuntuacion.draw(batch, "Puntuación: " + puntuacion, 10f, Gdx.graphics.getHeight() - 10f);

        obstaculo.update(delta);
        obstaculo.render(batch);

        carro.render(batch);

        batch.end();

        detectarColisionObstaculo(obstaculo);
        detectarColision(delta);

        comprobarTermino();
    }

    private void crearFuente(){
        fuentePuntuacion = new BitmapFont();
        fuentePuntuacion.getData().setScale(2);
    }

    private void detectarColisionObstaculo(Obstaculo obstaculo){
        if (carro.getRectanguloColision().overlaps(obstaculo.getRectanguloColision())){
            verificarTiempo();
        }
    }


    private void detectarColision(float delta) {
        if (carro.getRectanguloColision().overlaps(barreras[0].getRectanguloColision()) ||
                carro.getRectanguloColision().overlaps(barreras[1].getRectanguloColision())) {
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

    private void crearBarreras() {
        for (int i = 0; i<barreras.length; i++){
            barreras[i] = new Barrera();
        }
        definirBarreras();
    }

    private void definirBarreras() {
        barreras[0].definirBarreraIzq();
        barreras[1].definirBarreraDer();
    }

    private void renderizarBarreras(){
        for (Barrera barrera : barreras) {
            barrera.render(shapeRenderer);
        }
    }

    @Override
    protected void comprobarTermino() {
        if (juegoTerminado){
            irAlArchivo();
            Juego.PantallaJuego pantallaGameOver = new PantallaGameOver(juego, this);
            juego.setScreen(pantallaGameOver);
        }
    }

    @Override
    protected void irAlArchivo() {
        File archivo = new File("archivos/puntajes.json");
        if(archivo.exists()){
            comprobarSiHayDatos();
        }else {
            AlmacenamientoPuntaje.crearArchivo("archivos/puntajes.json", this.getClass().getSimpleName(), this.puntuacion);
        }
    }

    @Override
    protected void comprobarSiHayDatos() {
        if (AlmacenamientoPuntaje.comprobarExistenciaDato("archivos/puntajes.json", this.getClass().getSimpleName())){
            comprobarPuntajeMayor();
        }else {
            agregarDatos();
        }
    }

    @Override
    protected void agregarDatos() {
        AlmacenamientoPuntaje.agregarDatoAlArchivo("archivos/puntajes.json", this.getClass().getSimpleName(), this.puntuacion);
    }

    @Override
    protected void comprobarPuntajeMayor() {
        if (AlmacenamientoPuntaje.ObtenerPuntaje("archivos/puntajes.json", this.getClass().getSimpleName()) < this.puntuacion){
            AlmacenamientoPuntaje.sobreescribirArchivo("archivos/puntajes.json", this.getClass().getSimpleName(), this.puntuacion);
        }
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
