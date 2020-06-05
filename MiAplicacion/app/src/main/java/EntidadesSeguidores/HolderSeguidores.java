package EntidadesSeguidores;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.cabrerajesusk.miaplicacion.R;

import Modelos.Seguidores;
import de.hdodenhof.circleimageview.CircleImageView;

public class HolderSeguidores extends RecyclerView.ViewHolder {

    private CircleImageView fotoPerfil;
    private TextView nombre;
    private Button seguir;

    public HolderSeguidores(View itemView){
        super(itemView);
        fotoPerfil = (CircleImageView) itemView.findViewById(R.id.idFotoPerfilSeguidores);
        nombre = (TextView) itemView.findViewById(R.id.idNombreSeguidores);
        seguir = (Button) itemView.findViewById(R.id.idSeguirSeguidores);

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

    public Button getSeguir() {
        return seguir;
    }

    public void setSeguir(Button seguir) {
        this.seguir = seguir;
    }
}