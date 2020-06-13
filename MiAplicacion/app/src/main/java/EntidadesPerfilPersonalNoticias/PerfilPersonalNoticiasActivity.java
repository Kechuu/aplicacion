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
import Publicaciones.CrearNoticias;

import static com.google.firebase.auth.FirebaseAuth.getInstance;


public class PerfilPersonalNoticiasActivity extends AppCompatActivity {

    private TextView nombre,seguidores,publicaciones,rubro,direccion;
    private ImageView fotoPerfil;
    private Button configurarPerfil,crearPublicacion;
    private FirebaseAuth mAuth;
    private FirebaseDatabase database;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_personal_noticias);

        nombre = (TextView) findViewById(R.id.idNombreNegocioPerfilPersonalNoticias);
        seguidores = (TextView) findViewById(R.id.idSeguidoresPerfilPersonalNoticias);
        fotoPerfil = (ImageView) findViewById(R.id.idFotoPerfilPerfilPersonalNoticias);
        publicaciones = (TextView) findViewById(R.id.idPublicacionesPerfilPersonalNoticias);
        rubro = (TextView) findViewById(R.id.idRubroPerfilPersonalNoticias);
        direccion = (TextView) findViewById(R.id.idDirecionPerfilPersonalNoticias);
        configurarPerfil = (Button) findViewById(R.id.idBotonConfigurarPerfilPersonalNoticias);
        crearPublicacion = (Button) findViewById(R.id.idBotonCrearPublicacionPersonalNoticias);

        mAuth = getInstance();
        database = FirebaseDatabase.getInstance();

        crearPublicacion.setOnClickListener(v -> {
            startActivity(new Intent(PerfilPersonalNoticiasActivity.this, CrearNoticias.class));
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
                    String nombnree = usuario.getNombre();
                    String foto = usuario.getUrlFoto();
                    String rubroo = usuario.getRubro();
                    String barrio = usuario.getBarrio();
                    String calle = usuario.getCalle();
                    String nroCasa = usuario.getNrocasa();
                    boolean mostrar = usuario.isMostrar();

                    rubro.setText(rubroo);
                    nombre.setText(nombnree);
                    if (mostrar == true){
                        direccion.setText(barrio+" "+calle+" N° "+nroCasa);
                        direccion.setVisibility(View.VISIBLE);
                    }else{
                        direccion.setVisibility(View.GONE);
                    }

                    Glide.with(PerfilPersonalNoticiasActivity.this)
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
        startActivity(new Intent(PerfilPersonalNoticiasActivity.this, LoginActivity.class));
        finish();
    }
}
