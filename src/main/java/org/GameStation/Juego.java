package org.GameStation;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.ScreenAdapter;

public abstract class Juego extends Game {

    protected abstract static class PantallaJuego extends ScreenAdapter {
        protected Juego juego;

        public PantallaJuego(Juego juego) {
            this.juego = juego;
        }
    }

    @Override
    public void create() {
        setScreen(new MenuScreen(this));
    }
}