package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.MyGdxGame;

import java.awt.datatransfer.FlavorEvent;
import java.util.ArrayList;

public class PlayGame implements Screen {
    private MyGdxGame jogo;
    SpriteBatch batch;

    private Hud hud;
    private Dino dino;
    private ArrayList<Obstaculo> obstaculos;
    private ArrayList<Floor> floors;
    private ArrayList<BackgroundImage> backgrounds;

    private ArrayList<Sombras> sombras;

    public PlayGame(MyGdxGame jogo) {
        this.jogo = jogo;
        batch = new SpriteBatch();
        hud = new Hud();

        obstaculos = new ArrayList<Obstaculo>();

        floors = new ArrayList<Floor>();

        backgrounds = new ArrayList<BackgroundImage>();

        sombras = new ArrayList<Sombras>();

        dino = new Dino();
        dino.create();
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor((float)49/255, (float)48/255, (float)23/255, 0.65f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();

        // Verifica colisões, caso ainda não tenha ocorrido
        if(!Variaveis.perdeu) {
            verifyColisions();
        } else {
            this.dispose();
            jogo.setScreen(new MenuGameOver(jogo, this.hud));

        }

        // Adiciona os backgrounds
        addBackground();
        for (BackgroundImage image: backgrounds) {
            image.draw(batch);
        }

        // Adiciona as sombras
            addSombras();
            for (Sombras s : sombras) {
                s.draw(batch);
            }
        // Adiciona personagem dino
        dino.draw(batch);

        // Adiciona obstaculos na tela
        addObstaculo();
        for (Obstaculo ob : this.obstaculos) {
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
        obstaculos.clear();
        floors.clear();
        sombras.clear();
        batch.dispose();
    }

    private void addObstaculo() {
        // Remove da lista os não visiveis
        if (!obstaculos.isEmpty()) {
            if (obstaculos.get(obstaculos.size() - 1).getPosition() + 80 < 200) {
                obstaculos.add(new Obstaculo());
            }

            if (this.obstaculos.size() > 1) {
                for (Obstaculo ob : obstaculos) {
                    if (ob.getPosition() + 90 <= 0) {
                        obstaculos.remove(ob);
                        hud.addPoint(10);
                    }
                }
            }
        } else {
            obstaculos.add(new Obstaculo());
        }
    }


    private void addNextFloor() {
        // Remove da lista os não visiveis e adiciona outro, quando necessário.
        if (!floors.isEmpty()) {
            for (Floor i: floors) {
                if ((i.getPosition() + 721 + 241) <= 480) {
                    floors.add(new Floor());
                    floors.remove(i);
                }
            }
        } else {
            floors.add(new Floor());
        }
    }

    private void addBackground() {
        if (!backgrounds.isEmpty()) {
           for (BackgroundImage image: backgrounds) {
                if (image.getPosition() + 1000 <= 0) {
                    backgrounds.add(new BackgroundImage(0));
                    backgrounds.remove(image);
                }
           }
        } else {
            backgrounds.add(new BackgroundImage(0));
        }
    }

    private void addSombras() {
        if(!sombras.isEmpty()) {
            for (Sombras s: sombras) {
                if(s.getPosition() + 800 == 0) {
                    sombras.add(new Sombras(800));
                    sombras.remove(s);
                    break;
                }
            }
        } else {
            sombras.add(new Sombras());
            sombras.add(new Sombras(800));
        }
    }


    private void verifyColisions() {
        // Percorre lista floors e obstaculos por colisões
        for (Obstaculo ob : obstaculos) {
            // Usa o overlaps para verificar colisões em ambos os ossos
            if (dino.getDinoRectangle().overlaps(ob.getOssoInferior()) || // Colisão com o osso inferior
                dino.getDinoRectangle().overlaps(ob.getOssoSuperior())) { // Colisão com o osso superior
                    // Para toda a movimentação, pois perdeu o jogo
                    Variaveis.perdeu = true;
                    break;
            }
        }
    }
}
