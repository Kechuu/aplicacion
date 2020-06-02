package Controladores;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Modelos.FotoPerfil;


public class CtrlFotoPerfil {
	Connection connection;

	public CtrlFotoPerfil(Connection connection){
		this.connection=connection;
	}

	public void create(FotoPerfil ft) throws SQLException {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO FotoPerfil (foto) VALUES (?)");
            statement.setBlob(1,ft.getFoto());
            statement.execute();
        }catch (Exception e){

        }
        connection.close();
    }

    public void edit(FotoPerfil ft){
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE Foto SET foto = ? WHERE idFoto = ?");
            statement.setBlob(1,ft.getFoto());
            statement.setInt(2,ft.getIdFotoPerfil());

            statement.execute();
        }catch (Exception e){

        }
    }

    public FotoPerfil read(int id){
        FotoPerfil ft = new FotoPerfil();
        ResultSet rs = null;
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT idFoto, foto FROM Foto WHERE idFoto = ?");
            statement.setInt(1,id);

            rs=statement.executeQuery();
            if (rs.next()){
                ft.setIdFotoPerfil(rs.getInt("idFoto"));
                ft.setFoto(rs.getBlob("foto"));
            }else ft=null;
        }catch (Exception e){

        }
        return ft;
    }


}
