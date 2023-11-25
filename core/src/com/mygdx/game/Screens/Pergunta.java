package com.mygdx.game.Screens;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Pergunta {

    private int id;

    private String enunciado;

    private ArrayList<String> alternativas;

    private int certa;

    public Pergunta(int index) {
        this.id = index;
        int contadorLinhas = 0;
        // Lê o arquivo CSV

        try  {
            BufferedReader leitor = new BufferedReader(new FileReader("Perguntas/Perguntas.csv"));
            String linha;

            while ((linha = leitor.readLine()) != null) {
                contadorLinhas++;

                // Verificar se é a linha desejada
                if (contadorLinhas == this.id) {
                    // Processar a linha conforme necessário
                    System.out.println(linha);
                    break; // Parar de ler após encontrar a linha desejada
                }
            }
        } catch (IOException e) {
            e.printStackTrace(); // Tratar exceções, se necessário
        }

    }
}
