package com.facens.lista_personagem.model;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Personagem implements Serializable {
    //pegada da variavel do metodo construtor//
    private String nome;
    private String altura;
    private String nascimento;
    private int id = 0;

    public Personagem(String nome, String nascimento, String altura) { //realização das atribuições

        //declaração das variaveis onde será guardada as informações individuais
        this.nome = nome;
        this.altura = altura;
        this.nascimento = nascimento;
    }

    public Personagem() {
    }

    ;


    // converte o nome par uma string para possibilitar a exibição//

    @NonNull
    @Override
//local onde sera feitos get e sets de cada string
    public String toString() {
        return nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getAltura() {
        return altura;
    }

    public void setAltura(String altura) {
        this.altura = altura;
    }

    public String getNascimento() {
        return nascimento;
    }

    public void setNascimento(String nascimento) {
        this.nascimento = nascimento;
    }

//definição dos id´s
    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;


    }
//metodo do tipo bool caso id´s sejam de valor meno que 0
    public boolean IdValido() {
        return  id>0;
    }


}