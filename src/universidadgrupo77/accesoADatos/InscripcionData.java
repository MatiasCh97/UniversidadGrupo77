/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package universidadgrupo77.accesoADatos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import universidadgrupo77.entidades.Alumno;
import universidadgrupo77.entidades.Inscripcion;
import universidadgrupo77.entidades.Materia;

/**
 *
 * @author Matias
 */
public class InscripcionData {

    private Connection con = null;

    public InscripcionData() {
        this.con = Conexion.getConexion();
    }
    private MateriaData matData = new MateriaData();
    private AlumnoData aluData = new AlumnoData();

    public void guardarInscripcion(Inscripcion insc) {
        String sql = "INSERT INTO inscripcion ( id_Alumno,idMateria, nota)" + "VALUES(?,?,?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, insc.getAlumno().getId_Alumno());
            ps.setInt(2, insc.getMateria().getIdMateria());
            ps.setDouble(3, insc.getNota());
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                insc.setIdInscripcion(rs.getInt(1));
                JOptionPane.showMessageDialog(null, "Inscripcion registrada");
            }
            ps.close();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla inscripcion");
        }
    }

    public void actualizarNota(int id_Alumno, int idMateria, double nota) {
        String sql = "UPDATE inscripcion SET nota=?"
                + " WHERE id_Alumno = ? AND idMateria =?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setDouble(1, nota);
            ps.setInt(2, id_Alumno);
            ps.setInt(3, idMateria);
            int filas = ps.executeUpdate();

            if (filas == 1) {
                JOptionPane.showMessageDialog(null, "Nota Actualizada");

            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla Inscripcion");
        }
    }

    public void borrarIncripcionMateriaAlumno(int id_Alumno, int idMateria) {
        String sql = "DELETE FROM  inscripcion  WHERE id_Alumno = ? AND idMateria=? ";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id_Alumno);
            ps.setInt(2, idMateria);
            int filas = ps.executeUpdate();
            if (filas > 0) {
                JOptionPane.showMessageDialog(null, "Inscripcion eliminada");
            }
            ps.close();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla Inscripcion");

        }

    }

    public List<Inscripcion> obtenerInscripciones() {
        String sql = "SELECT * FROM inscripcion";
        ArrayList<Inscripcion> cursadas = new ArrayList<>();
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Inscripcion insc = new Inscripcion();
                insc.setIdInscripcion(rs.getInt("idInscripto"));
                Alumno alu = aluData.buscarAlumno(rs.getInt("id_Alumno"));
                Materia mat = matData.buscarMateria(rs.getInt("idMateria"));
                insc.setAlumno(alu);
                insc.setMateria(mat);
                insc.setNota(rs.getDouble("nota"));
                cursadas.add(insc);

            }

            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla Inscripcion");

        }
        return cursadas;

    }

    public List<Inscripcion> obtenerInscripcionesPorAlumno(int id_Alumno) {
        String sql = "SELECT * FROM inscripcion WHERE id_Alumno =?";
        ArrayList<Inscripcion> cursadas = new ArrayList<>();
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id_Alumno);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Inscripcion insc = new Inscripcion();
                insc.setIdInscripcion(rs.getInt("idInscripto"));
                Alumno alu = aluData.buscarAlumno(rs.getInt("id_Alumno"));
                Materia mat = matData.buscarMateria(rs.getInt("idMateria"));
                insc.setAlumno(alu);
                insc.setMateria(mat);
                insc.setNota(rs.getDouble("nota"));
                cursadas.add(insc);

            }

            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla Inscripcion");

        }
        return cursadas;

    }

    public List<Materia> obetenerMateriasCursadas(int id_Alumno) {
       
        ArrayList<Materia> materias = new ArrayList<>();
       
        String sql = "SELECT inscripcion.idMateria, nombre, año FROM inscripcion,"+" materia WHERE inscripcion.idMateria = materia.idMateria"+" AND  inscripcion.id_Alumno = ?;";
        
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id_Alumno);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Materia materia = new Materia();
                materia.setIdMateria(rs.getInt("idMateria"));
                materia.setNombre(rs.getString("nombre"));
                materia.setAño(rs.getInt("año"));
                materias.add(materia);

            }

            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla Inscripcion");

        }
        return materias;

    }

    public List<Materia> obetenerMateriasNoCursadas(int id_Alumno) {
        ArrayList<Materia> materias = new ArrayList<>();
        String sql = "SELECT * FROM materia WHERE estado=1 AND idMateria not in " 
                + "(SELECT idMateria FROM inscripcion WHERE id_Alumno = ?)";
       
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id_Alumno);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Materia materia = new Materia();
                materia.setIdMateria(rs.getInt("idMateria"));
                materia.setNombre(rs.getString("nombre"));
                materia.setAño(rs.getInt("año"));
                materias.add(materia);

            }

            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla Inscripcion");

        }
        return materias;

    }

    public List<Alumno> obetenerAlumnoPorMateria(int idMateria) {
        String sql = "SELECT a.id+Alumno, dni, nombre, apellido, fechaNacimiento, estado FROM inscripcion i, alumno a WHERE i.id_Alumno = a.id_Alumno AND idMateria= ? AND a.estado =1";
        ArrayList<Alumno> alumnosMateria = new ArrayList<>();
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idMateria);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Alumno alumno = new Alumno();
                alumno.setId_Alumno(rs.getInt("id_Alumno"));
                alumno.setApellido(rs.getString("apellido"));
                alumno.setNombre(rs.getString("nombre"));
                alumno.setDate(rs.getDate("fechaNacimiento").toLocalDate());
                alumno.setEstado(rs.getBoolean("estado"));
                alumnosMateria.add(alumno);

            }

            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla Inscripcion");

        }
        return alumnosMateria;

    }
}
