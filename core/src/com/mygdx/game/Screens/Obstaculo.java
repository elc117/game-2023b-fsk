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

    Rectangle retanguloParede; // Atua como um obstaculo
    Rectangle retanguloPassagem; // Destinado a verificação, não atua como obstaculo

    Vector2 velocity = new Vector2();

    public Obstaculo() {
        textureParede = new Texture(Gdx.files.internal("Textures/parede.png"));
        Random random = new Random();
        this.minY = 10;
        this.maxY = Gdx.graphics.getHeight()  - 40;

        this.paredeY = minY + (maxY - minY) * random.nextFloat(); // Sorteia posição Y
        retanguloPassagem = new Rectangle(Gdx.graphics.getWidth() + 60, paredeY, 80, 80);

        velocity.x = 4f;
    }

    void draw(SpriteBatch batch) {
        retanguloPassagem.x -= velocity.x;

        batch.draw(textureParede, retanguloPassagem.x, retanguloPassagem.y, 80, 80);
    }

    public float getPosition(){
        return this.retanguloPassagem.x;
    }
}
