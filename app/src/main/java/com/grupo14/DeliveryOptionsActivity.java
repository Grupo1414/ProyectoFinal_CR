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

            // Reemplaza "DeliveryActivity.class", "PickupActivity.class" y "DineInActivity.class" con las clases de tus actividades reales.
            if (selectedId == radioButtonDelivery.getId()) {
                intent = new Intent(DeliveryOptionsActivity.this, PaymentActivity.class);
            } else if (selectedId == radioButtonPickup.getId()) {
                intent = new Intent(DeliveryOptionsActivity.this, recoger.class);
            } else if (selectedId == radioButtonDineIn.getId()) {
                intent = new Intent(DeliveryOptionsActivity.this, comerAqui.class);
            } else {
                // Si no se seleccionó ninguna opción, puedes optar por no hacer nada
                // o mostrar un mensaje al usuario, o simplemente seleccionar una opción por defecto
                return;
            }

            startActivity(intent);
        });

    }
}

//  PaymentActivity.class);