package com.grupo14;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PickupActivity extends AppCompatActivity {
    private EditText editTextName;
    private Spinner spinnerTime;
    private RecyclerView recyclerView;
    private Button buttonConfirm, buttonAddCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pickup);

        editTextName = findViewById(R.id.editTextName);
        spinnerTime = findViewById(R.id.spinnerTime);
        recyclerView = findViewById(R.id.cart_items_list);
        buttonConfirm = findViewById(R.id.buttonConfirm);
        buttonAddCard = findViewById(R.id.button_add_credit_card);


        // Configura el botón para añadir tarjeta
        buttonAddCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PickupActivity.this, AddCardActivity.class);
                startActivity(intent);
            }
        });

        // Configura el botón para confirmar y pasar a la pantalla de pago
        buttonConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PickupActivity.this, PaymentActivity2.class);
                startActivity(intent);
            }
        });

        // RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Asegúrate de que getIntent().getStringArrayListExtra("cartItems") no sea null antes de usarla
        ArrayList<String> cartItems = getIntent().getStringArrayListExtra("cartItems");
        if (cartItems == null) {
            cartItems = new ArrayList<>();  // Si es null, inicializa una nueva lista vacía
        }
        CartAdapter cartAdapter = new CartAdapter(this, cartItems);
        recyclerView.setAdapter(cartAdapter);
        buttonConfirm.setOnClickListener(v -> {
            confirmPickup();
        });
    }

    private void confirmPickup() {
        String name = editTextName.getText().toString();
        String time = spinnerTime.getSelectedItem().toString();
        // Aquí podrías por ejemplo mostrar un mensaje o realizar otra acción
    }
}
