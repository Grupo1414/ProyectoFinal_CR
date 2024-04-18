package com.grupo14;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class ProductDetailActivity extends AppCompatActivity {

    // Hacer la lista de cartItems estática para mantener su estado a través de las instancias de la actividad
    private static List<String> cartItems = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        ImageView productImage = findViewById(R.id.image_product);
        TextView productTitle = findViewById(R.id.text_product_title);
        TextView productDescription = findViewById(R.id.text_product_description);
        TextView productPrice = findViewById(R.id.text_product_price);

        // Obtener los extras del Intent
        String imageResourceName = getIntent().getStringExtra("imageResourceName");
        String title = getIntent().getStringExtra("productTitle");
        String description = getIntent().getStringExtra("productDescription");
        String price = getIntent().getStringExtra("productPrice");

        // Configurar los elementos de la vista
        int imageResourceId = getResources().getIdentifier(imageResourceName, "drawable", getPackageName());
        productImage.setImageResource(imageResourceId);
        productTitle.setText(title);
        productDescription.setText(description);
        productPrice.setText(price);



        //Boton para Añadir al carrito la comida

        Button addToCartButton = findViewById(R.id.button_add_to_cart);
        addToCartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obtén los detalles del producto de la interfaz de usuario o del Intent.
                String productTitle = getIntent().getStringExtra("productTitle");
                String productDescription = getIntent().getStringExtra("productDescription");
                String productPrice = getIntent().getStringExtra("productPrice");

                // Crea un formato para la lista del carrito, por ejemplo: "Chicken Burger BBQ - 1,55€"
                String cartItem = productTitle + " - " + productPrice;

                // Añadir el producto al carrito
                cartItems.add(cartItem);

                // Inicia el carro con la lista completa de la comida en el carrito
                Intent intent = new Intent(ProductDetailActivity.this, CartActivity.class);
                intent.putStringArrayListExtra("cartItems", new ArrayList<>(cartItems));
                startActivity(intent);
            }
        });
    }
}
