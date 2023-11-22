package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Gdx2DPixmap;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Dino {
    Texture textureDino;

    Vector2 velocity = new Vector2();
    float gravity = 0.1f;
    // Posições de localização e tamanho usando os dados de um retangulo
    Rectangle retangulo = new Rectangle(0, (float)Gdx.graphics.getHeight() / 2, 40, 40);

    void create() {
        textureDino = new Texture(Gdx.files.internal("Textures/dino.png"));
    }

    void draw(SpriteBatch batch) {
        velocity.y -= gravity; //Velocidade cai
        retangulo.y += velocity.y; //Associa a nova velocidade ao retangulo, assim é usado no futuro

        batch.draw(textureDino, retangulo.x, retangulo.y);
    }
}
