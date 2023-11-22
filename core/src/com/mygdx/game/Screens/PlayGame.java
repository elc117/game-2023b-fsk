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

public class PlayGame implements Screen {

    private float dinoX, dinoY;
    private long tempoAnterior;
    private Vector2 velocidade;
    private MyGdxGame jogo;
    SpriteBatch batch;
    Texture dinoTexture;

    private Sprite dino;
    private Hud hud;
    public PlayGame(MyGdxGame jogo) {
        this.jogo = jogo;
        batch = new SpriteBatch();
        velocidade = new Vector2(0, 0);

        dinoTexture = new Texture("Textures/dino.png");
        dino = new Sprite(dinoTexture);
        dino.setSize(dino.getWidth() / 1000, dino.getHeight() / 1000);
        dino.setPosition(0, (float)Gdx.graphics.getHeight() / 2);
        hud = new Hud(jogo.batch);

        dino.setPosition(0, 500);
        dino.setX(0);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        jogo.batch.setProjectionMatrix(hud.stage.getCamera().combined);

        //updatePhysics();
       // this.moveDino();
        batch.begin();
        dino.draw(batch);
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

    private void updatePhysics() {
        this.velocidade.y -= 0.5f;

        this.dino.translate(velocidade.x, velocidade.y);

        if (dino.getY() < 0) {
            dino.setY(0);
            this.velocidade.y = 0;
        } else if (dino.getY() > Gdx.graphics.getHeight()) {
            dino.setY(Gdx.graphics.getHeight());
        }
    }

}
