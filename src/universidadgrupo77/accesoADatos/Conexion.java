/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package universidadgrupo77.accesoADatos;

import javax.swing.JOptionPane;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Matias
 */
public class Conexion {

    private static final String URL = "jdbc:mariadb://localhost/";
    private static final String DB = "universidad";
    private static final String USUARIO = "root";
    private static final String PASSWORD = "";
    private static Connection connection;

    private Conexion() {
    }

    public static Connection getConexion() {

        if (connection == null) {
            try {
                Class.forName("org.mariadb.jdbc.Driver");
                connection = DriverManager.getConnection(URL + DB, USUARIO, PASSWORD);
                JOptionPane.showMessageDialog(null, "Conectado");
            } catch (ClassNotFoundException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error al cargar los drivers");

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Error al concectarse");
            }
        }
        return connection;
    }

}
