package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;

public class Variaveis {
    // Largura da tela
    public static float WIDTH = 800;
    // Altura da tela
    public static float HEIGTH = 480;
    // Ponto X de inicio do dino
    public static float DinoX = 20;
    // Ponto Y de inicio do dino
    public static float DinoY = (float)Gdx.graphics.getHeight() / 2;
    // Antigravidade do dino, quando acionada
    public static float DinoAntigravity = 4f;
    // Gravidade
    public static float Gravity = 0.2f;
    // Velocidade das perguntas
    public static float PerguntaVelocity = 1f;
    // Velocidade geral
    public static float Velocity = 5.5f;
    // Volume dos sons
    public static float SoundVolume = 0.1f;
    // Index da ultima questão. Usada para não haver repetições próximas
    public static int lastIndex = 0;
    // Numero de questões existentes
    public static int numPerguntas = 13;
    // Altura do chão
    public static float floorHeight = 10;
    // Indica se o jogador perdeu ou não
    public static boolean perdeu = false;
    // Pontos do jogador
    public static int pontos = 0;
    // Perguntas acertadas
    public static int acertos = 0;
    // Tempo minimo entre cada ocorrencia da pergunta
    public static float tempoEntrePerguntas = 12;
    // Variavel de controle. Usada para bloquear a geração de obstaculos durante as perguntas
    public static boolean addNextObstacle = true;

    public static void setPerdeu(boolean status) {
        perdeu = status;
    }

}


