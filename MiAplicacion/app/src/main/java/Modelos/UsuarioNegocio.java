package Modelos;

public class UsuarioNegocio {

    private int idUsuarioNegocio;
    private Usuario idUsuario;
    private Negocio idNegocio;


    public int getIdUsuarioNegocio() {
        return idUsuarioNegocio;
    }

    public void setIdUsuarioNegocio(int idUsuarioNegocio) {
        this.idUsuarioNegocio = idUsuarioNegocio;
    }

    public Usuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuario idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Negocio getIdNegocio() {
        return idNegocio;
    }

    public void setIdNegocio(Negocio idNegocio) {
        this.idNegocio = idNegocio;
    }
}
