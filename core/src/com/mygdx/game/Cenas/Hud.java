package com.mygdx.game.Cenas;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

import java.awt.*;

public class Hud {
    public Stage stage;
    private Viewport viewport;
    private int tempoCounter;
    private int pontos;
    private int acertos;

    Label LabelTempo;
    Label LabelTextTempo;
    Label LabelPontos;
    Label LabelTextPontos;
    Label LabelAcertos;
    Label LabelTextAcertos;

    public Hud(SpriteBatch sprite) {
        this.tempoCounter = 0;
        this.acertos = 0;
        this.pontos = 0;

        viewport = new FitViewport(800, 400, new OrthographicCamera());
        stage = new Stage(viewport, sprite);

        // Define a moldura usada
        Table formato = new Table();
        formato.top();
        formato.setFillParent(true);

        // Configura cada Label
        LabelTempo = new Label(String.format("%03d", tempoCounter), new Label.LabelStyle(new BitmapFont(), Color.GOLD));
        LabelTextTempo = new Label("Timer: ", new Label.LabelStyle(new BitmapFont(), Color.WHITE));

        LabelAcertos = new Label(String.format("%02d", acertos), new Label.LabelStyle(new BitmapFont(), (acertos > 10 ) ? Color.WHITE : Color.GOLD));
        LabelTextAcertos = new Label("Acertos: ", new Label.LabelStyle(new BitmapFont(), Color.WHITE));


        LabelPontos = new Label(String.format("%03d", pontos), new Label.LabelStyle(new BitmapFont(), Color.GOLD));
        LabelTextPontos = new Label("Acertos: ", new Label.LabelStyle(new BitmapFont(), Color.WHITE));

        formato.add(LabelTextTempo).right();
        formato.add(LabelTempo).width(60).spaceBottom(10).right();
        formato.add(LabelTextAcertos);
        formato.add(LabelAcertos).width(60).spaceBottom(10);
        formato.add(LabelTextPontos);
        formato.add(LabelPontos).width(60).spaceBottom(10);

        stage.addActor(formato);

    }

}
