package com.grupo14;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {

    private List<String> cartItems;
    private LayoutInflater mInflater;
    private Context context; // Contexto para usar en la creación del AlertDialog

    // Constructor
    public CartAdapter(Context context, List<String> cartItems) {
        this.mInflater = LayoutInflater.from(context);
        this.cartItems = cartItems;
        this.context = context; // Guardamos el contexto
    }

    @NonNull
    @Override
    public CartAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_cart, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartAdapter.ViewHolder holder, int position) {
        String item = cartItems.get(position);
        holder.myTextView.setText(item);

        holder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDeleteConfirmationDialog(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return cartItems.size();
    }

    // Clase para Borrar del carrito con la X
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView myTextView;
        ImageButton deleteButton; // Botón de eliminar

        ViewHolder(View itemView) {
            super(itemView);
            myTextView = itemView.findViewById(R.id.item_text);
            deleteButton = itemView.findViewById(R.id.delete_button); // Inicializar el botón de eliminar
        }
    }

    //Método para borrar algún elemento del carrito
    private void showDeleteConfirmationDialog(int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage("¿Estás seguro de querer eliminar este ítem del carrito?");
        builder.setPositiveButton("Eliminar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // Usuario confirmó que quiere eliminar el ítem
                cartItems.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position, cartItems.size());
            }
        });
        //Boton de cancelar la eliminación
        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // Usuario canceló la operación, no hay que hacer nada
                dialog.dismiss();
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}
