package com.mygdx.game.Question;

import com.badlogic.gdx.Audio;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Screens.Variaveis;

import java.util.ArrayList;

// A classe Quiz é responsável por adicionar as questões na tela,
// selecionar aleatoriamente as questões e verificar se o jogador
// acertou.
public class Quiz {
    private boolean isActive = true;
    Texture texturePergunta;
    Texture textureBackgroundPergunta;
    Sound certoAudio;
    Sound erradoAudio;
    ArrayList<Rectangle> retangulos;
    ArrayList<Rectangle> ossosMeio;
    private float posicao;
    private boolean colision;
    private BitmapFont font;

    Pergunta pergunta;

    Vector2 velocity = new Vector2();

    public Quiz() {
        // Atualiza a questão
        retangulos = new ArrayList<Rectangle>();
        ossosMeio = new ArrayList<Rectangle>();

        font = new BitmapFont();

        texturePergunta = new Texture(Gdx.files.internal("Textures/Choice.png"));
        textureBackgroundPergunta = new Texture(Gdx.files.internal("Textures/backgroundChoice.png"));

        certoAudio = Gdx.audio.newSound(Gdx.files.internal("Sounds/certo.mp3"));
        erradoAudio = Gdx.audio.newSound(Gdx.files.internal("Sounds/errado.mp3"));

        this.pergunta = new Pergunta(Variaveis.lastIndex);

        colision = false;

        // Cria os pontos de referencia para as questões
        this.posicao = 38;
        for (int i = 0; i < pergunta.getNumAlternativas(); i++) {
            this.retangulos.add(new Rectangle(Gdx.graphics.getWidth(), (int)posicao, texturePergunta.getWidth(), texturePergunta.getHeight()));
            posicao += 165;
        }

        velocity.x = Variaveis.PerguntaVelocity;

        if (Variaveis.lastIndex + 1 > Variaveis.numPerguntas - 1) {
            Variaveis.lastIndex = 0;
        } else {
            Variaveis.lastIndex++;
        }
    }
    public void draw(SpriteBatch batch) {
        if (!Variaveis.perdeu) {
            for (Rectangle r : this.retangulos) {
                r.x -= velocity.x;
            }
            for (Rectangle osso : ossosMeio) {
                osso.x -= velocity.x;
            }
            // Adiciona o enunciado
            float fontPosition = ((float)Gdx.graphics.getWidth() / 2) - getCenterWidth(pergunta.getEnunciado()); // Centralização
            batch.draw(textureBackgroundPergunta, 0, 11);
            font.draw(batch, pergunta.getEnunciado(), fontPosition, 25);

            // Adiciona as alternativas
            int i = 0;
            for (Rectangle r : this.retangulos) {
                batch.draw(texturePergunta, r.x, r.y, texturePergunta.getWidth(), texturePergunta.getHeight());
                font.draw(batch, pergunta.getAlternativa(i), r.x + ((float)texturePergunta.getWidth() / 2) - (getCenterWidth(pergunta.getAlternativa(i))),
                r.y + 45);
                i++;
            }
        }
    }
    public boolean virifyColisions(Rectangle dino) {
        int i = 0;
        boolean colided = false;

        if (!colision) {
            for (Rectangle r : this.retangulos) {
                if (dino.overlaps(r)) {
                    colided = true;
                    colision = true;
                    break;
                }
                i++;
            }

            if (pergunta.getCerta() == i && colided) {
                certoAudio.play(Variaveis.SoundVolume);
                Variaveis.pontos += 100;
                Variaveis.acertos++;
            } else {
                if (Variaveis.pontos - 100 < 0 && colided) {
                    erradoAudio.play(Variaveis.SoundVolume);
                    Variaveis.pontos = 0;
                } else {
                    if (this.isActive && colided) {
                        erradoAudio.play(Variaveis.SoundVolume);
                        Variaveis.pontos -= 100;
                    }
                }
            }
        }
        return colided;
    }
    public float getPosition() {
        return this.retangulos.get(0).x;
    }
    public void setActive(boolean status) {
        this.isActive = status;
    }
    public boolean getActive() {
        return this.isActive;
    }
    private float getCenterWidth (String alternativa) {
        GlyphLayout layout = new GlyphLayout();
        layout.setText(font, alternativa);

        return layout.width / 2;
    }
}
