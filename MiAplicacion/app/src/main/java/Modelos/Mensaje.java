package Modelos;

public class Mensaje {

    private int idMensaje;
    private Negocio idNegocio;
    private Persona idPersona;
    private Publicaciones idPublicaciones;
    private String mensaje;


    public int getIdMensaje() {
        return idMensaje;
    }

    public void setIdMensaje(int idMensaje) {
        this.idMensaje = idMensaje;
    }

    public Negocio getIdNegocio() {
        return idNegocio;
    }

    public void setIdNegocio(Negocio idNegocio) {
        this.idNegocio = idNegocio;
    }

    public Persona getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(Persona idPersona) {
        this.idPersona = idPersona;
    }

    public Publicaciones getIdPublicaciones() {
        return idPublicaciones;
    }

    public void setIdPublicaciones(Publicaciones idPublicaciones) {
        this.idPublicaciones = idPublicaciones;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}
