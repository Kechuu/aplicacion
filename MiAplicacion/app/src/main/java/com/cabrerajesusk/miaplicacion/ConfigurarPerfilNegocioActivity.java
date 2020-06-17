package com.cabrerajesusk.miaplicacion;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
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

public class ConfigurarPerfilNegocioActivity extends AppCompatActivity {

    private TextView nombre;
    private ImageView fotoPerfil;
    private Button cambiarFoto, guardarConfiguracion;
    private EditText rubro,nroTelefono,barrio,calle,nroLocal;
    private CheckBox mostrar,verificar;
    private FirebaseAuth mAuth;
    private FirebaseDatabase database;
    private String idUsuarioo,correoo,nombree,apellidoo, telefonoCelularr,barrioo,callee,nrocasaa,urlFotoo,nombreEmpresaa,rubroo;
    private int jerarquiaa;
    private boolean mostrarr;
    private Bitmap thumb_bitmap = null;
    private ProgressDialog cargando;
    private StorageReference storageReference;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configurar_perfil_negocio);

        nombre = (TextView) findViewById(R.id.idNombreConfigurarPerfilNegocio);
        fotoPerfil = (ImageView) findViewById(R.id.idFotoConfigurarPerfilNegocio);
        cambiarFoto = (Button) findViewById(R.id.idCambiarFotoConfigurarPerfilNegocio);
        rubro = (EditText) findViewById(R.id.idDescripcionConfigurarPerfilNegocio);
        nroTelefono = (EditText) findViewById(R.id.idNroConfigurarPerfilNegocio);
        barrio = (EditText) findViewById(R.id.idBarrioConfigurarPerfilNegocio);
        calle = (EditText) findViewById(R.id.idCalleConfigurarPerfilNegocio);
        nroLocal = (EditText) findViewById(R.id.idNroCasaConfigurarPerfilNegocio);
        mostrar = (CheckBox) findViewById(R.id.idMostrarDireccionConfigurarPerfilNegocio);
        guardarConfiguracion = (Button) findViewById(R.id.idBotonGuardarConfigurarPerfilNegocio);
        verificar = (CheckBox) findViewById(R.id.idVerificarConfigurarPerfilNegocio);

        cargando = new ProgressDialog(this);
        storageReference = FirebaseStorage.getInstance().getReference().child("fotosPerfil");

        mAuth = getInstance();
        database = FirebaseDatabase.getInstance();

        cambiarFoto.setOnClickListener(v -> {
            CropImage.startPickImageActivity(ConfigurarPerfilNegocioActivity.this);
        });

            guardarConfiguracion.setOnClickListener(v -> {
                cargando.setTitle("Cargando...");
                cargando.setMessage("Guardando Cambios...");
                cargando.setCancelable(false);
                cargando.show();

                String telefono = nroTelefono.getText().toString();
                final String barrioo = barrio.getText().toString();
                final String callee = calle.getText().toString();
                String nroCasaa = nroLocal.getText().toString();
                String rubroo = rubro.getText().toString();
                final boolean mostrarr;
                if (mostrar.isSelected()){
                    mostrarr = true;
                }else{
                    mostrarr = false;
                }
                Usuario usuarios = new Usuario();
                usuarios.setIdUsuario(idUsuarioo);
                usuarios.setCorreo(correoo);
                usuarios.setJerarquia(jerarquiaa);
                usuarios.setNombre(nombree);
                usuarios.setApellido(apellidoo);
                usuarios.setNombreEmpresa(nombreEmpresaa);
                usuarios.setTelefonoCelular(telefono);
                usuarios.setUrlFoto(urlFotoo);
                usuarios.setBarrio(barrioo);
                usuarios.setCalle(callee);
                usuarios.setNrocasa(nroCasaa);
                usuarios.setMostrar(mostrarr);
                usuarios.setRubro(rubroo);
                FirebaseUser currentUser = mAuth.getCurrentUser();
                DatabaseReference reference = database.getReference("Usuarios/"+currentUser.getUid());
                reference.setValue(usuarios);
                cargando.dismiss();
                finish();
            });

    }
    @Override
    protected void onResume() {
        super.onResume();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser!=null){
            DatabaseReference reference = database.getReference("Usuarios/"+currentUser.getUid());
            reference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    Usuario usuario = dataSnapshot.getValue(Usuario.class);
                    idUsuarioo = mAuth.getCurrentUser().getUid();
                    correoo = usuario.getCorreo();
                    jerarquiaa = usuario.getJerarquia();
                    nombree = usuario.getNombre();
                    apellidoo = usuario.getApellido();
                    telefonoCelularr = usuario.getTelefonoCelular();
                    urlFotoo = usuario.getUrlFoto();
                    rubroo = usuario.getRubro();
                    barrioo = usuario.getBarrio();
                    callee = usuario.getCalle();
                    nrocasaa = usuario.getNrocasa();
                    mostrarr = usuario.isMostrar();
                    nombreEmpresaa = usuario.getNombreEmpresa();

                    Glide.with(ConfigurarPerfilNegocioActivity.this)
                            .load(urlFotoo).into(fotoPerfil);
                    nroTelefono.setText(telefonoCelularr);
                    barrio.setText(barrioo);
                    calle.setText(callee);
                    nroLocal.setText(nrocasaa);
                    nombre.setText(nombree);
                    rubro.setText(rubroo);

                    if (mostrarr == true){
                        mostrar.setSelected(true);
                    }else{
                        mostrar.setSelected(false);
                    }
                }
                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }else{
            retunrLogin();
        }
    }
    private void retunrLogin(){
        startActivity(new Intent(ConfigurarPerfilNegocioActivity.this, LoginActivity.class));
        finish();
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
                    .setAspectRatio(1,1).start(ConfigurarPerfilNegocioActivity.this);

        }
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE){
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK){

                verificar.setSelected(true);
                Uri resultUri = result.getUri();

                File url = new File(resultUri.getPath());

                Picasso.with(this).load(url).into(fotoPerfil);

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

                guardarConfiguracion.setOnClickListener(v -> {
                    cargando.setTitle("Cargando...");
                    cargando.setMessage("Guardando Cambios...");
                    cargando.setCancelable(false);
                    cargando.show();

                    String telefono = nroTelefono.getText().toString();
                    final String barrioo = barrio.getText().toString();
                    final String callee = calle.getText().toString();
                    String nroCasaa = nroLocal.getText().toString();
                    String rubroo = rubro.getText().toString();
                    final boolean mostrarr;
                    if (mostrar.isSelected()){
                        mostrarr = true;
                    }else{
                        mostrarr = false;
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

                        Usuario usuarios = new Usuario();
                        usuarios.setIdUsuario(idUsuarioo);
                        usuarios.setCorreo(correoo);
                        usuarios.setJerarquia(jerarquiaa);
                        usuarios.setNombre(nombree);
                        usuarios.setApellido(apellidoo);
                        usuarios.setNombreEmpresa(nombreEmpresaa);
                        usuarios.setTelefonoCelular(telefono);
                        usuarios.setUrlFoto(urlFotoo);
                        usuarios.setBarrio(barrioo);
                        usuarios.setCalle(callee);
                        usuarios.setNrocasa(nroCasaa);
                        usuarios.setMostrar(mostrarr);
                        usuarios.setRubro(rubroo);
                        usuarios.setUrlFoto(download.toString());
                        FirebaseUser currentUser = mAuth.getCurrentUser();
                        DatabaseReference reference = database.getReference("Usuarios/"+currentUser.getUid());
                        reference.setValue(usuarios);
                        cargando.dismiss();
                        finish();
                    });

                });

            }
        }
    }
}
