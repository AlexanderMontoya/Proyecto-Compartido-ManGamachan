package com.example.mangamachan;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mangamachan.controladores.CapituloController;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class CapitulosAdapter extends RecyclerView.Adapter<CapitulosAdapter.ViewHolder> {
        private List<CapituloController> mData2;
        private List<CapituloController> mDataOriginal;
        private LayoutInflater mInflater;
        private Context context;
        public CapitulosAdapter(List<CapituloController> itemList,Context context){
            this.mInflater= LayoutInflater.from(context);
            this.context=context;
            this.mData2=itemList;
            mDataOriginal=new ArrayList<>();
            mDataOriginal.addAll(mData2);
        }

        @Override
        public int getItemCount(){return mData2.size();}

        @Override
        public CapitulosAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
            View view=mInflater.inflate(R.layout.list_capitulos,null);
            return new CapitulosAdapter.ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final CapitulosAdapter.ViewHolder holder, final int position){
            holder.bindData(mData2.get(position));
        }

        public void setItems(List<CapituloController>items){
            mData2=items;
        }

        public class ViewHolder extends RecyclerView.ViewHolder{
            ImageView imageManga;
            TextView capitulo,paginas;
            public ViewHolder(@NonNull View itemView){
                super(itemView);
                imageManga=itemView.findViewById(R.id.iconImageViewCapitulo);
                capitulo=itemView.findViewById(R.id.nameTextViewCapitulo);
                paginas=itemView.findViewById(R.id.textpaginas);
            }

            public void bindData(final CapituloController item){

                capitulo.setText("Capitulo: "+item.getName());
                paginas.setText("Paginas: "+item.getPaginas());
                Picasso.get()
                        .load(item.getUrlimagen1())
                        .error(R.mipmap.ic_launcher_round)
                        .into(imageManga);
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Context context=view.getContext();
                        Intent intent=new Intent(context,lectura_manga.class);
                        intent.putExtra("idMangacap",item.getId());
                        intent.putExtra("idmanga",item.getIdmanga());
                        intent.putExtra("cantPaginas",item.getPaginas());
                        context.startActivity(intent);
                    }
                });
            }
        }
}
