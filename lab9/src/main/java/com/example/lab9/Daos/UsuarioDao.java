package com.example.lab9.Daos;

import com.example.lab9.Beans.Curso;
import com.example.lab9.Beans.Evaluaciones;
import com.example.lab9.Beans.Usuario;

import java.sql.*;
import java.util.ArrayList;

public class UsuarioDao extends DaoBase{

    public ArrayList<Evaluaciones> listaEvaluaciones() { //Listado de todos las evaluaciones
        ArrayList<Evaluaciones> listaEvaluaciones = new ArrayList<>();

        String sql = "SELECT * FROM lab_9.evaluaciones;";
        try (Connection conn = this.getConection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Evaluaciones evaluaciones = new Evaluaciones();
                evaluaciones.setIdEvaluaciones(rs.getInt(1));
                evaluaciones.setNombreEstudiante(rs.getString(2));
                evaluaciones.setCodigoEstudiantes(rs.getString(3));
                evaluaciones.setCorreoEstudiante(rs.getString(4));
                evaluaciones.setNota(rs.getInt(5));
                evaluaciones.setIdSemetre(rs.getInt(7)); // Luego pasara a ser el nombre

                listaEvaluaciones.add(evaluaciones);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return listaEvaluaciones;
    }

    public ArrayList<Curso> listaCursos() { //Listado de todos los cursos
        ArrayList<Curso> listaCursos = new ArrayList<>();

        String sql = "SELECT * FROM lab_9.curso;";
        try (Connection conn = this.getConection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Curso curso = new Curso();
                curso.setIdCurso(rs.getInt(1));
                curso.setCodigoCurso(rs.getString(2));
                curso.setNombreCurso(rs.getString(3));
                curso.setFechaRegistro(rs.getString(5));
                curso.setFechaEdicion(rs.getString(6));

                listaCursos.add(curso);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return listaCursos;
    }

    public ArrayList<Usuario> listaDocentesTotal() { //vista decano
        ArrayList<Usuario> listaDocentes = new ArrayList<>();

        String sql = "SELECT * FROM lab_9.usuario where idrol = 4;";

        try (Connection conn = this.getConection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Usuario docente = new Usuario();
                docente.setIdUsuario(rs.getInt(1));
                docente.setNombre(rs.getString(2));
                docente.setCorreo(rs.getString(3));
                docente.setUltimoIngreso(rs.getString(6));
                docente.setCantidadIngresos(rs.getInt(7));
                docente.setFechaRegistro(rs.getString(8));
                docente.setFechaEdicion(rs.getString(9));

                listaDocentes.add(docente);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return listaDocentes;
    }


    public ArrayList<Usuario> listaDocentesDisponibles() { //Combobox
        ArrayList<Usuario> listaDocentes = new ArrayList<>();

        String sql = "SELECT * FROM lab_9.usuario u\n" +
                "left join curso_has_docente c on u.idusuario = c.iddocente where c.idcurso is null and u.idrol = 4;";

        try (Connection conn = this.getConection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Usuario docentedisponible = new Usuario();
                docentedisponible.setIdUsuario(rs.getInt(1));
                docentedisponible.setNombre(rs.getString(2));
                docentedisponible.setCorreo(rs.getString(3));
                docentedisponible.setFechaRegistro(rs.getString(8));

                listaDocentes.add(docentedisponible);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return listaDocentes;
    }


}
