package Modelos;

public class LikePublicacion {

    private int idLike;
    private Usuario idUsuario;
    private Publicaciones idPublicaciones;


    public int getIdLike() {
        return idLike;
    }

    public void setIdLike(int idLike) {
        this.idLike = idLike;
    }

    public Usuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuario idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Publicaciones getIdPublicaciones() {
        return idPublicaciones;
    }

    public void setIdPublicaciones(Publicaciones idPublicaciones) {
        this.idPublicaciones = idPublicaciones;
    }
}
