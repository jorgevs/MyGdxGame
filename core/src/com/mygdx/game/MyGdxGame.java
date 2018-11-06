package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Logger;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.config.GameConfig;
import com.mygdx.game.util.GdxUtils;
import com.mygdx.game.util.ViewportUtils;
import com.mygdx.game.util.debug.DebugCameraController;

public class MyGdxGame extends ApplicationAdapter {
    private static final Logger LOGGER = new Logger(MyGdxGame.class.getName(), Logger.DEBUG);

    private Viewport viewport;
    // No need to create a camera because Viewport has one by default.
    // Unless we want to use one and handle it ourselves
    //private Camera camera;

    private SpriteBatch batch;
    private Texture img;

    private DebugCameraController debugCameraController;
    private ShapeRenderer shapeRenderer;

    @Override
    public void create() {
        //camera = new OrthographicCamera();
        viewport = new FitViewport(GameConfig.WORLD_WIDTH, GameConfig.WORLD_HEIGHT/*, camera*/);

        batch = new SpriteBatch();
        // This image is 256x256 pixels
        img = new Texture("badlogic.jpg");

        debugCameraController = new DebugCameraController();
        debugCameraController.setStartPosition(GameConfig.WORLD_CENTER_X, GameConfig.WORLD_CENTER_Y);
        shapeRenderer = new ShapeRenderer();
    }

    @Override
    public void resize(int width, int height) {
        //viewport.update(width, height, true);
        // Since we are adjusting the camera position with the DebugCameraController while rendering,
        // the camera position will be updated to its stored position, so there is no need to center
        // it if the window size changes.
        viewport.update(width, height);

        // prints xPPU and yPPU after resizing
        ViewportUtils.debugPixelPerUnit(viewport);
    }

    @Override
    public void render() {
        // clear screen
        GdxUtils.clearScreen();

        // Set the projection matrix (combined projection and view matrix)
        batch.setProjectionMatrix(viewport.getCamera().combined);

        // render image
        batch.begin();
        batch.draw(img, 0, 0);
        batch.end();


        // Handle input and updates camera's position values in DebugCameraController
        debugCameraController.handleDebugInput(Gdx.graphics.getDeltaTime());
        // Apply camera's position values from debugCameraController to the actual camera
        debugCameraController.applyTo((OrthographicCamera) viewport.getCamera());

        // render debug graphics
        ViewportUtils.drawGrid(viewport, shapeRenderer);

    }

    @Override
    public void dispose() {
        batch.dispose();
        img.dispose();

        shapeRenderer.dispose();
    }

}
