package com.example.recyclerjsonvolley;
//package com.example.musicplayer;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

public class AdapterItem extends RecyclerView.Adapter<AdapterItem.myViewHoler> {

Context context;
ArrayList<ModelItem> itemArrayList;

    public AdapterItem(Context context, ArrayList<ModelItem> itemArrayList) {
        this.context = context;
        this.itemArrayList = itemArrayList;
    }

    public AdapterItem() {

    }

    @NonNull
    @Override
    public myViewHoler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.item_recycler, parent, false);
        return new myViewHoler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHoler holder, int position) {
    ModelItem currentItem = itemArrayList.get(position);
    String ImageUrl = currentItem.getImageUrl();
    String Creator = currentItem.getCreator();
    int likes = currentItem.getLikes();

    holder.tvCreator.setText(Creator);
    holder.tvLikes.setText("Likes : " + likes);





    // GLIDES
    RequestOptions gestionDesErreurs = new RequestOptions()
            .centerCrop()
            .error(R.mipmap.ic_launcher_round) // iMAGE ERREUR
            .placeholder(R.mipmap.ic_launcher); // IMAGE PAR DEFAUT

    Context context = holder.ivImage.getContext();
    Glide.with(context)
            .load(ImageUrl)
            .apply(gestionDesErreurs)
            .fitCenter()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(holder.ivImage);


    }

    @Override
    public int getItemCount() {
        return itemArrayList.size();

    }

    public class myViewHoler extends RecyclerView.ViewHolder {
        //private ivImage imageView;
        private ImageView ivImage;
        private TextView tvCreator, tvLikes;
        public  myViewHoler(@NonNull View itemView) {
            super (itemView);
            ivImage = itemView.findViewById(R.id.ivImage);
            tvCreator = itemView.findViewById(R.id.tvCreator);
            tvLikes = itemView.findViewById(R.id.tvLikes);


            // l'autre commence ici


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (onItemClickListener != null) {
                        int pos = getBindingAdapterPosition();
                        if(pos != RecyclerView.NO_POSITION){
                            onItemClickListener.onItemClick(pos);
                        }
                    }
                }
            });
        }
    }

    private interface OnItemClickListener {

        void onItemClick(int position);

    }

    private onItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    };



}
