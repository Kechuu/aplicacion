package EntidadesNoticias;

import android.os.Bundle;

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
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import Modelos.Noticias;

public class NoticiasActivity extends AppCompatActivity {

    private RecyclerView rvNoticias;
    private AdapterNoticias adapterNoticias;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_noticias);

        rvNoticias = (RecyclerView) findViewById(R.id.rvInicioNoticias);
        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("PublicacionesNoticias");
        //Query query = databaseReference.limitToLast(10);

        adapterNoticias = new AdapterNoticias(this);
        LinearLayoutManager l = new LinearLayoutManager(this);
        rvNoticias.setLayoutManager(l);
        rvNoticias.setAdapter(adapterNoticias);

        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Noticias n = dataSnapshot.getValue(Noticias.class);
                adapterNoticias.addNoticia(n);
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
    }

}
