package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

public class Obstaculo {
    Texture textureParede;

    private float paredeY;
    private float minY;
    private float maxY;

    Rectangle retangulo;

    Vector2 velocity = new Vector2();

    public Obstaculo() {
        textureParede = new Texture(Gdx.files.internal("Textures/osso.png"));
        Random random = new Random();

        minY = 30;
        maxY = 340;

        this.paredeY = minY + (maxY - minY) * random.nextFloat(); // Sorteia um tamanho

        retangulo = new Rectangle(Gdx.graphics.getWidth() + 200, 0, 20, this.paredeY);

        velocity.x -= 5;
    }

    void draw(SpriteBatch batch) {
        retangulo.x += velocity.x;

        batch.draw(textureParede, retangulo.x, retangulo.y, 500, this.paredeY);
        batch.draw(textureParede, retangulo.x, retangulo.y, 20, this.paredeY);
    }
}
