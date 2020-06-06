package com.cabrerajesusk.miaplicacion;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

public class LoginActivity extends Fragment {

    private EditText correo;
    private EditText password;
    private Button logear;
    private Button registrarse;
    private Button contactanos;

    public LoginActivity() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_login, container, false);
    }

    @Override
    public void onViewCreated(View view , Bundle saveInstanceState){
        super.onViewCreated(view,saveInstanceState);

        correo = (EditText) view.findViewById(R.id.idCorreoLogin);
        password = (EditText) view.findViewById(R.id.idContraseñaLogin);
        logear = (Button) view.findViewById(R.id.idEntrarLogin);
        registrarse = (Button) view.findViewById(R.id.idRegistrarseLogin);
        contactanos = (Button) view.findViewById(R.id.idContactarnosLogin);

        //Esto se usara para navegar desde la ventana actual a las demas
        final NavController navController = Navigation.findNavController(view);

        contactanos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.contactanosActivity);
            }
        });

        registrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.registroActivity);
            }
        });

        logear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Falta el metodo de verificacion de cuentas
                navController.navigate(R.id.mainActivity);
            }
        });
    }
}
