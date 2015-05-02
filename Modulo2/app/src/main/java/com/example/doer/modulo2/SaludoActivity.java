package com.example.doer.modulo2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Color;
import android.support.v4.app.ListFragment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.widget.TabHost;
import android.widget.TextView;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.content.res.Configuration;
import android.support.v4.app.ActivityCompat;


public class SaludoActivity extends ActionBarActivity implements View.OnTouchListener {
        private Fragment1 fragment1;
        private Fragment2 fragment2;
        private SaludoFragment saludoFragment;
        private FragmentoLista fragmentoLista;
        private CrearOrden crearOrden;
        private Button btnVerOrden;
        private Button btnCrearOrden;
        private TextView txtSaludo;
        private String[] opcionesMenu;
        private DrawerLayout drawerLayout;
        private ListView drawerList;
        private CharSequence tituloSeccion;
        private CharSequence tituloApp;
        private ActionBarDrawerToggle drawerToggle;
        private Button action_settings;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_saludo);
            //Localizamos los controles ¿de donde sale el TxtSaludo?
           // txtSaludo = (TextView)findViewById(R.id.TxtSaludo);
            //Recuperamos la informacion pasada en el intent
            Bundle bundle = this.getIntent().getExtras();
            //Construimos el mensaje a mostrar
           // txtSaludo.setText("Bienvenido " + bundle.getString("NombreIngresado")+ " " + bundle.getString("ApellidoIngresado"));
            btnCrearOrden = (Button)findViewById(R.id.BtnCrearOrden);
           // btnCrearOrden.setOnTouchListener(this);
            btnVerOrden = (Button)findViewById(R.id.BtnVerOrdenes);
            //btnVerOrden.setOnTouchListener(this);
            Fragment fragment = null;
            fragment = new SaludoFragment();
            //Creamos la informacion a pasar entre actividades
            Intent intent = new Intent(SaludoActivity.this, SaludoFragment.class);
            Bundle b = new Bundle();
         //   b.putString("NombreIngresado", bundle.getString("NombreIngresado"));
         //   b.putString("ApellidoIngresado",bundle.getString("ApellidoIngresado"));
            //Añadimos la informacion al intent

            SharedPreferences prefs = getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);

            fragment.setArguments(bundle);
            fragment.setArguments(b);
            intent.putExtras(b);


            Resources resources = getResources();

            //CARGAR FRAGMENTS
            cargarFragmento(getFragmentoLista());

            //botones
/*            btnVerOrden.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    Intent intent= new Intent(SaludoActivity.this,ListaOrdenesViewBase.class);

                    //Creamos la informacion a pasar entre actividades
                    Bundle b = new Bundle();
                    b.putString("ApellidoIngresado",apellido);
                    b.putString("NombreIngresado", nombre);
                    //Añadimos la informacion al intent
                    intent.putExtras(b);
                    startActivity(intent);

                }
            });

  */          btnCrearOrden.setOnClickListener(new View.OnClickListener() {
                @Override

                public void onClick(View v) {
                    //Creamos un Intent
                   Intent intent = new Intent(SaludoActivity.this, OrdenActivity.class);
                 /*    //Creamos la informacion a pasar entre actividades
                    Bundle b = new Bundle();
                    b.putString("ApellidoIngresado",apellido);
                    b.putString("NombreIngresado", nombre);
                    //Añadimos la informacion al intent
                    intent.putExtras(b);
                   */ //Iniciamos la nueva actividad
                    intent.putExtra("idOrden","create");
                    startActivity(intent);
                }
            });
            btnVerOrden.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    Intent intentx = new Intent(SaludoActivity.this,VerOrdenes.class);
                    startActivity(intentx);
                }
            });

          /*  TabHost tabHost = (TabHost)findViewById(android.R.id.tabhost);
            tabHost.setup();

            TabHost.TabSpec tabSpec = tabHost.newTabSpec("mitab1");
            tabSpec.setContent(R.id.tabContenedor1);
            tabSpec.setIndicator("INICIO", resources.getDrawable(android.R.drawable.ic_btn_speak_now));
            tabHost.addTab(tabSpec);

            tabSpec = tabHost.newTabSpec("mitab2");
            tabSpec.setContent(R.id.tabContenedor2);
            tabSpec.setIndicator("PERFIL", resources.getDrawable(android.R.drawable.ic_dialog_map));
            tabHost.addTab(tabSpec);
/*
        tabSpec = tabHost.newTabSpec("mitab3");
        tabSpec.setContent(R.id.tabContenedor3);
        tabSpec.setIndicator("TAB3", resources.getDrawable(android.R.drawable.ic_dialog_email));
        tabHost.addTab(tabSpec);

            tabHost.setCurrentTab(0);

            tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
                @Override
                public void onTabChanged(String tabId) {
                    Log.i("AndroidTabsDemo", "Pulsada pestaña: " + tabId);
                }
            });
*/
            opcionesMenu = new String[] {"Inicio","Opción 1", "Opción 2","Saludo"};
            drawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
            drawerList = (ListView)findViewById(R.id.left_drawer);
            drawerList.setAdapter(new ArrayAdapter<String>(
                    getSupportActionBar().getThemedContext(),
                    //(Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) ? android.R.layout.simple_list_item_activated_1 :  android.R.layout.simple_list_item_1
                    android.R.layout.simple_list_item_activated_1
                    , opcionesMenu
            ));


            drawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    Fragment fragment = null;

                    switch (position){
                        case 0:
                            fragment = new FragmentoLista();
                            break;
                        case 1:
                            fragment = new Fragment1();
                            break;
                        case 2:
                            fragment = new Fragment2();
                            break;
                        case 3:
                            fragment = new SaludoFragment();
                            break;
                    }

                    FragmentManager fragmentManager = getSupportFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.content_frame,fragment).commit();

                    drawerList.setItemChecked(position, true);

                    tituloSeccion = opcionesMenu[position];
                    getSupportActionBar().setTitle(tituloSeccion);

                    drawerLayout.closeDrawer(drawerList);
                }
            });
            tituloSeccion = getTitle();
            tituloApp = getTitle();

            drawerToggle = new ActionBarDrawerToggle(this,
                    drawerLayout,
                    R.drawable.ic_action_action_view_headline,
                    R.string.drawer_open,
                    R.string.drawer_close) {
                public void onDrawerClosed(View view){
                    getSupportActionBar().setTitle(tituloSeccion);
                    ActivityCompat.invalidateOptionsMenu(SaludoActivity.this);
                }
                public void onDrawerOpened(View view){
                    getSupportActionBar().setTitle(tituloApp);
                    ActivityCompat.invalidateOptionsMenu(SaludoActivity.this);
                }
            };
            drawerLayout.setDrawerListener(drawerToggle);

            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
        }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_saludo, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu){
        boolean menuAbierto = drawerLayout.isDrawerOpen(drawerList);

        if(menuAbierto){
            menu.findItem(R.id.menu_new).setVisible(false);
            menu.findItem(R.id.menu_save).setVisible(false);
        }
        else{
            menu.findItem(R.id.menu_new).setVisible(true);
            menu.findItem(R.id.menu_save).setVisible(true);
        }

        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        if(drawerToggle.onOptionsItemSelected(item)){
            return true;
        }

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (id){
            case R.id.menu_new:
                Log.i("ActionBar", "Nuevo!");
                return true;
            case R.id.menu_save:
                Log.i("ActionBar", "Guardar!");
                return true;
            case R.id.action_settings:
                Log.i("ActionBar", "Preferencias");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void cargarFragmento(Fragment fragment){
        android.support.v4.app.FragmentManager manager = null;
        manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.content_frame, fragment);
        transaction.commit();
    }


    public Fragment1 getFragment1() {
        if(fragment1 == null){
            fragment1 = new Fragment1();
        }
        return fragment1;
    }

    public Fragment2 getFragment2() {
        if(fragment2 == null){
            fragment2 = new Fragment2();
        }
        return fragment2;
    }

    public SaludoFragment getSaludoFragment() {
        if(saludoFragment == null){
            saludoFragment = new SaludoFragment();
        }
        return saludoFragment;
    }

    public FragmentoLista getFragmentoLista() {
        if(fragmentoLista == null){
            fragmentoLista = new FragmentoLista();
        }
        return fragmentoLista;
    }

    public CrearOrden getCrearOrden() {
        if(crearOrden == null){
            crearOrden = new CrearOrden();
        }
        return crearOrden;
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState){
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig){
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent){
        Button btn = (Button) view;
        int actionMasked = motionEvent.getActionMasked();
        switch (actionMasked){
            case MotionEvent.ACTION_DOWN:
                btn.setBackgroundColor(Color.CYAN);
                btn.invalidate();
                cambiarFragmento(btn);
                break;
            case MotionEvent.ACTION_UP:
                btn.setBackgroundColor(0);
                break;
        }
        return true;
    }
    public void cambiarFragmento(View view){
        switch (view.getId()){
            case R.id.BtnCrearOrden: cargarFragmento(getCrearOrden()); break;
        }
    }

}

