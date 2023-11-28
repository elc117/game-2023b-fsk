package com.mygdx.game.Screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.Cenas.Hud;
import com.mygdx.game.MyGdxGame;

public class MenuGameOver implements Screen {
    private MyGdxGame jogo;
    private SpriteBatch batch;
    private Stage stage;
    private ImageButton btRestart;
    private ImageButton btAddLeaderBoard;
    Texture background;
    private OrthographicCamera camera;
    private Viewport ViewPortCamera;
    private TextureRegionDrawable btRestartImage;
    private TextureRegionDrawable btAddLeaderBoardImage;
    private Hud hud;
    private ImageButton btMenu;

    public MenuGameOver(MyGdxGame jogo, Hud hud) {
        batch = new SpriteBatch();
        this.jogo = jogo;
        this.hud = hud;
        batch = new SpriteBatch();

        Variaveis.setPerdeu(false);

        // Cria sistema de câmera e resize
        camera = new OrthographicCamera();
        ViewPortCamera = new StretchViewport(Variaveis.WIDTH, Variaveis.HEIGTH, camera);

        background = new Texture(Gdx.files.internal("MenuPrincipal/FundoIA.png"));

        // Carrega as texturas dos botões e labels

        btRestartImage = new TextureRegionDrawable(new Texture("MenuGameOver/BtnJogarNovamente.png"));

        btAddLeaderBoardImage = new TextureRegionDrawable(new Texture("MenuGameOver/BtnAddLeaderBoard.png"));
    }
    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        // Cria ImageButtons para os botões
        btRestart = new ImageButton(btRestartImage);
        btAddLeaderBoard = new ImageButton(btAddLeaderBoardImage);

        //Botão para voltar ao menu inicial
        btMenu= new ImageButton(new TextureRegionDrawable(new Texture("MenuGameOver/btnMenu.png")));
        btMenu.setSize(150f, 50f);
        btMenu.setPosition(20, Gdx.graphics.getHeight() - 70);

        btMenu.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                jogo.setScreen(new MenuPrincipal(jogo));
                dispose();
            }
        });
        // Define as posições dos botões considerando tamanhos e espaçamento

        float buttonWidth = 200f;
        float buttonHeight = 70f;


        float centerX = Gdx.graphics.getWidth() / 2f - buttonWidth / 2;
        float centerY = Gdx.graphics.getHeight() / 2f - buttonHeight / 2;

        btRestart.setSize(buttonWidth, buttonHeight);
        btRestart.setPosition(centerX - 150, centerY - 140);

        btAddLeaderBoard.setSize(buttonWidth, buttonHeight);
        btAddLeaderBoard.setPosition(centerX + 150, centerY - 140);

        // Imagem do titulo "Quarta Colonia"
        Image titleImage = new Image(new Texture("MenuGameOver/LabelGameOver.png"));
        titleImage.setSize(421f, 84f); // Ajuste o tamanho da imagem conforme necessário
        float titleX = Gdx.graphics.getWidth() / 2f - titleImage.getWidth() / 2;
        float titleY = Gdx.graphics.getHeight() - 120; // Ajuste a posição vertical conforme necessário
        titleImage.setPosition(titleX, titleY);

        // Imagem do titulo "ScoreBoard"
        Image titleScore = new Image(new Texture("MenuGameOver/ScoreBoard.png"));
        titleScore.setSize(350f, 144f); // Ajuste o tamanho da imagem conforme necessário
        float titleScoreX = Gdx.graphics.getWidth() / 2f - titleScore.getWidth() / 2 - 25;
        float titleScoreY = Gdx.graphics.getHeight() - 310; // Ajuste a posição vertical conforme necessário
        titleScore.setPosition(titleScoreX, titleScoreY);

        // Labels de pontuação, acertos e tempo
        Label.LabelStyle style = new Label.LabelStyle(new BitmapFont(), Color.WHITE);
        style.font.getData().setScale(2f);

        Label pontosLabel = new Label(String.valueOf(Variaveis.pontos), style);
        pontosLabel.setPosition(titleScoreX + 165, titleScoreY + 60);

        Label acertosLabel = new Label(String.valueOf(Variaveis.acertos), style);
        acertosLabel.setPosition(titleScoreX + 175, titleScoreY + 23);

        Label tempoLabel = new Label(String.valueOf(hud.getTempoCounter()) + " sec", style);
        tempoLabel.setPosition(titleScoreX + 160, titleScoreY + 100);

        // Adiciona os ImageButtons e titles à cena
        stage.addActor(btRestart);
        stage.addActor(btAddLeaderBoard);
        stage.addActor(btMenu);
        stage.addActor(titleImage);
        stage.addActor(titleScore);
        stage.addActor(pontosLabel);
        stage.addActor(acertosLabel);
        stage.addActor(tempoLabel);
    }
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.getBatch().begin();
        stage.getBatch().draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        stage.getBatch().end();

        this.visualization();

        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
    }
    @Override
    public void resize(int width, int height) {
        // Aplica a configuração definida de resize
        ViewPortCamera.update(width, height);
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
        stage.dispose();
        background.dispose();
        batch.dispose();
    }
    private void visualization(){
        Variaveis.setPerdeu(false);
        if (btRestart.isPressed()) {
            this.dispose();
            Variaveis.pontos = 0; // Reinicia toda a pontuação
            Variaveis.acertos = 0; // Reinicia todos os acertos
            jogo.setScreen(new PlayGame(jogo));
        }
        if (btAddLeaderBoard.isPressed()) {
            this.dispose();
            jogo.setScreen(new MenuPrincipal(jogo));
        }
    }
}