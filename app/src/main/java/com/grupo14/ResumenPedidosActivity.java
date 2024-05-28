package com.grupo14;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ResumenPedidosActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ResumenPedidoAdapter adapter;
    private ArrayList<ResumenPedido> listaPedidos;
    private DatabaseReference pedidosRef;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resumen_pedidos);

        recyclerView = findViewById(R.id.pedidos_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        listaPedidos = new ArrayList<>();
        adapter = new ResumenPedidoAdapter(listaPedidos);
        recyclerView.setAdapter(adapter);

        Button buttonBack = findViewById(R.id.button_back);
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Finaliza la actividad actual y vuelve a la anterior
            }
        });

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            String userId = currentUser.getUid();
            pedidosRef = FirebaseDatabase.getInstance().getReference("Pedidos").child(userId);

            pedidosRef.addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(@NonNull DataSnapshot dataSnapshot, String previousChildName) {
                    ResumenPedido pedido = dataSnapshot.getValue(ResumenPedido.class);
                    if (pedido != null) {
                        listaPedidos.add(pedido);
                        adapter.notifyDataSetChanged();
                    }
                }

                @Override
                public void onChildChanged(@NonNull DataSnapshot dataSnapshot, String previousChildName) {
                    // No es necesario para esta implementación
                }

                @Override
                public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
                    // No es necesario para esta implementación
                }

                @Override
                public void onChildMoved(@NonNull DataSnapshot dataSnapshot, String previousChildName) {
                    // No es necesario para esta implementación
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Log.e("ResumenPedidosActivity", "Error al leer datos: " + error.getMessage());
                }
            });
        } else {
            Log.e("ResumenPedidosActivity", "El usuario no está autenticado");
        }
    }
}