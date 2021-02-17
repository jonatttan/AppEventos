package com.eventos.eventos.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;
import com.eventos.eventos.database.contract.EventoContract;

public class DatabeseDBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "db.evento"; // Não poderia ser estar em EventoEntity?
    private static final int DATABASE_VERSION = 1; // Não poderia ser estar em EventoEntity?

    public DatabeseDBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(EventoContract.criarTabela()); // Aqui é executado a criação de tabela, que é chamada por método pelo db.execSQL
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Aqui está sendo executado de uma forma que não é a mais correta, pois o certo seria função de upgrade
        db.execSQL(EventoContract.removerTabela());
        db.execSQL(EventoContract.criarTabela());
    }
}
