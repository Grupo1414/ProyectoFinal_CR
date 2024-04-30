package com.grupo14;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
public class comerAqui extends AppCompatActivity {

    private EditText editTextName;
    private Spinner spinnerTables;
    private Button buttonConfirm, buttonSave;
    private ImageView[] tables = new ImageView[6]; // Array para almacenar las vistas de las mesas
    private int[] tableIds = {R.id.table1, R.id.table2, R.id.table3, R.id.table4, R.id.table5, R.id.table6}; // IDs de tus ImageView en el layout

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comer_aqui); // Asegúrate de usar el layout correcto.

        editTextName = findViewById(R.id.editTextName);
        spinnerTables = findViewById(R.id.spinnerTables);
        buttonConfirm = findViewById(R.id.buttonConfirm);
        buttonSave = findViewById(R.id.buttonSave);

        setupTables(); // Configurar los listeners para las mesas

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editTextName.getText().toString().trim();
                String table = spinnerTables.getSelectedItem().toString();
                if (!name.isEmpty()) {
                    // Aquí puedes añadir lógica para guardar la información o procesarla
                    Toast.makeText(comerAqui.this, "Nombre: " + name + ", Mesa: " + table, Toast.LENGTH_SHORT).show();
                } else {
                    editTextName.setError("Por favor, introduce tu nombre");
                }
            }
        });

        buttonConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lógica para confirmar la reserva y proceder a PaymentActivity o donde sea necesario
                Intent intent = new Intent(comerAqui.this, PaymentActivity.class);
                startActivity(intent);
            }
        });
    }

    private void setupTables() {
        for (int i = 0; i < tables.length; i++) {
            tables[i] = findViewById(tableIds[i]);
            final int tableNumber = i + 1;
            tables[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Mostrar un Toast o un AlertDialog cuando se selecciona una mesa
                    Toast.makeText(comerAqui.this, "Has seleccionado la mesa " + tableNumber, Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
