package com.cabrerajesusk.miaplicacion;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import static com.google.firebase.auth.FirebaseAuth.getInstance;

public class LoginActivity extends AppCompatActivity {

    private EditText txtCorreo,txtPassword;
    private Button btnLogear,btnRegistrarse,btnContactanos;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        txtCorreo = (EditText) findViewById(R.id.idCorreoLogin);
        txtPassword = (EditText) findViewById(R.id.idContraseñaLogin);
        btnLogear = (Button) findViewById(R.id.idEntrarLogin);
        btnRegistrarse = (Button) findViewById(R.id.idRegistrarseLogin);
        btnContactanos = (Button) findViewById(R.id.idContactarnosLogin);
        mAuth = getInstance();

        btnLogear.setOnClickListener(v -> {
            String correo = txtCorreo.getText().toString();
            if (isValidEmail(correo)){
                String contraseña = txtPassword.getText().toString();
                mAuth.signInWithEmailAndPassword(correo, contraseña)
                        .addOnCompleteListener(LoginActivity.this, task -> {

                            if (task.isSuccessful()){
                                Toast.makeText(LoginActivity.this, "Se logeo correctamente", Toast.LENGTH_SHORT).show();
                                nextActivity();
                            }else{
                                Toast.makeText(LoginActivity.this, "No se encuentra el usuario", Toast.LENGTH_SHORT).show();
                            }
                        });
            }else{
                Toast.makeText(LoginActivity.this, "Error contraseña o correo incorrectos", Toast.LENGTH_SHORT).show();
            }
        });

        btnRegistrarse.setOnClickListener(v -> startActivity(new Intent(LoginActivity.this, RegistroActivity.class)));
        btnContactanos.setOnClickListener(v -> startActivity(new Intent(LoginActivity.this,ContactanosActivity.class)));
    }


    private boolean isValidEmail(CharSequence target){
        return !TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }

    @Override
    protected void onResume() {
        super.onResume();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser!=null){
            Toast.makeText(this, "Bienvenido!", Toast.LENGTH_SHORT).show();
            nextActivity();
        }
    }
    private void nextActivity(){
        startActivity(new Intent(LoginActivity.this, MainActivity.class));
        finish();
    }
}
