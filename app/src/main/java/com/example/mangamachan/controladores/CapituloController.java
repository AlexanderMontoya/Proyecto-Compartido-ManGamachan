package com.example.mangamachan.controladores;

public class CapituloController {
    public String paginas;
    public String name;
    public String id;
    public String idmanga;

    public String getPaginas() {
        return paginas;
    }

    public void setPaginas(String paginas) {
        this.paginas = paginas;
    }

    public String urlimagen1;

    public String getUrlimagen1() {
        return urlimagen1;
    }

    public void setUrlimagen1(String urlimagen1) {
        this.urlimagen1 = urlimagen1;
    }

    public CapituloController(String paginas, String name, String id, String idmanga, String urlimagen1) {
        this.paginas = paginas;
        this.name = name;
        this.id = id;
        this.idmanga=idmanga;
        this.urlimagen1=urlimagen1;
    }

    public String getIdmanga() {
        return idmanga;
    }

    public void setIdmanga(String idmanga) {
        this.idmanga = idmanga;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
