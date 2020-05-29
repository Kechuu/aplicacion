package Controladores;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import Modelos.CategoriaEtiqueta;


public class CtrlCategoriaEtiqueta{

	Connection connection;
	CtrlCategoria ctrlCategoria;
	CtrlEtiqueta ctrlEtiqueta;

	public CtrlCategoriaEtiqueta(Connection connection){
	  this.connection=connection;

	  ctrlCategoria=new CtrlCategoria(connection);
	  ctrlEtiqueta=new CtrlEtiqueta(connection);
	}

	public void create(CategoriaEtiqueta ctg){
	  try{
		PreparedStatement stmt = connection.prepareStatement("INSERT INTO CategoriaEtiqueta (idCategoria, idEtiqueta) VALUES (?, ?)");
		stmt.setInt(1, ctg.getIdCategoria().getIdCategoria());
		stmt.setInt(2, ctg.getIdEtiqueta().getIdEtiqueta());

		stmt.execute();
	  }
	  catch (Exception e){

          }
	}




}
