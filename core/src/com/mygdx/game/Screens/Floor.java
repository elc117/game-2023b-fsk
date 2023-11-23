package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Floor {
    private Texture textureFloor;
    private Rectangle retangulo;

    Vector2 velocity = new Vector2();



    public Floor(){
        textureFloor = new Texture(Gdx.files.internal("Textures/Floor.png"));

        retangulo = new Rectangle(0, 0, 721, 10);
        velocity.x = 4f;
    }

    void draw(SpriteBatch batch) {

        retangulo.x -= velocity.x;

        batch.draw(textureFloor, retangulo.x, retangulo.y, 721, 10);
        batch.draw(textureFloor, retangulo.x + 721, retangulo.y, 721, 10);
    }

    public float getPosition() {
        return retangulo.x;
    }

    public void setInitialPosition(float position) {
        this.retangulo.x = position;
    }

}
