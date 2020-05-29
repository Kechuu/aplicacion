package Controladores;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import Modelos.Seguidores;


public class CtrlSeguidores{

	Connection connection;
	CtrlNegocio ctrlNegocio;
	CtrlUsuario ctrlUsuario;

	public CtrlSeguidores(Connection connection){
	  this.connection=connection;
	  ctrlNegocio=new CtrlNegocio(connection);
	  ctrlUsuario=new CtrlUsuario(connection);
	}


	public void create(Seguidores sgd){
	  try{
		PreparedStatement stmt = connection.prepareStatement("INSERT INTO Seguidores(idNegocio, idUsuario, borrado) VALUES (?, ?, ?)");

		stmt.setInt(1, sgd.getIdNegocio().getIdNegocio());
		stmt.setInt(2, sgd.getIdUsuario().getIdUsuario());
		stmt.setBoolean(3, false);

		stmt.execute();
	  }

	  catch (Exception e){

          }
	}

	public void deleter(int id){
        try{
            PreparedStatement stmt = connection.prepareStatement("UPDATE Seguidores SET borrado = true WHERE idSeguidores = ?");
            stmt.setInt(1, id);

            stmt.execute();
        }
        catch(SQLException ex){
           // JOptionPane.showMessageDialog(null, "Error al ELIMINAR Persona", "ERROR!!!...", ERROR_MESSAGE);
        }
    }


    public void recuperar(int id){
        try{
            PreparedStatement stmt = connection.prepareStatement("UPDATE Seguidores SET borrado = false WHERE idSeguidores = ?");
            stmt.setInt(1, id);

            stmt.execute();
        }
        catch(SQLException ex){
            //JOptionPane.showMessageDialog(null, "Error al RECUPERAR Persona", "ERROR!!!...", ERROR_MESSAGE);
        }
    }
}
