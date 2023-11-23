package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.MyGdxGame;

import java.util.ArrayList;

public class PlayGame implements Screen {
    private MyGdxGame jogo;
    SpriteBatch batch;

    private Hud hud;
    private Dino dino;
    private ArrayList<Obstaculo> obstaculos;
    private ArrayList<Floor> floors;
    public PlayGame(MyGdxGame jogo) {
        this.jogo = jogo;
        batch = new SpriteBatch();
        hud = new Hud();

        obstaculos = new ArrayList<Obstaculo>();

        floors = new ArrayList<Floor>();
        floors.add(new Floor());

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
        //jogo.batch.setProjectionMatrix(hud.stage.getCamera().combined);

        addObstaculo();

        batch.begin();

        // Adiciona personagem dino
        dino.draw(batch);

        // Adiciona obstaculos
        for (Obstaculo ob: this.obstaculos) {
            ob.draw(batch);
        }

        // Adiciona o chão
        addNextFloor();
        for (Floor i: floors) {
            i.draw(batch);
        }

        // Adiciona o HUD
        hud.draw(batch);

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
        this.obstaculos.add(new Obstaculo());
    }

    public void addNextFloor() {
        // Remove da lista os não visiveis e adiciona outro, quando necessário.
        for (Floor i: floors) {
            if ((i.getPosition() - 721 + 241) <= 480) {
                floors.add(new Floor());
                floors.remove(i);
            }
        }
    }
}
