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
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
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


        // Imagem do titulo "ScoreBoard"
        Image titlePlacar = new Image(new Texture("MenuPrincipal/layoutPlacar.png"));
        titlePlacar.setSize(500f, 330f); // Ajuste o tamanho da imagem conforme necessário
        float titleScoreX = Gdx.graphics.getWidth() / 2f - titlePlacar.getWidth() / 2;
        float titleScoreY = Gdx.graphics.getHeight() - 420f; // Ajuste a posição vertical conforme necessário
        titlePlacar.setPosition(titleScoreX, titleScoreY);
        stage.addActor(btVoltar);
        stage.addActor(titlePlacar);
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
        table.top().padTop(170f);

        Label.LabelStyle labelStyle = new Label.LabelStyle(font, Color.WHITE);

        int maxRows = 10;
        int displayedRows = Math.min(jogadores.size, maxRows);

        float espacamento = 5f;  // Ajuste o valor conforme necessário para o espaçamento desejado

        for (int i = 0; i < displayedRows; i++) {
            Jogador jogador = jogadores.get(i);

            Label nomeLabel = new Label(jogador.getNome(), labelStyle);
            table.add(nomeLabel).padBottom(espacamento).padLeft(-10f);  // Ajuste o valor conforme necessário para o espaçamento horizontal

            Label pontosLabel = new Label(String.valueOf(jogador.getPontos()), labelStyle);
            table.add(pontosLabel).padBottom(espacamento).padLeft(100f);  // Ajuste o valor conforme necessário para o espaçamento horizontal

            Label tempoLabel = new Label(String.valueOf(jogador.getTempo()) + "s", labelStyle);
            table.add(tempoLabel).padBottom(espacamento).padLeft(110f);  // Ajuste o valor conforme necessário para o espaçamento horizontal

            Label acertosLabel = new Label(String.valueOf(jogador.getAcertos()), labelStyle);
            table.add(acertosLabel).padBottom(espacamento).padLeft(110f);  // Ajuste o valor conforme necessário para o espaçamento horizontal

            // Adiciona uma nova linha para a próxima entrada
            table.row();
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
                int tempoEmSegundos = Integer.parseInt(values[1]);
                int pontos = Integer.parseInt(values[2]);
                int acertos = Integer.parseInt(values[3]);

                // Convertendo tempo de segundos para minutos
                int tempoEmMinutos = tempoEmSegundos / 60;

                Jogador jogador = new Jogador(nome, tempoEmMinutos, pontos, acertos);
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
