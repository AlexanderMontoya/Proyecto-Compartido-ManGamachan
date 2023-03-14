package com.example.mangamachan.controladores;

public class PaginaController {
    public String url_page;



    public String getUrl_page() {
        return url_page;
    }

    public void setUrl_page(String url_page) {
        this.url_page = url_page;
    }

    public PaginaController(String url) {
        this.url_page = url;
    }
}
