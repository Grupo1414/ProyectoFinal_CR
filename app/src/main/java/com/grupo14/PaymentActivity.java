package com.grupo14;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class PaymentActivity extends AppCompatActivity {

    private EditText editTextAddress;
    private String userAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);


        // EditText y boton de pagar y variable del Alias de la tarjeta
        editTextAddress = findViewById(R.id.editText_address);
        Button buttonSaveAddress = findViewById(R.id.button_edit_address);
        Button addCardButton = findViewById(R.id.button_add_credit_card);
        Button buttonPay = findViewById(R.id.button_pay);
        String alias = getIntent().getStringExtra("ALIAS");

        //Enseña el alias de la tarjeta que hemos puesto en activity_payment
        // Mostrar el alias en el TextView
        TextView textViewSelectedCard = findViewById(R.id.textView_selected_card);
        textViewSelectedCard.setText("Tarjeta Alias seleccionada: " + alias);

        // Evento de clic para guardar la dirección
        buttonSaveAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Obtiene la dirección del EditText y la guarda
                userAddress = editTextAddress.getText().toString().trim();
                saveAddress(userAddress);
            }
        });

        // Nos lleva a meter la tarjeta de credito
        addCardButton.setOnClickListener(v -> {
            Intent intent = new Intent(PaymentActivity.this, AddCardActivity.class);
            startActivity(intent);
        });

        //Nos permite meter la dirección
        buttonSaveAddress.setOnClickListener(v -> {
            if (!editTextAddress.isFocused()) {
                editTextAddress.requestFocus();
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(editTextAddress, InputMethodManager.SHOW_IMPLICIT);
            }
        });

        // Boton de pagar que nos lleva a pagar2
        buttonPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PaymentActivity.this, PaymentActivity2.class);
                startActivity(intent);
            }
        });
    }

    // FALTA GUARDAR posiblemente en base de datos
    private void saveAddress(String address) {

        System.out.println("Dirección guardada: " + address);
    }
}
