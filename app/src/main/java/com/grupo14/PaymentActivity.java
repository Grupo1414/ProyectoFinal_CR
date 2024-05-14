package com.grupo14;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class PaymentActivity extends AppCompatActivity {

    private TextView textViewSelectedCard, subtotalTextView;
    private double subtotal = 0.0; // Este valor ahora será obtenido del intent

    private Spinner ProgramarComida;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        // Inicialización de las vistas
        subtotalTextView = findViewById(R.id.subtotal_price);
        Button addCardButton = findViewById(R.id.button_add_credit_card);
        Button buttonPay = findViewById(R.id.button_pay);
        Button buttonBackToCart = findViewById(R.id.VolverCarrito); // Agrega el ID correcto si es diferente

        // Obtener el subtotal desde el intent
        subtotal = getIntent().getDoubleExtra("SUBTOTAL", 0.0);

        // Establecer el subtotal en el TextView
        subtotalTextView.setText(String.format("%.2f €", subtotal));

        // Listener para añadir tarjeta
        addCardButton.setOnClickListener(v -> {
            Intent intent = new Intent(PaymentActivity.this, AddCardActivity.class);
            startActivity(intent);
        });

        // Listener para el botón de pagar que lleva a PaymentActivity2
        buttonPay.setOnClickListener(v -> {
            Intent intent = new Intent(PaymentActivity.this, PaymentActivity2.class);
            startActivity(intent);
        });

        // Listener para el botón "Volver al carrito"
        buttonBackToCart.setOnClickListener(v -> {
            // Navega de regreso a CartActivity
            Intent intent = new Intent(PaymentActivity.this, CartActivity.class);
            startActivity(intent);
        });

        // Spinner de "Programar Comida"
        ProgramarComida = findViewById(R.id.spinner_schedule_time);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.pickup_times, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ProgramarComida.setAdapter(adapter);
    }
}
