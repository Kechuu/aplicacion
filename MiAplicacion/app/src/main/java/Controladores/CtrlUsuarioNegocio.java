package Controladores;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Modelos.UsuarioNegocio;

public class CtrlUsuarioNegocio{
	
	Connection connection;
	CtrlUsuario ctrlUsuario;
	CtrlNegocio ctrlNegocio;
	
	public CtrlUsuarioNegocio(Connection connection){
		this.connection=connection;
		ctrlUsuario=new CtrlUsuario(connection);
		ctrlNegocio=new CtrlNegocio(connection);
	}
	
	
	public void create(UsuarioNegocio srn) {
		try {
			PreparedStatement stmt=connection.prepareStatement("INSERT INTO UsuarioNegocio (idUsuario, idNegocio) VALUES (?, ?)");
			stmt.setInt(1, srn.getIdUsuario().getIdUsuario());
			stmt.setInt(2, srn.getIdNegocio().getIdNegocio());
			
			stmt.execute();
			
		}
		catch(Exception ex) {
			
		}
	}
	
}
