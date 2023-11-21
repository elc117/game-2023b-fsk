package com.mygdx.game;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.utils.viewport.ScalingViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class MenuPrincipal implements Screen {
    private MyGdxGame jogo;
    private OrthographicCamera camera;
    private Viewport ViewPortCamera;
    Texture texture;
    public MenuPrincipal(MyGdxGame jogo) {
        this.jogo = jogo;

        //Cria sistema de camera e resize
        camera = new OrthographicCamera();
        ViewPortCamera = new StretchViewport(800, 480, camera);
        texture = new Texture("badlogic.jpg");
    }
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        jogo.batch.begin();
        jogo.batch.draw(texture, 0, 0);
        jogo.batch.end();
    }

    @Override
    public void resize(int width, int height) {
        // Aplica a configuração definida de resize
        ViewPortCamera.update(width, height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
