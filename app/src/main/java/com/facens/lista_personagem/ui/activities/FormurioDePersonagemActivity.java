 package com.facens.lista_personagem.ui.activities;

 import android.content.Intent;
 import android.os.Bundle;
 import android.view.Menu;
 import android.view.MenuItem;
 import android.view.View;
 import android.widget.Button;
 import android.widget.EditText;

 import androidx.annotation.NonNull;
 import androidx.appcompat.app.AppCompatActivity;

 import com.facens.lista_personagem.R;
 import com.facens.lista_personagem.dao.personagemDao;
 import com.facens.lista_personagem.model.Personagem;

 import static com.facens.lista_personagem.ui.activities.ConstantesActivitie.CHAVE_PERSONAGEM;

 public class  FormurioDePersonagemActivity extends AppCompatActivity {

     public static final String TITULO_APPBAR_EDITA_PERSONAGEM = "editar de personagens";
     public static final String TITULO_APPBAR_NOVO_PERSONAGEM = "novo de personagens";
     private  EditText campoNome;
       private  EditText campoAltura;
       private  EditText campoNascimento;
       // variavel de utilização geral dentro dessa classe
       private  final  personagemDao dao =  new personagemDao();
       private  Personagem personagem;

     @Override
     public boolean onCreateOptionsMenu(Menu menu) {
         getMenuInflater().inflate(R.menu.activity_fomulario_personagem_menu_salvar, menu);
         return super.onCreateOptionsMenu(menu);
     }

     @Override
     public boolean onOptionsItemSelected(@NonNull MenuItem item) {
         int itemId = item.getItemId();
         if(itemId == R.id.activity_formulario_personagem_menu_salvar){
             finalizarFormulario();
         }
         return super.onOptionsItemSelected(item);
     }

     @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_de_personagem);
        //pegando os IDS´s dos campos//
           inicializacaoCampos();
           // pegando o botão para colocar um listener de açoes nele- click
           ButtonConfig();
           carregaPersonagem();
       }

     private void carregaPersonagem() {
         Intent dados = getIntent();

         if (dados.hasExtra(CHAVE_PERSONAGEM)) {

             // seta o titulo
             setTitle(TITULO_APPBAR_EDITA_PERSONAGEM);
             //Passar os parâmetros que estou buscando//
              personagem = (Personagem) dados.getSerializableExtra(CHAVE_PERSONAGEM);
             preencheCampos();
         }else {
             setTitle(TITULO_APPBAR_NOVO_PERSONAGEM);
             personagem = new Personagem();

         }
     }

     private void preencheCampos() {
         campoNome.setText(personagem.getNome());
         campoAltura.setText(personagem.getAltura());
         campoNascimento.setText(personagem.getNascimento());
     }

     private void ButtonConfig() {
         Button botaoSalvar = findViewById(R.id.string_salvar);
         botaoSalvar.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 finalizarFormulario();


             }

         });
     }

     private void finalizarFormulario() {
         preenchePersonagem();

         if (personagem.IdValido()){
             dao.editar(personagem);
             finish();

         }else {
             dao.salva(personagem);

         }
         finish();
     }

     private void inicializacaoCampos() {

         campoNome = findViewById(R.id.string_name);
         campoAltura = findViewById(R.id.string_altura);
         campoNascimento = findViewById(R.id.string_nascimento);

       /*  SimpleMaskFormatter smfAltura = new SimpleMaskFormatter("N,NN");
         MaskTextWatcher mtwAltura = new MaskTextWatcher(campoAltura, smfAltura);
         campoAltura.addTextChangedListener(mtwAltura);

         SimpleMaskFormatter smfNascimento = new SimpleMaskFormatter("NN/NN/NNNN");
         MaskTextWatcher mtwNascimento = new MaskTextWatcher(campoNascimento, smfNascimento);
         campoNascimento.addTextChangedListener(mtwNascimento);
*/



     }

     private  void  preenchePersonagem(){



         //convertendo os textos para string//
         String nome = campoNome.getText().toString();
         String altura = campoAltura.getText().toString();
         String nascimento = campoNascimento.getText().toString();


         personagem.setNome(nome);
         personagem.setAltura(altura);
         personagem.setNascimento(nascimento);
     }

 }