package com.grupo14.Carrito;

//Crear objetos de tipo carrito para persistir en realtime database
public class Carrito {
    private String id;
    private String userId;  // Añadir este campo
    private String nombre;
    private int cantidad;
    private double precio;

    public Carrito() {
        // Constructor vacío necesario para Firebase
    }

    public Carrito(String id, String userId, String nombre, int cantidad, double precio) {
        this.id = id;
        this.userId = userId;  // Inicializar este campo
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.precio = precio;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
}