package com.grupo14;


import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
public class comerAqui extends AppCompatActivity {

    private EditText editTextName;
    private Spinner spinnerTables;
    private Button buttonConfirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comer_aqui); // Asegúrate de que el nombre del layout sea correcto.

        editTextName = findViewById(R.id.editTextName);
        spinnerTables = findViewById(R.id.spinnerTables);
        buttonConfirm = findViewById(R.id.buttonConfirm);

        // Setup the spinner with the table numbers
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.table_numbers, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTables.setAdapter(adapter);

        buttonConfirm.setOnClickListener(v -> {
            String name = editTextName.getText().toString().trim();
            String table = spinnerTables.getSelectedItem().toString();
            if (!name.isEmpty()) {
                Intent intent = new Intent(comerAqui.this, PaymentActivity.class);
                intent.putExtra("Name", name);
                intent.putExtra("Table", table);
                startActivity(intent);
            } else {
                editTextName.setError("Please enter your name");
            }
        });
    }
}
