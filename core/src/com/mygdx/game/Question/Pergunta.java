package com.mygdx.game.Question;

import javax.swing.text.html.parser.Parser;
import java.io.*;
import java.util.ArrayList;

public class Pergunta {

    private int id;

    private String enunciado;

    private ArrayList<String> alternativas;

    private int certa;

    public Pergunta(int index) {
        this.id = index;
        alternativas = new ArrayList<String>();

        int contadorLinhas = 0;
        // Lê o arquivo CSV
        try  {
            BufferedReader leitor = new BufferedReader(new FileReader("Assets/Perguntas/Perguntas.csv"));
            String linha;

            while ((linha = leitor.readLine()) != null) {

                if (contadorLinhas == this.id) {

                    String[] colunas = linha.split("\\|"); // |

                    this.enunciado = colunas[1];
                    for(int i = 2; i < colunas.length - 1; i++) {
                        this.alternativas.add(colunas[i]);
                    }
                    this.certa = Integer.parseInt(colunas[colunas.length - 1]);

                    break;
                }
                contadorLinhas++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }



    public int getNumAlternativas() {
        return this.alternativas.size();
    }

    public int getCerta() {
        return this.certa;
    }

    public String getEnunciado() {
        return this.enunciado;
    }
}
