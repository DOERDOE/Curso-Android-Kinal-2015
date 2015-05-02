package com.example.doer.noticias;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class DetalleNoticia extends ActionBarActivity {

    TextView txtDetalleDescripcion;
    TextView txtDetalleTitulo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_noticia);

        this.txtDetalleDescripcion = (TextView)findViewById(R.id.TxtDetalleDescripcion);
        this.txtDetalleTitulo = (TextView)findViewById(R.id.TxtDetalleTitulo);

        Bundle bun = getIntent().getExtras();

        txtDetalleDescripcion.setText(bun.getString("desc"));
        txtDetalleTitulo.setText(bun.getString("tit"));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_detalle_noticia, menu);
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
}
