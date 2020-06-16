package EntidadesPublicaciones;

import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cabrerajesusk.miaplicacion.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class HolderPublicaciones extends RecyclerView.ViewHolder {

    private CircleImageView fotoPerfil;
    private TextView nombre;
    private TextView titulo;
    private ImageView fotoPublicacion;
    private TextView precio;
    private ImageButton enviarMensaje;
    private TextView descripcion;

    public HolderPublicaciones(@NonNull View itemView) {
        super(itemView);

        fotoPerfil = (CircleImageView) itemView.findViewById(R.id.idFotoPerfilPublicacion);
        nombre = (TextView) itemView.findViewById(R.id.idNombreNegocioPublicacion);
        titulo = (TextView) itemView.findViewById(R.id.idTituloPublicacion);
        fotoPublicacion = (ImageView) itemView.findViewById(R.id.idFotoPublicacion);
        precio = (TextView) itemView.findViewById(R.id.idPrecioPublicacion);
        enviarMensaje = (ImageButton) itemView.findViewById(R.id.idMensajePublicacion);
        descripcion = (TextView) itemView.findViewById(R.id.idDescripcionPublicacion);
    }

    public CircleImageView getFotoPerfil() {
        return fotoPerfil;
    }

    public void setFotoPerfil(CircleImageView fotoPerfil) {
        this.fotoPerfil = fotoPerfil;
    }

    public TextView getNombre() {
        return nombre;
    }

    public void setNombre(TextView nombre) {
        this.nombre = nombre;
    }

    public TextView getTitulo() {
        return titulo;
    }

    public void setTitulo(TextView titulo) {
        this.titulo = titulo;
    }

    public ImageView getFotoPublicacion() {
        return fotoPublicacion;
    }

    public void setFotoPublicacion(ImageView fotoPublicacion) {
        this.fotoPublicacion = fotoPublicacion;
    }

    public TextView getPrecio() {
        return precio;
    }

    public void setPrecio(TextView precio) {
        this.precio = precio;
    }

    public ImageButton getEnviarMensaje() {
        return enviarMensaje;
    }

    public void setEnviarMensaje(ImageButton enviarMensaje) {
        this.enviarMensaje = enviarMensaje;
    }

    public TextView getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(TextView descripcion) {
        this.descripcion = descripcion;
    }
}
