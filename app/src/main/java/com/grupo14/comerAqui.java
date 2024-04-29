package com.grupo14;


import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class comerAqui extends AppCompatActivity {

    private EditText editTextName;
    private Spinner spinnerTables;
    private Button buttonConfirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comer_aqui);

        editTextName = findViewById(R.id.editTextName);
        spinnerTables = findViewById(R.id.spinnerTables);
        buttonConfirm = findViewById(R.id.buttonConfirm);

        // Configura el Spinner para las mesas
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.table_numbers, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTables.setAdapter(adapter);

        buttonConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = editTextName.getText().toString();
                String table = spinnerTables.getSelectedItem().toString();
                if (!name.isEmpty()) {
                    // Aquí puedes implementar la lógica para manejar la confirmación
                    // Por ejemplo, mostramos un Toast
                    Toast.makeText(comerAqui.this, "Nombre: " + name + "\nMesa: " + table, Toast.LENGTH_LONG).show();
                    // Si necesitas enviar esta información a otra actividad o servidor, lo harías aquí.
                } else {
                    // El nombre está vacío, así que mostramos un error
                    editTextName.setError("Por favor, ingresa tu nombre.");
                }
            }
        });
    }
}
