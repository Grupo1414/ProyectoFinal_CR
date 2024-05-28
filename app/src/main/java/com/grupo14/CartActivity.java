package com.grupo14;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class CartActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private CartAdapter cartAdapter;
    private List<Carrito> carritoItems;
    private DatabaseReference cartRef;
    private TextView subtotalPrice;
    private FirebaseUser currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        recyclerView = findViewById(R.id.cart_items_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        carritoItems = new ArrayList<>();
        cartAdapter = new CartAdapter(this, carritoItems);
        recyclerView.setAdapter(cartAdapter);

        subtotalPrice = findViewById(R.id.subtotal_price);
        currentUser = FirebaseAuth.getInstance().getCurrentUser();

        if (currentUser != null) {
            cartRef = FirebaseDatabase.getInstance().getReference("carritos").child(currentUser.getUid());
            loadCartItems();
        }

        Button checkoutButton = findViewById(R.id.checkout_button);
        checkoutButton.setOnClickListener(v -> {
            Intent intent = new Intent(CartActivity.this, PaymentActivity.class);
            intent.putExtra("SUBTOTAL", calcularTotal());  // Añadimos el subtotal al Intent
            startActivity(intent);
        });

        Button backButton = findViewById(R.id.button_back);
        backButton.setOnClickListener(v -> finish());

        Button addMoreItemsButton = findViewById(R.id.button_add_more_items);
        addMoreItemsButton.setOnClickListener(v -> {
            Intent intent = new Intent(CartActivity.this, CartaPrincipal.class);
            startActivity(intent);
        });
    }

    private void loadCartItems() {
        cartRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                carritoItems.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Carrito item = snapshot.getValue(Carrito.class);
                    if (item != null) {
                        carritoItems.add(item);
                    }
                }
                cartAdapter.notifyDataSetChanged();
                updateTotalPrice();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Manejar error
            }
        });
    }

    public void updateTotalPrice() {
        double total = calcularTotal();
        subtotalPrice.setText(String.format("%.2f €", total));
    }

    private double calcularTotal() {
        double total = 0;
        for (Carrito item : carritoItems) {
            total += item.getCantidad() * item.getPrecio();
        }
        return total;
    }
}