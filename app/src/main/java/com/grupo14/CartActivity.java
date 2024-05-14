package com.grupo14;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CartActivity extends AppCompatActivity {

    //Variables
    private RecyclerView recyclerView;
    private TextView subtotalTextView;
    private Button payButton, addButton;
    private CartAdapter cartAdapter; // Adaptador personalizado para RecyclerView
    private ArrayList<String> cartItems; // Lista de ítems del carrito
    private double subtotal = 0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);


        //Relacionamos las variables con el xml
        recyclerView = findViewById(R.id.cart_items_list);
        subtotalTextView = findViewById(R.id.subtotal_price);
        payButton = findViewById(R.id.checkout_button);
        addButton = findViewById(R.id.button_add_more_items);


        cartItems = getIntent().getStringArrayListExtra("cartItems");
        if (cartItems == null) {
            cartItems = new ArrayList<>();
        }

        cartAdapter = new CartAdapter(this, cartItems);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(cartAdapter);

        Button checkoutButton = findViewById(R.id.checkout_button);
        checkoutButton.setOnClickListener(v -> {
            calculateSubtotal();
            Intent intent = new Intent(CartActivity.this, PaymentActivity.class);
            intent.putExtra("SUBTOTAL", subtotal);
            startActivity(intent);
        });

        addButton.setOnClickListener(v -> {
            Intent intent = new Intent(CartActivity.this, CartaPrincipal.class);
            startActivity(intent);
        });
    }

    //Metodo para ver el total del carrito
    private void calculateSubtotal() {
        subtotal = 0.0;
        for (String item : cartItems) {
            String[] parts = item.split("-");
            if (parts.length > 1) {
                String priceString = parts[parts.length - 1].trim().replace("€", "").replace(",", ".");
                try {
                    subtotal += Double.parseDouble(priceString);
                } catch (NumberFormatException e) {
                    Log.e("CartActivity", "Error parsing item price: " + item, e);
                }
            }
        }
        subtotalTextView.setText(String.format("%.2f €", subtotal));
    }

    @Override
    protected void onResume() {
        super.onResume();
        calculateSubtotal();
    }
}