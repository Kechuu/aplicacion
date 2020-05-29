package Controladores;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Modelo.Lugar;

public class CtrlLugar{

	public CtrlLugar(Connection connection){
		this.connection=connection;
	}

	public void crear(Lugar lgr){
        	ResultSet rs;
        	try{
	            PreparedStatement stmt = cnx.prepareStatement("INSERT INTO Lugar (nombre, nivel, de) VALUES (?, ?, ?)");
        	    stmt.setString(1, lgr.getNombre());
        	    stmt.setInt(2, lgr.getNivel());
        	    if(lgr.getDe() != null)
        	        stmt.setInt(3, lgr.getDe().getIdLugar());
        	    else
        	        stmt.setInt(3, 0);
            
        	    stmt.execute();
        	}
        	catch(SQLException ex){

	            JOptionPane.showMessageDialog(null, "Error al CREAR nuevo Lugar", "ERROR!!!...", ERROR_MESSAGE);
	        }
    	}
    
    public void editar(Lugar lgr)
    {
        try
        {
            PreparedStatement stmt = cnx.prepareStatement("UPDATE Lugar SET nombre = ?, de = ?, nivel = ? WHERE idLugar = ?");
            stmt.setString(1, lgr.getNombre());
            if(lgr.getDe() != null)
                stmt.setInt(2, lgr.getDe().getIdLugar());
            else
                stmt.setInt(2, 0);
            stmt.setInt(3, lgr.getNivel());
            stmt.setInt(4, lgr.getIdLugar());

            stmt.execute();
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Error al MODIFICAR Lugar", "ERROR!!!...", ERROR_MESSAGE);
        }
    }


	public Lugar leer(int id){
        Lugar lgr = new Lugar();
        ResultSet rs = null;
        try
        {
            PreparedStatement stmt = cnx.prepareStatement("SELECT nombre, de, nivel FROM Lugar WHERE idLugar = ?");
            stmt.setInt(1, id);

            rs = stmt.executeQuery();

            if(rs.next())
            {
                lgr.setNombre(rs.getString("nombre"));
                lgr.setDe(leer(rs.getInt("de")));
                lgr.setNivel(rs.getInt("nivel"));
                lgr.setIdLugar(id);
            }
            else
                lgr = null;
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Error al LEER Lugar", "ERROR!!!...", ERROR_MESSAGE);
        }

        return lgr;
    }
    
    public Lugar leer(String nmbr, int nvl)
    {
        Lugar lgr = new Lugar();
        ResultSet rs = null;
            
        try
        {
            PreparedStatement stmt = cnx.prepareStatement("SELECT idLugar, de FROM Lugar WHERE nombre = ? AND nivel = ?");
            stmt.setString(1, nmbr);
            stmt.setInt(2, nvl);

            rs = stmt.executeQuery();

            if(rs.next())
            {
                lgr.setNombre(nmbr);
                lgr.setDe(leer(rs.getInt("de")));
                lgr.setNivel(nvl);
                lgr.setIdLugar(rs.getInt("idLugar"));
            }
            else
                lgr = null;
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Error al LEER Lugar", "ERROR!!!...", ERROR_MESSAGE);
        }

        return lgr;
    }
    
    public Lugar leer(String nmbr, int nvl, int d)
    {
        Lugar lgr = new Lugar();
        ResultSet rs = null;
            
        try
        {
            PreparedStatement stmt = cnx.prepareStatement("SELECT idLugar FROM Lugar WHERE nombre = ? AND nivel = ? AND de = ?");
            stmt.setString(1, nmbr);
            stmt.setInt(2, nvl);
            stmt.setInt(3, d);

            rs = stmt.executeQuery();

            if(rs.next())
            {
                lgr.setNombre(nmbr);
                lgr.setDe(leer(d));
                lgr.setNivel(nvl);
                lgr.setIdLugar(rs.getInt("idLugar"));
            }
            else
                lgr = null;
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Error al LEER Lugar", "ERROR!!!...", ERROR_MESSAGE);
        }

        return lgr;
    }

    public List<String> leerTodos(int nvl)
    {
        List<String> lista = new ArrayList();
        ResultSet rs;
            
        try
        {
            PreparedStatement stmt = cnx.prepareStatement("SELECT nombre FROM Lugar WHERE nivel = ? ORDER BY nombre");
            stmt.setInt(1, nvl);

            rs = stmt.executeQuery();

            while(rs.next())
            {
                lista.add(rs.getString("nombre"));
            }
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Error al LEER Lugares", "ERROR!!!...", ERROR_MESSAGE);
        }

        return lista;
    }

    public List<String> leerTodosDe(Lugar lgr)
    {
        List<String> lista = new ArrayList();
        ResultSet rs;
            
        try
        {
            PreparedStatement stmt = cnx.prepareStatement("SELECT nombre FROM Lugar WHERE de = ?");
            stmt.setInt(1, lgr.getIdLugar());

            rs = stmt.executeQuery();

            while(rs.next())
            {
                lista.add(rs.getString("nombre"));
            }
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Error al LEER Lugares", "ERROR!!!...", ERROR_MESSAGE);
        }

        return lista;
    }
 

}
