package com.grupo14;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class PaymentActivity extends AppCompatActivity {

    //... Omitido por brevedad

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        // Configura el botón para añadir tarjeta
        Button addCardButton = findViewById(R.id.button_add_credit_card);
        addCardButton.setOnClickListener(v -> {
            Intent intent = new Intent(PaymentActivity.this, AddCardActivity.class);
            startActivity(intent);
        });

        Button editButton = findViewById(R.id.button_edit_address);
        EditText addressEditText = findViewById(R.id.editText_address);

        editButton.setOnClickListener(v -> {
            if (!addressEditText.isFocused()) {
                addressEditText.requestFocus();
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(addressEditText, InputMethodManager.SHOW_IMPLICIT);
            }
        });


    }
}
