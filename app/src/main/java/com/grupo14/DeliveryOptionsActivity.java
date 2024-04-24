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

        // Configura el evento de clic para el botón de continuar
        buttonContinue.setOnClickListener(v -> {
            // Aquí puedes recoger la opción seleccionada y proceder como necesites
            int selectedId = optionsContainer.getCheckedRadioButtonId();
            // Por ejemplo, inicia la siguiente actividad dependiendo de la selección
            Intent intent;
            if (selectedId == radioButtonDelivery.getId()) {
                // Lógica para la entrega a domicilio
            } else if (selectedId == radioButtonPickup.getId()) {
                // Lógica para recoger en tienda
            } else if (selectedId == radioButtonDineIn.getId()) {
                // Lógica para comer en el lugar
            }

            // Supongamos que todos llevan a la misma PaymentActivity por ahora
            intent = new Intent(DeliveryOptionsActivity.this, PaymentActivity.class);
            startActivity(intent);
        });
    }
}
