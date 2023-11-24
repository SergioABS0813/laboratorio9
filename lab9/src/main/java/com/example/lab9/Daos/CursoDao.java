package com.example.lab9.Daos;

import com.example.lab9.Beans.Curso;

import java.sql.*;

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



}
