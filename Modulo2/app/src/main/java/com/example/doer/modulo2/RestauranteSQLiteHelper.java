package com.example.doer.modulo2;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by DOER on 17/04/2015.
 */
public class RestauranteSQLiteHelper extends SQLiteOpenHelper {
    String dbOrdenCreate = "CREATE TABLE Orden (idOrden INTEGER PRIMARY KEY AUTOINCREMENT," +
            " g1 INTEGER," +
            " g2 INTEGER," +
            " g3 INTEGER," +
            " g4 INTEGER," +
            " deseaSopa INTEGER," +
            " gaseosaOFresco INTEGER," +
            " postres INTEGER," +
            " cafe INTEGER," +
            " te INTEGER," +
            " cerveza INTEGER," +
            " whisky INTEGER," +
            " ron INTEGER," +
            " tequila INTEGER," +
            " ordenTextual TEXT )";
//TE QUEDASTE ACAA!!!
    public RestauranteSQLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(dbOrdenCreate);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Orden");

        //Se crea la nueva versión de la tabla
        db.execSQL(dbOrdenCreate);
    }
}
