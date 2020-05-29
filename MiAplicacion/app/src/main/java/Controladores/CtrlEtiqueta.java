package Controladores;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Modelos.Etiqueta;

public class CtrlEtiqueta {
    Connection connection;

    public CtrlEtiqueta(Connection connection) {
        this.connection = connection;
    }

    public void create(Etiqueta tqt) throws SQLException {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO Etiqueta (etiqueta) VALUES (?)");
            statement.setString(1, tqt.getEtiqueta());
            statement.execute();
        } catch (Exception e) {

        }
        connection.close();
    }

    public void edit(Etiqueta tqt) {
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE Etiqueta SET etiqueta = ? WHERE idEtiqueta = ?");
            statement.setString(1, tqt.getEtiqueta());
            statement.setInt(2, tqt.getIdEtiqueta());

            statement.execute();
        } catch (Exception e) {

        }
    }

    public Etiqueta read(int id) {
        Etiqueta tqt = new Etiqueta();
        ResultSet rs = null;
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT idEtiqueta, etiqueta FROM Etiqueta WHERE idEtiqueta = ?");
            statement.setInt(1, id);

            rs = statement.executeQuery();
            if (rs.next()) {
                tqt.setIdEtiqueta(rs.getInt("idEtiqueta"));
                tqt.setEtiqueta(rs.getString("etiqueta"));
            } else tqt = null;
        } catch (Exception e) {

        }
        return tqt;
    }

    public Etiqueta read(String etiqueta) {
        Etiqueta tqt = new Etiqueta();
        ResultSet rs = null;
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT idEtiqueta, etiqueta FROM Etiqueta WHERE etiqueta = ?");
            statement.setString(1, etiqueta);

            rs = statement.executeQuery();
            if (rs.next()) {
                tqt.setIdEtiqueta(rs.getInt("idEtiqueta"));
                tqt.setEtiqueta(rs.getString("etiqueta"));
            } else tqt = null;
        } catch (Exception e) {

        }
        return tqt;
    }

    public List<String> readAll() {
        ResultSet rs = null;
        List<String> lista = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT etiqueta FROM Etiqueta ORDER BY etiqueta");
            rs = statement.executeQuery();

            while (rs.next()) {
                lista.add(rs.getString("etiqueta"));
            }
        } catch (Exception e) {

        }
        return lista;
    }

}
