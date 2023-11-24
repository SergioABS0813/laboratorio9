package com.example.lab9.Daos;

import com.example.lab9.Beans.Curso;
import com.example.lab9.Beans.Evaluaciones;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EvaluacionesDao extends DaoBase{

    public Evaluaciones obtenerEvaxId(int cursoId) {

        String sql = "SELECT * FROM lab_9.evaluaciones where idevaluaciones = ?;";

        Evaluaciones evaluaciones = new Evaluaciones();

        try (Connection conn = this.getConection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, cursoId);

            try (ResultSet rs = pstmt.executeQuery()) {

                while (rs.next()) {
                    evaluaciones.setIdEvaluaciones(rs.getInt(1));
                    evaluaciones.setNombreEstudiante(rs.getString(2));
                    evaluaciones.setCodigoEstudiantes(rs.getString(3));
                    evaluaciones.setCorreoEstudiante(rs.getString(4));
                    evaluaciones.setNota(rs.getInt(5));
                    evaluaciones.setIdSemetre(rs.getInt(7));
                    evaluaciones.setFechaRegistro(rs.getString(8));
                    evaluaciones.setFechaEdicion(rs.getString(9));

                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return evaluaciones;
    }
}
