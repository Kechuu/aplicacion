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
import Modelos.Usuario;

public class MainActivity extends AppCompatActivity {

    /*
    String id = mAuth.getCurrentUser().getUid();
    Toast.makeText(MainActivity.this, id, Toast.LENGTH_SHORT).show();
     */
    private TextView tvDias;
    private ImageButton btNoticias, btnComida, btnRopa, btnRopaBebe, btnContactos, btnFarmacias;
    private Button btnPerfil;
    private FirebaseDatabase database;
    private String NOMBRE_USUARIO = "";
    private String APELLIDO_USUARIO = "";
    private String CORREO_USUARIO = "";
    private String BARRIO_USUARIO = "";
    private String CALLE_USUARIO = "";
    private String NROCASA_USUARIO = "";
    private String TELEFONO_USUARIO = "";
    private boolean MOSTRAR_USUARIO;
    private int JERARQUIA_USUARIO=0;
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
        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();

        btnPerfil.setOnClickListener(v -> {
            switch (JERARQUIA_USUARIO){
                case 0:
                    Intent intenten = new Intent(MainActivity.this, PerfilPersonalConfigurarActivity.class);
                    intenten.putExtra("nombre",NOMBRE_USUARIO);
                    intenten.putExtra("apellido", APELLIDO_USUARIO);
                    intenten.putExtra("correo", CORREO_USUARIO);
                    intenten.putExtra("jerarquia", JERARQUIA_USUARIO);
                    intenten.putExtra("telefono", TELEFONO_USUARIO);
                    intenten.putExtra("barrio", BARRIO_USUARIO);
                    intenten.putExtra("calle", CALLE_USUARIO);
                    intenten.putExtra("nroCasa", NROCASA_USUARIO);
                    intenten.putExtra("mostrar", MOSTRAR_USUARIO);
                    startActivity(intenten);
                    break;

                case 1:

                    Intent intenten1 = new Intent(MainActivity.this, ConfigurarPerfilNoticiasActivity.class);
                    intenten1.putExtra("nombre",NOMBRE_USUARIO);
                    intenten1.putExtra("apellido", APELLIDO_USUARIO);
                    intenten1.putExtra("correo", CORREO_USUARIO);
                    intenten1.putExtra("jerarquia", JERARQUIA_USUARIO);
                    intenten1.putExtra("telefono", TELEFONO_USUARIO);
                    intenten1.putExtra("barrio", BARRIO_USUARIO);
                    intenten1.putExtra("calle", CALLE_USUARIO);
                    intenten1.putExtra("nroCasa", NROCASA_USUARIO);
                    intenten1.putExtra("mostrar", MOSTRAR_USUARIO);
                    startActivity(intenten1);
                    break;

                case 2:

                    Intent intenten2 = new Intent(MainActivity.this, ConfigurarPerfilNegocioActivity.class);
                    intenten2.putExtra("nombre",NOMBRE_USUARIO);
                    intenten2.putExtra("apellido", APELLIDO_USUARIO);
                    intenten2.putExtra("correo", CORREO_USUARIO);
                    intenten2.putExtra("jerarquia", JERARQUIA_USUARIO);
                    intenten2.putExtra("telefono", TELEFONO_USUARIO);
                    intenten2.putExtra("barrio", BARRIO_USUARIO);
                    intenten2.putExtra("calle", CALLE_USUARIO);
                    intenten2.putExtra("nroCasa", NROCASA_USUARIO);
                    intenten2.putExtra("mostrar", MOSTRAR_USUARIO);
                    startActivity(intenten2);
                    break;
                default:
                    Toast.makeText(MainActivity.this, "No se puede saber el destino", Toast.LENGTH_SHORT).show();
                    break;
            }
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
                    NOMBRE_USUARIO = usuario.getNombre();
                    APELLIDO_USUARIO = usuario.getApellido();
                    JERARQUIA_USUARIO = usuario.getJerarquia();
                    CORREO_USUARIO = usuario.getCorreo();
                    TELEFONO_USUARIO = usuario.getTelefonoCelular();
                    BARRIO_USUARIO = usuario.getBarrio();
                    CALLE_USUARIO = usuario.getCalle();
                    NROCASA_USUARIO = usuario.getNrocasa();
                    MOSTRAR_USUARIO = usuario.isMostrar();
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
