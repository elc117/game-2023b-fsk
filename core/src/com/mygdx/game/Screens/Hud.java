package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

import java.awt.*;

public class Hud {
    private long tempoCounter;
    private int pontos;
    private int acertos;
    private long tempoAnterior;

    BitmapFont bfPontos;
    BitmapFont bfAcertos;
    BitmapFont bfTempo;
    public Hud() {
       bfPontos = new BitmapFont();
       bfAcertos = new BitmapFont();
       bfTempo = new BitmapFont();
       tempoAnterior = TimeUtils.millis();

       this.pontos = 0;
       this.acertos = 0;
       this.tempoCounter = 0;
    }

    void draw(SpriteBatch batch) {
        this.tempoCounter = (TimeUtils.millis() - tempoAnterior) / 1000;



        bfPontos.draw(batch, "Pontos: "+this.pontos, 10, Gdx.graphics.getHeight() - 1);
        bfAcertos.draw(batch, "Acertos: "+this.acertos, (float)Gdx.graphics.getWidth() / 2 - 10, Gdx.graphics.getHeight() - 1);
        bfTempo.draw(batch, "Tempo: "+this.tempoCounter+"s", Gdx.graphics.getWidth() - 100, Gdx.graphics.getHeight() - 1);
    }

}
