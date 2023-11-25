package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import java.awt.*;

public class Choices {
    Texture textureChoice;
    Rectangle retangulo;

    Vector2 velocity = new Vector2();

    public Choices() {
        textureChoice = new Texture(Gdx.files.internal("Textures/Choice.png"));

        retangulo = new Rectangle(Gdx.graphics.getWidth(), Gdx.graphics.getHeight() / 2, 85, 60);
        velocity.x = Variaveis.Velocity;
    }

    void draw(SpriteBatch batch) {
        if (!Variaveis.perdeu)
            retangulo.x -= velocity.x;

        batch.draw(textureChoice, retangulo.x, retangulo.y, 85, 60);
    }

}
