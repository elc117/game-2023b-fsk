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

import java.sql.Time;
import java.sql.Timestamp;

public class PlayGame implements Screen {

    private float dinoX, dinoY;
    private long tempoAnterior;
    private MyGdxGame jogo;
    SpriteBatch batch;
    Texture dinoTexture;

    private Sprite dino;
    private Hud hud;
    public PlayGame(MyGdxGame jogo) {
        this.jogo = jogo;
        batch = new SpriteBatch();

        dinoTexture = new Texture("Textures/dino.png");
        dino = new Sprite(dinoTexture);
        dino.setSize(dino.getWidth() / 1000, dino.getHeight() / 1000);
        hud = new Hud(jogo.batch);

        this.dinoX = 0;
        this.dinoY = (float)Gdx.graphics.getHeight() / 2;

        Timer.schedule(new Timer.Task(){
            @Override
            public void run() {
                if (dinoY - 100 > 0)
                  dinoY -= 30;
                else
                  dinoY = 0;
            }
        }, 0.5f, 0.5f);


    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        jogo.batch.setProjectionMatrix(hud.stage.getCamera().combined);

        this.moveDino();
        batch.begin();
        batch.draw(dino, dinoX, dinoY, 35, 35);
        batch.end();

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
        batch.dispose();
        dinoTexture.dispose();

    }


    private void moveDino() {
        if (Gdx.input.justTouched())
            if (dinoY + 30 < Gdx.graphics.getHeight())
              dinoY += 30;
            else
              dinoY = Gdx.graphics.getHeight() - 30;
    }

}
