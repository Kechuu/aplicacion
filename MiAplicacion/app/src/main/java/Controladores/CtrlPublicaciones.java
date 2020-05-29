package Controladores;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import Modelos.Publicaciones;


public class CtrlPublicaciones{

	Connection connection;
	CtrlNegocio ctrlNegocio;
	CtrlEtiqueta ctrlEtiqueta;
	CtrlCategoria ctrlCategoria;
	CtrlFoto ctrlFoto;

	public CtrlPublicaciones(Connection connection){
	  this.connection=connection;
	  ctrlNegocio = new CtrlNegocio(connection);
	  ctrlEtiqueta = new CtrlEtiqueta(connection);
	  ctrlCategoria = new CtrlCategoria(connection);
	  ctrlFoto = new CtrlFoto(connection);
	}


	public void create(Publicaciones pblc){
	  try{
		PreparedStatement stmt = connection.prepareStatement("INSERT INTO Publicaciones (titulo, descripcion, precio, negocio, like, stock, idEtiqueta, idCategoria, idFoto) VALUES (?, ?, ? , ?, ?, ?, ?, ?, ?)");

		stmt.setString(1, pblc.getTitulo());
		stmt.setString(2, pblc.getDescripcion());
		stmt.setFloat(3, pblc.getPrecio());
		stmt.setInt(4, pblc.getIdNegocio().getIdNegocio());
		stmt.setInt(5, pblc.getLike());
		stmt.setBoolean(6, true);
		stmt.setInt(7, pblc.getIdEtiqueta().getIdEtiqueta());
		stmt.setInt(8, pblc.getIdCategoria().getIdCategoria());
		
		stmt.execute();		
	  }
	  catch (Exception e){

          }
	}

	
	public void edit(Publicaciones pblc){

	  try{
		  PreparedStatement stmt = connection.prepareStatement("UPDATE Publicaciones SET titulo =?, descripcion =?, precio =?, stock = ?, idEtiqueta = ?, idCategoria=?, idFoto=? WHERE idPublicaciones =?");

		  stmt.setString(1,pblc.getTitulo());
		  stmt.setString(2,pblc.getDescripcion());
		  stmt.setFloat(3,pblc.getPrecio());
		  stmt.setBoolean(4, false);
		  stmt.setInt(5, pblc.getIdEtiqueta().getIdEtiqueta());
		  stmt.setInt(6,pblc.getIdCategoria().getIdCategoria());
		  stmt.setBlob(7,pblc.getIdFoto().getFoto());

		  stmt.setInt(8, pblc.getIdPublicaciones());
	 	  stmt.execute();	
	  }
	  catch (Exception e){

          }

	}


	public List<Vector>read(int tqt, int ctg){
	  ResultSet rs=null;
	  List<Vector> lista = new ArrayList<>();
	  Vector<String> nodo = new Vector();
	  
	  try{
		PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Publicaciones WHERE idEtiqueta = ? AND idCategoria = ?");
		stmt.setInt(1, tqt);
		stmt.setInt(2, ctg);
		rs=stmt.executeQuery();

		while(rs.next()){
			
		  nodo=new Vector();
		  
		  nodo.add(rs.getString("titulo"));
		  nodo.add(rs.getString("descripcion"));
		  nodo.add(String.valueOf(rs.getFloat("precio")));
		  nodo.add(String.valueOf(ctrlNegocio.read(rs.getInt("negocio")).getNombre()));
		  
		  lista.add(nodo);
		}
	  }
	  catch (Exception e){

          }
	  return lista;
	}

	public List<Vector>read(int ng){
	  ResultSet rs=null;
	  List<Vector> lista = new ArrayList<>();
	  Vector<String> nodo= new Vector();

	  try{
		PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Publicaciones WHERE negocio = ?");
		stmt.setInt(1, ng);
		rs=stmt.executeQuery();

		while(rs.next()){
			nodo = new Vector();
			
			nodo.add(rs.getString("titulo"));
		    nodo.add(rs.getString("descripcion"));
			nodo.add(String.valueOf(rs.getFloat("precio")));
			nodo.add(String.valueOf(ctrlNegocio.read(rs.getInt("negocio"))));

			lista.add(nodo);
		}
	  }
	  catch (Exception e){

          }
	  return lista;
	}

}
