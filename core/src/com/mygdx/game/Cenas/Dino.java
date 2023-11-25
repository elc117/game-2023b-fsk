package com.mygdx.game.Cenas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Gdx2DPixmap;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Screens.Variaveis;

public class Dino {
    Texture textureDino;

    Vector2 velocity = new Vector2();
    // Posições de localização e tamanho usando os dados de um retangulo
    Rectangle retangulo = new Rectangle(Variaveis.DinoX, Variaveis.DinoY, 40, 40);

    public void create() {
        textureDino = new Texture(Gdx.files.internal("Textures/dino.png"));
    }

    public void draw(SpriteBatch batch) {

        if (!Variaveis.perdeu) {
            velocity.y -= Variaveis.Gravity; //Velocidade cai
            retangulo.y += velocity.y; //Associa a nova velocidade ao retangulo, assim é usado no futuro

            // Pular
            if (Gdx.input.justTouched()) {
                velocity.y = Variaveis.DinoAntigravity;
            }
        }

        //Delimita o tamanho
        if (retangulo.y >= Gdx.graphics.getHeight() - 40 || retangulo.y <= Variaveis.floorHeight) {
            // Perde o jogo
            Variaveis.setPerdeu(true);
        }

        batch.draw(textureDino, retangulo.x, retangulo.y);
    }

    void dispose() {
        textureDino.dispose();
    }
    public Rectangle getDinoRectangle() {
        return this.retangulo;
    }


}
