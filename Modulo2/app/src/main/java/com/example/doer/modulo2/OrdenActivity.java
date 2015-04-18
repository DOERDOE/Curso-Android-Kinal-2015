package com.example.doer.modulo2;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.Fragment;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TabHost;
import android.widget.Toast;
import android.widget.ToggleButton;


public class OrdenActivity extends ActionBarActivity {

    private Button btnGuardar;
    private Button btnVerOrdenes;
    String idOrden="";
    private CheckBox chk1, chk2,chk3, chk4;
    private ToggleButton tgbSopa, tgbFrescoOGaseosa;
    private RadioButton rb1, rb2, rb3;
    private EditText etxtCafe, etxtTe,etxtCerveza,etxtWhisky,etxtRon,etxtTequila;
    private boolean update;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //BASE DE DATOS
        RestauranteSQLiteHelper rsdb = new RestauranteSQLiteHelper(this,"DBRestaurante",null,2);
        final SQLiteDatabase db = rsdb.getWritableDatabase();


        setContentView(R.layout.activity_orden);
        btnGuardar = (Button)findViewById(R.id.BtnGuardar);
        chk1 = (CheckBox)findViewById(R.id.Chk1); chk2 = (CheckBox)findViewById(R.id.Chk2); chk3 = (CheckBox)findViewById(R.id.Chk3); chk4 = (CheckBox)findViewById(R.id.Chk4);
        tgbSopa = (ToggleButton)findViewById(R.id.TGBSopa); tgbFrescoOGaseosa = (ToggleButton)findViewById(R.id.TGBFrescoOGaseosa);
        rb1 = (RadioButton)findViewById(R.id.Rb1);rb2 = (RadioButton)findViewById(R.id.Rb2);rb3 = (RadioButton)findViewById(R.id.Rb3);
        etxtCafe = (EditText)findViewById(R.id.EtxtCafe);        etxtTe = (EditText)findViewById(R.id.EtxtTe);         etxtCerveza = (EditText)findViewById(R.id.EtxtCerveza);        etxtWhisky = (EditText)findViewById(R.id.EtxtWhisky);        etxtRon = (EditText)findViewById(R.id.EtxtRon);        etxtTequila = (EditText)findViewById(R.id.EtxtTequila);
        Resources resources = getResources();

        TabHost tabHost = (TabHost)findViewById(android.R.id.tabhost);
        tabHost.setup();

        TabHost.TabSpec tabSpec = tabHost.newTabSpec("mitab1");
        tabSpec.setContent(R.id.tabMenu);
        tabSpec.setIndicator("Menu", resources.getDrawable(android.R.drawable.ic_btn_speak_now));
        tabHost.addTab(tabSpec);

        tabSpec = tabHost.newTabSpec("mitab2");
        tabSpec.setContent(R.id.tabAcompanamientos);
        tabSpec.setIndicator("Acompañamientos", resources.getDrawable(android.R.drawable.ic_dialog_map));
        tabHost.addTab(tabSpec);

        tabHost.setCurrentTab(0);
        Bundle bundle = this.getIntent().getExtras();
       // final String nombre = bundle.getString("NombreIngresado");
        //final String apellido = bundle.getString("ApellidoIngresado");
        this.idOrden = bundle.getString("idOrden");
        if(this.idOrden!=""){
            Cursor c = db.rawQuery("SELECT * FROM Orden WHERE Orden.idOrden ="+idOrden+"", null);
            if (c.moveToFirst()) {
                //Recorremos el cursor hasta que no haya más registros
                do {
                    String idOrden = c.getString(0);
                    chk1.setChecked(((c.getInt(1)==1)?true:false));
                    chk2.setChecked(((c.getInt(2)==1)?true:false));
                    chk3.setChecked(((c.getInt(3)==1)?true:false));
                    chk4.setChecked(((c.getInt(4)==1)?true:false));
                    tgbSopa.setChecked(((c.getInt(5)==1)?true:false));
                    tgbFrescoOGaseosa.setChecked(((c.getInt(6)==1)?true:false));
                    if(c.getInt(7)==1){
                        rb1.setChecked(true);
                    }else if(c.getInt(7)==2){
                        rb2.setChecked(true);
                    }else if(c.getInt(7)==3){
                        rb3.setChecked(true);
                    }else{
                        rb1.setChecked(false);
                        rb2.setChecked(false);
                        rb3.setChecked(false);
                    }
                    etxtCafe.setText(((c.getString(8))!="")? c.getString(8):"");
                    etxtTe.setText(((c.getString(9))!="")? c.getString(9):"");
                    etxtCerveza.setText(((c.getString(10))!="")? c.getString(10):"");
                    etxtWhisky.setText(((c.getString(11))!="")? c.getString(11):"");
                    etxtRon.setText(((c.getString(12))!="")? c.getString(13):"");
                    etxtTequila.setText(((c.getString(13))!="")? c.getString(13):"");
                    update = true;
                } while(c.moveToNext());
            }
        }

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    String ordenTextual = "";
                    //PARA GUARDAR EL REGISTRO
                    ordenTextual = (ordenTextual + "GUARNICIONES: \n");
                    int g1 = 0;
                    g1 = (chk1.isChecked()) ? 1 : 0;
                    ordenTextual = (chk1.isChecked()) ? (ordenTextual = (ordenTextual + " " + chk1.getText().toString())) : (ordenTextual = ordenTextual);
                    int g2 = 0;
                    g2 = (chk2.isChecked() == true) ? 1 : 0;
                    ordenTextual = (chk2.isChecked()) ? (ordenTextual = (ordenTextual + " " + chk2.getText().toString())) : (ordenTextual = ordenTextual);
                    int g3 = 0;
                    g3 = (chk3.isChecked() == true) ? 1 : 0;
                    ordenTextual = (chk3.isChecked()) ? (ordenTextual = (ordenTextual + " " + chk3.getText().toString())) : (ordenTextual = ordenTextual);
                    int g4 = 0;
                    g4 = (chk4.isChecked() == true) ? 1 : 0;
                    ordenTextual = (chk4.isChecked()) ? (ordenTextual = (ordenTextual + " " + chk4.getText().toString())) : (ordenTextual = ordenTextual);

                    ordenTextual = (ordenTextual + "\n¿Desea Sopa? ");

                    int deseaSopa = 0;
                    deseaSopa = (tgbSopa.isChecked() == true) ? 1 : 0;
                    ordenTextual = (tgbSopa.isChecked() == true) ? (ordenTextual = (ordenTextual + " ->" + "SI")) : (ordenTextual = (ordenTextual + " ->" + "NO"));
                    int gaseosaOFresco = 0;
                    ordenTextual = (ordenTextual + "¿Gaseosa o Fresco? -");
                    gaseosaOFresco = (tgbFrescoOGaseosa.isChecked() == true) ? 1 : 0;
                    ordenTextual = (tgbFrescoOGaseosa.isChecked() == true) ? (ordenTextual = (ordenTextual + " ->" + "Gaseosa")) : (ordenTextual = (ordenTextual + " ->" + "Fresco"));

                    int postres = 0;
                    String postre;
                    if (rb1.isChecked()) {
                        postres = 1;
                        postre = (String) rb1.getText();
                    } else if (rb2.isChecked()) {
                        postres = 2;
                        postre = (String) rb2.getText();
                    } else if (rb3.isChecked()) {
                        postres = 3;
                        postre = (String) rb3.getText();
                    } else {
                        postres = 0;
                        postre = "";
                    }
                    ordenTextual = ordenTextual + "\nPOSTRE: ";
                    if (postre == "") {
                        postre = "ninguno";
                    }
                    ordenTextual = ordenTextual + postre;
                    ordenTextual = ordenTextual + "\nBEBIDAS: ";

                    int cafe = 0;
                    try {
                        cafe = ((etxtCafe.getText()).toString() != "") ? Integer.parseInt((etxtCafe.getText()).toString()) : 0;
                    } catch (Exception e) {
                        cafe = 0;
                    }
                    ordenTextual = (ordenTextual + ("  ->cafe: " + cafe));
                    int te = 0;
                    try {
                        te = ((etxtTe.getText()).toString() != "") ? Integer.parseInt((etxtTe.getText()).toString()) : 0;
                    } catch (Exception e) {
                        te = 0;
                    }
                    ordenTextual = (ordenTextual + ("  ->te: " + te));
                    int cerveza = 0;
                    try {
                        cerveza = ((etxtCerveza.getText()).toString() != "") ? Integer.parseInt((etxtCerveza.getText()).toString()) : 0;
                    } catch (Exception e) {
                        cerveza = 0;
                    }
                    ordenTextual = ordenTextual + "\nBEBIDAS FRIAS ALCOHOLICAS: ";
                    ordenTextual = (ordenTextual + ("\n  ->Cerveza " + cerveza));
                    int whisky = 0;
                    try {
                        whisky = ((etxtWhisky.getText()).toString() != "") ? Integer.parseInt((etxtWhisky.getText()).toString()) : 0;
                    } catch (Exception e) {
                        whisky = 0;
                    }
                    ordenTextual = (ordenTextual + ("  ->Whisky: " + whisky));
                    int ron = 0;
                    try {
                        ron = ((etxtRon.getText()).toString() != "") ? Integer.parseInt((etxtRon.getText()).toString()) : 0;
                    } catch (Exception e) {
                        ron = 0;
                    }
                    ordenTextual = (ordenTextual + ("\n ->Ron: " + ron));
                    int tequila = 0;
                    try {
                        tequila = ((etxtTequila.getText()).toString() != "") ? Integer.parseInt((etxtTequila.getText()).toString()) : 0;
                    } catch (Exception e) {
                        tequila = 0;
                    }
                    ordenTextual = (ordenTextual + (" ->Tequila: " + tequila));

                //SETEAR VALORES PARA ACTUALIZAR
                ContentValues valores = new ContentValues();
                valores.put("g1",g1);
                valores.put("g2",g2);
                valores.put("g3",g3);
                valores.put("g4",g4);
                valores.put("deseaSopa",deseaSopa);
                valores.put("gaseosaOFresco",gaseosaOFresco);
                valores.put("postres",postres);
                valores.put("cafe",cafe);
                valores.put("te",te);
                valores.put("cerveza",cerveza);
                valores.put("whisky",whisky);
                valores.put("ron",ron);
                valores.put("tequila",tequila);
                valores.put("ordenTextual",ordenTextual);

                if(update==false) {
                    if (db != null) {
                        db.execSQL("INSERT INTO Orden (g1,g2,g3,g4,deseaSopa,gaseosaOFresco,postres,cafe,te,cerveza,whisky,ron,tequila,ordenTextual)" +
                                " VALUES (" + g1 + "," + g2 + "," + g3 + "," + g4 + "," + deseaSopa + "," + gaseosaOFresco + "," + postres + "," + cafe + "," + te + "," + cerveza + "," + whisky + "," + ron + "," + tequila + ",'" + ordenTextual + "')");
                        db.close();
                        Intent intent = new Intent(OrdenActivity.this, SaludoActivity.class);
                        Toast.makeText(btnGuardar.getContext(), "Su orden ha sido creada", Toast.LENGTH_LONG).show();
                        startActivity(intent);

                    }
                }else{
                    if (db != null) {
                        db.update("Orden", valores, "idOrden="+idOrden+"", null);
                        db.close();
                        Intent intent = new Intent(OrdenActivity.this, SaludoActivity.class);
                        Toast.makeText(btnGuardar.getContext(), "Su orden ha sido Actualizada", Toast.LENGTH_LONG).show();
                        startActivity(intent);

                    }
                }
            }
        });



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
    //    getMenuInflater().inflate(R.menu.menu_orden, menu);
        return true;
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

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

      /*  @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_crear_orden, container, false);
            return rootView;
        }*/
    }
}