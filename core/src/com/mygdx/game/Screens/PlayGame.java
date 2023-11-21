package com.mygdx.game.Screens;

import com.badlogic.gdx.Screen;
import com.mygdx.game.Cenas.Hud;
import com.mygdx.game.MyGdxGame;

public class PlayGame implements Screen {

    private MyGdxGame jogo;
    private Hud hud;
    public PlayGame(MyGdxGame jogo) {
        this.jogo = jogo;
        hud = new Hud(jogo.batch);

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        jogo.batch.setProjectionMatrix(hud.stage.getCamera().combined);
        hud.stage.draw();
    }

    @Override
    public void resize(int width, int height) {

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
