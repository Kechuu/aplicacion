package Controladores;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Modelos.Foto;


public class CtrlFoto{ 
	Connection connection;

	public CtrlFoto(Connection connection){
		this.connection=connection;
	}

	public void create(Foto ft) throws SQLException {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO Foto (foto) VALUES (?)");
            statement.setBlob(1,ft.getFoto());
            statement.execute();
        }catch (Exception e){

        }
        connection.close();
    }

    public void edit(Foto ft){
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE Foto SET foto = ? WHERE idFoto = ?");
            statement.setBlob(1,ft.getFoto());
            statement.setInt(2,ft.getIdFoto());

            statement.execute();
        }catch (Exception e){

        }
    }

    public Foto read(int id){
        Foto ft = new Foto();
        ResultSet rs = null;
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT idFoto, foto FROM Foto WHERE idFoto = ?");
            statement.setInt(1,id);

            rs=statement.executeQuery();
            if (rs.next()){
                ft.setIdFoto(rs.getInt("idFoto"));
                ft.setFoto(rs.getBlob("foto"));
            }else ft=null;
        }catch (Exception e){

        }
        return ft;
    }


}
