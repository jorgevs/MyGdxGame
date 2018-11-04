package com.mygdx.game.desktop;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.utils.Logger;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.config.GameConfig;

public class DesktopLauncher {
    private static final Logger LOGGER = new Logger(DesktopLauncher.class.getName(), Logger.DEBUG);

    public static void main(String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.width = (int) GameConfig.WIDTH;
        config.height = (int) GameConfig.HEIGHT;
        new LwjglApplication(new MyGdxGame(), config);

        Gdx.app.setLogLevel(Application.LOG_DEBUG);
        LOGGER.info(">> LOGGER LEVEL: " + Gdx.app.getLogLevel());
    }
}
