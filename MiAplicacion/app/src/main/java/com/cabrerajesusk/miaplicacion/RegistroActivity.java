package com.cabrerajesusk.miaplicacion;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import Modelos.Usuario;

public class RegistroActivity extends AppCompatActivity {

    private EditText txtNombre, txtApellido, txtCorreo, txtContraseña,txtContraseñaRepetida,txtTelefono, txtBarrio,txtCalle,txtNroCasa;
    private CheckBox cbxMostrar;
    private Button btnRegistrar;
    private FirebaseAuth mAuth;
    private FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();

        txtNombre = (EditText)findViewById(R.id.idNombreRegistro);
        txtApellido = (EditText)findViewById(R.id.idApellidoRegistro);
        txtCorreo = (EditText)findViewById(R.id.idCorreoRegistro);
        txtContraseña = (EditText)findViewById(R.id.idContraseñaRegistro);
        txtContraseñaRepetida = (EditText)findViewById(R.id.idContraseñaRepetidaRegistro);
        txtTelefono = (EditText)findViewById(R.id.idCelularRegistro);
        txtBarrio = (EditText)findViewById(R.id.idBarrioRegistro);
        txtCalle = (EditText)findViewById(R.id.idCalleRegistro);
        txtNroCasa = (EditText)findViewById(R.id.idNroCasaRegistro);
        cbxMostrar = (CheckBox) findViewById(R.id.idMostrarDireccionRegistro);
        btnRegistrar = (Button) findViewById(R.id.idRegistrarseRegistro);

        btnRegistrar.setOnClickListener(v -> {
            final String nombre = txtNombre.getText().toString();
            final String apellido = txtApellido.getText().toString();
            final String correo = txtCorreo.getText().toString();
            final String contraseña = txtContraseña.getText().toString();
            String telefono = txtTelefono.getText().toString();
            final String barrio = txtBarrio.getText().toString();
            final String calle = txtCalle.getText().toString();
            String nroCasa = txtNroCasa.getText().toString();
            final boolean mostrar = cbxMostrar.isSelected();

            if (isValidEmail(correo) && validarContraseña() && validar(nombre,apellido,telefono,barrio,calle)){

                String finalTelefono = telefono;
                mAuth.createUserWithEmailAndPassword(correo, contraseña)
                        .addOnCompleteListener(RegistroActivity.this, task -> {
                            if (task.isSuccessful()){
                                Toast.makeText(RegistroActivity.this, "Se registró correctamente", Toast.LENGTH_SHORT).show();
                                Usuario usuarios = new Usuario();
                                usuarios.setNombre(nombre);
                                usuarios.setApellido(apellido);
                                usuarios.setCorreo(correo);
                                usuarios.setTelefonoCelular(telefono);
                                usuarios.setBarrio(barrio);
                                usuarios.setCalle(calle);
                                usuarios.setNrocasa(nroCasa);
                                usuarios.setMostrar(mostrar);
                                FirebaseUser currentUser = mAuth.getCurrentUser();
                                DatabaseReference reference = database.getReference("Usuarios/"+currentUser.getUid());
                                reference.setValue(usuarios);
                                finish();
                            }else{
                                Toast.makeText(RegistroActivity.this, "Error al registrarse", Toast.LENGTH_SHORT).show();
                            }
                        });

            }else{
                Toast.makeText(RegistroActivity.this, "Validacion correcta", Toast.LENGTH_SHORT).show();
            }

        });
    }
    private boolean isValidEmail(CharSequence target){
        return !TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }
    public boolean validarContraseña(){
        String contraseña,contraseñaRepetida;
        contraseña = txtContraseña.getText().toString();
        contraseñaRepetida = txtContraseñaRepetida.getText().toString();
        if (contraseña.equals(contraseñaRepetida)){
            if (contraseña.length()>=6 && contraseña.length()<=16){
                return true;
            }else{
                Toast.makeText(this, "La contraseña debe tener de 6 a 16 caracteres", Toast.LENGTH_SHORT).show();
                return false;
            }
        }else return false;
    }
    public boolean validar(String nombre,String apellido,String telefono,String barrio,String calle){
        return !nombre.isEmpty() && !apellido.isEmpty()&& !telefono.isEmpty()&& !barrio.isEmpty()&& !calle.isEmpty();
    }
}
