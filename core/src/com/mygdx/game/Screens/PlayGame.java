package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.TimeUtils;
import com.mygdx.game.Cenas.Hud;
import com.mygdx.game.MyGdxGame;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.math.Vector2;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;

public class PlayGame implements Screen {
    private MyGdxGame jogo;
    SpriteBatch batch;

    private Hud hud;
    private Dino dino;

    private ArrayList<Obstaculo> obstaculos;
    public PlayGame(MyGdxGame jogo) {
        this.jogo = jogo;
        batch = new SpriteBatch();
        hud = new Hud(jogo.batch);

        obstaculos = new ArrayList<Obstaculo>();

        dino = new Dino();
        dino.create();
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        jogo.batch.setProjectionMatrix(hud.stage.getCamera().combined);


        addObstaculo();

        batch.begin();

        hud.stage.draw();

        // Adiciona personagem dino
        dino.draw(batch);

        // Adiciona obstaculos
        for (Obstaculo ob: this.obstaculos) {
            ob.draw(batch);
        }
        batch.end();


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
        batch.dispose();
    }

    public void addObstaculo() {
        Obstaculo obstaculo1 = new Obstaculo();
        this.obstaculos.add(obstaculo1);
    }


}
