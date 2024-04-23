package com.grupo14;


import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class PaymentActivity2 extends AppCompatActivity {

    private ImageView imageViewRedsys;
    private CardView cardViewThankYou;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment2);

        imageViewRedsys = findViewById(R.id.imageViewRedsys);
        cardViewThankYou = findViewById(R.id.cardViewThankYou);

        // Handler para retrasar la aparición del CardView
        new Handler().postDelayed(() -> cardViewThankYou.setVisibility(View.VISIBLE), 6000); // 6 segundos
    }
}


