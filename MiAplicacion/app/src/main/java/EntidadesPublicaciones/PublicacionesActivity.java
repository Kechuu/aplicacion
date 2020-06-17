package EntidadesPublicaciones;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cabrerajesusk.miaplicacion.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import Modelos.Publicaciones;

public class PublicacionesActivity extends AppCompatActivity {

    private Button categoria,categoria1,categoria2,categoria3;
    private EditText escribirCategoria;
    private ImageButton buscar;
    private RecyclerView rvInicio;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference,databaseReference1,databaseReference2;
    private AdapterPublicaciones adapterPublicaciones;
    private int level =0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publicaciones);

        Bundle dts = this.getIntent().getExtras();
        level = dts.getInt("level");

        categoria = (Button) findViewById(R.id.idCategoriaPublicaciones);
        categoria1 = (Button) findViewById(R.id.idCategoria1Publicaciones);
        categoria2 = (Button) findViewById(R.id.idCategoria2Publicaciones);
        categoria3 = (Button) findViewById(R.id.idCategoria3Publicaciones);
        escribirCategoria = (EditText) findViewById(R.id.idEscribirCategoriaPublicaciones);
        buscar = (ImageButton) findViewById(R.id.idBuscarCategoriaPublicaciones);
        rvInicio = (RecyclerView) findViewById(R.id.rvInicio);

        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("PublicacionesComida");
        databaseReference1 = database.getReference("PublicacionesRopa");
        databaseReference2 = database.getReference("PublicacionesRopaBebe");

        adapterPublicaciones = new AdapterPublicaciones(this);
        LinearLayoutManager l = new LinearLayoutManager(this);
        rvInicio.setLayoutManager(l);
        rvInicio.setAdapter(adapterPublicaciones);

        setDatabaseReference(level);
    }
    public void setDatabaseReference(int level){
        switch (level){
            case 0:
                categoria.setText("Sandwitch");
                categoria1.setText("Pizzas");
                categoria2.setText("Empanadas");
                categoria3.setText("Promos");
                databaseReference.addChildEventListener(new ChildEventListener() {
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
                categoria.setText("Remeras");
                categoria1.setText("Pantalones");
                categoria2.setText("Zapatos");
                categoria3.setText("Camperas");
                databaseReference1.addChildEventListener(new ChildEventListener() {
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
                categoria.setText("Recien Nacidos");
                categoria1.setText("Cunas");
                categoria2.setText("Pañales");
                categoria3.setText("Leche");
                databaseReference2.addChildEventListener(new ChildEventListener() {
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
                Toast.makeText(this, "Error de carga", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
