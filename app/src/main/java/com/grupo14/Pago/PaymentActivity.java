package com.grupo14.Pago;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.grupo14.Carrito.Carrito;
import com.grupo14.Carta.CartaPrincipal;
import com.grupo14.HistorialPedidos.ResumenPedido;
import com.grupo14.R;

import java.util.ArrayList;

public class PaymentActivity extends AppCompatActivity {

    private EditText direccionEditText, numeroTarjetaEditText, nombreTarjetaEditText;
    private Spinner spinnerHoraProgramada;
    private double subtotal = 0.0;
    private Button buttonPay, buttonAddCreditCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pago);

        direccionEditText = findViewById(R.id.edittext_additional_info);
        numeroTarjetaEditText = findViewById(R.id.edittext_card_number);
        nombreTarjetaEditText = findViewById(R.id.edittext_card_name);
        spinnerHoraProgramada = findViewById(R.id.spinner_schedule_time);
        buttonPay = findViewById(R.id.button_pay);
        buttonAddCreditCard = findViewById(R.id.button_add_credit_card);

        Button backButton = findViewById(R.id.button_volverCarrito);
        backButton.setOnClickListener(v -> finish());

        // Obtener el subtotal del Intent
        subtotal = getIntent().getDoubleExtra("SUBTOTAL", 0.0);
        if (subtotal == 0.0) {
            Toast.makeText(this, "Error: el subtotal es 0.0", Toast.LENGTH_SHORT).show();
        }

        // Mostrar campos de tarjeta solo al pulsar el botón de añadir
        buttonAddCreditCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numeroTarjetaEditText.setVisibility(View.VISIBLE);
                nombreTarjetaEditText.setVisibility(View.VISIBLE);
            }
        });

        // Gestion del botón de pago
        buttonPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardarPedido();
            }
        });
    }

    // Método para guardar el pedido en firebase
    private void guardarPedido() {
        // Obtener valores de los campos de texto
        String direccion = direccionEditText.getText().toString().trim();
        String numeroTarjeta = numeroTarjetaEditText.getText().toString().trim();
        String nombreTarjeta = nombreTarjetaEditText.getText().toString().trim();
        String horaProgramada = spinnerHoraProgramada.getSelectedItem().toString();

        // Validar los campos
        if (direccion.isEmpty() || horaProgramada.isEmpty()) {
            Toast.makeText(this, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        // Validar campos de tarjeta si son visibles
        if (numeroTarjetaEditText.getVisibility() == View.VISIBLE || nombreTarjetaEditText.getVisibility() == View.VISIBLE) {
            if (numeroTarjeta.isEmpty() || nombreTarjeta.isEmpty()) {
                Toast.makeText(this, "Por favor, completa todos los campos de la tarjeta", Toast.LENGTH_SHORT).show();
                return;
            }
        }

        // Verificar el valor de subtotal
        if (subtotal == 0.0) {
            Toast.makeText(this, "Error: el subtotal es 0.0", Toast.LENGTH_SHORT).show();
            return;
        }

        // Obtener el usuario actual
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        if (firebaseAuth.getCurrentUser() != null) {
            String usuarioId = firebaseAuth.getCurrentUser().getUid();

            // Obtener los productos del carrito
            obtenerProductosDelCarrito(usuarioId, new CarritoCallback() {
                @Override
                public void onCarritoObtenido(ArrayList<Carrito> carrito) {
                    // Referencia a la base de datos para guardar el pedido
                    DatabaseReference pedidoRef = FirebaseDatabase.getInstance().getReference("Pedidos").child(usuarioId).push();
                    ResumenPedido resumenPedido = new ResumenPedido(direccion, numeroTarjeta, nombreTarjeta, horaProgramada, subtotal, carrito, usuarioId);
                    pedidoRef.setValue(resumenPedido);

                    // Vaciar el carrito del usuario
                    vaciarCarrito(usuarioId);

                    // Mostrar un mensaje de éxito y redirigir
                    Toast.makeText(PaymentActivity.this, "Pedido realizado con éxito", Toast.LENGTH_SHORT).show();

                    // Redireccionar a PasarelaPago
                    Intent intentPasarelaPago = new Intent(PaymentActivity.this, PasarelaPago.class);
                    startActivity(intentPasarelaPago);

                    // Retrasar la redirección a CartaPrincipal
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Intent intentCartaPrincipal = new Intent(PaymentActivity.this, CartaPrincipal.class);
                            startActivity(intentCartaPrincipal);

                            finish();
                        }
                    }, 5000); // 5000 milisegundos = 5 segundos
                }
            });
        } else {
            Toast.makeText(this, "Error: usuario no autenticado", Toast.LENGTH_SHORT).show();
        }
    }

    private void obtenerProductosDelCarrito(String usuarioId, CarritoCallback callback) {
        ArrayList<Carrito> productosCarrito = new ArrayList<>();
        DatabaseReference carritoRef = FirebaseDatabase.getInstance().getReference("carritos").child(usuarioId);
        carritoRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Carrito producto = snapshot.getValue(Carrito.class);
                    productosCarrito.add(producto);
                }
                callback.onCarritoObtenido(productosCarrito);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Manejar el error si la operación es cancelada
                Toast.makeText(PaymentActivity.this, "Error al obtener productos del carrito", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void vaciarCarrito(String usuarioId) {
        DatabaseReference carritoRef = FirebaseDatabase.getInstance().getReference("carritos").child(usuarioId);
        carritoRef.removeValue();
    }

    // Interfaz para el callback del carrito
    private interface CarritoCallback {
        void onCarritoObtenido(ArrayList<Carrito> carrito);
    }
}