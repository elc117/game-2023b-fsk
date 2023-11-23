package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

public class Obstaculo {
    Texture textureParede;
    Texture textureOsso;

    private float paredeY;
    private float minY;
    private float maxY;
    private float minTamanhoArea;
    private float maxTamanhoArea;

    private float areaLivre;
    Rectangle retanguloOssoSuperior; // Atua como um obstaculo
    Rectangle retanguloOssoInferior;
    Rectangle retanguloPassagem; // Destinado a verificação, não atua como obstaculo

    Vector2 velocity = new Vector2();

    public Obstaculo() {
        textureParede = new Texture(Gdx.files.internal("Textures/parede.png"));
        textureOsso = new Texture(Gdx.files.internal("Textures/Osso.png"));

        Random random = new Random();

        this.minY = 10;
        this.maxY = Gdx.graphics.getHeight()  - 100;

        this.minTamanhoArea = 80;
        this.maxTamanhoArea = Gdx.graphics.getHeight() * 0.35f; // Area livre de passagem de no max 65% da tela

        this.areaLivre = minTamanhoArea + (maxTamanhoArea - minTamanhoArea) * random.nextFloat();

        this.paredeY = minY + (maxY - minY) * random.nextFloat(); // Sorteia posição Y
        retanguloPassagem = new Rectangle(Gdx.graphics.getWidth() + 60, paredeY, 80, areaLivre);

        retanguloOssoInferior = new Rectangle(Gdx.graphics.getWidth() + 60, paredeY - 335, 84, 340);
        retanguloOssoSuperior = new Rectangle(Gdx.graphics.getWidth() + 60, paredeY + areaLivre, 64, 340);
        velocity.x = 4f;
    }

    void draw(SpriteBatch batch) {
        retanguloPassagem.x -= velocity.x;
        batch.draw(textureOsso, retanguloPassagem.x - 10, paredeY - 335, 84, 340);
        batch.draw(textureOsso, retanguloPassagem.x + 10, paredeY + areaLivre, 84, 340);
       // batch.draw(textureParede, retanguloPassagem.x, retanguloPassagem.y, 80, areaLivre);
    }

    public float getPosition(){
        return this.retanguloPassagem.x;
    }


}
