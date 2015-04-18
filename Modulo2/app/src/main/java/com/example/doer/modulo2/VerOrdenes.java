package com.example.doer.modulo2;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class VerOrdenes extends Activity {

    private EditText txtCodigo;
    private TextView txtResultado;

    private Button btnInsertar;
    private Button btnActualizar;
    private Button btnEliminar;
    private Button btnConsultar;

    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_ordenes);

        //Obtenemos las referencias a los controles
        txtCodigo = (EditText)findViewById(R.id.txtReg);
        txtResultado = (TextView)findViewById(R.id.txtResultado);

        btnInsertar = (Button)findViewById(R.id.btnInsertar);
        btnActualizar = (Button)findViewById(R.id.btnActualizar);
        btnEliminar = (Button)findViewById(R.id.btnEliminar);
        btnConsultar = (Button)findViewById(R.id.btnConsultar);

        //Abrimos la base de datos 'DBUsuarios' en modo escritura
        RestauranteSQLiteHelper usdbh =
                new RestauranteSQLiteHelper(this, "DBRestaurante", null, 2);

        db = usdbh.getWritableDatabase();

        btnInsertar.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(VerOrdenes.this,OrdenActivity.class);
                startActivity(intent);

            }
        });

        //ACTUALIZACION POR DEFECTO
        Cursor c = db.rawQuery("SELECT * FROM Orden", null);
        txtResultado.setText("");
        if (c.moveToFirst()) {
            //Recorremos el cursor hasta que no haya más registros
            do {
                String idOrden = c.getString(0);
                String nom = c.getString(14);

                txtResultado.append(" " + idOrden + ": " + nom + "\n");
            } while(c.moveToNext());
        }
        btnActualizar.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {

                //Recuperamos los valores de los campos de texto
                String cod = txtCodigo.getText().toString();
                Intent intent = new Intent(VerOrdenes.this,OrdenActivity.class);
                intent.putExtra("idOrden",cod);
                startActivity(intent);
                //Alternativa 1: método sqlExec()
                //String sql = "UPDATE Usuarios SET nombre='" + nom + "' WHERE codigo=" + cod;
                //db.execSQL(sql);

                //Alternativa 2: método update()
            }
        });

        btnEliminar.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {

                //Recuperamos los valores de los campos de texto
                String cod = txtCodigo.getText().toString();

                //Alternativa 1: método sqlExec()
                //String sql = "DELETE FROM Usuarios WHERE codigo=" + cod;
                //db.execSQL(sql);

                //Alternativa 2: método delete()
                db.delete("Orden", "idOrden=" + cod, null);

                //ACTUALIZAR TRAS ELIMINAR
                Cursor c = db.rawQuery("SELECT * FROM Orden", null);
                txtResultado.setText("");
                if (c.moveToFirst()) {
                    //Recorremos el cursor hasta que no haya más registros
                    do {
                        String idOrden = c.getString(0);
                        String nom = c.getString(14);

                        txtResultado.append(" \n" + idOrden + ": " + nom + "\n " +
                                "-------------------------------------------------\n");
                    } while(c.moveToNext());
                }
            }
        });

        btnConsultar.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {

                //Alternativa 1: método rawQuery()
                Cursor c = db.rawQuery("SELECT * FROM Orden", null);

                //Alternativa 2: método delete()
                //String[] campos = new String[] {"codigo", "nombre"};
                //Cursor c = db.query("Usuarios", campos, null, null, null, null, null);

                //Recorremos los resultados para mostrarlos en pantalla
                txtResultado.setText("");
                if (c.moveToFirst()) {
                    //Recorremos el cursor hasta que no haya más registros
                    do {
                        String idOrden = c.getString(0);
                        String nom = c.getString(14);

                        txtResultado.append(" " + idOrden + ": " + nom + "\n");
                    } while(c.moveToNext());
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_ver_ordenes, menu);
        return true;
    }

}