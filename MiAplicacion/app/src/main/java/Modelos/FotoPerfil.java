package Modelos;

import java.sql.Blob;

public class FotoPerfil {

    private int idFotoPerfil;
    private Blob foto;


    public int getIdFotoPerfil() {
        return idFotoPerfil;
    }

    public void setIdFotoPerfil(int idFotoPerfil) {
        this.idFotoPerfil = idFotoPerfil;
    }

    public Blob getFoto() {
        return foto;
    }

    public void setFoto(Blob foto) {
        this.foto = foto;
    }

}
