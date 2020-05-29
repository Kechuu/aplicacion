package Controladores;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Modelos.Domicilio;

public class CtrlDomicilio {
    Connection cnx;
    CtrlLugar ctrlLugar;
    //CtrlEdificio ctrlEdificio;
    
    public CtrlDomicilio(Connection cnx)
    {
        this.cnx = cnx;
    }
    
    public void create(Domicilio dmcl)
    {
        ResultSet rs;
        try {
            PreparedStatement stmt = cnx.prepareStatement("INSERT INTO Domicilio (idLugar, nroLote, idEdificio) VALUES (?, ?, ?)");
            stmt.setInt(1, dmcl.getIdLugar().getIdLugar());
            stmt.setInt(2, dmcl.getNroLote());
            stmt.setInt(3, dmcl.getIdEdificio());

            stmt.execute();
            rs = stmt.executeQuery("SELECT MAX(idDomicilio) AS id FROM Domicilio");
            rs.next();
            dmcl.setIdDomicilio(rs.getInt("id"));
        }

        catch(SQLException ex)
        {
            //JOptionPane.showMessageDialog(null, "Error al CREAR nuevo Domicilio", "ERROR!!!...", ERROR_MESSAGE);
        }

    }
    
    public void edit(Domicilio dmcl)
    {
        try
        {
            PreparedStatement stmt = cnx.prepareStatement("UPDATE Domicilio SET idLugar = ?, nroLote = ?, idEdificio = ? WHERE idDomicilio = ?");
            stmt.setInt(1, dmcl.getIdLugar().getIdLugar());
            stmt.setInt(2, dmcl.getNroLote());
		    stmt.setInt(3,dmcl.getIdEdificio());

		    stmt.setInt(4, dmcl.getIdDomicilio());
            
            stmt.execute();
        }
        catch(SQLException ex)
        {
            //JOptionPane.showMessageDialog(null, "Error al MODIFICAR Domicilio", "ERROR!!!...", ERROR_MESSAGE);
        }
    }


    public Domicilio read(int id)
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
                dmcl.setIdLugar(ctrlLugar.read(rs.getInt("idLugar")));
                dmcl.setNroLote(rs.getInt("NroLote"));
                //dmcl.setidEdificio(ctrlEdificio.leer(rs.getInt("edificio")));
                dmcl.setIdEdificio(rs.getInt("idEdificio"));
            }
            else
                dmcl = null;
        }
        catch(SQLException ex)
        {
            //JOptionPane.showMessageDialog(null, "Error al LEER Domicilio", "ERROR!!!...", ERROR_MESSAGE);
        }

        return dmcl;
    }
    
    public Domicilio read(int lgr, String nmr, int dfc)
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
                dmcl.setIdLugar(ctrlLugar.read(rs.getInt("idLugar")));
                dmcl.setNroLote(rs.getInt("nroLote"));
                //dmcl.setIdEdificio(ctrlEdificio.leer(rs.getInt("edificio")));
                dmcl.setIdEdificio(rs.getInt("idEdificio"));
            }
            else
                dmcl = null;
        }
        catch(SQLException ex)
        {
            //JOptionPane.showMessageDialog(null, "Error al LEER Domicilio", "ERROR!!!...", ERROR_MESSAGE);
        }
        return dmcl;
    }

}
