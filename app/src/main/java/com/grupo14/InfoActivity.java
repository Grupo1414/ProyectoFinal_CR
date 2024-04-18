package com.grupo14;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class InfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        // Recibir datos enviados desde MainActivity
        String description = getIntent().getStringExtra("description");
        int photoResId = getIntent().getIntExtra("photoResId", R.drawable.foto1);

        // Mostrar la descripción del plato y la foto
        TextView descriptionTextView = findViewById(R.id.descriptionTextView);
        descriptionTextView.setText(description);

        ImageView photoImageView = findViewById(R.id.photoImageView);
        photoImageView.setImageResource(photoResId);
    }
}
