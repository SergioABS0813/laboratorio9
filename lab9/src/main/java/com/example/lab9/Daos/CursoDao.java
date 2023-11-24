package com.example.lab9.Daos;

import com.example.lab9.Beans.Curso;
import com.example.lab9.Beans.Evaluaciones;
import com.example.lab9.Beans.Usuario;
import com.example.lab9.Dto.CursoDto;

import java.sql.*;
import java.util.ArrayList;

public class CursoDao extends DaoBase{


    public Curso obtenerCursoxId(int cursoId) {

        String sql = "SELECT * FROM lab_9.curso where idCurso = ?;";

        Curso curso = new Curso();

        try (Connection conn = this.getConection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, cursoId);

            try (ResultSet rs = pstmt.executeQuery()) {

                while (rs.next()) {
                    curso.setIdCurso(rs.getInt(1));
                    curso.setCodigoCurso(rs.getString(2));
                    curso.setNombreCurso(rs.getString(3));
                    curso.setIdFacultad(rs.getInt(4));
                    curso.setFechaRegistro(rs.getString(5));
                    curso.setFechaEdicion(rs.getString(6));

                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return curso;
    }

    public int proximoIdCurso() {
        int proximoid = 0;

        String sql = "SELECT * FROM lab_9.curso;";

        try (Connection conn = this.getConection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                proximoid++;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return proximoid;
    }

    public ArrayList<CursoDto> listaCursoconEvaluaciones() { // borrados
        ArrayList<CursoDto> listaCursoyEvaluaciones = new ArrayList<>();

        String sql = "SELECT c.idcurso, c.codigo, c.nombre, c.idfacultad, c.fecha_registro, c.fecha_edicion, count(e.idevaluaciones) as cantidadEvaluaciones FROM lab_9.curso c left join evaluaciones e on c.idcurso = e.idcurso group by c.idcurso;";

        try (Connection conn = this.getConection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                CursoDto curso = new CursoDto();
                curso.setIdCurso(rs.getInt(1));
                curso.setCodigoCurso(rs.getString(2));
                curso.setNombreCurso(rs.getString(3));
                curso.setIdFacultad(rs.getInt(4));
                curso.setFechaRegistro(rs.getString(5));
                curso.setFechaEdicion(rs.getString(6));
                curso.setCantidadEvaluaciones(rs.getInt(7)); //nos sirve para verificar si está o no asignado a un curso (Si es cero entonces no)

                listaCursoyEvaluaciones.add(curso);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return listaCursoyEvaluaciones;
    }

    public void borrarCurso(int cursoId) {
        String sql = "delete FROM lab_9.curso_has_docente where idcurso = ?;\n" +
                "delete from lab_9.curso where idcurso = ?;";

        try (Connection conn = getConection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, cursoId);
            pstmt.setInt(2, cursoId);
            pstmt.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }


    public void registroCurso(Curso curso){

        String sql = "INSERT INTO curso (idcurso, codigo, nombre, idfacultad, fecha_registro, fecha_edicion ) VALUES (?, ?, ?, ?, ?, ?);\n" +
                "insert into curso_has_docente (idcurso, iddocente) values (?,?);"; //REVISAR SQL

        try (Connection conn = getConection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1,curso.getIdCurso());
            pstmt.setString(2,curso.getCodigoCurso());
            pstmt.setString(3, curso.getNombreCurso());
            pstmt.setInt(4, curso.getIdFacultad());
            pstmt.setString(5, curso.getFechaRegistro());
            pstmt.setString(6, curso.getFechaEdicion());
            pstmt.setInt(7,curso.getIdCurso());
            pstmt.setInt(8, curso.getDocente().getIdUsuario());

            pstmt.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }



}
