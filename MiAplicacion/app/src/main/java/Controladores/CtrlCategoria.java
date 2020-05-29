package Controladores;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Modelos.Categoria;

public class CtrlCategoria {
    Connection connection;

    public CtrlCategoria(Connection connection) {
        this.connection = connection;
    }

    public void create(Categoria ctgr) throws SQLException {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO Categoria (categoria) VALUES (?)");
            statement.setString(1,ctgr.getCategoria());
            statement.execute();
        }catch (Exception e){

        }
        connection.close();
    }

    public void edit(Categoria ctgr){
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE Categoria SET categoria = ? WHERE idCategoria = ?");
            statement.setString(1,ctgr.getCategoria());
            statement.setInt(2,ctgr.getIdCategoria());

            statement.execute();
        }catch (Exception e){

        }
    }

    public Categoria read(int id){
        Categoria ctgr = new Categoria();
        ResultSet rs = null;
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT idCategoria, categoria FROM Categoria WHERE idCategoria = ?");
            statement.setInt(1,id);

            rs=statement.executeQuery();
            if (rs.next()){
                ctgr.setIdCategoria(rs.getInt("idCategoria"));
                ctgr.setCategoria(rs.getString("categoria"));
            }else ctgr=null;
        }catch (Exception e){

        }
        return ctgr;
    }

    public Categoria read(String categoria){
        Categoria ctgr = new Categoria();
        ResultSet rs = null;
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT idCategoria, categoria FROM Categoria WHERE categoria = ?");
            statement.setString(1,categoria);

            rs=statement.executeQuery();
            if (rs.next()){
                ctgr.setIdCategoria(rs.getInt("idCategoria"));
                ctgr.setCategoria(rs.getString("categoria"));
            }else ctgr=null;
        }catch (Exception e){

        }
        return ctgr;
    }

    public List<String> readAll(){
        ResultSet rs =null;
        List<String> lista = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT categoria FROM Categoria ORDER BY categoria");
            rs = statement.executeQuery();

            while (rs.next()){
                lista.add(rs.getString("categoria"));
            }
        }catch (Exception e){

        }
        return lista;
    }
}
