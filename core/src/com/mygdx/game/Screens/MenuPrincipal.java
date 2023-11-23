package com.mygdx.game.Screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.MyGdxGame;

public class MenuPrincipal implements Screen {
    private MyGdxGame jogo;
    private SpriteBatch batch;
    private Stage stage;
    private ImageButton btIniciar;
    private ImageButton btPlacar;
    Texture background;
    private OrthographicCamera camera;
    private Viewport ViewPortCamera;
    private TextureRegionDrawable btIniciarImage;
    private TextureRegionDrawable btPlacarImage;



    public MenuPrincipal(MyGdxGame jogo) {
        batch = new SpriteBatch();
        this.jogo = jogo;
        // Cria sistema de câmera e resize
        camera = new OrthographicCamera();
        ViewPortCamera = new StretchViewport(800, 480, camera);

        background = new Texture(Gdx.files.internal("MenuPrincipal/FundoIA.png"));

        // Carrega as texturas dos botões e labels
        btIniciarImage = new TextureRegionDrawable(new Texture("MenuPrincipal/btnJogar.png"));
        btPlacarImage = new TextureRegionDrawable(new Texture("MenuPrincipal/btnPlacar.png"));

    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        // Cria ImageButtons para os botões
        btIniciar = new ImageButton(btIniciarImage);
        btPlacar = new ImageButton(btPlacarImage);

        // Define as posições dos botões considerando tamanhos e espaçamento


        float buttonWidth = 150f;
        float buttonHeight = 50f;


        float centerX = Gdx.graphics.getWidth() / 2f - buttonWidth / 2;
        float centerY = Gdx.graphics.getHeight() / 2f - buttonHeight / 2;

        btIniciar.setSize(buttonWidth, buttonHeight);
        btIniciar.setPosition(centerX, centerY);

        btPlacar.setSize(buttonWidth, buttonHeight);
        btPlacar.setPosition(centerX, centerY - 100);


        // Imagem do titulo "Quarta Colonia"
        Image titleImage = new Image(new Texture("MenuPrincipal/LabelQuartaColonia.png"));
        titleImage.setSize(421f, 84f); // Ajuste o tamanho da imagem conforme necessário
        float titleX = Gdx.graphics.getWidth() / 2f - titleImage.getWidth() / 2;
        float titleY = Gdx.graphics.getHeight() - 120; // Ajuste a posição vertical conforme necessário
        titleImage.setPosition(titleX, titleY);
        // Adiciona os ImageButtons à cena
        stage.addActor(btIniciar);
        stage.addActor(btPlacar);
        stage.addActor(titleImage);
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
        if (btIniciar.isPressed()) {
            jogo.setScreen(new PlayGame(jogo));
        }

        if (btPlacar.isPressed()) {
            // Adicionar o menu de placar
        }
    }
}