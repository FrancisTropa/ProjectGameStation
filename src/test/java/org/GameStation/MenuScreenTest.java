package org.GameStation;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MenuScreenTest {
    private Juego juego;
    private MenuScreen menuScreen;

    @BeforeEach
    void setup() {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.title = "GameStation";
        config.width = 640;
        config.height = 480;

        juego = new Juego() {
            @Override
            public void create() {
                setScreen(menuScreen = new MenuScreen(this));
            }
        };

        new LwjglApplication(juego, config);
    }

    @Test
    void testShow(){
        menuScreen.show();
        assertAll("Verificar inicialización de elementos del menú",
                () -> assertNotNull(menuScreen.stage, "El stage no se ha inicializado correctamente"),
                () -> assertNotNull(menuScreen.tabla, "La tabla no se ha inicializado correctamente"),
                () -> assertNotNull(menuScreen.titulo, "El título no se ha creado correctamente"),
                () -> assertNotNull(menuScreen.BotonTetris, "El botón de Tetris no se ha creado correctamente"),
                () -> assertNotNull(menuScreen.BotonCarrera, "El botón de Carreras no se ha creado correctamente")
        );
    }
}