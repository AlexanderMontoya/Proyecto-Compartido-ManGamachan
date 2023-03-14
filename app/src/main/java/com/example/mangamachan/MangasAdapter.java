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

import com.example.mangamachan.controladores.MangasController;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MangasAdapter extends RecyclerView.Adapter<MangasAdapter.ViewHolder> {
    private List<MangasController> mData;
    private List<MangasController> mDataOriginal;
    private LayoutInflater mInflater;
    private Context context;
    public MangasAdapter(List<MangasController> itemList, Context context){
        this.mInflater= LayoutInflater.from(context);
        this.context=context;
        this.mData=itemList;
        mDataOriginal=new ArrayList<>();
        mDataOriginal.addAll(mData);
    }

    @Override
    public int getItemCount(){return mData.size();}

    @Override
    public MangasAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view=mInflater.inflate(R.layout.list_element,null);
        return new MangasAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MangasAdapter.ViewHolder holder, final int position){
        holder.bindData(mData.get(position));
    }

    public void filtrado(String txtBuscar){
        int longitud =txtBuscar.length();
        if(longitud == 0){
            mData.clear();
            mData.addAll(mDataOriginal);
        }else{
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                List<MangasController> collecion=mData.stream().filter(i -> i.getName().toLowerCase().contains(txtBuscar.toLowerCase())).collect(Collectors.toList());
                mData.clear();
                mData.addAll(collecion);
            }else{
                for(MangasController c:mDataOriginal){
                    if(c.getName().toLowerCase().contains(txtBuscar.toLowerCase())){
                        mData.add(c);
                    }
                }
            }
        }
        notifyDataSetChanged();
    }

    public void setItems(List<MangasController>items){
        mData=items;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
            ImageView imageManga;
            TextView titulo;
            public ViewHolder(@NonNull View itemView){
                super(itemView);
                imageManga=itemView.findViewById(R.id.mangaImageView);
                titulo=itemView.findViewById(R.id.nameTextView);

            }

            void bindData(final MangasController item){
                Picasso.get()
                        .load(item.getImagen())
                        .error(R.mipmap.ic_launcher_round)
                        .into(imageManga);
                titulo.setText(item.getName());
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Context context=view.getContext();
                        Intent intent=new Intent(context,CapitulosList.class);
                        intent.putExtra("ID",item.getId());
                        context.startActivity(intent);
                    }
                });
            }
    }


}
