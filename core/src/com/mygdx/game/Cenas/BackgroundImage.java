package com.mygdx.game.Cenas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Screens.Variaveis;

import java.util.Random;

public class BackgroundImage {

    Vector2 velocity = new Vector2();
    private Texture backgroundImage;
    private Rectangle retangulo;
    private int indexImage;
    public BackgroundImage(int indexImage) {
        this.indexImage = indexImage;

        backgroundImage = new Texture(Gdx.files.internal("Images/"+indexImage+".png"));

        retangulo = new Rectangle(0, 0, backgroundImage.getWidth(), backgroundImage.getHeight());

        velocity.x = Variaveis.Velocity;
        velocity.y = 0.4f;
    }

    public void draw(SpriteBatch batch) {


        Random random = new Random();
        if(!Variaveis.perdeu)
            retangulo.x -= velocity.x;

        // Se for a imagem padrão, cria uma movimentação aleatória
        if (this.indexImage == 0) {
            float minY = 1;
            float maxY = 40;

            if (random.nextBoolean()) {

                float deslocamento = minY + (maxY - minY) * random.nextFloat();
                // Movimentação para cima
                if (retangulo.y + deslocamento < 0) {
                    retangulo.y += velocity.y;
                }
            } else {
                float deslocamento = minY + (maxY - minY) * random.nextFloat();
                // Movimentação para baixo
                if (retangulo.y - deslocamento < backgroundImage.getHeight()) {
                    retangulo.y -= velocity.y;
                }
            }
        }
        batch.draw(backgroundImage, retangulo.x, retangulo.y, backgroundImage.getWidth(), backgroundImage.getHeight());
    }

    public float getPosition() {
        return retangulo.x;
    }


}
