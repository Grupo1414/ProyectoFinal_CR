package com.grupo14;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;

import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

public class CartaPrincipal extends AppCompatActivity {

    private List<String> cart = new ArrayList<>();




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


    //Aquí le metemos toda la información a los productos
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carta);

        // Configuración de los alimentos y sus descipciones

        setupProductButton(R.id.addToCartButton1, "foto1", "Asadillo de pimientos confitados con queso", "Asadillo de Pimientos Confitados con Queso Feta: Delicados pimientos multicolores lentamente confitados en un aceite de oliva virgen extra seleccionado, acompañados de aromáticas hierbas del Mediterráneo y un toque de ajo fresco. Coronado con exquisito queso feta artesanal, este plato ofrece una sinfonía de " + "sabores que celebra la esencia de los ingredientes más puros. Una elección sublime para deleitar el paladar.", "15,55 €");
        setupProductButton(R.id.addToCartButton2, "foto2", "Hamburguesa especial de la Casa", " Exquisita carne de res de primera, cocinada a la perfección y servida en un pan artesanal tostado con queso cheddar añejo, crujiente bacon ahumado, tomate fresco y lechuga tierna. Coronada con nuestra exclusiva salsa secreta, ofrece un deleite gourmet en cada bocado.", "14,50 €");
        setupProductButton(R.id.addToCartButton3, "foto3", "Spaguettis Bolognesa de la casa", "Auténticos spaghetti al dente bañados en una rica y robusta salsa boloñesa, cocinada lentamente para potenciar los sabores del tomate fresco y carne selecta. Este clásico plato se enriquece con un toque de hierbas italianas y se espolvorea con parmesano recién rallado, ofreciendo una experiencia culinaria tradicional y reconfortante.", "15,85 €");
        setupProductButton(R.id.addToCartButton4, "foto4", "Rape sapito con ajetes tiernos", "Delicado lomo de rape, cocinado a la perfección hasta obtener una textura suave y jugosa, servido sobre un lecho de ajetes tiernos salteados. Este plato se realza con una ligera emulsión de ajo y aceite de oliva, que sublima los sabores marinos del pescado mientras preserva la frescura y el ligero picor de los ajetes. Un equilibrio perfecto entre elegancia y sabor.", "19,85 €");
        setupProductButton(R.id.addToCartButton5, "foto5", "Kebap Mixto de la Casa", "Jugosa combinación de carnes de cordero y pollo, marinadas en especias orientales y asadas a la perfección. Servido con pan pita fresco, crujientes verduras y una salsa de yogur casera, este kebap es una delicia llena de sabores intensos y texturas variadas.", "8,85 €");
        setupProductButton(R.id.addToCartButton6, "foto6", "Albondigas caseras", "Nuestras albóndigas, hechas a mano con carne de res y cerdo, se cocinan lentamente en una salsa de tomate rica y herbácea. Este plato casero se sirve espolvoreado con queso Parmesano fresco, ofreciendo un sabor reconfortante y auténtico.", "13,85 €");
        setupProductButton(R.id.addToCartButton7, "foto7", "Pizza al gusto en horno de leña", "Elaborada con una masa artesanal que fermenta durante 48 horas, nuestra pizza se cocina en horno de leña, lo que le otorga un borde crujiente y un sabor ahumado único. Personalízala con ingredientes de tu elección para una experiencia culinaria verdaderamente a medida.", "18,50 €");
        setupProductButton(R.id.addToCartButton8, "foto8", "Tartar de salmón ahumado", "Fino tartar de salmón ahumado, preparado con cebolla roja, alcaparras y un toque de eneldo fresco. Aliñado con aceite de oliva extra virgen y jugo de limón, este plato se presenta con elegancia y es ideal para los amantes de los sabores delicados y frescos del mar.", "16,85 €");


        //Botones de AÑADIR CARRITO y Carrito

        ImageView imageViewCart = findViewById(R.id.imageViewCart);
        ImageButton addToCartButton1 = findViewById(R.id.addToCartButton1);
        ImageButton addToCartButton2 = findViewById(R.id.addToCartButton2);
        ImageButton addToCartButton3 = findViewById(R.id.addToCartButton3);
        ImageButton addToCartButton4 = findViewById(R.id.addToCartButton4);
        ImageButton addToCartButton5 = findViewById(R.id.addToCartButton5);
        ImageButton addToCartButton6 = findViewById(R.id.addToCartButton6);
        ImageButton addToCartButton7 = findViewById(R.id.addToCartButton7);
        ImageButton addToCartButton8 = findViewById(R.id.addToCartButton8);

        imageViewCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Crea un Intent para iniciar CartActivity
                Intent intent = new Intent(getApplicationContext(), CartActivity.class);
                startActivity(intent);
            }
        });

    }
}
