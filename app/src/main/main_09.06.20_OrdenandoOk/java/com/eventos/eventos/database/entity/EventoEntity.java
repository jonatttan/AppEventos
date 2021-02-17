//Classe criadada para definir nome da tabela e seus campos, atribuindo à constantes para fácil manutenção do código e evitar erros.

package com.eventos.eventos.database.entity;
import android.provider.BaseColumns;

public class EventoEntity implements BaseColumns {

    private EventoEntity(){}

    public static final String TABLE_NAME = "evento";
    public static final String COLLUMN_NAME = "nome";
    public static final String COLLUMN_NAME_DATA = "data";
    public static final String COLLUMN_NAME_LOCAL = "local";
}