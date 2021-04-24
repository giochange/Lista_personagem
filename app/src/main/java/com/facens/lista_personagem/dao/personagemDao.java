package com.facens.lista_personagem.dao;

import com.facens.lista_personagem.model.Personagem;

import java.util.ArrayList;

import java.util.List;

public class personagemDao {

    private final static List<Personagem> personagens = new ArrayList<>();//geração da lista  dos personagens
    private static int contadorDeId = 1;
  //lugar onde será pego as informações dos personagens
    public void salva(Personagem persoangemSalvo) {

        persoangemSalvo.setId(contadorDeId);// ADD PERSONAGEM

        personagens.add(persoangemSalvo);
        atualizaID();

    }
//metodo de atualição de id
    private void atualizaID() {
        contadorDeId++; //seta o contador +1
    }

    public void editar(Personagem personagem) {
        Personagem personagemEscolhido = buscaPersonagemId(personagem);
        if ((personagemEscolhido != null)) {
            int posicaDoPersonagem = personagens.indexOf(personagemEscolhido);// melhor posicionamento
            personagens.set(posicaDoPersonagem, personagem);
        }
    }
//metodo para busca do personagem
    private Personagem buscaPersonagemId(Personagem personagem) {
        //condicionamento perante o indice que aloca as informações
        for (Personagem p :
                personagens) {// aqui é feita a passagem do for Each pela lista
            if (p.getId() == personagem.getId()) {
                return p;// lugar onde fica alocado as informações
            }

        }
        return null;//retorna null caso a condição do if não seja correta
    }
//publicação da lista dentro do "xml"
    public List<Personagem> todos() {
        return new ArrayList<>(personagens);
    }


    public  void  remove (Personagem personagem){
        Personagem personagemDevolvido = buscaPersonagemId(personagem);
        if (personagemDevolvido!=null){
            personagens.remove(personagemDevolvido);
        }
    }
}
