package com.example.fooddeliveryapp.Adapter;



import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.fooddeliveryapp.Activity.ShowDetailActivity;
import com.example.fooddeliveryapp.Domain.FoodDomain;
import com.example.fooddeliveryapp.R;

import java.util.ArrayList;

public class PopularAdapter extends RecyclerView.Adapter<PopularAdapter.ViewHolder> {
    ArrayList<FoodDomain> PopularFood;
    private ViewHolder holder;
    private int position;

    public PopularAdapter(ArrayList<FoodDomain> PopularFood) {
        this.PopularFood = PopularFood;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_popular,parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(PopularAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        // Bind your data here
        holder.title.setText(PopularFood.get(position).getTitle());
        holder.fee.setText( PopularFood.get(position).getFee());

        @SuppressLint("DiscouragedApi") int drawableResourceId = holder.itemView.getContext().getResources().getIdentifier(PopularFood.get(position).getPic(),"drawable",holder.itemView.getContext().getPackageName());

        Glide.with(holder.itemView.getContext())
                .load(drawableResourceId)
                .into(holder.pic);

        holder.addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (PopularFood.get(position) != null) {
                    Intent intent = new Intent(holder.itemView.getContext(), ShowDetailActivity.class);
                    Intent intent1 = intent.putExtra("object", PopularFood.get(position));
                    holder.itemView.getContext().startActivity(intent);
                }
                return null;
            }
        });
    }

    @Override
    public int getItemCount() {
        return PopularFood.size(); // Return the size of your data list
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        private TextView fee;
        TextView addBtn;
        ImageView pic;
        @SuppressLint("WrongViewCast")
        public ViewHolder(@NonNull @org.jetbrains.annotations.NotNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            setFee(itemView.findViewById(R.id.fee));
            pic = itemView.findViewById(R.id.pic);
            addBtn = itemView.findViewById(R.id.addBtn);

        }

        public TextView getFee() {
            return fee;
        }

        public void setFee(TextView fee) {
            this.fee = fee;
        }
    }
}
