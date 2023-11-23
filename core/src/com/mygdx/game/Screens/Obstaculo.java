package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Obstaculo {
    Texture textureParede;

    Rectangle retangulo = new Rectangle(Gdx.graphics.getWidth() + 30, 0, 20, 360);

    Vector2 velocity = new Vector2();

    public Obstaculo() {
        textureParede = new Texture(Gdx.files.internal("Textures/parede.png"));

        velocity.x -= 1;
    }

    void draw(SpriteBatch batch) {
        retangulo.x += velocity.x;
        batch.draw(textureParede, retangulo.x, retangulo.y);
    }



}
