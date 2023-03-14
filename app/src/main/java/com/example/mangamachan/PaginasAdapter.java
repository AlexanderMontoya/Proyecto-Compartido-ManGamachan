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

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PaginasAdapter extends RecyclerView.Adapter<PaginasAdapter.ViewHolder>{
    private List<PaginaController> mData;
    private List<PaginaController> mDataOriginal;
    private LayoutInflater mInflater;
    private Context context;
    public PaginasAdapter(List<PaginaController> itemList, Context context){
        this.mInflater= LayoutInflater.from(context);
        this.context=context;
        this.mData=itemList;
        mDataOriginal=new ArrayList<>();
        mDataOriginal.addAll(mData);
    }

    @Override
    public int getItemCount(){return mData.size();}

    @Override
    public PaginasAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view=mInflater.inflate(R.layout.list_page,null);
        return new PaginasAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final PaginasAdapter.ViewHolder holder, final int position){
        holder.bindData(mData.get(position));
    }

    public void setItems(List<PaginaController>items){
        mData=items;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imagePag;
        public ViewHolder(@NonNull View itemView){
            super(itemView);
            imagePag=itemView.findViewById(R.id.PageImageView);
        }

        void bindData(final PaginaController item){
            Picasso.get()
                    .load(item.getUrl_page())
                    .error(R.mipmap.ic_launcher_round)
                    .into(imagePag);
        }
    }
}
