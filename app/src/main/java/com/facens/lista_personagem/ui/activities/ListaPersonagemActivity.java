package com.facens.lista_personagem.ui.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.facens.lista_personagem.R;
import com.facens.lista_personagem.dao.personagemDao;
import com.facens.lista_personagem.model.Personagem;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import static com.facens.lista_personagem.ui.activities.ConstantesActivitie.CHAVE_PERSONAGEM;

//class Lista de personagem
public class ListaPersonagemActivity extends AppCompatActivity {
    //variavel responsavel pelo titulo
    public static final String TIULO_APPBAR = "Lista de personagens";

    // variavel e atributo//
    private final personagemDao dao = new personagemDao();
    private ArrayAdapter<Personagem> adapter;

    @Override
    protected void onCreate(@NonNull Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_personagem);
        // seta o titulo atravez da variavel criada
        setTitle(TIULO_APPBAR);
        // metodo responsavel pelo botao
        configBtAddPerson();
        configList();


    }

    private void configBtAddPerson() {
       // reaaliza a chamada de um activity para em seguidar direcionar a outro local
        FloatingActionButton botaoSalvar = findViewById(R.id.fab_add);
        botaoSalvar.setOnClickListener(new View.OnClickListener() {
           // inicia o start activity
            @Override
            public void onClick(View v) {
                abreFormulario();

            }
        });
    }

    private void abreFormulario() {
        startActivity(new Intent(this, FormurioDePersonagemActivity.class));
    }

    //cria uma prote????o para os dados fazendo eles nao serem apagado quando clicar em voltar//
    @Override
    protected void onResume() {// faz as informa????es se manterem independente do estado
        super.onResume();
        atualizaPersonagem();
    }
    private void atualizaPersonagem() {
        adapter.clear(); //limpeza de informa????o dentro da lista
        adapter.addAll(dao.todos());// adi????o de todos dentro da lista
    }
//metodo para remo????o de personagem para n??o duplicar ids
    private void remove(Personagem personagem){

        dao.remove((personagem));
        adapter.remove(personagem);

    }

    @Override
    //pop up que vai aparecer ao se remover item
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
       getMenuInflater().inflate(R.menu.activity_list_personagem_menu,menu);
    }

    @Override
    //sele????o de item e retorno ?? adpter e identifica????o de sele????o
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        return configMenu(item);

    }

    private boolean configMenu(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.activity_list_personagem_menu_remover) {

            //alerta dentro da janela do app para informar a possibilidade delete da informa????o
            new AlertDialog.Builder(this).setTitle("removendo personagem")
                    .setMessage("tem certeza que deseja remover esse item ?")
                    .setPositiveButton("sim ", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //remo????o do item desejado
                            AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
                            Personagem personagemEscolhido = adapter.getItem(menuInfo.position);
                            remove(personagemEscolhido);
                         }
                    })

                    .setNegativeButton("n??o", null)
                    .show();//faz a apari????o na interface//
        }
        return super.onContextItemSelected(item);





    }




    private void configList() {
        ListView listpersonagem = findViewById(R.id.activity_main_list_personagem);
        // modifica????o dos personagens na lista//
        listaDePernsonagens(listpersonagem);
        configItemPClick(listpersonagem);
        registerForContextMenu(listpersonagem);
    }

    private void configItemPClick(ListView listpersonagem) {
        listpersonagem.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            // metodo para sele????o dos personagens//
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Personagem personagemEscolhido = (Personagem) adapterView.getItemAtPosition(position);
                abreFormularioEditar(personagemEscolhido);


            }
        });
    }


    private void abreFormularioEditar(Personagem personagemEscolhido) {
        Intent vaiParaFormulario = new Intent(ListaPersonagemActivity.this, FormurioDePersonagemActivity.class);
        //realiza o transicionamento das informa????es que ir??o para o formulario
        vaiParaFormulario.putExtra(CHAVE_PERSONAGEM, personagemEscolhido);
        startActivity(vaiParaFormulario);
    }

    private void listaDePernsonagens(ListView listpersonagem) {
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
        listpersonagem.setAdapter(adapter);//defini????o dos dados atr??s do listview
    }
}
