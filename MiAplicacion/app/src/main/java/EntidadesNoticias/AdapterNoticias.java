package EntidadesNoticias;

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

public class AdapterNoticias  extends RecyclerView.Adapter<HolderNoticias> {
    private List<Noticias> noticiasList = new ArrayList<>();
    private Context c;

    public AdapterNoticias(Context c) {
        this.c = c;
    }
    public void addNoticia(Noticias n){
        noticiasList.add(n);
        notifyItemInserted(noticiasList.size());
    }

    @NonNull
    @Override
    public HolderNoticias onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(c).inflate(R.layout.card_view_noticias,parent,false);
        return new HolderNoticias(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HolderNoticias holder, int position) {

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
