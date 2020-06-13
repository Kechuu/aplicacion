package Modelos;

public class Noticias {

    private String nombreNoticiero;
    private String urlFoto;
    private String descripcion;

    public String getNombreNoticiero() {
        return nombreNoticiero;
    }

    public void setNombreNoticiero(String nombreNoticiero) {
        this.nombreNoticiero = nombreNoticiero;
    }

    public String getUrlFoto() {
        return urlFoto;
    }

    public void setUrlFoto(String urlFoto) {
        this.urlFoto = urlFoto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
