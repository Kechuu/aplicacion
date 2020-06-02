package Controladores;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Modelos.Usuario;


public class CtrlUsuario{

	Connection connection;
	CtrlPersona ctrlPersona;
	CtrlFotoPerfil ctrlFotoPerfil;

	public CtrlUsuario(Connection connection){

		this.connection=connection;
		ctrlPersona=new CtrlPersona(connection);
		ctrlFotoPerfil = new CtrlFotoPerfil(connection);
	}

	
	public void create(Usuario sr){
		ResultSet rs;
		try{
	    	PreparedStatement stmt=connection.prepareStatement("INSERT INTO Usuario (correo, password, jerarquia, idPersona, borrado, idFotoPerfil) VALUES (?, ?, ?, ?, ?, ?)");
		stmt.setString(1, sr.getCorreo());
		stmt.setString(2, sr.getPassword());
		stmt.setInt(3, sr.getJerarquia());
		stmt.setInt(4, sr.getIdPersona().getIdPersona());
		stmt.setBoolean(5, false);
		stmt.setInt(6,sr.getIdFotoPerfil().getIdFotoPerfil());
		
		stmt.execute();

		rs=stmt.executeQuery("SELECT MAX (idUsuario) AS idUsuario FROM Usuario");
		rs.next();
		sr.setIdUsuario(rs.getInt("idUsuario"));
		}
		catch(SQLException ex){
		//JOptionPane.showMessageDialog(null, "Error al crear nuevo Usuario","ERROR!! ",ERROR_MESSAGE);
		}
	}

	public void edit(Usuario sr){

		try{
		PreparedStatement stmt=connection.prepareStatement("UPDATE Usuario SET correo = ?, password = ?, jerarquia = ?, idFotoPerfil WHERE idUsuario = ?");

		stmt.setString(1, sr.getCorreo());
		stmt.setString(2, sr.getPassword());
		stmt.setInt(3, sr.getJerarquia());
		stmt.setInt(4,sr.getIdFotoPerfil().getIdFotoPerfil());

		stmt.setInt(5, sr.getIdUsuario());

		stmt.execute();

		}
		catch(SQLException ex){
		//JOptionPane.showMessageDialog(null, "Error al editar nuevo Usuario","ERROR!! ",ERROR_MESSAGE);
		}
	}

	public Usuario read(int id){

		Usuario sr=new Usuario();
		ResultSet rs=null;

		try{
		  PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Usuario WHERE idUsuario = ?");
		  stmt.setInt(1, id);

		  rs = stmt.executeQuery();

		  if(rs.next()){
			sr.setIdUsuario(id);
			sr.setCorreo(rs.getString("correo"));
			sr.setPassword(rs.getString("password"));
			sr.setJerarquia(rs.getInt("jerarquia"));
			sr.setIdPersona(ctrlPersona.read(rs.getInt("idPersona")));
			sr.setBorrado(rs.getBoolean("borrado"));
			sr.setIdFotoPerfil(ctrlFotoPerfil.read(rs.getInt("idFotoPerfil")));
		  }else{
			sr = null;
		  }
		}
		catch(SQLException ex){
		  //JOptionPane.showMessageDialog(null, "Error al leer Usuario","ERROR!! ",ERROR_MESSAGE);
		}
		return sr;
	}

	public Usuario read(String rr, String psw){

		Usuario sr=new Usuario();
		ResultSet rs=null;

		try{
		  PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Usuario WHERE correo = ? AND password = ?");
		  stmt.setString(1, rr);
		  stmt.setString(2, psw);

		  rs=stmt.executeQuery();

		  if(rs.next()){
			sr.setIdUsuario(rs.getInt("idUsuario"));
			sr.setCorreo(rs.getString("correo"));
			sr.setPassword(rs.getString("password"));
			sr.setJerarquia(rs.getInt("jerarquia"));
			sr.setIdPersona(ctrlPersona.read(rs.getInt("idPersona")));
			sr.setBorrado(rs.getBoolean("borrado"));
			  sr.setIdFotoPerfil(ctrlFotoPerfil.read(rs.getInt("idFotoPerfil")));
		  }else{
			sr = null;
		  }
		}
		catch(SQLException ex){
		  //JOptionPane.showMessageDialog(null, "Error al leer Usuario","ERROR!! ",ERROR_MESSAGE);
		}

		return sr;
	}

	public void delete(int id){
		try{
            	PreparedStatement stmt = connection.prepareStatement("UPDATE Usuario SET borrado = true WHERE idUsuario = ?");
            	stmt.setInt(1, id);

                stmt.execute();
        	}
        	catch(SQLException ex){
	            //JOptionPane.showMessageDialog(null, "Error al ELIMINAR Usuario", "ERROR!!!...", ERROR_MESSAGE);
        	}

	}

	public void recuperar(int id){

	 try{
            PreparedStatement stmt = connection.prepareStatement("UPDATE Usuario SET borrado = false WHERE idUsuario = ?");
            stmt.setInt(1, id);

            stmt.execute();
         }
         catch(SQLException ex){
            //JOptionPane.showMessageDialog(null, "Error al RECUPERAR Usuario", "ERROR!!!...", ERROR_MESSAGE);
         }

	}
}
