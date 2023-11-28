package com.mygdx.game.Screens;

import com.badlogic.gdx.Audio;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.TimeUtils;
import com.mygdx.game.Cenas.Floor;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.Cenas.*;
import com.mygdx.game.Question.Quiz;
import java.util.ArrayList;

public class PlayGame implements Screen {
    Sound audioColision;
    private OrthographicCamera camera;
    private MyGdxGame jogo;
    public SpriteBatch batch;
    private Hud hud;
    private Dino dino;
    private ArrayList<Obstaculo> obstaculos;
    private ArrayList<Floor> floors;
    private ArrayList<Sombras> sombras;
    private ArrayList<Quiz> perguntas;
    private long tempoInicial;
    Texture background;
    ParticleEffect particle;

    public PlayGame(MyGdxGame jogo) {
        this.jogo = jogo;
        batch = new SpriteBatch();
        hud = new Hud();
        Variaveis.acertos = 0;
        Variaveis.pontos = 0;
        Variaveis.perdeu = false;

        this.tempoInicial = TimeUtils.millis();

        camera = new OrthographicCamera(Variaveis.WIDTH, Variaveis.HEIGTH);
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
        camera.update();

        background = new Texture(Gdx.files.internal("Images/0.png"));

        audioColision = Gdx.audio.newSound(Gdx.files.internal("Sounds/colision.ogg"));

        obstaculos = new ArrayList<Obstaculo>();

        floors = new ArrayList<Floor>();

        sombras = new ArrayList<Sombras>();

        perguntas = new ArrayList<Quiz>();
        perguntas.add(new Quiz());

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
        batch.setProjectionMatrix(camera.combined);

        // Atualizar a camera
        controlCamera();

        batch.draw(background, 0, 0);  // Adiciona background

        addCenario(); // Adiciona todos os objetos, caso não existir colisão

        dino.draw(batch); // Adiciona personagem dino

        hud.draw(batch); // Adiciona o HUD

        controlQuiz(); // Controla as perguntas

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
        perguntas.clear();
        background.dispose();
        batch.dispose();
    }
    private void addCenario() {
        // Verifica colisões, caso ainda não tenha ocorrido
        if(!Variaveis.perdeu) {
            verifyColisions();
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
                if (Variaveis.addNextObstacle)
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
            if (Variaveis.addNextObstacle)
                obstaculos.add(new Obstaculo());
        }
    }
    private void addNextFloor() {
        // Remove da lista os não visiveis e adiciona outro, quando necessário.
        if (!floors.isEmpty()) {
            for (Floor i: floors) {
                if (i.getPosition() + Variaveis.WIDTH <= 0) {
                    floors.add(new Floor(Variaveis.WIDTH));
                    floors.remove(i);
                    break;
                }
            }
        } else {
            floors.add(new Floor());
            floors.add(new Floor(Variaveis.WIDTH));
        }
    }
    private void addSombras() {
        if(!sombras.isEmpty()) {
            for (Sombras s: sombras) {
                if(s.getPosition() + Variaveis.WIDTH <= Variaveis.Velocity) {
                    sombras.add(new Sombras(Variaveis.WIDTH));
                    sombras.remove(s);
                    break;
                }
            }
        } else {
            sombras.add(new Sombras());
            sombras.add(new Sombras(Variaveis.WIDTH));
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
        int last = perguntas.size() - 1;
        if ((float) (TimeUtils.millis() - this.tempoInicial) / 1000 >= Variaveis.tempoEntrePerguntas && perguntas.get(last).getPosition() > 0) {
            if (perguntas.get(last).getPosition() > 0 && !perguntas.get(last).virifyColisions(dino.getDinoRectangle())) {
                Variaveis.addNextObstacle = false;
                perguntas.get(last).draw(batch);
            } else {
                Variaveis.addNextObstacle = true;
                this.tempoInicial = TimeUtils.millis();
                perguntas.get(last).setActive(false);
                perguntas.add(new Quiz());
            }

        } else {
            Variaveis.addNextObstacle = true;
        }

        // Mantem no maximo 2 no vetor
        if (perguntas.size() > 1) {
            perguntas.remove(perguntas.get(0));
        }
    }
    private void controlCamera() {
        if (Variaveis.perdeu) {
            if (Gdx.graphics.getDeltaTime() > 0.002) {
                audioColision.play(Variaveis.SoundVolume);

                jogo.setScreen(new MenuGameOver(jogo, hud));
            }
        }
    }
}
