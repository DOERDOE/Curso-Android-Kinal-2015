package com.example.doer.moduloiii.helpers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by DOER on 5/16/2015.
 */
public class ClientesSqliteHelper extends SQLiteOpenHelper{


    String sqlCreate = "CREATE TABLE Clientes (_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "nombre TEXT, " +
                        "telefono TEXT, " +
                        "email TEXT)";

    public ClientesSqliteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sqlCreate);

        for (int i = 0; i < 5; i++) {
            String nombre = "Cliente " + i;
            String telefono = "12345678";
            String email = "email" + i + "@gmail.com";
            db.execSQL("INSERT INTO Clientes (nombre, telefono, email) " +
                        "VALUES ('" + nombre + "', '" + telefono +"', '" + email + "')");
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Clientes");
        db.execSQL(sqlCreate);
    }
}
