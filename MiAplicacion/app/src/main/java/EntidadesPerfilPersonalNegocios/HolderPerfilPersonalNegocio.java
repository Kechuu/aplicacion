package EntidadesPerfilPersonalNegocios;

import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cabrerajesusk.miaplicacion.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class HolderPerfilPersonalNegocio extends RecyclerView.ViewHolder {
    private CircleImageView fotoPerfil;
    private TextView nombre;
    private TextView titulo;
    private ImageView fotoPublicacion;
    private TextView precio;
    private ImageButton borrarPublicacion;
    private TextView descripcion;

    public HolderPerfilPersonalNegocio(@NonNull View itemView) {
        super(itemView);
        fotoPerfil = (CircleImageView) itemView.findViewById(R.id.idFotoPerfilPublicacionPerfil);
        nombre = (TextView) itemView.findViewById(R.id.idNombreNegocioPublicacionPerfil);
        titulo = (TextView) itemView.findViewById(R.id.idTituloPublicacionPerfil);
        fotoPublicacion = (ImageView) itemView.findViewById(R.id.idFotoPublicacionPerfil);
        precio = (TextView) itemView.findViewById(R.id.idPrecioPublicacionPerfil);
        borrarPublicacion = (ImageButton) itemView.findViewById(R.id.idBorrarPublicacionPerfil);
        descripcion = (TextView) itemView.findViewById(R.id.idDescripcionPublicacionPerfil);
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

    public ImageButton getBorrarPublicacion() {
        return borrarPublicacion;
    }

    public void setBorrarPublicacion(ImageButton borrarPublicacion) {
        this.borrarPublicacion = borrarPublicacion;
    }

    public TextView getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(TextView descripcion) {
        this.descripcion = descripcion;
    }
}
