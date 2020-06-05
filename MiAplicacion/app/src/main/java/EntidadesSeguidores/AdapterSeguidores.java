package EntidadesSeguidores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.cabrerajesusk.miaplicacion.R;

import java.util.ArrayList;
import java.util.List;

import Modelos.Seguidores;

public class AdapterSeguidores extends RecyclerView.Adapter<HolderSeguidores> {

    private List<Seguidores> listaSeguidores = new ArrayList<>();
    private Context c;

    public AdapterSeguidores(Context c){
        this.c = c;
    }

    public void addSeguidor(Seguidores s){
        listaSeguidores.add(s);
        notifyItemInserted(listaSeguidores.size());
    }
    @Override
    public HolderSeguidores onCreateViewHolder(ViewGroup parent, int viewType){
        View v = LayoutInflater.from(c).inflate(R.layout.card_view_seguidores,parent,false);
        return new HolderSeguidores(v);
    }

    @Override
    public void onBindViewHolder(HolderSeguidores holder,int position){
        Glide.with(c).load(listaSeguidores.get(position).getIdUsuario().getIdFotoPerfil().getFoto()).into(holder.getFotoPerfil());
        holder.getNombre().setText(listaSeguidores.get(position).getIdUsuario().getIdPersona().getNombre());
    }

    @Override
    public int getItemCount(){
        return listaSeguidores.size();
    }
}
