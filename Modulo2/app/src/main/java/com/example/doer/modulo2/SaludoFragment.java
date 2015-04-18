package com.example.doer.modulo2;


import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.prefs.Preferences;


/**
 * A simple {@link Fragment} subclass.
 */
public class SaludoFragment extends Fragment {
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

    public SaludoFragment() {
        // Required empty public constructor

    }

    @Override
    public void onActivityCreated(Bundle state){
        super.onActivityCreated(state);
        //Construimos el mensaje a mostrar
        Bundle b = this.getArguments();
        Bundle bundle = this.getArguments();
        //this.Saludar(Preferences.importPreferences(s));
        Saludar("NombrePorDefecto","ApellidoPorDefecto");
        //Creamos la informacion a pasar entre actividades
        //Añadimos la informacion al intent
        Resources resources = getResources();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_saludo, container, false);

    }
    public void Saludar(String nombre, String apellido){
        TextView txtSaludo = (TextView)getView().findViewById(R.id.TxtSaludo);
        txtSaludo.setText("Bienvenido " + nombre+" " +apellido);
    }


}
