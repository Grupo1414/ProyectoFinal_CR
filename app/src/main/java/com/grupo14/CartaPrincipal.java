package com.grupo14;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;
import android.widget.ImageButton;

public class CartaPrincipal extends AppCompatActivity {

    private List<String> cart = new ArrayList<>();


    // Método para abrir ProductDetailActivity
    // Método para abrir ProductDetailActivity con todos los datos del producto
    private void openProductDetail(String imageResourceName, String title, String description, String precio) {
        Intent intent = new Intent(CartaPrincipal.this, ProductDetailActivity.class);
        intent.putExtra("imageResourceName", imageResourceName);
        intent.putExtra("productTitle", title);
        intent.putExtra("productDescription", description);
        intent.putExtra("productPrice", precio);
        startActivity(intent);
    }


    //Rellena los objetos individualmente
    private void setupProductButton(int buttonId, String imageName, String title, String description, String precio) {
        ImageButton button = findViewById(buttonId);
        button.setOnClickListener(v -> {
            Intent intent = new Intent(CartaPrincipal.this, ProductDetailActivity.class);
            intent.putExtra("imageResourceName", imageName);
            intent.putExtra("productTitle", title);
            intent.putExtra("productDescription", description);
            intent.putExtra("productPrice", precio); // Cambiado a productPrice para mantener la consistencia
            startActivity(intent);
        });
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carta);

        // Configuración de los botones de producto con los precios ascendentes
        setupProductButton(R.id.addToCartButton1, "foto1", "Chicken Burger BBQ", "Delicioso pollo con salsa BBQ.", "10 €");
        setupProductButton(R.id.addToCartButton2, "foto2", "Comida especial de la foto 2  ", "Descripción del producto 2.", "20 €");
        setupProductButton(R.id.addToCartButton3, "foto3", "Comida especial de la foto 2 ", "Descripción del producto 3.", "30 €");
        setupProductButton(R.id.addToCartButton4, "foto4", "Comida especial de la foto 4", "Descripción del producto 4.", "40 €");
        setupProductButton(R.id.addToCartButton5, "foto5", "Comida especial de la foto 5", "Descripción del producto 5.", "50 €");
        setupProductButton(R.id.addToCartButton6, "foto6", "Comida especial de la foto 6", "Descripción del producto 6.", "60 €");
        setupProductButton(R.id.addToCartButton7, "foto7", "Comida especial de la foto 7", "Descripción del producto 7.", "70 €");
        setupProductButton(R.id.addToCartButton8, "foto8", "Comida especial de la foto 8", "Descripción del producto 8.", "80 €");


        //Botones de AÑADIR CARRITO

        ImageButton addToCartButton1 = findViewById(R.id.addToCartButton1);
        ImageButton addToCartButton2 = findViewById(R.id.addToCartButton2);
        ImageButton addToCartButton3 = findViewById(R.id.addToCartButton3);
        ImageButton addToCartButton4 = findViewById(R.id.addToCartButton4);
        ImageButton addToCartButton5 = findViewById(R.id.addToCartButton5);
        ImageButton addToCartButton6 = findViewById(R.id.addToCartButton6);
        ImageButton addToCartButton7 = findViewById(R.id.addToCartButton7);
        ImageButton addToCartButton8 = findViewById(R.id.addToCartButton8);


    }
}
