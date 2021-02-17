//Nessa classe é utilizado um padrão de projeto chamado Singleton, que consiste em ter apenas um único ponto de acesso para operações

package com.eventos.eventos.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class DBGateway {

    private static DBGateway dbGateway; //Criado um atributo estático e privado de sua própria classe para seu gerenciamento
    private SQLiteDatabase db;

    public static DBGateway getInstance(Context context){ //se dbGateway não houver sido chamado, não haverá instância, e
        // nesse momento ele será chamado. Se já tiver sido chamado, apenas será retornado
        if (dbGateway == null){
            dbGateway = new DBGateway(context);
        }
        return dbGateway;
    }

    private DBGateway(Context context){
        DatabeseDBHelper dbHelper = new DatabeseDBHelper(context);
        db = dbHelper.getWritableDatabase(); // Aqui temos uma conexão aberta com o banco de dados para interação
    }

    public SQLiteDatabase getDatabase(){
        return db;
    }


}
