package Modelos;

public class CategoriaEtiqueta {
    private int idCategoriaEtiqueta;
    private Categoria idCategoria;
    private Etiqueta idEtiqueta;


    public int getIdCategoriaEtiqueta() {
        return idCategoriaEtiqueta;
    }

    public void setIdCategoriaEtiqueta(int idCategoriaEtiqueta) {
        this.idCategoriaEtiqueta = idCategoriaEtiqueta;
    }

    public Categoria getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Categoria idCategoria) {
        this.idCategoria = idCategoria;
    }

    public Etiqueta getIdEtiqueta() {
        return idEtiqueta;
    }

    public void setIdEtiqueta(Etiqueta idEtiqueta) {
        this.idEtiqueta = idEtiqueta;
    }
}
