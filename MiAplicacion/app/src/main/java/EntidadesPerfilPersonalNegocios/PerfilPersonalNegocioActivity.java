package EntidadesPerfilPersonalNegocios;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.cabrerajesusk.miaplicacion.ConfigurarPerfilNegocioActivity;
import com.cabrerajesusk.miaplicacion.LoginActivity;
import com.cabrerajesusk.miaplicacion.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import EntidadesPublicaciones.AdapterPublicaciones;
import Modelos.Publicaciones;
import Modelos.Usuario;
import Publicaciones.CrearPublicacionActivity;
import Publicaciones.CrearPublicacionRopaActivity;
import Publicaciones.CrearPublicacionRopaBebeActivity;

import static com.google.firebase.auth.FirebaseAuth.getInstance;


public class PerfilPersonalNegocioActivity extends AppCompatActivity {

    private TextView nombre,rubro,direccion;
    private ImageView fotoPerfil;
    private Button configPerfil,crearPublicacion;
    private FirebaseAuth mAuth;
    private FirebaseDatabase database;
    private RecyclerView rvInicio;
    private String idUsuario="";
    private DatabaseReference databaseReference,databaseReference1,databaseReference2;
    private AdapterPublicaciones adapterPublicaciones;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_personal_negocio);
        Bundle dato = this.getIntent().getExtras();
        int bussines = dato.getInt("bussines");

        nombre = (TextView) findViewById(R.id.idNombreNegocioPerfilPersonalNegocio);
        fotoPerfil = (ImageView) findViewById(R.id.idFotoPerfilPerfilPersonalNegocio);
        rubro = (TextView) findViewById(R.id.idRubroPerfilPersonalNegocio);
        direccion = (TextView) findViewById(R.id.idDirecionPerfilPersonalNegocio);
        configPerfil = (Button) findViewById(R.id.idBotonConfigurarPerfilPersonalNegocio);
        crearPublicacion = (Button) findViewById(R.id.idBotonCrearPublicacionPersonalNegocio);
        rvInicio = (RecyclerView) findViewById(R.id.rvInicioPerfilPersonalNegocio);

        mAuth = getInstance();
        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("PublicacionesComida");
        databaseReference1 = database.getReference("PublicacionesRopa");
        databaseReference2 = database.getReference("PublicacionesRopaBebe");

        adapterPublicaciones = new AdapterPublicaciones(this);
        LinearLayoutManager l = new LinearLayoutManager(this);
        rvInicio.setLayoutManager(l);
        rvInicio.setAdapter(adapterPublicaciones);

        crearPublicacion.setOnClickListener(v -> {
            switch (bussines){
                case 1:
                    startActivity(new Intent(PerfilPersonalNegocioActivity.this, CrearPublicacionActivity.class));
                    break;
                case 2:
                    startActivity(new Intent(PerfilPersonalNegocioActivity.this, CrearPublicacionRopaActivity.class));
                    break;
                case 3:
                    startActivity(new Intent(PerfilPersonalNegocioActivity.this, CrearPublicacionRopaBebeActivity.class));
                    break;
                default:
                    break;
            }
        });
        configPerfil.setOnClickListener(v ->{
            startActivity(new Intent(PerfilPersonalNegocioActivity.this, ConfigurarPerfilNegocioActivity.class));
        });

        //Crear el holder y el adpter del cardView de perfil personal Negocio y noticia
        //El filtrado de las noticias y publicaciones ya está hecho..
        //Me falta solamente implementarlo en el de perfil personal de noticias cuando cree el holder y el adapter de los dos

    }
    public void setCategoriaSelected(int level, String idUsuario){
        switch (level){
            case 0:
                databaseReference.orderByChild("idUsuario").startAt(idUsuario).endAt(idUsuario+"\uf8ff").addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                        Publicaciones publicaciones = dataSnapshot.getValue(Publicaciones.class);
                        adapterPublicaciones.addPublicaciones(publicaciones);
                    }

                    @Override
                    public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                    }

                    @Override
                    public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

                    }

                    @Override
                    public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
                break;
            case 1:
                databaseReference1.orderByChild("idUsuario").startAt(idUsuario).endAt(idUsuario+"\uf8ff").addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                        Publicaciones publicaciones = dataSnapshot.getValue(Publicaciones.class);
                        adapterPublicaciones.addPublicaciones(publicaciones);
                    }

                    @Override
                    public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                    }

                    @Override
                    public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

                    }

                    @Override
                    public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
                break;
            case 2:
                databaseReference2.orderByChild("idUsuario").startAt(idUsuario).endAt(idUsuario+"\uf8ff").addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                        Publicaciones publicaciones = dataSnapshot.getValue(Publicaciones.class);
                        adapterPublicaciones.addPublicaciones(publicaciones);
                    }

                    @Override
                    public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                    }

                    @Override
                    public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

                    }

                    @Override
                    public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
                break;
            default:
                break;
        }
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
                    String nombnree = usuario.getNombreEmpresa();
                    String foto = usuario.getUrlFoto();
                    String rubroo = usuario.getRubro();
                    String barrio = usuario.getBarrio();
                    String calle = usuario.getCalle();
                    String nroCasa = usuario.getNrocasa();
                    boolean mostrar = usuario.isMostrar();
                    idUsuario = mAuth.getCurrentUser().getUid();

                    rubro.setText(rubroo);
                    nombre.setText(nombnree);
                    if (mostrar == true){
                        direccion.setText(barrio+" "+calle+" N° "+nroCasa);
                        direccion.setVisibility(View.VISIBLE);
                    }else{
                        direccion.setVisibility(View.GONE);
                    }

                    Glide.with(PerfilPersonalNegocioActivity.this)
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
        startActivity(new Intent(PerfilPersonalNegocioActivity.this, LoginActivity.class));
        finish();
    }
}
