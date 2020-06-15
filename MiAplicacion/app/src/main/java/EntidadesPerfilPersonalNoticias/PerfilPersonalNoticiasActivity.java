package EntidadesPerfilPersonalNoticias;

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
import com.cabrerajesusk.miaplicacion.ConfigurarPerfilNoticiasActivity;
import com.cabrerajesusk.miaplicacion.LoginActivity;
import com.cabrerajesusk.miaplicacion.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import Modelos.Usuario;
import Publicaciones.CrearNoticiasActivity;

import static com.google.firebase.auth.FirebaseAuth.getInstance;


public class PerfilPersonalNoticiasActivity extends AppCompatActivity {

    private TextView nombre,rubro,direccion;
    private ImageView fotoPerfil;
    private Button configurarPerfil,crearPublicacion;
    private FirebaseAuth mAuth;
    private FirebaseDatabase database;
    private String idUsuarioo,correoo,nombree,apellidoo, telefonoCelularr,barrioo,callee,nrocasaa,urlFotoo,nombreEmpresaa,rubroo;
    private int jerarquiaa;
    private boolean mostrarr;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_personal_noticias);

        nombre = (TextView) findViewById(R.id.idNombreNegocioPerfilPersonalNoticias);
        fotoPerfil = (ImageView) findViewById(R.id.idFotoPerfilPerfilPersonalNoticias);
        rubro = (TextView) findViewById(R.id.idRubroPerfilPersonalNoticias);
        direccion = (TextView) findViewById(R.id.idDirecionPerfilPersonalNoticias);
        configurarPerfil = (Button) findViewById(R.id.idBotonConfigurarPerfilPersonalNoticias);
        crearPublicacion = (Button) findViewById(R.id.idBotonCrearPublicacionPersonalNoticias);

        mAuth = getInstance();
        database = FirebaseDatabase.getInstance();

        crearPublicacion.setOnClickListener(v -> {
            startActivity(new Intent(PerfilPersonalNoticiasActivity.this, CrearNoticiasActivity.class));
        });
        configurarPerfil.setOnClickListener(v ->{
            startActivity(new Intent(PerfilPersonalNoticiasActivity.this, ConfigurarPerfilNoticiasActivity.class));
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
                    nombree = usuario.getNombreEmpresa();
                    apellidoo = usuario.getApellido();
                    telefonoCelularr = usuario.getTelefonoCelular();
                    urlFotoo = usuario.getUrlFoto();
                    rubroo = usuario.getRubro();
                    barrioo = usuario.getBarrio();
                    callee = usuario.getCalle();
                    nrocasaa = usuario.getNrocasa();
                    mostrarr = usuario.isMostrar();
                    nombreEmpresaa = usuario.getNombreEmpresa();

                    rubro.setText(rubroo);
                    nombre.setText(nombreEmpresaa);
                    if (mostrarr == true){
                        direccion.setText(barrioo+" "+callee+" N° "+nrocasaa);
                        direccion.setVisibility(View.VISIBLE);
                    }else{
                        direccion.setVisibility(View.GONE);
                    }

                    Glide.with(PerfilPersonalNoticiasActivity.this)
                            .load(urlFotoo).into(fotoPerfil);
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
        startActivity(new Intent(PerfilPersonalNoticiasActivity.this, LoginActivity.class));
        finish();
    }
}
