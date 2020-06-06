package com.cabrerajesusk.miaplicacion;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

public class RegistroActivity extends Fragment {

    private EditText nombre, apellido, correo, contraseña, contraseñaRepetida, numero, barrio, calle, nroCasa;
    private CheckBox direccion;
    private Button registrarse;

    public RegistroActivity() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_registro, container, false);
    }

    @Override
    public void onViewCreated(View view , Bundle saveInstanceState){
        super.onViewCreated(view,saveInstanceState);
        nombre = (EditText) view.findViewById(R.id.idNombreRegistro);
        apellido = (EditText) view.findViewById(R.id.idApellidoRegistro);
        correo = (EditText) view.findViewById(R.id.idCorreoRegistro);
        contraseña = (EditText) view.findViewById(R.id.idContraseñaRegistro);
        contraseñaRepetida = (EditText) view.findViewById(R.id.idContraseñaRepetidaRegistro);
        numero = (EditText) view.findViewById(R.id.idCelularRegistro);
        barrio = (EditText) view.findViewById(R.id.idBarrioRegistro);
        calle = (EditText) view.findViewById(R.id.idCalleRegistro);
        nroCasa = (EditText) view.findViewById(R.id.idNroCasaRegistro);
        direccion = (CheckBox) view.findViewById(R.id.idMostrarDireccionRegistro);
        registrarse = (Button) view.findViewById(R.id.idRegistrarseRegistro);

        //Esto se usara para navegar desde la ventana actual a las demas
        final NavController navController = Navigation.findNavController(view);

        registrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Metodo de Registracion

                //Esto vuelve al login cuando ya se haya hecho todo el proceso
                navController.navigate(R.id.loginActivity);
            }
        });
    }
}
