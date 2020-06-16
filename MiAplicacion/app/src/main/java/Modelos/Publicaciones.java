package Modelos;

public class Publicaciones {
    private String nombreNegocio;
    private String titulo;
    private String precio;
    private String descripcion;
    private boolean stock;
    private String urlFoto;
    private String idUsuario;
    private String urlFotoPerfil;
    private int jerarquia;

    public Publicaciones(String nombreNegocio, String titulo, String precio, String descripcion, boolean stock, String urlFoto, String idUsuario, String urlFotoPerfil, int jerarquia) {
        this.nombreNegocio = nombreNegocio;
        this.titulo = titulo;
        this.precio = precio;
        this.descripcion = descripcion;
        this.stock = stock;
        this.urlFoto = urlFoto;
        this.idUsuario = idUsuario;
        this.urlFotoPerfil = urlFotoPerfil;
        this.jerarquia = jerarquia;
    }

    public Publicaciones() {
    }

    public String getNombreNegocio() {
        return nombreNegocio;
    }

    public void setNombreNegocio(String nombreNegocio) {
        this.nombreNegocio = nombreNegocio;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean isStock() {
        return stock;
    }

    public void setStock(boolean stock) {
        this.stock = stock;
    }

    public String getUrlFoto() {
        return urlFoto;
    }

    public void setUrlFoto(String urlFoto) {
        this.urlFoto = urlFoto;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getUrlFotoPerfil() {
        return urlFotoPerfil;
    }

    public void setUrlFotoPerfil(String urlFotoPerfil) {
        this.urlFotoPerfil = urlFotoPerfil;
    }
}
