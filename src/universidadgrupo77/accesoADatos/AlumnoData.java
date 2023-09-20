/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package universidadgrupo77.accesoADatos;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import universidadgrupo77.entidades.Alumno;

/**
 *
 * @author Matias
 */
public class AlumnoData {

    private Connection con = null;

    public AlumnoData() {
        con = Conexion.getConexion();
    }

    public void guardarAlumno(Alumno alumno) {
        String sql = " INSERT INTO alumno (dni, apellido, nombre, fechaNacimiento, estado)" + "VALUES(?,?,?,?,?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, alumno.getDni());
            ps.setString(2, alumno.getApellido());
            ps.setString(3, alumno.getNombre());
            ps.setDate(4, Date.valueOf(alumno.getDate()));
            ps.setBoolean(5, alumno.isEstado());
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                alumno.setId_Alumno(rs.getInt(1));
                JOptionPane.showMessageDialog(null, "Alumno guardado");
            }
            ps.close();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla alumno");
        }
    }

    public void modificarAlumno(Alumno alumno) {
        String sql = "UPDATE alumno SET dni=?, apellido=?, nombre=?, fechaNacimiento=?"
                + " WHERE id_Alumno = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, alumno.getDni());
            ps.setString(2, alumno.getApellido());
            ps.setString(3, alumno.getNombre());
            ps.setDate(4, Date.valueOf(alumno.getDate()));
            ps.setInt(5, alumno.getId_Alumno());
            int exito = ps.executeUpdate();
            System.out.println(exito);

            if (exito == 1) {
                JOptionPane.showMessageDialog(null, "Alumno modificado");

            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla alumno");
        }
    }

    public void elminarAlumno(int id) {
        String sql = "UPDATE alumno SET estado = 0 WHERE id_Alumno = ? ";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            int exito = ps.executeUpdate();
            if (exito == 1) {
                JOptionPane.showMessageDialog(null, "Alumno eliminado");
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla alumno");
        }

    }

    public Alumno buscarAlumno(int id) {
        String sql = "SELECT dni, apellido, nombre, fechaNacimiento FROM alumno WHERE id_Alumno = ? AND estado = 1";
        Alumno alumno = null;
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                alumno = new Alumno();
                alumno.setId_Alumno(id);
                alumno.setNombre(rs.getString("nombre"));
                alumno.setApellido(rs.getString("apellido"));
                alumno.setDni(rs.getInt("dni"));
                alumno.setDate(rs.getDate("fechaNacimiento").toLocalDate());
                alumno.setEstado(true);
                JOptionPane.showMessageDialog(null, "ALUMNO encontrado");

            } else {
                JOptionPane.showMessageDialog(null, "No existe ese alumno");
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla alumno");

        }
        return alumno;

    }

    public Alumno buscarAlumnoPorDni(int dni) {
        String sql = "SELECT id_Alumno, dni, apellido, nombre, fechaNacimiento FROM alumno WHERE dni = ? AND estado = 1";
        Alumno alumno = null;
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, dni);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                alumno = new Alumno();
                alumno.setId_Alumno(rs.getInt("id_Alumno"));
                alumno.setDni(rs.getInt("dni"));
                alumno.setNombre(rs.getString("nombre"));
                alumno.setApellido(rs.getString("apellido"));
                alumno.setDni(rs.getInt("dni"));
                alumno.setDate(rs.getDate("fechaNacimiento").toLocalDate());
                alumno.setEstado(true);
                JOptionPane.showMessageDialog(null, "ALUMNO encontrado");

            } else {
                JOptionPane.showMessageDialog(null, "No existe ese alumno");
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla alumno");

        }
        return alumno;

    }

    public List<Alumno> listarAlumnos() {
        String sql = "SELECT dni, apellido, nombre, fechaNacimiento FROM alumno WHERE estado = 1";
        ArrayList<Alumno> alumnos = new ArrayList<>();
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Alumno alumno = new Alumno();
                alumno.setDni(rs.getInt("dni"));
                alumno.setNombre(rs.getString("nombre"));
                alumno.setApellido(rs.getString("apellido"));
                alumno.setDni(rs.getInt("dni"));
                alumno.setDate(rs.getDate("fechaNacimiento").toLocalDate());
                alumno.setEstado(true);

                alumnos.add(alumno);

            }

            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla alumno");

        }
        return alumnos;

    }
}
