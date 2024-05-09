package com.grupo14;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class PaymentActivity extends AppCompatActivity {

    private TextView textViewSelectedCard, subtotalTextView;
    private double subtotal = 0.0; // Este valor ahora será obtenido del intent

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        // Inicialización de las vistas
        textViewSelectedCard = findViewById(R.id.textView_selected_card);
        subtotalTextView = findViewById(R.id.subtotal_price);
        Button addCardButton = findViewById(R.id.button_add_credit_card);
        Button buttonPay = findViewById(R.id.button_pay);

        // Obtener el alias y subtotal desde el intent
        String alias = getIntent().getStringExtra("ALIAS");
        subtotal = getIntent().getDoubleExtra("SUBTOTAL", 0.0);

        // Establecer el texto del alias de la tarjeta seleccionada
        textViewSelectedCard.setText("Tarjeta Alias seleccionada: " + (alias != null ? alias : "Ninguna"));

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
    }
}
