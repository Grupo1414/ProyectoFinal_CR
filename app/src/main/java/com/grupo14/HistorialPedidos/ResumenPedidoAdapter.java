package com.grupo14.HistorialPedidos;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.grupo14.R;

import java.util.ArrayList;

public class ResumenPedidoAdapter extends RecyclerView.Adapter<ResumenPedidoAdapter.PedidoViewHolder> {

    private ArrayList<ResumenPedido> listaPedidos;

    public ResumenPedidoAdapter(ArrayList<ResumenPedido> listaPedidos) {
        this.listaPedidos = listaPedidos;
    }

    @NonNull
    @Override
    public PedidoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_resumen_pedido, parent, false);
        return new PedidoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PedidoViewHolder holder, int position) {
        ResumenPedido pedido = listaPedidos.get(position);
        holder.direccionTextView.setText(pedido.getDireccion());
        holder.numeroTarjetaTextView.setText(pedido.getNumeroTarjeta());
        holder.nombreTarjetaTextView.setText(pedido.getNombreTarjeta());
        holder.horaProgramadaTextView.setText(pedido.getHoraProgramada());
        holder.subtotalTextView.setText(String.valueOf(pedido.getSubtotal()));
    }

    @Override
    public int getItemCount() {
        return listaPedidos.size();
    }

    public static class PedidoViewHolder extends RecyclerView.ViewHolder {
        TextView direccionTextView;
        TextView numeroTarjetaTextView;
        TextView nombreTarjetaTextView;
        TextView horaProgramadaTextView;
        TextView subtotalTextView;

        public PedidoViewHolder(@NonNull View itemView) {
            super(itemView);
            direccionTextView = itemView.findViewById(R.id.direccion_text_view);
            numeroTarjetaTextView = itemView.findViewById(R.id.numero_tarjeta_text_view);
            nombreTarjetaTextView = itemView.findViewById(R.id.nombre_tarjeta_text_view);
            horaProgramadaTextView = itemView.findViewById(R.id.hora_programada_text_view);
            subtotalTextView = itemView.findViewById(R.id.subtotal_text_view);
        }
    }
}