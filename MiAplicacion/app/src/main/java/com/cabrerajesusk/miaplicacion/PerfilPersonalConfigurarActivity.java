package com.cabrerajesusk.miaplicacion;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import Modelos.Usuario;

public class PerfilPersonalConfigurarActivity extends AppCompatActivity {

    private TextView tvNombreApellido, tvDireccion;
    private ImageView fotoPerfil;
    private FirebaseDatabase database;
    private FirebaseAuth mAuth;
    private Button configuracion;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_personal_configurar);

        tvNombreApellido = (TextView) findViewById(R.id.idNombrePerfilPersonalConfig);
        tvDireccion = (TextView) findViewById(R.id.idDireccionPerfilPersonalConfig);
        fotoPerfil = (ImageView) findViewById(R.id.idFotoPerfilPersonalConfig);
        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        configuracion = (Button) findViewById(R.id.idConfigurarPerfilPersonalConfig);

        configuracion.setOnClickListener(v -> {
            startActivity(new Intent(PerfilPersonalConfigurarActivity.this, ConfigurarPerfilActivity.class));
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

                    String nombre = usuario.getNombre();
                    String apellido = usuario.getApellido();
                    String barrio = usuario.getBarrio();
                    String calle = usuario.getCalle();
                    String nroCasa = usuario.getNrocasa();
                    String foto = usuario.getUrlFoto();
                    boolean mostrar = usuario.isMostrar();

                    tvNombreApellido.setText(nombre+" "+apellido);
                    if (mostrar == true){
                        tvDireccion.setText(barrio+" "+calle+" N° "+nroCasa);
                        tvDireccion.setVisibility(View.VISIBLE);
                    }else{
                        tvDireccion.setVisibility(View.GONE);
                    }

                    Glide.with(PerfilPersonalConfigurarActivity.this)
                            .load(foto).into(fotoPerfil);

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
        startActivity(new Intent(PerfilPersonalConfigurarActivity.this, LoginActivity.class));
        finish();
    }
}
