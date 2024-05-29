package com.grupo14.Carrito;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.grupo14.R;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {

    private List<Carrito> carritoItems;
    private LayoutInflater mInflater;
    private Context context;
    private CartActivity cartActivity;
    private FirebaseUser currentUser;

    // Constructor
    public CartAdapter(Context context, List<Carrito> carritoItems) {
        this.mInflater = LayoutInflater.from(context);
        this.carritoItems = carritoItems;
        this.context = context;
        this.cartActivity = (CartActivity) context;
        this.currentUser = FirebaseAuth.getInstance().getCurrentUser();

        // Verificar si el usuario está autenticado
        if (currentUser == null) {
            throw new IllegalStateException("Debes iniciar sesión para gestionar el carrito");
        }
    }

    // Método para crear un nuevo ViewHolder
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_cart, parent, false);
        return new ViewHolder(view);
    }

    // Método para obtener el precio total del carrito
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Carrito item = carritoItems.get(position);
        holder.myTextView.setText(item.getNombre() + " - " + item.getCantidad() + " - " + item.getPrecio() + " €");

        holder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int adapterPosition = holder.getAdapterPosition();
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    showDeleteConfirmationDialog(adapterPosition);
                }
            }
        });
    }
    // Método para obtener el número de elementos en el carrito
    @Override
    public int getItemCount() {
        return carritoItems.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView myTextView;
        ImageButton deleteButton;

        ViewHolder(View itemView) {
            super(itemView);
            myTextView = itemView.findViewById(R.id.item_text);
            deleteButton = itemView.findViewById(R.id.delete_button);
        }
    }

    // método para mostrar el diálogo de confirmación de eliminación
    private void showDeleteConfirmationDialog(int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage("¿Estás seguro de querer eliminar este ítem del carrito?");
        builder.setPositiveButton("Eliminar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                Carrito item = carritoItems.get(position);

                // Verificar si currentUser no es nulo antes de usarlo
                if (currentUser != null) {
                    DatabaseReference cartRef = FirebaseDatabase.getInstance("https://grupo14-252f0-default-rtdb.europe-west1.firebasedatabase.app")
                            .getReference("carritos")
                            .child(currentUser.getUid())
                            .child(item.getId());
                    cartRef.removeValue();
                    carritoItems.remove(position);
                    notifyItemRemoved(position);
                    notifyItemRangeChanged(position, carritoItems.size());
                    cartActivity.updateTotalPrice();  // Actualizar el total del precio
                } else {
                    Toast.makeText(context, "Error: usuario no autenticado", Toast.LENGTH_SHORT).show();
                }
            }
        });
        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}