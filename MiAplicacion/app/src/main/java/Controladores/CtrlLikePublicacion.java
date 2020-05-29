package Controladores;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Modelos.LikePublicacion;


public class CtrlLikePublicacion{
	
	Connection connection;
	CtrlUsuario ctrlUsuario;
	CtrlPublicaciones ctrlPublicaciones;
	
	public CtrlLikePublicacion(Connection connection) {
		this.connection=connection;
		
		ctrlUsuario=new CtrlUsuario(connection);
		ctrlPublicaciones=new CtrlPublicaciones(connection);
		
	}
	
	public void create(LikePublicacion lkp) {
		try {
			PreparedStatement stmt= connection.prepareStatement("INSERT INTO LikePublicacion (idUsuario, idPublicacion, borrado) VALUES (?, ?, ?)");
			stmt.setInt(1, lkp.getIdUsuario().getIdUsuario());
			stmt.setInt(2, lkp.getIdPublicaciones().getIdPublicaciones());
			stmt.setBoolean(3, false);
			
			stmt.execute();
			
		}
		catch(Exception ex) {
			
		}
	}
	
	public int readId(int sr, int pbl) {
		int idLike=0;
		ResultSet rs=null;
		
		try {
			PreparedStatement stmt= connection.prepareStatement("SELECT idLikePublicacion FROM LikePublicacion WHERE idUsuario= ? AND idPublicaciones = ?");
			stmt.setInt(1, sr);
			stmt.setInt(2, pbl);
			
			rs=stmt.executeQuery();
			
			if(rs.next()) {
				idLike=rs.getInt("idLikePublicacion");
			}
		}
		catch(Exception ex) {
			
		}
		
		return idLike;
	}
	
	public void delete(int id) {
		try {
			PreparedStatement stmt=connection.prepareStatement("UPDATE LikePublicacion SET borrado = true WHERE idLikePublicacion = ?");
			stmt.setInt(1, id);
			
			stmt.execute();
		}
		catch(Exception ex) {
			
		}
	}
	
	public void recuperar(int id) {
		try {
			PreparedStatement stmt = connection.prepareStatement("UPDATE LikePublicacion SET borrado = false WHERE idLikePublicacion = ?");
            stmt.setInt(1, id);

            stmt.execute();
		}
		catch(Exception ex) {
			
		}
	}
	
	
}