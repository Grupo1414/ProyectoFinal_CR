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

        // Inicialización de vistas
        recyclerView = findViewById(R.id.cart_items_list);
        subtotalTextView = findViewById(R.id.subtotal_price);
        payButton = findViewById(R.id.checkout_button);
        addButton = findViewById(R.id.button_add_more_items);
        Button checkoutButton = findViewById(R.id.checkout_button);


        // Obtiene los ítems del carrito, asegurándose de que la lista no sea nula
        cartItems = getIntent().getStringArrayListExtra("cartItems");
        if (cartItems == null) {
            cartItems = new ArrayList<>();
        }



        // Inicialización y configuración del adaptador y RecyclerView
        cartAdapter = new CartAdapter(this, cartItems);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(cartAdapter);


        //Boton para ir a DeliveryOption, comer aqui, domicilio etc
        checkoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CartActivity.this, DeliveryOptionsActivity.class);
                startActivity(intent);
            }
        });


        //Configuración de los botones ( Añadir más y Pagar )
        //Nos lleva a

     /*   payButton.setOnClickListener(v -> {
            Intent intent = new Intent(CartActivity.this, PaymentActivity.class);
            // Puedes enviar información adicional a PaymentActivity si es necesario.
            startActivity(intent);
        }); */

        //Boton para añadir mas alimentos, nos lleva a mainActivity
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CartActivity.this, CartaPrincipal.class);
                startActivity(intent);
            }
        });
    }



    private void calculateSubtotal() {
        subtotal = 0.0; // Restablece el subtotal
        for (String item : cartItems) {
            String[] parts = item.split("-");
            if (parts.length > 1) {
                String priceString = parts[parts.length - 1].trim().replace("€", "").replace(",", ".");
                try {
                    subtotal += Double.parseDouble(priceString);
                } catch (NumberFormatException e) {
                    Log.e("CartActivity", "Error al parsear el precio del ítem: " + item, e);
                }
            }
        }

        subtotalTextView.setText("Subtotal: " + String.format("%.2f", subtotal) + " €");
    }

    @Override
    protected void onResume() {
        super.onResume();
        calculateSubtotal(); // Llama a este método para actualizar el subtotal cada vez que regreses a la actividad.
    }


}
