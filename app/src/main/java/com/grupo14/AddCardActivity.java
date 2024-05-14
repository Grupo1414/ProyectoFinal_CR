package com.grupo14;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class AddCardActivity extends AppCompatActivity {

    //Variables de los textos
    private EditText editTextAlias;
    private TextView textViewSelectedCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_card);

        Button buttonAddCard = findViewById(R.id.button_add_card);
        editTextAlias = findViewById(R.id.textView_alias_card);
        textViewSelectedCard = findViewById(R.id.textView_alias_card);

        buttonAddCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardarTarjeta();
            }
        });
    }

    private void guardarTarjeta() {
        // Aquí deberías implementar la lógica para guardar la tarjeta con el alias en tu base de datos o donde corresponda

        //Método que te dice que está guardada
        mostrarDialogo();

        // Mostrar el mensaje para avisar de que se ha guardado
        textViewSelectedCard.setText("Tarjeta Alias seleccionada: " + editTextAlias.getText().toString());

        // Navegar a la siguiente actividad
        Intent intent = new Intent(AddCardActivity.this, PaymentActivity.class);
        startActivity(intent);
    }

    //Método que te dice que está guardada
    private void mostrarDialogo() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("La tarjeta con el alias '" + editTextAlias.getText().toString() + "' se ha guardado correctamente.")
                .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // Cerrar el diálogo
                        dialog.dismiss();
                    }
                });
        // Crear y mostrar el diálogo
        builder.create().show();
    }
}
