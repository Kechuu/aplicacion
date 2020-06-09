package com.cabrerajesusk.miaplicacion;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class PerfilPersonalConfigurarActivity extends AppCompatActivity {

    private TextView tvNombreApellido, tvDireccion;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_personal_configurar);

        tvNombreApellido = (TextView) findViewById(R.id.idNombrePerfilPersonalConfig);
        tvDireccion = (TextView) findViewById(R.id.idDireccionPerfilPersonalConfig);

        Bundle datos = this.getIntent().getExtras();

        String nombre = datos.getString("nombre");
        String apellido = datos.getString("apellido");
        String barrio = datos.getString("barrio");
        String calle = datos.getString("calle");
        String nroCasa = datos.getString("nroCasa");
        boolean mostrar = datos.getBoolean("mostrar");
        tvNombreApellido.setText(nombre+" "+apellido);
        if (mostrar){
            tvDireccion.setText(barrio+" "+calle+" N° "+nroCasa);
        }else{
            //Hacer invisible el text que contiene la direccion
        }
    }
}
