package EntidadesPerfilPersonalNoticias;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cabrerajesusk.miaplicacion.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class HolderPerfilPersonalNoticias extends RecyclerView.ViewHolder {
    private CircleImageView fotoPerfil;
    private TextView nombreEmpresa;
    private ImageView fotoNoticia;
    private TextView descripcion;

    public HolderPerfilPersonalNoticias(@NonNull View itemView) {
        super(itemView);
        fotoPerfil = (CircleImageView) itemView.findViewById(R.id.idFotoPerfilPublicacionPerfilNoticia);
        nombreEmpresa = (TextView) itemView.findViewById(R.id.idNombreNoticieroPublicacionPerfilNoticia);
        fotoNoticia = (ImageView) itemView.findViewById(R.id.idFotoPublicacionPerfilNoticia);
        descripcion = (TextView) itemView.findViewById(R.id.idDescripcionPublicacionPerfilNoticia);
    }

    public CircleImageView getFotoPerfil() {
        return fotoPerfil;
    }

    public void setFotoPerfil(CircleImageView fotoPerfil) {
        this.fotoPerfil = fotoPerfil;
    }

    public TextView getNombreEmpresa() {
        return nombreEmpresa;
    }

    public void setNombreEmpresa(TextView nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }

    public ImageView getFotoNoticia() {
        return fotoNoticia;
    }

    public void setFotoNoticia(ImageView fotoNoticia) {
        this.fotoNoticia = fotoNoticia;
    }

    public TextView getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(TextView descripcion) {
        this.descripcion = descripcion;
    }
}
