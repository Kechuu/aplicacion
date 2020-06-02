package Controladores;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Modelos.Negocio;


public class CtrlNegocio{

	Connection connection;
	CtrlCategoria ctrlCategoria;
	CtrlDomicilio ctrlDomicilio;
	CtrlFotoPerfil ctrlFotoPerfil;

	public CtrlNegocio(Connection connection){
	  this.connection=connection;
	  ctrlCategoria=new CtrlCategoria(connection);
	  ctrlDomicilio=new CtrlDomicilio(connection);
	  ctrlFotoPerfil=new CtrlFotoPerfil(connection);
	}

	public void create(Negocio ngc){
		ResultSet rs;
		try{
		  PreparedStatement stmt=connection.prepareStatement("INSERT INTO Negocio (nombre, descripcion, idCategoria, idDomicilio, borrado, idFotoPerfil) VALUES (?, ?, ?, ?, ?, ?)");
		  stmt.setString(1, ngc.getNombre());
		  stmt.setString(2, ngc.getDescripcion());
		  stmt.setInt(3, ngc.getIdCategoria().getIdCategoria());
		  stmt.setInt(4, ngc.getIdDomicilio().getIdDomicilio());
		  stmt.setBoolean(5, false);
		  stmt.setInt(6,ngc.getIdFotoPerfil().getIdFotoPerfil());

		  stmt.execute();
		
		}
		catch (Exception e){

	        }
	}

	public void edit(Negocio ngc){

		try{
		  PreparedStatement stmt=connection.prepareStatement("UPDATE Negocio SET nombre =?, descripcion =?, idCategoria =?, idFotoPerfil) VALUES (?, ?, ?, ?)");
		  stmt.setString(1, ngc.getNombre());
		  stmt.setString(2, ngc.getDescripcion());
		  stmt.setInt(3, ngc.getIdCategoria().getIdCategoria());
		  stmt.setInt(4,ngc.getIdFotoPerfil().getIdFotoPerfil());
		  
		  stmt.execute();
		}
		catch (Exception e){

	        }
	}

	public void delete(int id){
  	try{
            PreparedStatement stmt = connection.prepareStatement("UPDATE Negocio SET borrado = true WHERE idNegocio = ?");
            stmt.setInt(1, id);

            stmt.execute();
        }
        catch(SQLException ex)
        {
            //JOptionPane.showMessageDialog(null, "Error al ELIMINAR Persona", "ERROR!!!...", ERROR_MESSAGE);
        }
   	}

	public void recuperar(int id){

 	try{
            PreparedStatement stmt = connection.prepareStatement("UPDATE Negocio SET borrado = false WHERE idNegocio = ?");
            stmt.setInt(1, id);

            stmt.execute();
        }
        catch(SQLException ex)
        {
            //JOptionPane.showMessageDialog(null, "Error al RECUPERAR Persona", "ERROR!!!...", ERROR_MESSAGE);
        }
	}

	public Negocio read(int id){

	Negocio ng=new Negocio();
        ResultSet rs = null;
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM Negocio WHERE idNegocio = ?");
            statement.setInt(1,id);

            rs=statement.executeQuery();
            if (rs.next()){
                ng.setIdNegocio(rs.getInt("idNegocio"));
                ng.setNombre(rs.getString("nombre"));
				ng.setDescripcion(rs.getString("descripcion"));
				ng.setIdCategoria(ctrlCategoria.read(rs.getInt("idCategoria")));
				ng.setIdDomicilio(ctrlDomicilio.read(rs.getInt("idDomicilio")));
				ng.setIdFotoPerfil(ctrlFotoPerfil.read(rs.getInt("idFotoPerfil")));
		
            }else ng=null;
        }catch (Exception e){

        }
        return ng;
	}

	public Negocio read(String nmb){

	Negocio ng=new Negocio();
        ResultSet rs = null;
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM Negocio WHERE nombre = ?");
            statement.setString(1,nmb);

            rs=statement.executeQuery();
            if (rs.next()){
                ng.setIdNegocio(rs.getInt("idNegocio"));
                ng.setNombre(rs.getString("nombre"));
				ng.setDescripcion(rs.getString("descripcion"));
				ng.setIdCategoria(ctrlCategoria.read(rs.getInt("idCategoria")));
				ng.setIdDomicilio(ctrlDomicilio.read(rs.getInt("idDomicilio")));
				ng.setIdFotoPerfil(ctrlFotoPerfil.read(rs.getInt("idFotoPerfil")));
		
            }else ng=null;
        }catch (Exception e){

        }
        return ng;
	}
}
