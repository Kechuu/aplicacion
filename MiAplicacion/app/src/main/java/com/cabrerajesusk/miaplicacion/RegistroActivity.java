package com.cabrerajesusk.miaplicacion;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Objects;
import Modelos.Usuario;
import id.zelory.compressor.Compressor;
import static com.google.firebase.auth.FirebaseAuth.getInstance;

public class RegistroActivity extends AppCompatActivity {

    private EditText txtNombre, txtApellido, txtCorreo, txtContraseña,txtContraseñaRepetida,txtTelefono, txtBarrio,txtCalle,txtNroCasa;
    private Button fotoPerfil;
    private CheckBox cbxMostrar;
    private ImageView foto;
    private Button btnRegistrar;
    private FirebaseAuth mAuth;
    private FirebaseDatabase database;
    private StorageReference storageReference;
    private ProgressDialog cargando;
    private Bitmap thumb_bitmap = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        mAuth = getInstance();
        database = FirebaseDatabase.getInstance();
        storageReference = FirebaseStorage.getInstance().getReference().child("fotosPerfil");
        cargando = new ProgressDialog(this);

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
        fotoPerfil = (Button) findViewById(R.id.idFotoPErfilRegistros);
        foto = (ImageView) findViewById(R.id.idFotoRegistro);
        btnRegistrar = (Button) findViewById(R.id.idRegistrarseRegistro);

        fotoPerfil.setOnClickListener(v -> {
            CropImage.startPickImageActivity(RegistroActivity.this);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CropImage.PICK_IMAGE_CHOOSER_REQUEST_CODE && resultCode == Activity.RESULT_OK){
            Uri imagenUri = CropImage.getPickImageResultUri(this,data);

            //Recortar Imagen

            CropImage.activity(imagenUri)
                    .setGuidelines(CropImageView.Guidelines.ON)
                    .setRequestedSize(480,480)
                    .setAspectRatio(1,1).start(RegistroActivity.this);

        }
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE){
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK){
                btnRegistrar.setEnabled(true);
                Uri resultUri = result.getUri();

                File url = new File(resultUri.getPath());

                Picasso.with(this).load(url).into(foto);

                //Comprimir imagen para que no sea pesada

                try {
                    thumb_bitmap = new Compressor(this)
                            .setMaxWidth(480)
                            .setMaxHeight(480)
                            .setQuality(90)
                            .compressToBitmap(url);
                }catch (IOException e){
                    e.printStackTrace();
                }
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                thumb_bitmap.compress(Bitmap.CompressFormat.JPEG,90,byteArrayOutputStream);
                final byte[] thumb_byte = byteArrayOutputStream.toByteArray();
                //fin del compresor

                int p = (int) (Math.random() * 25 + 1); int s = (int) (Math.random() * 25 + 1);
                int t = (int) (Math.random() * 25 + 1); int c = (int) (Math.random() * 25 + 1);
                int numero1 = (int) (Math.random() * 1012 + 2111);
                int numero2 = (int) (Math.random() * 1012 + 2111);

                String[] elementos ={"a","b","c","d","e","f","g","h","i","k",
                        "k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"};

                final String aleatorio = elementos[p]+elementos[s]+
                        numero1+elementos[t]+elementos[c]+numero2 + "comprimido.jpg";

                btnRegistrar.setOnClickListener(v -> {
                    cargando.setTitle("Cargando...");
                    cargando.setMessage("Creando Usuario");
                    cargando.setCancelable(false);
                    cargando.show();

                    final String nombre = txtNombre.getText().toString();
                    final String apellido = txtApellido.getText().toString();
                    final String correo = txtCorreo.getText().toString();
                    final String contraseña = txtContraseña.getText().toString();
                    String telefono = txtTelefono.getText().toString();
                    final String barrio = txtBarrio.getText().toString();
                    final String calle = txtCalle.getText().toString();
                    String nroCasa = txtNroCasa.getText().toString();
                    final boolean mostrar;
                    if (cbxMostrar.isSelected()){
                        mostrar = true;
                    }else{
                        mostrar = false;
                    }

                    final StorageReference ref = storageReference.child(aleatorio);
                    UploadTask uploadTask = ref.putBytes(thumb_byte);

                    Task<Uri> uriTask = uploadTask.continueWithTask(task -> {
                        if (!task.isSuccessful()){
                            throw Objects.requireNonNull(task.getException());
                        }
                        return ref.getDownloadUrl();
                    }).addOnCompleteListener(task -> {
                        Uri download = task.getResult();

                        if (isValidEmail(correo) && validarContraseña() && validar(nombre,apellido,telefono,barrio,calle)){

                            String finalTelefono = telefono;
                            mAuth.createUserWithEmailAndPassword(correo, contraseña)
                                    .addOnCompleteListener(RegistroActivity.this, task1 -> {
                                        if (task1.isSuccessful()){
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
                                            usuarios.setUrlFoto(download.toString());
                                            FirebaseUser currentUser = mAuth.getCurrentUser();
                                            DatabaseReference reference = database.getReference("Usuarios/"+currentUser.getUid());
                                            reference.setValue(usuarios);
                                            cargando.dismiss();
                                            finish();
                                        }else{
                                            Toast.makeText(RegistroActivity.this, "Error al registrarse", Toast.LENGTH_SHORT).show();
                                        }
                                    });

                        }else{
                            Toast.makeText(RegistroActivity.this, "Falta Completar Campos", Toast.LENGTH_SHORT).show();
                        }

                    });

                });

            }
        }
    }
}
