package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Array;
import com.opencsv.*;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.MyGdxGame;
import com.opencsv.exceptions.CsvValidationException;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Comparator;

public class Placar implements Screen {
    private MyGdxGame jogo;
    private SpriteBatch batch;
    private Stage stage;
    private ImageButton btVoltar;
    private Texture background;
    private OrthographicCamera camera;
    private Viewport ViewPortCamera;
    private BitmapFont font;
    private Array<Jogador> jogadores;

    public Placar(MyGdxGame jogo) {
        this.jogo = jogo;
        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        ViewPortCamera = new StretchViewport(800, 480, camera);

        background = new Texture(Gdx.files.internal("MenuPrincipal/FundoIA.png"));
        font = new BitmapFont();
        jogadores = carregarJogadores();
    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        btVoltar = new ImageButton(new TextureRegionDrawable(new Texture("MenuPrincipal/btnVoltar.png")));
        btVoltar.setSize(150f, 50f);
        btVoltar.setPosition(20, Gdx.graphics.getHeight() - 70);

        btVoltar.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                jogo.setScreen(new MenuPrincipal(jogo));
                dispose();
            }
        });

        stage.addActor(btVoltar);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.getBatch().begin();
        stage.getBatch().draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        stage.getBatch().end();

        exibirPlacar();

        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
    }

    private void exibirPlacar() {
        Table table = new Table();
        table.setFillParent(true);
        table.top().padTop(120f);

        Label.LabelStyle labelStyle = new Label.LabelStyle(font, Color.WHITE);

        Label labelTitulo = new Label("PLACAR", labelStyle);
        labelTitulo.setFontScale(2f);
        table.add(labelTitulo).colspan(3).padBottom(20f).row();

        table.add(new Label("NOME", labelStyle)).padRight(50f);
        table.add(new Label("PONTOS", labelStyle)).padRight(50f);
        table.add(new Label("TEMPO", labelStyle)).padRight(50f);
        table.add(new Label("ACERTOS", labelStyle)).padBottom(20f).row();

        for (Jogador jogador : jogadores) {
            table.add(new Label(jogador.getNome(), labelStyle)).padRight(50f);
            table.add(new Label(String.valueOf(jogador.getPontos()), labelStyle)).padRight(50f);
            table.add(new Label(String.valueOf(jogador.getTempo()), labelStyle)).padRight(50f);
            table.add(new Label(String.valueOf(jogador.getAcertos()), labelStyle)).padBottom(20f).row();
        }

        stage.addActor(table);
    }

    private Array<Jogador> carregarJogadores() {
        Array<Jogador> jogadores = new Array<>();

        File file = Gdx.files.local("LeaderBoard.csv").file();
        CSVReader reader = null;

        try {
            reader = new CSVReaderBuilder(new FileReader(file)).withSkipLines(1).build();

            String[] values;
            while ((values = reader.readNext()) != null) {
                String nome = values[0];
                int tempo = Integer.parseInt(values[1]);
                int pontos = Integer.parseInt(values[2]);
                int acertos = Integer.parseInt(values[3]);

                Jogador jogador = new Jogador(nome, tempo, pontos, acertos);
                jogadores.add(jogador);
            }
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        // Ordenar os jogadores pelo número de pontos (do maior para o menor)
        jogadores.sort(new Comparator<Jogador>() {
            @Override
            public int compare(Jogador j1, Jogador j2) {
                return Integer.compare(j2.getPontos(), j1.getPontos());
            }
        });

        return jogadores;
    }
    @Override
    public void resize(int width, int height) {
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

    private class Jogador {
        private String nome;
        private int tempo;
        private int pontos;
        private int acertos;

        public Jogador(String nome, int tempo, int pontos, int acertos) {
            this.nome = nome;
            this.tempo = tempo;
            this.pontos = pontos;
            this.acertos = acertos;
        }

        public String getNome() {
            return nome;
        }

        public int getTempo() {
            return tempo;
        }

        public int getPontos() {
            return pontos;
        }

        public int getAcertos() {
            return acertos;
        }
    }
}
