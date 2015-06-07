package com.example.doer.moduloiii;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.provider.CallLog;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class ContentProvider extends ActionBarActivity {

    private Button btnInsertar;
    private Button btnConsultar;
    private Button btnEliminar;
    private TextView txtResultado;
    private Button btnLlamadas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_provider);
        txtResultado = (TextView) findViewById(R.id.TxtResultado);
        btnConsultar = (Button) findViewById(R.id.BtnConsultar);
        btnInsertar = (Button) findViewById(R.id.BtnInsertar);
        btnEliminar = (Button) findViewById(R.id.BtnEliminar);
        btnLlamadas = (Button) findViewById(R.id.BtnLlamadas);

        btnConsultar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                txtResultado.setText("");
                String[] campos = new String[] {
                        ClientesProvider.Clientes._ID,
                        ClientesProvider.Clientes.COL_NOMBRE,
                        ClientesProvider.Clientes.COL_TELEFONO,
                        ClientesProvider.Clientes.COL_EMAIL
                };
                Uri clientesUri = ClientesProvider.CONTENT_URI;
                ContentResolver cr = getContentResolver();

                Cursor cursor = cr.query(clientesUri, campos, null, null, null);

                if(cursor.moveToFirst()){
                    String nombre;
                    String telefono;
                    String email;

                    int colNombre = cursor.getColumnIndex(ClientesProvider.Clientes.COL_NOMBRE);
                    int colTelefono = cursor.getColumnIndex(ClientesProvider.Clientes.COL_TELEFONO);
                    int colEmail = cursor.getColumnIndex(ClientesProvider.Clientes.COL_EMAIL);

                    do{
                        nombre = cursor.getString(colNombre);
                        telefono = cursor.getString(colTelefono);
                        email = cursor.getString(colEmail);

                        txtResultado.append(nombre + " - " + telefono + " - " + email + "\n");
                    } while (cursor.moveToNext());
                }
            }
        });

        btnInsertar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentValues values = new ContentValues();
                values.put(ClientesProvider.Clientes.COL_NOMBRE, "Cliente Insertado");
                values.put(ClientesProvider.Clientes.COL_TELEFONO, "87654321");
                values.put(ClientesProvider.Clientes.COL_EMAIL, "correo@xD.com");

                ContentResolver cr = getContentResolver();

                cr.insert(ClientesProvider.CONTENT_URI, values);
            }
        });

        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentResolver cr = getContentResolver();
                cr.delete(ClientesProvider.CONTENT_URI, ClientesProvider.Clientes.COL_NOMBRE + " = 'Cliente Insertado'", null);
            }
        });

        btnLlamadas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] campos = new String[]{
                        CallLog.Calls.TYPE,
                        CallLog.Calls.NUMBER
                };
                Uri llamadasUri = CallLog.Calls.CONTENT_URI;

                ContentResolver cr = getContentResolver();

                Cursor cursor = cr.query(llamadasUri, campos, null, null, null);

                if (cursor.moveToFirst()) {
                    int tipo;
                    String tipoLlamada = "";
                    String telefono;


                    int colTipo = cursor.getColumnIndex(CallLog.Calls.TYPE);
                    int colTelefono = cursor.getColumnIndex(CallLog.Calls.NUMBER);

                    txtResultado.setText("");

                    do {
                        tipo = cursor.getInt(colTipo);
                        telefono = cursor.getString(colTelefono);
                        switch (tipo) {
                            case CallLog.Calls.INCOMING_TYPE:
                                tipoLlamada = "ENTRANTE";
                                break;
                            case CallLog.Calls.OUTGOING_TYPE:
                                tipoLlamada = "SALIENTE";
                                break;
                            case CallLog.Calls.MISSED_TYPE:
                                tipoLlamada = "PERDIDA";
                                break;
                            default:
                                break;
                        }

                        txtResultado.append(tipoLlamada + " - " + telefono + "\n");
                    } while (cursor.moveToNext());
                }
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
