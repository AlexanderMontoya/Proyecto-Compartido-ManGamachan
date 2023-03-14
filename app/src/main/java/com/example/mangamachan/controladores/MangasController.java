package com.example.mangamachan.controladores;

public class MangasController {
    public String url_imagen;
    public String name;
    public String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public MangasController(String url_imagen, String name, String id) {
        this.url_imagen = url_imagen;
        this.name = name;
        this.id=id;
    }

    public String getImagen() {
        return url_imagen;
    }

    public void setImagen(String url_imagen) {
        this.url_imagen = url_imagen;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
