package com.example.fooddeliveryapp.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.fooddeliveryapp.Domain.FoodDomain;
import com.example.fooddeliveryapp.Helper.ManagementCart;
import com.example.fooddeliveryapp.Interface.ChangeNumberItemListener;
import com.example.fooddeliveryapp.R;

import java.util.ArrayList;

public class CartListAdapter extends RecyclerView.Adapter<CartListAdapter.ViewHolder> {
    private ArrayList<FoodDomain> foodDomains;
    private ManagementCart managementCart;
    private ChangeNumberItemListener changeNumberItemListener;
    private Object num;

    public CartListAdapter(ArrayList<FoodDomain> foodDomains, Context context, ChangeNumberItemListener changeNumberItemListener) {
        this.foodDomains = foodDomains;
        this.managementCart = new ManagementCart(context);
        this.changeNumberItemListener = changeNumberItemListener;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View inflate= LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_cart,parent,false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.title.setText(foodDomains.get(position).getTitle());
        holder.feeEachItem.setText(String.valueOf(foodDomains.get(position).getFee()));
        holder.num.setText(String.valueOf(foodDomains.get(position).getNumberInCart()));
        int numberInCart = foodDomains.get(position).getNumberInCart();
        double fee = Double.parseDouble(foodDomains.get(position).getFee());

        holder.totalEachItem.setText(String.valueOf(Math.round(numberInCart * fee * 100.0) / 100.0));
        int drawableResourceId = holder.itemView.getContext().getResources().getIdentifier(foodDomains.get(position).getPic(),"drawable",holder.itemView.getContext().getPackageName());
        Glide.with(holder.itemView.getContext())
                .load(drawableResourceId)
                .into(holder.pic);

        holder.plusItem.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                managementCart.plusNumberFood(foodDomains, position, new ChangeNumberItemListener() {
                    @SuppressLint("NotifyDataSetChanged")
                    @Override
                    public void changed() {
                        notifyDataSetChanged();
                        changeNumberItemListener.changed();
                        
                    }
                });



            }
        });
        holder.minusItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                managementCart.minusNumberFood(foodDomains, position, new ChangeNumberItemListener() {
                    @SuppressLint("NotifyDataSetChanged")
                    @Override
                    public void changed() {
                        notifyDataSetChanged();
                        changeNumberItemListener.changed();

                    }
                });

            }
        });

    }

    @Override
    public int getItemCount() {
        return foodDomains.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView title,feeEachItem;
        ImageView pic,plusItem,minusItem;
        TextView totalEachItem,num;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.titleTxt);
            feeEachItem = itemView.findViewById(R.id.feeEachItemTxt);
            pic = itemView.findViewById(R.id.picCart);
            totalEachItem = itemView.findViewById(R.id.totalEachItem);
            num = itemView.findViewById(R.id.numberItemTxt); // corrected line
            plusItem = itemView.findViewById(R.id.plusCartItem);
            minusItem = itemView.findViewById(R.id.minusCartBtn);
        }
    }
}
