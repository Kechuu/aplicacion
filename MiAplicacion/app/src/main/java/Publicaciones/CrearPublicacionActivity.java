package Publicaciones;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.bumptech.glide.Glide;
import com.cabrerajesusk.miaplicacion.LoginActivity;
import com.cabrerajesusk.miaplicacion.R;
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
import Modelos.Publicaciones;
import Modelos.Usuario;
import de.hdodenhof.circleimageview.CircleImageView;
import id.zelory.compressor.Compressor;

import static com.google.firebase.auth.FirebaseAuth.getInstance;

public class CrearPublicacionActivity extends AppCompatActivity {

    private CircleImageView fotoPerfil;
    private TextView nombre;
    private EditText titulo;
    private EditText precio;
    private ImageView fotoPublicacion;
    private EditText descripcion;
    private Button crearPublicacion,elegirFoto;
    private FirebaseAuth mAuth;
    private FirebaseDatabase database;
    private String UID_USUARIO ="",URLFOTOPERFIL="";
    private Bitmap thumb_bitmap = null;
    private ProgressDialog cargando;
    private StorageReference storageReference;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_publicacion);

        fotoPerfil = (CircleImageView) findViewById(R.id.idFotoPerfilCrearPublicacion);
        nombre = (TextView) findViewById(R.id.idNombreCrearPublicacion);
        titulo = (EditText) findViewById(R.id.idTituloCrearPublicacion);
        precio = (EditText) findViewById(R.id.idPrecioCrearPublicacion);
        fotoPublicacion = (ImageView) findViewById(R.id.idFotoCrearPublicacion);
        descripcion = (EditText) findViewById(R.id.idDescripcionCrearPublicacion);
        crearPublicacion = (Button) findViewById(R.id.idCrearCrearPublicacion);
        elegirFoto = (Button) findViewById(R.id.idElegirFotoCrearPublicacion);
        cargando = new ProgressDialog(this);

        mAuth = getInstance();
        database = FirebaseDatabase.getInstance();
        storageReference = FirebaseStorage.getInstance().getReference().child("fotosComida");

        elegirFoto.setOnClickListener(v -> CropImage.startPickImageActivity(CrearPublicacionActivity.this));

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
                    String foto = usuario.getUrlFoto();
                    String nombree = usuario.getNombre();

                    UID_USUARIO = mAuth.getCurrentUser().getUid();
                    nombre.setText(nombree);
                    Glide.with(CrearPublicacionActivity.this)
                            .load(foto).into(fotoPerfil);
                    URLFOTOPERFIL =foto;
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }else{
            retunrLogin();
        }
    }
    private void retunrLogin(){
        startActivity(new Intent(CrearPublicacionActivity.this, LoginActivity.class));
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
                    .setAspectRatio(1,1).start(CrearPublicacionActivity.this);

        }
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE){
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK){
                crearPublicacion.setEnabled(true);
                Uri resultUri = result.getUri();

                File url = new File(resultUri.getPath());

                Picasso.with(this).load(url).into(fotoPublicacion);

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

                crearPublicacion.setOnClickListener(v -> {
                    cargando.setTitle("Subiendo...");
                    cargando.setMessage("Cargando publicacion");
                    cargando.setCancelable(false);
                    cargando.show();

                    final String nombreNegocio = nombre.getText().toString();
                    final String tituloNegocio = titulo.getText().toString();
                    final String precioNegocio = precio.getText().toString();
                    final String descripcionNegocio = descripcion.getText().toString();

                    final StorageReference ref = storageReference.child(aleatorio);
                    UploadTask uploadTask = ref.putBytes(thumb_byte);

                    Task<Uri> uriTask = uploadTask.continueWithTask(task -> {
                        if (!task.isSuccessful()){
                            throw Objects.requireNonNull(task.getException());
                        }
                        return ref.getDownloadUrl();
                    }).addOnCompleteListener(task -> {
                        Uri download = task.getResult();

                        if (validar(nombreNegocio,tituloNegocio,precioNegocio,descripcionNegocio)){
                            Publicaciones publicaciones = new Publicaciones();
                            publicaciones.setNombreNegocio(nombreNegocio);
                            publicaciones.setTitulo(tituloNegocio);
                            publicaciones.setPrecio(precioNegocio);
                            publicaciones.setDescripcion(descripcionNegocio);
                            publicaciones.setStock(true);
                            publicaciones.setUrlFoto(download.toString());
                            publicaciones.setIdUsuario(UID_USUARIO);
                            publicaciones.setUrlFotoPerfil(URLFOTOPERFIL);
                            FirebaseUser currentUser = mAuth.getCurrentUser();
                            DatabaseReference reference = database.getReference("PublicacionesComida/"+currentUser.getUid());
                            reference.setValue(publicaciones);
                            cargando.dismiss();
                            finish();
                        }else{
                            Toast.makeText(CrearPublicacionActivity.this, "Falta Completar Campos", Toast.LENGTH_SHORT).show();
                        }

                    });

                });

            }
        }
    }
    public boolean validar(String nombre,String apellido,String telefono,String barrio){
        return !nombre.isEmpty() && !apellido.isEmpty()&& !telefono.isEmpty()&& !barrio.isEmpty();
    }
}
