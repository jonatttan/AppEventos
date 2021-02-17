// Classe para criação e deleção de tabela dentro do Banco de dados
package com.eventos.eventos.database.contract;

import com.eventos.eventos.database.entity.EventoEntity;

public class EventoContract {

    private EventoContract(){}

    public static final String criarTabela(){

        return "CREATE TABLE "+ EventoEntity.TABLE_NAME + " (" +
                EventoEntity._ID + " INTEGER PRIMARY KEY, " +
                EventoEntity.COLLUMN_NAME + " TEXT, " +
                EventoEntity.COLLUMN_NAME_DATA + " TEXT, " +
                EventoEntity.COLLUMN_NAME_LOCAL + " TEXT)";
    }

    public static final String removerTabela(){
        return "DROP TABLE IF EXISTS " + EventoEntity.TABLE_NAME;
    }
}