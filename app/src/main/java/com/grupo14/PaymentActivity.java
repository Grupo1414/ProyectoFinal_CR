package com.grupo14;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class PaymentActivity extends AppCompatActivity {

    private EditText editTextAddress;
    private String userAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        // Inicializa los componentes de la UI
        editTextAddress = findViewById(R.id.editText_address);
        Button buttonSaveAddress = findViewById(R.id.button_edit_address);
        Button addCardButton = findViewById(R.id.button_add_credit_card);
        Button buttonPay = findViewById(R.id.button_pay);

        // Evento de clic para guardar la dirección
        buttonSaveAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Obtiene la dirección del EditText y la guarda
                userAddress = editTextAddress.getText().toString().trim();
                saveAddress(userAddress);
            }
        });

        // Configura el botón para añadir tarjeta
        addCardButton.setOnClickListener(v -> {
            Intent intent = new Intent(PaymentActivity.this, AddCardActivity.class);
            startActivity(intent);
        });

        // Evento de clic para editar la dirección
        buttonSaveAddress.setOnClickListener(v -> {
            if (!editTextAddress.isFocused()) {
                editTextAddress.requestFocus();
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(editTextAddress, InputMethodManager.SHOW_IMPLICIT);
            }
        });

        // Evento de clic para pagar
        buttonPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PaymentActivity.this, PaymentActivity2.class);
                startActivity(intent);
            }
        });
    }

    // Método para guardar la dirección fuera del método onCreate
    private void saveAddress(String address) {
        // Por ahora, solo guardamos la dirección en una variable.
        // Aquí es donde implementarías el guardado en Firebase más adelante.
        // Muestra la dirección para verificar que se haya guardado
        System.out.println("Dirección guardada: " + address);
    }
}
