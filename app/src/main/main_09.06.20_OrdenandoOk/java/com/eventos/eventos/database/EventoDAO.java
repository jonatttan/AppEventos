package com.eventos.eventos.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.eventos.eventos.database.entity.EventoEntity;
import com.eventos.eventos.modelo.Eventos;

import java.util.ArrayList;
import java.util.List;

public class EventoDAO {

    private final String SQL_LISTAR_TODOS = "SELECT * FROM " + EventoEntity.TABLE_NAME;
    private final String SQL_LISTAR_FILTRADO = "SELECT * FROM " + EventoEntity.TABLE_NAME +
            " WHERE " + EventoEntity.COLLUMN_NAME + " LIKE ? ;"; //em vez de ...  + "LIKE %?%";
    private final String SQL_LISTAR_ASC = "SELECT * FROM " + EventoEntity.TABLE_NAME + " WHERE " +
            EventoEntity.COLLUMN_NAME + " LIKE ? " +
            " ORDER BY " + EventoEntity.COLLUMN_NAME + " ASC";
    private final String SQL_LISTAR_FILTRADO_DESC = "SELECT * FROM " + EventoEntity.TABLE_NAME + " WHERE " +
            EventoEntity.COLLUMN_NAME + " LIKE ? " +
            " ORDER BY " + EventoEntity.COLLUMN_NAME + " DESC";

    private DBGateway dbGateway;

    public EventoDAO(Context context) {
        dbGateway = DBGateway.getInstance(context);
    }

    public boolean salvar(Eventos evento) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(EventoEntity.COLLUMN_NAME, evento.getNome());
        contentValues.put(EventoEntity.COLLUMN_NAME_DATA, evento.getData());
        contentValues.put(EventoEntity.COLLUMN_NAME_LOCAL, evento.getLocal());
        if (evento.getId() > 0){
            return dbGateway.getDatabase().update(EventoEntity.TABLE_NAME,
                    contentValues,
                    EventoEntity._ID + "=?",
                    new String[]{String.valueOf(evento.getId())}) > 0;
        } else {

        }
        long id = dbGateway.getDatabase().insert(EventoEntity.TABLE_NAME,
                null, contentValues);
        return id > 0;
    }

    public List<Eventos> listar(){
        List<Eventos> eventos = new ArrayList<>();
        Cursor cursor = dbGateway.getDatabase().rawQuery(SQL_LISTAR_TODOS, null);
        while (cursor.moveToNext()){
            int id = cursor.getInt(cursor.getColumnIndex(EventoEntity._ID));
            String nome = cursor.getString(cursor.getColumnIndex(EventoEntity.COLLUMN_NAME));
            String data = cursor.getString(cursor.getColumnIndex(EventoEntity.COLLUMN_NAME_DATA));
            String local = cursor.getString(cursor.getColumnIndex(EventoEntity.COLLUMN_NAME_LOCAL));
            eventos.add(new Eventos(id, nome, data, local)); // Populando a lista com os resultados da consulta para exibição na Main
        }
        cursor.close();
        return eventos;
    }

    public boolean deletar(Eventos evento){
        return dbGateway.getDatabase().delete(EventoEntity.TABLE_NAME,
                EventoEntity._ID + "=?",
                new String[]{String.valueOf(evento.getId())}) > 0;

    }

    public List<Eventos> pesquisar(String pesquisa){
        List<Eventos> eventos = new ArrayList<>();
        Cursor cursor = dbGateway.getDatabase().rawQuery(SQL_LISTAR_FILTRADO, new String[]{"%"+pesquisa+"%"});
        while (cursor.moveToNext()){
            int id = cursor.getInt(cursor.getColumnIndex(EventoEntity._ID));
            String nome = cursor.getString(cursor.getColumnIndex(EventoEntity.COLLUMN_NAME));
            String data = cursor.getString(cursor.getColumnIndex(EventoEntity.COLLUMN_NAME_DATA));
            String local = cursor.getString(cursor.getColumnIndex(EventoEntity.COLLUMN_NAME_LOCAL));
            eventos.add(new Eventos(id, nome, data, local)); // Populando a lista com os resultados da consulta para exibição na Main
        }
        cursor.close();
        return eventos;
    }

    public List<Eventos> listarAsc(String pesquisa){
        List<Eventos> eventos = new ArrayList<>();
        Cursor cursor = dbGateway.getDatabase().rawQuery(SQL_LISTAR_ASC, new String[]{"%"+pesquisa+"%"});
        while (cursor.moveToNext()){
            int id = cursor.getInt(cursor.getColumnIndex(EventoEntity._ID));
            String nome = cursor.getString(cursor.getColumnIndex(EventoEntity.COLLUMN_NAME));
            String data = cursor.getString(cursor.getColumnIndex(EventoEntity.COLLUMN_NAME_DATA));
            String local = cursor.getString(cursor.getColumnIndex(EventoEntity.COLLUMN_NAME_LOCAL));
            eventos.add(new Eventos(id, nome, data, local)); // Populando a lista com os resultados da consulta para exibição na Main
        }
        cursor.close();
        return eventos;
    }

    public List<Eventos> listarDesc(String pesquisa){
        List<Eventos> eventos = new ArrayList<>();
        Cursor cursor = dbGateway.getDatabase().rawQuery(SQL_LISTAR_FILTRADO_DESC, new String[]{"%"+pesquisa+"%"});
        while (cursor.moveToNext()){
            int id = cursor.getInt(cursor.getColumnIndex(EventoEntity._ID));
            String nome = cursor.getString(cursor.getColumnIndex(EventoEntity.COLLUMN_NAME));
            String data = cursor.getString(cursor.getColumnIndex(EventoEntity.COLLUMN_NAME_DATA));
            String local = cursor.getString(cursor.getColumnIndex(EventoEntity.COLLUMN_NAME_LOCAL));
            eventos.add(new Eventos(id, nome, data, local)); // Populando a lista com os resultados da consulta para exibição na Main
        }
        cursor.close();
        return eventos;
    }

}
