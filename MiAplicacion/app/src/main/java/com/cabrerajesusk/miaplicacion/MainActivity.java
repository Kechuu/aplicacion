package com.cabrerajesusk.miaplicacion;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import EntidadesNoticias.NoticiasActivity;
import EntidadesPerfilPersonalNegocios.PerfilPersonalNegocioActivity;
import EntidadesPerfilPersonalNoticias.PerfilPersonalNoticiasActivity;
import Modelos.Usuario;
import static com.google.firebase.auth.FirebaseAuth.*;

public class MainActivity extends AppCompatActivity {

    private TextView tvDias;
    private ImageButton btNoticias, btnComida, btnRopa, btnRopaBebe, btnContactos, btnFarmacias;
    private Button btnPerfil;
    private FirebaseDatabase database;
    private int JERARQUIA_USUARIO=0;
    private String ID_USUARIO="";
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvDias=(TextView) findViewById(R.id.idDiasInicio);
        btNoticias = (ImageButton) findViewById(R.id.idNoticiasInicio);
        btnComida = (ImageButton) findViewById(R.id.idComidaInicio);
        btnRopa = (ImageButton) findViewById(R.id.idRopaInicio);
        btnRopaBebe = (ImageButton) findViewById(R.id.idBebeInicio);
        btnContactos = (ImageButton) findViewById(R.id.idContactoInicio);
        btnFarmacias= (ImageButton) findViewById(R.id.idFarmaciaInicio);
        btnPerfil = (Button) findViewById(R.id.idPerfilInicio);
        mAuth = getInstance();
        database = FirebaseDatabase.getInstance();

        btnPerfil.setOnClickListener(v -> {
            switch (JERARQUIA_USUARIO){
                case 0:
                    Intent intenten = new Intent(MainActivity.this, PerfilPersonalConfigurarActivity.class);
                    intenten.putExtra("jerarquia", JERARQUIA_USUARIO);
                    intenten.putExtra("id",ID_USUARIO);
                    startActivity(intenten);
                    break;

                case 1:

                    Intent intenten1 = new Intent(MainActivity.this, PerfilPersonalNoticiasActivity.class);
                    intenten1.putExtra("jerarquia", JERARQUIA_USUARIO);
                    intenten1.putExtra("id",ID_USUARIO);
                    startActivity(intenten1);
                    break;

                case 2:

                    Intent intenten2 = new Intent(MainActivity.this, PerfilPersonalNegocioActivity.class);
                    intenten2.putExtra("jerarquia", JERARQUIA_USUARIO);
                    intenten2.putExtra("id",ID_USUARIO);
                    startActivity(intenten2);
                    break;
                default:
                    Toast.makeText(MainActivity.this, "No se puede saber el destino", Toast.LENGTH_SHORT).show();
                    break;
            }
        });

        btNoticias.setOnClickListener(v->{
            startActivity(new Intent(MainActivity.this, NoticiasActivity.class));
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
                    JERARQUIA_USUARIO = usuario.getJerarquia();
                    ID_USUARIO = mAuth.getCurrentUser().getUid();
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
        startActivity(new Intent(MainActivity.this, LoginActivity.class));
        finish();
    }
}
