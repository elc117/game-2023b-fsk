package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;

public class Variaveis {
    // Largura da tela
    public static float WIDTH = 480;
    // Altura da tela
    public static float HEIGTH = 800;
    // Ponto X de inicio do dino
    public static float DinoX = 20;
    // Ponto Y de inicio do dino
    public static float DinoY = (float)Gdx.graphics.getHeight() / 2;
    // Antigravidade do dino, quando acionada
    public static float DinoAntigravity = 3.5f;
    // Gravidade
    public static float Gravity = 0.2f;
    // Velocidade
    public static float Velocity = 4f;


    // Altura do chão
    public static float floorHeight = 10;
    // Indica se o jogador perdeu ou não
    public static boolean perdeu = false;



    public static void setPerdeu(boolean status) {
        perdeu = status;
    }

}
