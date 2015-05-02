package com.example.doer.noticias;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Date;
import java.util.List;


public class MainActivity extends ActionBarActivity {

    private ListView lstNoticias;
    private Button btnCargar;
    private List<Noticia> noticias;
    private Noticia[] noticiasArray;
    private AdaptadorNoticias adaptador = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView lblFechaHoy = (TextView)findViewById(R.id.LblFechaHoy);
        java.util.Date fecha = new Date();
        lblFechaHoy.setText("Hoy: "+fecha.toString());
        //txtResultado = (TextView)findViewById(R.id.txtResultado);
        btnCargar = (Button)findViewById(R.id.btnCargar);
        lstNoticias = (ListView)findViewById(R.id.LstNoticias);
        btnCargar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    CargarXmlTask tarea = new CargarXmlTask();
                    tarea.execute("http://www.europapress.es/rss/rss.aspx");
                }catch(Exception e){
                    AlertDialog ad = new AlertDialog.Builder(getParent()).create();
                    ad.setCancelable(false); // This blocks the 'BACK' button
                    ad.setMessage("Hello World");
                    ad.setButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    ad.show();
                }
            }
        });


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    private class CargarXmlTask extends AsyncTask<String, Integer, Boolean> {

        private ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);

        protected  void onPreExecute(){
            progressDialog.setMessage("Descargando datos...");
            progressDialog.show();
            progressDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                @Override
                public void onCancel(DialogInterface dialog) {
                    CargarXmlTask.this.cancel(true);
                }
            });
        }

        protected Boolean doInBackground(String... params){
            try{
                RssParserDom saxParser = new RssParserDom(params[0]);
                noticias = saxParser.parse();

            }catch(Exception e){
             /*   AlertDialog ad = new AlertDialog.Builder(getParent()).create();
                ad.setCancelable(false); // This blocks the 'BACK' button
                ad.setMessage("Se necesita internet para esta aplicacion");
                ad.setButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });*/
            }
            return true;
        }

        protected void onPostExecute(Boolean result){
            if(noticias.size()==0){

            }else {
                noticiasArray = new Noticia[noticias.size()];
                noticias.toArray(noticiasArray);

                adaptador =
                        new AdaptadorNoticias(MainActivity.this, noticiasArray);

                lstNoticias.setAdapter(adaptador);

                this.progressDialog.dismiss();
            }
        }
    }


    private class AdaptadorNoticias extends ArrayAdapter<Noticia> {
        public AdaptadorNoticias(Context context, Noticia[] noticias){
            super(context, R.layout.listitem_noticia, noticias);
        }

        public View getView(final int position, View convertView, ViewGroup parent){
            LayoutInflater inflater = LayoutInflater.from(getContext());
            View item = inflater.inflate(R.layout.listitem_noticia, null);
            item.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    Intent intentx = new Intent(MainActivity.this,DetalleNoticia.class);
                    Bundle b = new Bundle();
                    b.putString("desc",noticiasArray[position].getDescription());
                    b.putString("tit",noticiasArray[position].getTitulo());
                    intentx.putExtras(b);
                    startActivity(intentx);
                }
            });



            //ELIMINAR AL DESLIZAR

            //Instanciamos el adaptador, le pasamos el arraylist y al listview la pasamos nuestro adapter como adaptador de contenido
            //COMENTADO adaptador = new ArrayAdapter<String>( this, android.R.layout.simple_list_item_1, not);
//            lstNoticias.setAdapter(adaptador);

            //Deslizar item para borrarlo
            SwipeListViewTouchListener touchListener =new SwipeListViewTouchListener(lstNoticias,new SwipeListViewTouchListener.OnSwipeCallback() {
                @Override
                public void onSwipeLeft(ListView listView, int [] reverseSortedPositions) {
                    //Aqui ponemos lo que hara el programa cuando deslizamos un item ha la izquierda
                    noticias.remove(reverseSortedPositions[0]);
                    adaptador.notifyDataSetChanged();
                }

                @Override
                public void onSwipeRight(ListView listView, int [] reverseSortedPositions) {
                    //Aqui ponemos lo que hara el programa cuando deslizamos un item ha la derecha
                    noticias.remove(reverseSortedPositions[0]);
                    adaptador.notifyDataSetChanged();
                }
            },true, false);

            //Escuchadores del listView
            lstNoticias.setOnTouchListener(touchListener);
            lstNoticias.setOnScrollListener(touchListener.makeScrollListener());




            TextView lblNumero = (TextView)item.findViewById(R.id.LblNunero);
            lblNumero.setText(noticiasArray[position].getNumero());

            TextView lblTitulo = (TextView)item.findViewById(R.id.LblTitulo);
            lblTitulo.setText(noticiasArray[position].getTitulo());

            TextView lblFecha = (TextView)item.findViewById(R.id.LblFecha);
            lblFecha.setText(noticiasArray[position].getPubDate());

            return(item);
        }


    }








}
