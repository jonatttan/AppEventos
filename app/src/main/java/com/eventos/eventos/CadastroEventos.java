package com.eventos.eventos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.EventLog;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.eventos.eventos.database.EventoDAO;
import com.eventos.eventos.modelo.Eventos;

public class CadastroEventos extends AppCompatActivity {

    private int id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_eventos);

        carregarDados();
    }

    private void carregarDados(){

        Intent intent = getIntent();

        if (intent != null && intent.getExtras() != null && intent.getExtras().get("eventoAlteracao") != null){
            Eventos evento = (Eventos) intent.getExtras().get("eventoAlteracao");
            EditText editText_nome = findViewById(R.id.editText_nomeEvento);
            EditText editText_data = findViewById(R.id.editText_dataEvento);
            EditText editText_local = findViewById(R.id.editText_localEvento);
            editText_nome.setText(evento.getNome());
            editText_data.setText(evento.getData());
            editText_local.setText(evento.getLocal());
            id = evento.getId();
        }

    }

    public void onClickSalvar(View v){

        EditText editText_nome = findViewById(R.id.editText_nomeEvento);
        EditText editText_data = findViewById(R.id.editText_dataEvento);
        EditText editText_local = findViewById(R.id.editText_localEvento);

        String nome = editText_nome.getText().toString();
        String data = editText_data.getText().toString();
        String local = editText_local.getText().toString();

        if (nome.length() == 0){
            Toast.makeText(this,"Preencha o nome.", Toast.LENGTH_LONG).show();
        }else if (data.length() == 0){
            Toast.makeText(this,"Preencha a data.", Toast.LENGTH_LONG).show();
        }else if (local.length() == 0){
            Toast.makeText(this,"Preencha o local.", Toast.LENGTH_LONG).show();
        }else {
            Eventos evento = new Eventos(id, nome, data, local);
            EventoDAO eventoDao = new EventoDAO(getBaseContext());
            boolean salvou = eventoDao.salvar(evento);
            if (salvou){
                finish();
            } else {
                Toast.makeText(CadastroEventos.this, "Erro ao salvar.", Toast.LENGTH_LONG).show();
            }
        }
    }

    public void onClickVoltar(View v){ finish();}

}
