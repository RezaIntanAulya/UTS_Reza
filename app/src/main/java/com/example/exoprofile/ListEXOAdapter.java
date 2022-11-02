package com.example.exoprofile;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

public class ListEXOAdapter extends RecyclerView.Adapter<ListEXOAdapter.CardViewHolder> {

    private ArrayList<EXO> listEXO;
    private OnItemClickCallback onItemClickCallback;

    public ListEXOAdapter(ArrayList<EXO> list) {
        this.listEXO = list;
    }

    public void setOnItemClickCallback(OnItemClickCallback onItemClickCallback){
        this.onItemClickCallback = onItemClickCallback;
    }

    @NonNull
    @Override
    public CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row_exo, parent,false);
        return new CardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewHolder holder, int position) {
        EXO exo = listEXO.get(position);
        Glide.with(holder.itemView.getContext())
                .load(exo.getPhoto())
                .apply(new RequestOptions().override(350, 550))
                .into(holder.imgPhoto);
        holder.tvNama.setText(exo.getName());
        holder.tvDetail.setText(exo.getDescription());
        holder.btnBaca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickCallback.onItemClicked(listEXO.get(holder.getAdapterPosition()));
                Toast.makeText(holder.itemView.getContext(), listEXO.get(holder.getAdapterPosition()).getName(),Toast.LENGTH_SHORT).show();
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickCallback.onItemClicked(listEXO.get(holder.getAdapterPosition()));
                Toast.makeText(holder.itemView.getContext(), listEXO.get(holder.getAdapterPosition()).getName(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return listEXO.size();
    }

    public class CardViewHolder extends RecyclerView.ViewHolder {

        ImageView imgPhoto;
        TextView tvNama, tvDetail;
        Button btnBaca;
        public CardViewHolder(@NonNull View itemView) {
            super(itemView);
            imgPhoto = itemView.findViewById(R.id.iv_item_photo);
            tvNama = itemView.findViewById(R.id.tv_item_name);
            tvDetail = itemView.findViewById(R.id.tv_item_description);
            btnBaca = itemView.findViewById(R.id.btnBaca);

        }
    }

    public interface OnItemClickCallback {
        void onItemClicked(EXO exo);
        
    }
}
