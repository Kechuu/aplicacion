package Controladores;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Modelo.Domicilio;

public class CtrlDomicilio {
    Connection cnx;
    CtrlLugar ctrlLugar;
    //CtrlEdificio ctrlEdificio;
    
    public CtrlDomicilio(Connection cnx)
    {
        this.cnx = cnx;
    }
    
    public void crear(Domicilio dmcl)
    {
        ResultSet rs;
        try
        {
            PreparedStatement stmt = cnx.prepareStatement("INSERT INTO Domicilio (idLugar, nroLote, idEdificio) VALUES (?, ?, ?)");
            stmt.setInt(1, dmcl.getLugar().getIdLugar());
            stmt.setString(2, dmcl.getNroLote());
            if(dmcl.getEdificio()!=null)
                //stmt.setInt(3, dmcl.getEdificio().getIdEdificio());
		stmt.setInt(3, dmcl.getIdEdificio());
            else
                stmt.setInt(3, 0);
           
            stmt.execute();
            rs = stmt.executeQuery("SELECT MAX(idDomicilio) AS id FROM Domicilio");
            rs.next();
            dmcl.setIdDomicilio(rs.getInt("id"));
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Error al CREAR nuevo Domicilio", "ERROR!!!...", ERROR_MESSAGE);
        }
    }
    
    public void editar(Domicilio dmcl)
    {
        try
        {
            PreparedStatement stmt = cnx.prepareStatement("UPDATE Domicilio SET idLugar = ?, nroLote = ?, idEdificio = ? WHERE idDomicilio = ?");
            stmt.setInt(1, dmcl.getLugar().getIdLugar());
            stmt.setString(2, dmcl.getNroLote());
            if(dmcl.getEdificio()!=null)
                //stmt.setInt(3, dmcl.getEdificio().getIdEdificio());
		stmt.setInt(3,dmcl.getIdEdificio());
            else
                stmt.setInt(3, 0);
            stmt.setInt(4, dmcl.getIdDomicilio());
            
            stmt.execute();
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Error al MODIFICAR Domicilio", "ERROR!!!...", ERROR_MESSAGE);
        }
    }


    public Domicilio leer(int id)
    {
        Domicilio dmcl = new Domicilio();
        ResultSet rs = null;
        try
        {
            PreparedStatement stmt = cnx.prepareStatement("SELECT idLugar, nroLote, idEdificio FROM Domicilio WHERE idDomicilio = ?");
            stmt.setInt(1, id);

            rs = stmt.executeQuery();

            if(rs.next())
            {
                dmcl.setIdDomicilio(id);
                dmcl.setLugar(ctrlLugar.leer(rs.getInt("idLugar")));
                dmcl.setNroLote(rs.getString("nroLote"));
                //dmcl.setidEdificio(ctrlEdificio.leer(rs.getInt("edificio")));
                dmcl.setIdEdificio(rs.getInt("idEdificio"));
            }
            else
                dmcl = null;
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Error al LEER Domicilio", "ERROR!!!...", ERROR_MESSAGE);
        }

        return dmcl;
    }
    
    public Domicilio leer(int lgr, String nmr, int dfc)
    {
        Domicilio dmcl = new Domicilio();
        ResultSet rs = null;
            
        try
        {
            PreparedStatement stmt = cnx.prepareStatement("SELECT idDomicilio, idLugar, nroLote, idEdificio FROM Domicilio WHERE idLugar = ? AND nroLote = ? AND idEdificio = ?");
            stmt.setInt(1, lgr);
            stmt.setString(2, nmr);
            stmt.setInt(3, dfc);

            rs = stmt.executeQuery();

            if(rs.next())
            {
                dmcl.setIdDomicilio(rs.getInt("idDomicilio"));
                dmcl.setLugar(ctrlLugar.leer(rs.getInt("idLugar")));
                dmcl.setNroLote(rs.getString("nroLote"));
                //dmcl.setIdEdificio(ctrlEdificio.leer(rs.getInt("edificio")));
                dmcl.setIdEdificio(rs.getInt("idEdificio"));
            }
            else
                dmcl = null;
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Error al LEER Domicilio", "ERROR!!!...", ERROR_MESSAGE);
        }

        return dmcl;
    }

}
