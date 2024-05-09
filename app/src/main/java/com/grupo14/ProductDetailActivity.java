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

    private static List<String> cartItems = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);


        //Relacionamos la variable con las id del layaout
        ImageView productImage = findViewById(R.id.image_product);
        TextView productTitle = findViewById(R.id.text_product_title);
        TextView productDescription = findViewById(R.id.text_product_description);
        TextView productPrice = findViewById(R.id.text_product_price);
        Button addToCartButton = findViewById(R.id.button_add_to_cart);
        Button goBackButton = findViewById(R.id.button_go_back);

        // Configurar la vista con la información del producto
        String imageResourceName = getIntent().getStringExtra("imageResourceName");
        String title = getIntent().getStringExtra("productTitle");
        String description = getIntent().getStringExtra("productDescription");
        String price = getIntent().getStringExtra("productPrice");
        int imageResourceId = getResources().getIdentifier(imageResourceName, "drawable", getPackageName());

        productImage.setImageResource(imageResourceId);
        productTitle.setText(title);
        productDescription.setText(description);
        productPrice.setText(price);

        // Configurar el botón Añadir al carrito
        addToCartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cartItem = productTitle.getText().toString() + " - " + productPrice.getText().toString();
                cartItems.add(cartItem);
                Intent intent = new Intent(ProductDetailActivity.this, CartActivity.class);
                intent.putStringArrayListExtra("cartItems", new ArrayList<>(cartItems));
                startActivity(intent);
            }
        });

        // Configurar el botón Volver
        goBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Simplemente termina la actividad actual, volviendo a la anterior en el stack
                finish();
            }
        });
    }
}
