package EntidadesPerfilPersonalNoticias;

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

import Modelos.Noticias;

public class AdapterPerfilPersonalNoticias extends RecyclerView.Adapter<HolderPerfilPersonalNoticias> {
    private List<Noticias> noticiasList = new ArrayList<>();
    private Context c;

    public AdapterPerfilPersonalNoticias(Context c) {
        this.c = c;
    }
    public void addNoticiasPerfil(Noticias noticias){
        noticiasList.add(noticias);
        notifyItemInserted(noticiasList.size());
    }

    @NonNull
    @Override
    public HolderPerfilPersonalNoticias onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(c).inflate(R.layout.card_view_perfil_noticias,parent,false);
        return new HolderPerfilPersonalNoticias(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HolderPerfilPersonalNoticias holder, int position) {
        holder.getDescripcion().setText(noticiasList.get(position).getDescripcion());
        holder.getNombreEmpresa().setText(noticiasList.get(position).getNombreNoticiero());
        Glide.with(c).load(noticiasList.get(position).getUrlFotoPerfil()).into(holder.getFotoPerfil());
        Glide.with(c).load(noticiasList.get(position).getUrlFoto()).into(holder.getFotoNoticia());
    }

    @Override
    public int getItemCount() {
        return noticiasList.size();
    }
}
