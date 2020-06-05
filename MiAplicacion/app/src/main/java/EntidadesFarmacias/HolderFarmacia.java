package EntidadesFarmacias;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.cabrerajesusk.miaplicacion.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class HolderFarmacia extends RecyclerView.ViewHolder {

    private CircleImageView fotoPerfil;
    private TextView nombre;
    private ImageView fotoPerfil2;
    private TextView direccion;

    public HolderFarmacia(View itemView){
        super(itemView);
        fotoPerfil = (CircleImageView) itemView.findViewById(R.id.idFotoPerfilFarmacia);
        nombre = (TextView) itemView.findViewById(R.id.idNombreFarmacia);
        fotoPerfil2 = (ImageView) itemView.findViewById(R.id.idFotoPerfilFarmacia2);
        direccion = (TextView) itemView.findViewById(R.id.idDireccionFarmacia);
    }
}
