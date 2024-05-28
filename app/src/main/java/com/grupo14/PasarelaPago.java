package com.grupo14;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class PasarelaPago extends AppCompatActivity {

    private static final long RETRASO_MOSTRAR_CARTA_PRINCIPAL = 5000; // 5 segundos
    private CardView cardViewThankYou;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pasarelapago);

        cardViewThankYou = findViewById(R.id.cardViewThankYou);

        // Retraso para mostrar el mensaje de agradecimiento
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                cardViewThankYou.setVisibility(View.VISIBLE);
                // Iniciar la actividad CartaPrincipal después de 5 segundos
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intentCartaPrincipal = new Intent(PasarelaPago.this, CartaPrincipal.class);
                        startActivity(intentCartaPrincipal);
                        // Finalizar la actividad actual
                        finish();
                    }
                }, RETRASO_MOSTRAR_CARTA_PRINCIPAL);
            }
        }, RETRASO_MOSTRAR_CARTA_PRINCIPAL);
    }
}