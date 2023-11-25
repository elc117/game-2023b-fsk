package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Cenas.Floor;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.Cenas.*;
import com.mygdx.game.Question.Quiz;

import java.util.ArrayList;

public class PlayGame implements Screen {
    private MyGdxGame jogo;
    public SpriteBatch batch;
    private Hud hud;
    private Dino dino;
    private ArrayList<Obstaculo> obstaculos;
    private ArrayList<Floor> floors;
    private ArrayList<BackgroundImage> backgrounds;
    private ArrayList<Sombras> sombras;

    private Quiz quiz;
    Texture background;

    ParticleEffect particle;

    public PlayGame(MyGdxGame jogo) {
        this.jogo = jogo;
        batch = new SpriteBatch();
        hud = new Hud();

        background = new Texture(Gdx.files.internal("Images/0.png"));

        obstaculos = new ArrayList<Obstaculo>();

        floors = new ArrayList<Floor>();

        backgrounds = new ArrayList<BackgroundImage>();

        sombras = new ArrayList<Sombras>();

        this.quiz = new Quiz();

        dino = new Dino();
        dino.create();
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();

        batch.draw(background, 0, 0);  // Adiciona background

        addCenario(); // Adiciona todos os objetos, caso não existir colisão

        dino.draw(batch); // Adiciona personagem dino

        hud.draw(batch); // Adiciona o HUD

        controlQuiz();

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

    private void addCenario() {

        // Verifica colisões, caso ainda não tenha ocorrido
        if(!Variaveis.perdeu) {
            verifyColisions();
        } else {
            //  this.dispose();
            jogo.setScreen(new MenuGameOver(jogo, this.hud));
        }

        // Adiciona os backgrounds
        for (BackgroundImage image: backgrounds) {
            image.draw(batch);
        }

        // Adiciona as sombras
        addSombras();
        for (Sombras s : sombras) {
            s.draw(batch);
        }

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
                        Variaveis.pontos += 10;
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
                if (i.getPosition() + 800 <= 0) {
                    floors.add(new Floor(800));
                    floors.remove(i);
                    break;
                }
            }
        } else {
            floors.add(new Floor());
            floors.add(new Floor(800));
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

    private void controlQuiz() {
        if (!quiz.virifyColisions(dino.getDinoRectangle())) {
            quiz.draw(batch);
        }
    }
}
