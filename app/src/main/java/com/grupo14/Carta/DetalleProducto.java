package com.grupo14.Carta;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.grupo14.Carrito.Carrito;
import com.grupo14.Carrito.CartActivity;
import com.grupo14.R;

public class DetalleProducto extends AppCompatActivity {

    private FirebaseUser currentUser;
    private DatabaseReference cartRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalleproducto);

        // Relacionamos la variable con las id del layout
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

        // Obtener la instancia de FirebaseAuth y el usuario actual
        currentUser = FirebaseAuth.getInstance().getCurrentUser();

        // Verificar si el usuario está autenticado
        if (currentUser == null) {
            Toast.makeText(this, "Debes iniciar sesión para añadir productos al carrito", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        // Obtener la referencia de Firebase para el carrito del usuario
        cartRef = FirebaseDatabase.getInstance().getReference("carritos").child(currentUser.getUid());

        // Configurar el botón Añadir al carrito
        addToCartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                añadirAlCarrito(title, price);
            }
        });

        // Configurar el botón Volver
        goBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Simplemente termina la actividad actual, volviendo a la anterior
                finish();
            }
        });
    }

    // Método para añadir el producto al carrito
    private void añadirAlCarrito(String title, String price) {
        String userId = currentUser.getUid();
        String cartItemId = cartRef.push().getKey();
        Carrito carritoItem = new Carrito(cartItemId, userId, title, 1, Double.parseDouble(price.replace(" €", "").replace(",", ".")));

        if (cartItemId != null) {
            cartRef.child(cartItemId).setValue(carritoItem).addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    Toast.makeText(DetalleProducto.this, "Producto añadido al carrito", Toast.LENGTH_SHORT).show();
                    // Redirigir a la actividad del carrito
                    Intent intent = new Intent(DetalleProducto.this, CartActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(DetalleProducto.this, "Error al añadir el producto al carrito", Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(this, "Error al generar ID del carrito", Toast.LENGTH_SHORT).show();
        }
    }
}