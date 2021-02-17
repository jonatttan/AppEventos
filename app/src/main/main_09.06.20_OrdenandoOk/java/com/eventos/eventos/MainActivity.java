//Aplicativo de registro de eventos. Desenvolvido em 06/2020

package com.eventos.eventos;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.eventos.eventos.database.EventoDAO;
import com.eventos.eventos.modelo.Eventos;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private int id = 0; //Esse valor será zero em breve

    private ListView listView_eventos; //Declaração de lisView
    private ArrayAdapter<Eventos> adapterEventos; //Declaração de ArrayAdapter

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Lista de Eventos");
        listView_eventos = findViewById(R.id.listView_eventos);
        funcaoOnClickListenerListView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        EventoDAO eventoDao = new EventoDAO(getBaseContext());
        adapterEventos = new ArrayAdapter<Eventos>(MainActivity.this,
                android.R.layout.simple_list_item_1,
                eventoDao.listar()); // Populando o listView_eventos
        listView_eventos.setAdapter(adapterEventos);

    }

    private void funcaoOnClickListenerListView(){
//        listView_eventos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//
//            final Eventos eventoClicado = adapterEventos.getItem(position);
//            Intent intent = new Intent(MainActivity.this, CadastroEventos.class);
//            intent.putExtra("eventoAlteracao", eventoClicado);
//            startActivityForResult(intent, REQUEST_CODE_ALTERACAO_EVENTO);
//            }
//        });

        listView_eventos.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                final Eventos eventoLongClick = adapterEventos.getItem(position);
                final CharSequence[] opcoes = {"Excluir","Editar"};
                //ação de exclusão

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Escolha");
                builder.setItems(opcoes,
                    new DialogInterface.OnClickListener(){
                            public void onClick (DialogInterface dialog, int escolha){
                                switch (escolha){
                                    case 0:
                                        EventoDAO eventoDao = new EventoDAO(getBaseContext());
                                        boolean excluiu = eventoDao.deletar(eventoLongClick);
                                        onResume();
                                        break;
                                    case 1:
                                        Intent intent = new Intent(MainActivity.this, CadastroEventos.class);
                                        intent.putExtra("eventoAlteracao", eventoLongClick);
                                        startActivity(intent);
                                        break;
                                }
                        }
                    });
                AlertDialog dialogo = builder.create();
                dialogo.show();

                return false;
            }
        });

    }

    public void onClickCadastroEventos(View v){
        Intent intent = new Intent(MainActivity.this, CadastroEventos.class);
        startActivity(intent);
    }

    private void excluirEvento(Eventos eventoSelecionado){
        for (int i = 0; i < adapterEventos.getCount(); i++){
            Eventos roletaEventos = adapterEventos.getItem(i);
            if (roletaEventos.getId() == eventoSelecionado.getId()){
                adapterEventos.remove(roletaEventos);
            break;
            }
        }

    }

    public void onClickPesquisar(View v){
        EditText editText_pesquisa = findViewById(R.id.editText_pesquisa);
        String strPesquisa = editText_pesquisa.getText().toString();

        EventoDAO eventoDao = new EventoDAO(getBaseContext());
        adapterEventos = new ArrayAdapter<Eventos>(MainActivity.this,
                android.R.layout.simple_list_item_1,
                eventoDao.pesquisar(strPesquisa));
        listView_eventos.setAdapter(adapterEventos);
    }

    public void onClickAscendente(View v){
        EditText editText_pesquisa = findViewById(R.id.editText_pesquisa);
        String strPesquisa = editText_pesquisa.getText().toString();

        EventoDAO eventoDao = new EventoDAO(getBaseContext());
        adapterEventos = new ArrayAdapter<Eventos>(MainActivity.this,
                android.R.layout.simple_list_item_1,
                eventoDao.listarAsc(strPesquisa));
        listView_eventos.setAdapter(adapterEventos);
    }

    public void onClickDescendente(View v){
        EditText editText_pesquisa = findViewById(R.id.editText_pesquisa);
        String strPesquisa = editText_pesquisa.getText().toString();

        EventoDAO eventoDao = new EventoDAO(getBaseContext());
        adapterEventos = new ArrayAdapter<Eventos>(MainActivity.this,
                android.R.layout.simple_list_item_1,
                eventoDao.listarDesc(strPesquisa));
        listView_eventos.setAdapter(adapterEventos);
    }

}
