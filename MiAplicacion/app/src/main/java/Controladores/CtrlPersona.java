package Controladores;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Modelos.Persona;

public class CtrlPersona {

    Connection cnx;
    CtrlDomicilio ctrlDomicilio;

    public CtrlPersona(Connection cnx){
        this.cnx = cnx;
        this.ctrlDomicilio = new CtrlDomicilio(cnx);
    }
    
    public void create(Persona prsn){
        ResultSet rs;
        try
        {
            PreparedStatement stmt = cnx.prepareStatement("INSERT INTO Persona (nombre, apellido, telefonoFijo, telefonoCelular, borrado, idDomicilio) VALUES (?, ?, ?, ?, ?, ?)");
            stmt.setString(1, prsn.getNombre());
            stmt.setString(2, prsn.getApellido());
	        stmt.setString(3, prsn.getTelefonoFijo());
	        stmt.setString(4, prsn.getTelefonoCelular());
            stmt.setBoolean(5, false);
            stmt.setInt(6, prsn.getIdDomicilio().getIdDomicilio());
            
            stmt.execute();

            rs = stmt.executeQuery("SELECT MAX(idPersona) AS id FROM Persona");
            rs.next();
            prsn.setIdPersona(rs.getInt("id"));
        }
        catch(SQLException ex)
        {
            //JOptionPane.showMessageDialog(null, "Error al CREAR nueva Persona", "ERROR!!!...", ERROR_MESSAGE);
        }
    }
    
    public void edit(Persona prsn)
    {
        try
        {
            PreparedStatement stmt = cnx.prepareStatement("UPDATE Persona SET nombre = ?, apellido = ?, telefonoFijo = ?, telefonoCelular = ? WHERE idPersona = ?");
            stmt.setString(1, prsn.getNombre());
            stmt.setString(2, prsn.getApellido());
	    stmt.setString(3, prsn.getTelefonoFijo());
	    stmt.setString(4, prsn.getTelefonoCelular());
            stmt.setInt(5, prsn.getIdPersona());
            stmt.execute();
        }
        catch(SQLException ex)
        {
            //JOptionPane.showMessageDialog(null, "Error al MODIFICAR Datos de Persona", "ERROR!!!...", ERROR_MESSAGE);
        }
    }

    public void delete(int id)
    {
        try
        {
            PreparedStatement stmt = cnx.prepareStatement("UPDATE Persona SET borrado = true WHERE idPersona = ?");
            stmt.setInt(1, id);

            stmt.execute();
        }
        catch(SQLException ex)
        {
            //JOptionPane.showMessageDialog(null, "Error al ELIMINAR Persona", "ERROR!!!...", ERROR_MESSAGE);
        }
    }

    public void recuperar(int id)
    {
        try
        {
            PreparedStatement stmt = cnx.prepareStatement("UPDATE Persona SET borrado = false WHERE idPersona = ?");
            stmt.setInt(1, id);

            stmt.execute();
        }
        catch(SQLException ex)
        {
            //JOptionPane.showMessageDialog(null, "Error al RECUPERAR Persona", "ERROR!!!...", ERROR_MESSAGE);
        }
    }

    public Persona read(int id)
    {
        Persona prsn = new Persona();
        ResultSet rs = null;
        try
        {
            PreparedStatement stmt = cnx.prepareStatement("SELECT * FROM Persona WHERE idPersona = ?");
            stmt.setInt(1, id);

            rs = stmt.executeQuery();

            if(rs.next())
            {
                prsn.setIdPersona(id);
                prsn.setNombre(rs.getString("nombre"));
		        prsn.setApellido(rs.getString("apellido"));
                prsn.setTelefonoFijo(rs.getString("telefonoFijo"));
		        prsn.setTelefonoCelular(rs.getString("telefonoCelular"));
		        prsn.setBorrado(rs.getBoolean("borrado"));
                prsn.setIdDomicilio(ctrlDomicilio.read(rs.getInt("idDomicilio")));
                
            }
            else
                prsn = null;
        }
        catch(SQLException ex)
        {
            //JOptionPane.showMessageDialog(null, "Error al LEER Persona", "ERROR!!!...", ERROR_MESSAGE);
        }

        return prsn;
    }
    

    public Persona read(String nmbr, String plld)
    {
        Persona prsn = new Persona();
        ResultSet rs;
        try
        {
            PreparedStatement stmt = cnx.prepareStatement
        ("SELECT * FROM Persona WHERE nombre = ? AND apellido = ?");
            stmt.setString(1, nmbr);
            stmt.setString(2, plld);
            rs = stmt.executeQuery();
            
            if(rs.next())
            {
                prsn = new Persona();
                prsn.setIdPersona(rs.getInt("idPersona"));
                prsn.setNombre(rs.getString("nombre"));
		prsn.setApellido(rs.getString("apellido"));
		prsn.setTelefonoFijo(rs.getString("telefonoFijo"));
		prsn.setTelefonoCelular(rs.getString("telefonoCelular"));
		prsn.setBorrado(rs.getBoolean("borrado"));
                prsn.setIdDomicilio(ctrlDomicilio.read(rs.getInt("idDomicilio")));
                
            }
        }
        catch(SQLException ex)
        {
            //JOptionPane.showMessageDialog(null, "Error al LEER Persona", "ERROR!!!...", ERROR_MESSAGE);
        }

        return prsn;
    }
    
    public void actualizarDomicilio(int idDmclo, int idPrsn){
         try
        {
            PreparedStatement stmt = cnx.prepareStatement("UPDATE Persona SET iDomicilio = ?  WHERE idPersona = ?");
            stmt.setInt(1, idDmclo);
            stmt.setInt(2, idPrsn);
            
            stmt.execute();
        }
        catch(SQLException ex)
        {
            //JOptionPane.showMessageDialog(null, "Error al MODIFICAR Datos de Persona", "ERROR!!!...", ERROR_MESSAGE);
        }
    }

}
