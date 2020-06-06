package com.cabrerajesusk.miaplicacion;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends Fragment {

    private TextView dias;
    private ImageButton noticias, comida, ropa, ropaBebe, contactos, farmacias;
    private Button perfil;

    public MainActivity() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_main, container, false);
    }

    @Override
    public void onViewCreated(View view , Bundle saveInstanceState){
        super.onViewCreated(view,saveInstanceState);

        dias = (TextView) view.findViewById(R.id.idDiasInicio);
        noticias = (ImageButton) view.findViewById(R.id.idNoticiasInicio);
        comida = (ImageButton) view.findViewById(R.id.idComidaInicio);
        ropa = (ImageButton) view.findViewById(R.id.idRopaInicio);
        ropaBebe = (ImageButton) view.findViewById(R.id.idBebeInicio);
        contactos = (ImageButton) view.findViewById(R.id.idContactoInicio);
        farmacias = (ImageButton) view.findViewById(R.id.idFarmaciaInicio);
        perfil = (Button) view.findViewById(R.id.idPerfilInicio);

        //Esto se usara para navegar desde la ventana actual a las demas
        final NavController navController = Navigation.findNavController(view);

        noticias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.noticias);
            }
        });
        comida.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.publicacionesActivity);
            }
        });
        ropa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.publicacionesActivity);
            }
        });
        ropaBebe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.publicacionesActivity);
            }
        });
        contactos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.contactos);
            }
        });
        farmacias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.farmacias);
            }
        });
        perfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Mejorar el metodo para determinar a que perfil debe redireccionarse
                if(true){
                    navController.navigate(R.id.perfilPersonal);
                }
            }
        });
    }
}
