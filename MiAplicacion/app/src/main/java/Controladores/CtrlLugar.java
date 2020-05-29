package Controladores;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Modelos.Lugar;

public class CtrlLugar{

	Connection connection;

	public CtrlLugar(Connection connection){
		this.connection=connection;
	}

	public void create(Lugar lgr){
        	ResultSet rs;
        	try{
	            PreparedStatement stmt = connection.prepareStatement("INSERT INTO Lugar (nombre, nivel, de) VALUES (?, ?, ?)");
        	    stmt.setString(1, lgr.getNombre());
        	    stmt.setInt(2, lgr.getNivel());
        	    stmt.setInt(3, lgr.getDe());

        	    stmt.execute();
        	}
        	catch(SQLException ex){

	           // JOptionPane.showMessageDialog(null, "Error al CREAR nuevo Lugar", "ERROR!!!...", ERROR_MESSAGE);
	        }
    	}
    
    public void edit(Lugar lgr)
    {
        try
        {
            PreparedStatement stmt = connection.prepareStatement("UPDATE Lugar SET nombre = ?, de = ?, nivel = ? WHERE idLugar = ?");
            stmt.setString(1, lgr.getNombre());
            stmt.setInt(2,  lgr.getDe());
            stmt.setInt(3, lgr.getNivel());
            stmt.setInt(4, lgr.getIdLugar());

            stmt.execute();
        }
        catch(SQLException ex)
        {
            //JOptionPane.showMessageDialog(null, "Error al MODIFICAR Lugar", "ERROR!!!...", ERROR_MESSAGE);
        }
    }


	public Lugar read(int id){
        Lugar lgr = new Lugar();
        ResultSet rs = null;
        try
        {
            PreparedStatement stmt = connection.prepareStatement("SELECT nombre, de, nivel FROM Lugar WHERE idLugar = ?");
            stmt.setInt(1, id);

            rs = stmt.executeQuery();

            if(rs.next())
            {
                lgr.setNombre(rs.getString("nombre"));
                lgr.setDe(rs.getInt("de"));
                lgr.setNivel(rs.getInt("nivel"));
                lgr.setIdLugar(id);
            }
            else
                lgr = null;
        }
        catch(SQLException ex)
        {
            //JOptionPane.showMessageDialog(null, "Error al LEER Lugar", "ERROR!!!...", ERROR_MESSAGE);
        }

        return lgr;
    }
    
    public Lugar read(String nmbr, int nvl)
    {
        Lugar lgr = new Lugar();
        ResultSet rs = null;
            
        try
        {
            PreparedStatement stmt = connection.prepareStatement("SELECT idLugar, de FROM Lugar WHERE nombre = ? AND nivel = ?");
            stmt.setString(1, nmbr);
            stmt.setInt(2, nvl);

            rs = stmt.executeQuery();

            if(rs.next())
            {
                lgr.setNombre(nmbr);
                lgr.setDe(rs.getInt("de"));
                lgr.setNivel(nvl);
                lgr.setIdLugar(rs.getInt("idLugar"));
            }
            else
                lgr = null;
        }
        catch(SQLException ex)
        {
            //JOptionPane.showMessageDialog(null, "Error al LEER Lugar", "ERROR!!!...", ERROR_MESSAGE);
        }

        return lgr;
    }
    
    public Lugar read(String nmbr, int nvl, int d)
    {
        Lugar lgr = new Lugar();
        ResultSet rs = null;
            
        try
        {
            PreparedStatement stmt = connection.prepareStatement("SELECT idLugar FROM Lugar WHERE nombre = ? AND nivel = ? AND de = ?");
            stmt.setString(1, nmbr);
            stmt.setInt(2, nvl);
            stmt.setInt(3, d);

            rs = stmt.executeQuery();

            if(rs.next())
            {
                lgr.setNombre(nmbr);
                lgr.setDe(read(d).getDe());
                lgr.setNivel(nvl);
                lgr.setIdLugar(rs.getInt("idLugar"));
            }
            else
                lgr = null;
        }
        catch(SQLException ex)
        {
//            JOptionPane.showMessageDialog(null, "Error al LEER Lugar", "ERROR!!!...", ERROR_MESSAGE);
        }

        return lgr;
    }

    public List<String> readAll(int nvl)
    {
        List<String> lista = new ArrayList();
        ResultSet rs;
            
        try
        {
            PreparedStatement stmt = connection.prepareStatement("SELECT nombre FROM Lugar WHERE nivel = ? ORDER BY nombre");
            stmt.setInt(1, nvl);

            rs = stmt.executeQuery();

            while(rs.next())
            {
                lista.add(rs.getString("nombre"));
            }
        }
        catch(SQLException ex)
        {
            //JOptionPane.showMessageDialog(null, "Error al LEER Lugares", "ERROR!!!...", ERROR_MESSAGE);
        }

        return lista;
    }

    public List<String> readAllFor(Lugar lgr)
    {
        List<String> lista = new ArrayList();
        ResultSet rs;
            
        try
        {
            PreparedStatement stmt = connection.prepareStatement("SELECT nombre FROM Lugar WHERE de = ?");
            stmt.setInt(1, lgr.getIdLugar());

            rs = stmt.executeQuery();

            while(rs.next())
            {
                lista.add(rs.getString("nombre"));
            }
        }
        catch(SQLException ex)
        {
            //JOptionPane.showMessageDialog(null, "Error al LEER Lugares", "ERROR!!!...", ERROR_MESSAGE);
        }

        return lista;
    }
 

}
