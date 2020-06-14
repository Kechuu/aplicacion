package EntidadesPerfilSeguidor;

import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.cabrerajesusk.miaplicacion.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import static com.google.firebase.auth.FirebaseAuth.getInstance;

public class PerfilSeguidorActivity extends AppCompatActivity {

    private TextView nombre,rubro,direccion;
    private ImageView fotoPerfil;
    private CheckBox verificar;
    private FirebaseAuth mAuth;
    private FirebaseDatabase database;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_seguidor);

        nombre = (TextView) findViewById(R.id.idNombreNegocioPerfilSeguidor);
        rubro = (TextView) findViewById(R.id.idRubroPerfilSeguidor);
        fotoPerfil = (ImageView) findViewById(R.id.idFotoPerfilPerfilSeguidor);
        direccion = (TextView) findViewById(R.id.idDirecionPerfilSeguidor);
        verificar = (CheckBox) findViewById(R.id.idVerificarPerfilSeguidor);

        mAuth = getInstance();
        database = FirebaseDatabase.getInstance();
    }
}
