package EntidadesPerfilPersonalNegocios;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.cabrerajesusk.miaplicacion.R;

import java.util.ArrayList;
import java.util.List;

import Modelos.Publicaciones;

public class AdapterPerfilPersonalNegocios extends RecyclerView.Adapter<HolderPerfilPersonalNegocio> {
    private List<Publicaciones> publicacionesList = new ArrayList<>();
    private Context c;

    public AdapterPerfilPersonalNegocios(Context c) {
        this.c = c;
    }
    public void addPublicacionesInicio(Publicaciones publicaciones){
        publicacionesList.add(publicaciones);
        notifyItemInserted(publicacionesList.size());
    }

    @NonNull
    @Override
    public HolderPerfilPersonalNegocio onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(c).inflate(R.layout.card_view_perfil_personal_negocio,parent,false);
        return new HolderPerfilPersonalNegocio(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HolderPerfilPersonalNegocio holder, int position) {
        holder.getDescripcion().setText(publicacionesList.get(position).getDescripcion());
        holder.getNombre().setText(publicacionesList.get(position).getNombreNegocio());
        holder.getPrecio().setText(publicacionesList.get(position).getPrecio());
        holder.getTitulo().setText(publicacionesList.get(position).getTitulo());
        Glide.with(c).load(publicacionesList.get(position).getUrlFotoPerfil()).into(holder.getFotoPerfil());
        Glide.with(c).load(publicacionesList.get(position).getUrlFoto()).into(holder.getFotoPublicacion());
    }

    @Override
    public int getItemCount() {
        return publicacionesList.size();
    }
}
