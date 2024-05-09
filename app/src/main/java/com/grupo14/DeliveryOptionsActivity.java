package com.grupo14;


import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import androidx.appcompat.app.AppCompatActivity;

public class DeliveryOptionsActivity extends AppCompatActivity {

    private RadioGroup optionsContainer;
    private RadioButton radioButtonDelivery;
    private RadioButton radioButtonPickup;
    private RadioButton radioButtonDineIn;
    private Button buttonContinue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery_options); // Asegúrate de que este sea el nombre correcto de tu layout XML.

        // Inicializa los componentes de la UI
        optionsContainer = findViewById(R.id.options_container);
        radioButtonDelivery = findViewById(R.id.radioButtonDelivery);
        radioButtonPickup = findViewById(R.id.radioButtonPickup);
        radioButtonDineIn = findViewById(R.id.radioButtonDineIn);
        buttonContinue = findViewById(R.id.buttonContinue);

        buttonContinue.setOnClickListener(v -> {
            int selectedId = optionsContainer.getCheckedRadioButtonId();
            Intent intent;

            if (selectedId == radioButtonPickup.getId()) {
                intent = new Intent(DeliveryOptionsActivity.this, PickupActivity.class);
                intent.putStringArrayListExtra("cartItems", getIntent().getStringArrayListExtra("cartItems")); // Asegúrate de tener los items disponibles aquí
                startActivity(intent);
            } else if (selectedId == radioButtonDelivery.getId()) {
                intent = new Intent(DeliveryOptionsActivity.this, PaymentActivity.class);
                startActivity(intent);
            } else if (selectedId == radioButtonDineIn.getId()) {
                intent = new Intent(DeliveryOptionsActivity.this, comerAqui.class);
                startActivity(intent);
            }
        });

    }
}

//  PaymentActivity.class);