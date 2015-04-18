package com.example.doer.modulo2;

import android.app.Activity;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TabHost;
import android.widget.ToggleButton;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link CrearOrden#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CrearOrden extends Fragment {
    private Button btnGuardar;
    private Button btnVerOrdenes;
    int idOrden = 1;
    private CheckBox chk1, chk2,chk3, chk4;
    private ToggleButton tgbSopa, tgbFrescoOGaseosa;
    private RadioButton rb1, rb2, rb3;
    private EditText etxtCafe, etxtTe,etxtCerveza,etxtWhisky,etxtRon,etxtTequila;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CrearOrden.
     */
    // TODO: Rename and change types and number of parameters
    public static CrearOrden newInstance(String param1, String param2) {
        CrearOrden fragment = new CrearOrden();

        return fragment;
    }

    public CrearOrden() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_crear_orden, container, false);
    }


}
