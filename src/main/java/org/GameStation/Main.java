package org.GameStation;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class Main {
    public static void main(String[] args) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.title = "GameStation";
        config.width = 640;
        config.height = 480;
        MenuScreen menu = new MenuScreen();
        new LwjglApplication(menu, config);
    }
}