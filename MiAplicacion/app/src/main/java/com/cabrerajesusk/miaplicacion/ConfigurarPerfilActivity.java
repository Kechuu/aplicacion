package com.cabrerajesusk.miaplicacion;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ConfigurarPerfilActivity extends AppCompatActivity {


    private ImageView fotoPerfil;
    private Button cambiarFoto,guardarConfiguracion;
    private EditText numeroTelefono,barrio,calle,nroCasa;
    private CheckBox mostrar,verificar;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configurar_perfil);
        
        fotoPerfil = (ImageView) findViewById(R.id.idFotoConfigurarPerfil);
        cambiarFoto = (Button) findViewById(R.id.idCambiarFotoConfigurarPerfil);
        numeroTelefono = (EditText) findViewById(R.id.idNroTelefonoConfigurarPerfil);
        barrio = (EditText) findViewById(R.id.idBarrioConfigurarPerfil);
        calle = (EditText) findViewById(R.id.idCalleConfigurarPerfil);
        nroCasa = (EditText) findViewById(R.id.idNroCasaConfigurarPerfil);
        mostrar = (CheckBox) findViewById(R.id.idMostrarDireccionConfigurarPerfil);
        guardarConfiguracion = (Button) findViewById(R.id.idBotonGuardarConfigurarPerfil);
        verificar = (CheckBox) findViewById(R.id.idVerificarConfigurarPerfil);
    }
}
