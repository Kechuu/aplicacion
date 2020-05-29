package Controladores;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Modelos.Mensaje;

public class CtrlMensaje{
	
	Connection connection;
	CtrlNegocio ctrlNegocio;
	CtrlPersona ctrlPersona;
	CtrlPublicaciones ctrlPublicaciones;
	
	public CtrlMensaje(Connection connection) {
		this.connection=connection;
		ctrlNegocio=new CtrlNegocio(connection);
		ctrlPersona=new CtrlPersona(connection);
		ctrlPublicaciones=new CtrlPublicaciones(connection);
	}
	
	public void create(Mensaje msj) {
		try {
			PreparedStatement stmt=connection.prepareStatement("INSERT INTO Mensaje(idNegocio, idPersona, idPublicaciones, mensaje) VALUES (?, ?, ?, ?)");
			stmt.setInt(1, msj.getIdNegocio().getIdNegocio());
			stmt.setInt(2, msj.getIdPersona().getIdPersona());
			stmt.setInt(3, msj.getIdPublicaciones().getIdPublicaciones());
			stmt.setString(4, msj.getMensaje());
			
			stmt.execute();
		}
		catch(Exception ex) {
			
		}
	}
	
	
	public void edit(Mensaje msj) {
		try {
			PreparedStatement stmt=connection.prepareStatement("UPDATE Mensaje SET mensaje = ? WHERE idNegocio = ? ADN idPersona = ? AND idPublicaciones = ?");
			stmt.setString(1, msj.getMensaje());
			stmt.setInt(2, msj.getIdNegocio().getIdNegocio());
			stmt.setInt(3, msj.getIdPersona().getIdPersona());
			stmt.setInt(4, msj.getIdPublicaciones().getIdPublicaciones());
			
			stmt.execute();
		}
		catch(Exception ex) {
			
		}
	}
}