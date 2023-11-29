package com.mygdx.game.Screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
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

import java.io.FileWriter;
import java.io.IOException;

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
    private String name;
    private Image textFieldBackground;
    private TextField textField;

    private Long tempoCounter;

    public MenuGameOver(MyGdxGame jogo, Hud hud) {
        batch = new SpriteBatch();
        this.jogo = jogo;
        this.hud = hud;
        batch = new SpriteBatch();

        Variaveis.setPerdeu(false);

        // Cria sistema de câmera e resize
        camera = new OrthographicCamera();
        ViewPortCamera = new StretchViewport(Variaveis.WIDTH, Variaveis.HEIGTH, camera);

        background = new Texture(Gdx.files.internal("MainMenuScreen/FundoIA.png"));

        // Carrega as texturas dos botões e labels

        btRestartImage = new TextureRegionDrawable(new Texture("MenuGameOver/BtnJogarNovamente.png"));

        btAddLeaderBoardImage = new TextureRegionDrawable(new Texture("MenuGameOver/BtnAddLeaderBoard.png"));

        // Carrega a textura para o fundo do TextField
        Texture textFieldBgTexture = new Texture("MenuGameOver/TextFieldBackground.png"); // Substitua "path_para_o_background.png" pelo caminho real da sua textura
        TextureRegionDrawable textFieldBgDrawable = new TextureRegionDrawable(new TextureRegion(textFieldBgTexture));

        // Cria a Image para o fundo do TextField
        textFieldBackground = new Image(textFieldBgDrawable);
        textFieldBackground.setSize(220f, 40f);  // Ajuste o tamanho conforme necessário
        textFieldBackground.setPosition(Gdx.graphics.getWidth() / 2f - 110f, Gdx.graphics.getHeight() / 2f - 220f);  // Ajuste a posição conforme necessário
        TextField.TextFieldFilter filter = new TextField.TextFieldFilter() {
            @Override
            public boolean acceptChar(TextField textField, char c) {
                // Permite caracteres enquanto o comprimento do texto for menor que 10
                return textField.getText().length() < 10;
            }
        };



        textField = new TextField("", new TextField.TextFieldStyle(new BitmapFont(), Color.WHITE, null, null, null));
        textField.setSize(220f, 40f);  // Ajuste o tamanho conforme necessário
        textField.setPosition(Gdx.graphics.getWidth() / 2f - 100f, Gdx.graphics.getHeight() / 2f - 215f);  // Ajuste a posição conforme necessário
        textField.setTextFieldFilter(filter);  // Aplica o filtro
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
                jogo.setScreen(new MainMenuScreen(jogo));
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

        // Label de Nome
        Image labelNome = new Image(new Texture("MenuGameOver/LabelNome.png"));
        labelNome.setSize(58f, 15f);  // Ajuste o tamanho conforme necessário
        labelNome.setPosition(Gdx.graphics.getWidth() / 2f - 170f, Gdx.graphics.getHeight() / 2f - 205f);

        // Labels de pontuação, acertos e tempo
        Label.LabelStyle style = new Label.LabelStyle(new BitmapFont(), Color.WHITE);
        style.font.getData().setScale(2f);

        Label pontosLabel = new Label(String.valueOf(Variaveis.pontos), style);
        pontosLabel.setPosition(titleScoreX + 165, titleScoreY + 60);

        Label acertosLabel = new Label(String.valueOf(Variaveis.acertos), style);
        acertosLabel.setPosition(titleScoreX + 175, titleScoreY + 23);

        tempoCounter = hud.getTempoCounter();
        Label tempoLabel = new Label(String.valueOf(tempoCounter) + " s", style);
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
        stage.addActor(textFieldBackground);
        stage.addActor(textField);
        stage.addActor(labelNome);
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

    public void salvarLeaderboard(String nome, long tempoCounter){
        FileHandle file = Gdx.files.local("LeaderBoard.csv");
        // Verifique se o arquivo já existe
        boolean fileExists = file.exists();

        // Use FileWriter para escrever no arquivo
        try {
            FileWriter fileWriter = new FileWriter(file.file(), true); // O segundo parâmetro true indica que estamos anexando ao arquivo

            // Se o arquivo não existia antes, adicione um cabeçalho
            if (!fileExists) {
                fileWriter.append("Nome,Tempo,Pontos,Acertos\n");
            }
            // Adicione os dados ao arquivo
            fileWriter.append(nome)
                    .append(',')
                    .append(String.valueOf(tempoCounter))
                    .append(',')
                    .append(String.valueOf(Variaveis.pontos))
                    .append(',')
                    .append(String.valueOf(Variaveis.acertos))
                    .append('\n');
            // Feche o FileWriter
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void visualization() {
        Variaveis.setPerdeu(false);
        if (btRestart.isPressed()) {
            this.dispose();
            Variaveis.pontos = 0; // Reinicia toda a pontuação
            Variaveis.acertos = 0; // Reinicia todos os acertos
            Variaveis.lastIndex = 0; // Reinicia o index padrão de pergunta
            jogo.setScreen(new PlayGame(jogo));
        }
        if (btAddLeaderBoard.isPressed() && !textField.getText().isEmpty()) {
            setName(textField.getText());
            this.dispose();
            salvarLeaderboard(name, tempoCounter);
            //Falta receber o nome ainda
            jogo.setScreen(new MainMenuScreen(jogo));
        }
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}