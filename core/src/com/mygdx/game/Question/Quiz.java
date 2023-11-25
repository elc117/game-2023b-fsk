package com.mygdx.game.Question;

import com.badlogic.gdx.Gdx;
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
    Texture imagePergunta;
    Texture texturePergunta;

    ArrayList<Rectangle> retangulos;
    private float posicao;
    private boolean colision;
    private BitmapFont font;

    Pergunta pergunta;

    Vector2 velocity = new Vector2();

    public Quiz() {
        // Atualiza a questão
        retangulos = new ArrayList<Rectangle>();

        font = new BitmapFont();

        texturePergunta = new Texture(Gdx.files.internal("Textures/Choice.png"));
        this.imagePergunta = new Texture(Gdx.files.internal("Images/"+1+".png"));

        this.pergunta = new Pergunta(Variaveis.lastIndex);

        colision = false;

        this.posicao = (float) (Gdx.graphics.getHeight() * 0.20);
        for (int i = 0; i < pergunta.getNumAlternativas(); i++) {
            this.retangulos.add(new Rectangle(Gdx.graphics.getWidth(), (int)posicao, 85, 60));
            posicao += 128;
        }

        velocity.x = Variaveis.Velocity;

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

            GlyphLayout layout = new GlyphLayout();
            layout.setText(font, pergunta.getEnunciado());

            float fontPosition = ((float)Gdx.graphics.getWidth() / 2) - layout.width / 2;

            font.draw(batch, pergunta.getEnunciado(), fontPosition, 30);

            for (Rectangle r : this.retangulos) {
                batch.draw(texturePergunta, r.x, r.y, 85, 60);
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

            if (pergunta.getCerta() == i) {
                Variaveis.pontos += 100;
                Variaveis.acertos++;
            } else {
                if (Variaveis.pontos - 100 < 0) {
                    Variaveis.pontos = 0;
                } else {
                    Variaveis.pontos -= 100;
                }
            }
        }

        return colided;
    }

}
