package com.example.mangamachan;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mangamachan.controladores.FavoritoController;
import com.example.mangamachan.db.DbFavoritos;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class FavoritosAdapter extends RecyclerView.Adapter<FavoritosAdapter.FavoritoViewHolder> {

    ArrayList<FavoritoController> listaFavoritos;
    public FavoritosAdapter(ArrayList<FavoritoController> listaFavoritos){
        this.listaFavoritos = listaFavoritos;
    }

    @NonNull
    @Override
    public FavoritoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_favoritos,null,false);
        return new FavoritoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoritoViewHolder holder, int position) {
        holder.viewTitle.setText(listaFavoritos.get(position).getNombre_manga());
        Picasso.get()
                .load(listaFavoritos.get(position).getImagen_manga())
                .error(R.mipmap.ic_launcher_round)
                .into(holder.viewImagen);
        holder.btnQuitarFavorito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DbFavoritos dbFavoritos= new DbFavoritos(view.getContext());
                boolean elimino= dbFavoritos.eliminarFavorito(listaFavoritos.get(position).getId_manga());
                String idManga=listaFavoritos.get(position).getId_manga();
                if(elimino){

                }else{
                    Toast.makeText(view.getContext(), listaFavoritos.get(position).getNombre_manga()+"fue eliminado de tus Favoritos", Toast.LENGTH_SHORT).show();
                    for(int i=0;i<listaFavoritos.size();i++){
                        if(listaFavoritos.get(i).getId_manga()==idManga){
                            listaFavoritos.remove(i);
                            break;
                        }
                    }
                    //enganchePet.notifyDataSetChanged();
                    notifyDataSetChanged();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return listaFavoritos.size();
    }

    public class FavoritoViewHolder extends RecyclerView.ViewHolder {
        TextView viewId,viewTitle;
        Button btnQuitarFavorito;
        ImageView viewImagen;
        public FavoritoViewHolder(@NonNull View itemView) {
            super(itemView);
            viewTitle=itemView.findViewById(R.id.nameTextView);
            viewImagen=itemView.findViewById(R.id.mangaImageView);
            btnQuitarFavorito=itemView.findViewById(R.id.btnEliminarFavorito);
        }
    }
}

