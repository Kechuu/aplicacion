package Modelos;

public class Seguidores {

    private int idSeguidores;
    private Negocio idNegocio;
    private Usuario idUsuario;

    public Seguidores() {
    }

    public Seguidores(int idSeguidores, Negocio idNegocio, Usuario idUsuario) {
        this.idSeguidores = idSeguidores;
        this.idNegocio = idNegocio;
        this.idUsuario = idUsuario;
    }
    public Seguidores(Negocio idNegocio, Usuario idUsuario) {
        this.idNegocio = idNegocio;
        this.idUsuario = idUsuario;
    }

    public int getIdSeguidores() {
        return idSeguidores;
    }

    public void setIdSeguidores(int idSeguidores) {
        this.idSeguidores = idSeguidores;
    }

    public Negocio getIdNegocio() {
        return idNegocio;
    }

    public void setIdNegocio(Negocio idNegocio) {
        this.idNegocio = idNegocio;
    }

    public Usuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuario idUsuario) {
        this.idUsuario = idUsuario;
    }
}
