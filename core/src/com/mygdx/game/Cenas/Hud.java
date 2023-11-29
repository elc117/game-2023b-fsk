package com.mygdx.game.Cenas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.TimeUtils;
import com.mygdx.game.Screens.Variaveis;


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

    public void draw(SpriteBatch batch) {
        if (!Variaveis.perdeu)
            this.tempoCounter = (TimeUtils.millis() - tempoAnterior) / 1000;

        bfPontos.draw(batch, "Pontos: " + Variaveis.pontos, 10, Gdx.graphics.getHeight() - 1);
        bfAcertos.draw(batch, "Acertos: " + Variaveis.acertos, (float) Gdx.graphics.getWidth() / 2 - 10, Gdx.graphics.getHeight() - 1);
        bfTempo.draw(batch, "Tempo: " + this.tempoCounter + "s", Gdx.graphics.getWidth() - 100, Gdx.graphics.getHeight() - 1);
    }


    //Getters and Setters
    public void setPontos(int pontos) {
        this.pontos = pontos;
    }

    public int getPontos() {
        return this.pontos;
    }

    public void setAcertos(int acertos) {
        this.acertos = acertos;
    }

    public int getAcertos() {
        return this.acertos;
    }

    public void setTempoCounter(long tempoCounter) {
        this.tempoCounter = tempoCounter;
    }

    public long getTempoCounter() {
        return this.tempoCounter;
    }


}
