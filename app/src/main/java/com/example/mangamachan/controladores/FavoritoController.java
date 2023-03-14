package com.example.mangamachan.controladores;

public class FavoritoController {
    private int id;
    private String id_manga;
    private String nombre_manga;
    private String imagen_manga;

    public String getNombre_manga() {
        return nombre_manga;
    }

    public void setNombre_manga(String nombre_manga) {
        this.nombre_manga = nombre_manga;
    }

    public String getImagen_manga() {
        return imagen_manga;
    }

    public void setImagen_manga(String imagen_manga) {
        this.imagen_manga = imagen_manga;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getId_manga() {
        return id_manga;
    }

    public void setId_manga(String id_manga) {
        this.id_manga = id_manga;
    }

    public FavoritoController(int id, String id_manga, String nombre_manga, String imagen_manga) {
        this.id = id;
        this.id_manga = id_manga;
        this.nombre_manga=nombre_manga;
        this.imagen_manga=imagen_manga;
    }
}
