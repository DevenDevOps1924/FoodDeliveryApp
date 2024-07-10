package com.example.fooddeliveryapp.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fooddeliveryapp.Adapter.CartListAdapter;
import com.example.fooddeliveryapp.Helper.ManagementCart;
import com.example.fooddeliveryapp.Helper.TinyDB;
import com.example.fooddeliveryapp.Interface.ChangeNumberItemListener;
import com.example.fooddeliveryapp.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class CartListActivity extends AppCompatActivity {

    private RecyclerView.Adapter adapter;
    private RecyclerView recyclerViewList;
    private ManagementCart managementCart;
    TextView totalFeeTxt, taxTxt, deliveryTxt, totalTxt, emptyTxt;
    private double tax;
    private ScrollView scrollView;
    private TinyDB tinyDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_list);

        tinyDB = new TinyDB(this);

        managementCart = new ManagementCart(this);
        initView();
        initList();
        CalculateCart();
        TextView checkOutBtn = findViewById(R.id.checkOutBtn);
        checkOutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double totalFee = tinyDB.getDouble("total_fee", 0.0);
                // Use the total fee here, e.g., display it in a toast message
                Toast.makeText(CartListActivity.this, "Total fee: ₹" + totalFee, Toast.LENGTH_SHORT).show();
            }
        });
        bottomNavigation();
    }

    private void bottomNavigation() {
        FloatingActionButton floatingActionButton = findViewById(R.id.cartBtn);
        LinearLayout homeBtn = findViewById(R.id.homeBtn);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CartListActivity.this, CartListActivity.class));
            }
        });
        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CartListActivity.this, MainActivity.class));
            }
        });

    }

    private void initView() {
        recyclerViewList = findViewById(R.id.cartView);
        totalFeeTxt = findViewById(R.id.totalFeeTxt);
        taxTxt = findViewById(R.id.TaxTxt);
        deliveryTxt = findViewById(R.id.deliveryTxt);
        totalTxt = findViewById(R.id.totalTxt);
        emptyTxt = findViewById(R.id.emptyTxt);
        scrollView = findViewById(R.id.scrollView2);
        recyclerViewList=findViewById(R.id.cartView);

    }

    private void initList() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerViewList.setLayoutManager(linearLayoutManager);
        adapter = new CartListAdapter(managementCart.getListCart(), this, new ChangeNumberItemListener() {
            @Override
            public void changed() {
                CalculateCart();
            }
        });
        recyclerViewList.setAdapter(adapter);
        if (managementCart.getListCart().isEmpty()) {
            emptyTxt.setVisibility(View.VISIBLE);
            scrollView.setVisibility(View.GONE);
        } else {
            emptyTxt.setVisibility(View.GONE);
            scrollView.setVisibility(View.VISIBLE);
        }
    }

    private void CalculateCart() {
        double percentTax = 0.5;
        double delivery = 50;
        tax = Math.round((managementCart.getTotalFee() * percentTax) * 100) / 100;
        double total = Math.round((managementCart.getTotalFee() + tax + delivery) * 100) / 108;
        double itemTotal = Math.round(managementCart.getTotalFee() * 100) / 100;
        totalFeeTxt.setText("₹" + itemTotal);
        taxTxt.setText("₹" + tax);
        deliveryTxt.setText("₹" + delivery);
        totalTxt.setText("₹" + total);
        tinyDB.putDouble("total_fee", total);
    }


}
