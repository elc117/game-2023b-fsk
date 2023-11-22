package com.mygdx.game.Screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.utils.viewport.ScalingViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.Cenas.Hud;
import com.mygdx.game.MyGdxGame;

public class MenuPrincipal implements Screen {
    private MyGdxGame jogo;
    private SpriteBatch batch;
    private Stage stage;
    private TextButton btIniciar;
    private TextButton btPlacar;
    Texture background;
    private OrthographicCamera camera;
    private Viewport ViewPortCamera;

    public MenuPrincipal(MyGdxGame jogo) {
        batch = new SpriteBatch();
        this.jogo = jogo;
        //Cria sistema de camera e resize
        camera = new OrthographicCamera();
        ViewPortCamera = new StretchViewport(800, 480, camera);

        background = new Texture(Gdx.files.internal("MenuPrincipal/FundoIA.png"));

    }
    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        TextButton.TextButtonStyle estilo = new TextButton.TextButtonStyle();
        estilo.font = new BitmapFont();
        estilo.fontColor = Color.CYAN;
        estilo.up = createButtonBackground(Color.DARK_GRAY);
        estilo.down = createButtonBackground(Color.GRAY);

        // Cria o botao sde inicio
        btIniciar = new TextButton("Iniciar", estilo);
        btPlacar = new TextButton("Placar", estilo);
        btIniciar.setPosition((float)Gdx.graphics.getWidth() / 2, (float)Gdx.graphics.getHeight() / 2 - 40);
        btIniciar.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y){
                jogo.setScreen(new PlayGame(jogo));
                dispose();
            }
        });
        btPlacar.setPosition((float) Gdx.graphics.getWidth() / 2, (float)Gdx.graphics.getHeight() / 2);

        stage.addActor(btIniciar);
        stage.addActor(btPlacar);

    }

    private Drawable createButtonBackground(Color color) {
        Pixmap pixmap = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        pixmap.setColor(color);
        pixmap.fill();

        // Crie um drawable com base na pixmap
        Texture texture = new Texture(pixmap);
        pixmap.dispose(); // Libere os recursos da pixmap

        return new TextureRegionDrawable(new TextureRegion(texture));
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.getBatch().begin();
        stage.getBatch().draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        stage.getBatch().end();

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
}
