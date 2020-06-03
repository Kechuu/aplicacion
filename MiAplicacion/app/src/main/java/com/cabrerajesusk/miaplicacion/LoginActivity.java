package com.cabrerajesusk.miaplicacion;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    ImageView imagenLogin;
    EditText correoLogin;
    EditText contraseñaLogin;
    Button entrarLogin;
    Button registrarseLogin;
    Button contactanosLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        imagenLogin = (ImageView) findViewById(R.id.idImagenLogin);
        correoLogin = (EditText) findViewById(R.id.idCorreoLogin);
        contraseñaLogin = (EditText) findViewById(R.id.idContraseñaLogin);
        entrarLogin = (Button) findViewById(R.id.idEntrarLogin);
        registrarseLogin = (Button) findViewById(R.id.idRegistrarseLogin);
        contactanosLogin = (Button) findViewById(R.id.idContactarnosLogin);

        registrarseLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,RegistroActivity.class));
                finish();
            }
        });

        contactanosLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,ContactanosActivity.class));
            }
        });

        entrarLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,MainActivity.class));
            }
        });
    }
}
