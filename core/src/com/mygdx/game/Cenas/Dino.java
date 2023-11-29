package com.mygdx.game.Cenas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Screens.Variaveis;

public class Dino {
    Texture textureDino;
    Texture textureDinoFalling; // Textura para o personagem caindo
    Texture textureDinoJumping; // Textura para o personagem subindo
    Sound soundPulo;
    boolean isFalling = false;
    boolean isJumping = false;
    Vector2 velocity = new Vector2();
    // Posições de localização e tamanho usando os dados de um retangulo
    Rectangle retangulo = new Rectangle(Variaveis.DinoX, Variaveis.DinoY, 40, 40);

    public void create() {
        textureDino = new Texture(Gdx.files.internal("Textures/dino.png"));
        textureDinoFalling = new Texture(Gdx.files.internal("Textures/dino_caindo.png"));
        textureDinoJumping = new Texture(Gdx.files.internal("Textures/dino_subindo.png"));
        soundPulo = Gdx.audio.newSound(Gdx.files.internal("Sounds/pulo.wav"));
    }
    public void draw(SpriteBatch batch) {
        if (!Variaveis.perdeu) {
            velocity.y -= Variaveis.Gravity; // Velocidade cai
            retangulo.y += velocity.y; // Associa a nova velocidade ao retângulo, assim é usado no futuro

            // Pular
            if (Gdx.input.justTouched()) {
                soundPulo.play(Variaveis.SoundVolume);
                velocity.y = Variaveis.DinoAntigravity;
            }
        }
        // Delimita o tamanho
        if (retangulo.y >= Gdx.graphics.getHeight() - 40 || retangulo.y <= Variaveis.floorHeight) {
            // Perde o jogo
            Variaveis.setPerdeu(true);
        }
        if (velocity.y < 0) {
            // O personagem está caindo
            isFalling = true;
            isJumping = false;
        } else if (velocity.y > 0) {
            // O personagem está subindo
            isJumping = true;
            isFalling = false;
        } else {
            // O personagem não está caindo nem subindo
            isFalling = false;
            isJumping = false;
        }
        // Escolhe a textura com base no movimento vertical
        Texture currentTexture;
        if (isFalling) {
            currentTexture = textureDinoFalling;
        } else if (isJumping) {
            currentTexture = textureDinoJumping;
        } else {
            currentTexture = textureDino;
        }
        batch.draw(currentTexture, retangulo.x, retangulo.y);
    }
    void dispose() {
        textureDino.dispose();
        textureDinoFalling.dispose();
        textureDinoJumping.dispose();
        soundPulo.dispose();
    }
    public Rectangle getDinoRectangle() {
        return this.retangulo;
    }
}
