package com.mygdx.game.Cenas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Screens.Variaveis;

public class Sombras {
    Texture textureShadow;
    Rectangle retangulo;
    Vector2 velocity = new Vector2();
    public Sombras() {
        textureShadow = new Texture(Gdx.files.internal("Textures/shadows.png"));

        retangulo = new Rectangle(0, 0, Variaveis.WIDTH, 172);

        velocity.x = Variaveis.Velocity;
    }
    public Sombras(float inicialPosition) {
        textureShadow = new Texture(Gdx.files.internal("Textures/shadows.png"));

        retangulo = new Rectangle(inicialPosition, 0, Variaveis.WIDTH, 172);

        velocity.x = Variaveis.Velocity;
    }
    public void draw(SpriteBatch batch) {
        if(!Variaveis.perdeu)
            retangulo.x -= velocity.x;

        batch.draw(textureShadow, retangulo.x, retangulo.y, Variaveis.WIDTH, 172);
    }
    public float getPosition() {
        return retangulo.x;
    }
}
