package com.grupo14.HistorialPedidos;

import com.grupo14.Carrito.Carrito;

import java.util.ArrayList;

public class ResumenPedido {
    private String direccion;
    private String numeroTarjeta;
    private String nombreTarjeta;
    private String horaProgramada;
    private double subtotal;
    private ArrayList<Carrito> carrito;
    private String userId;

    public ResumenPedido() {
        // Constructor vacío requerido por Firebase
    }

    // Constructor para el resumen de pedido
    public ResumenPedido(String direccion, String numeroTarjeta, String nombreTarjeta, String horaProgramada, double subtotal, ArrayList<Carrito> carrito, String userId) {
        this.direccion = direccion;
        this.numeroTarjeta = numeroTarjeta;
        this.nombreTarjeta = nombreTarjeta;
        this.horaProgramada = horaProgramada;
        this.subtotal = subtotal;
        this.carrito = carrito;
        this.userId = userId;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getNumeroTarjeta() {
        return numeroTarjeta;
    }

    public void setNumeroTarjeta(String numeroTarjeta) {
        this.numeroTarjeta = numeroTarjeta;
    }

    public String getNombreTarjeta() {
        return nombreTarjeta;
    }

    public void setNombreTarjeta(String nombreTarjeta) {
        this.nombreTarjeta = nombreTarjeta;
    }

    public String getHoraProgramada() {
        return horaProgramada;
    }

    public void setHoraProgramada(String horaProgramada) {
        this.horaProgramada = horaProgramada;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public ArrayList<Carrito> getCarrito() {
        return carrito;
    }

    public void setCarrito(ArrayList<Carrito> carrito) {
        this.carrito = carrito;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}