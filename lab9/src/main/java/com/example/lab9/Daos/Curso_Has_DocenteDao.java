package com.example.lab9.Daos;

import com.example.lab9.Beans.Curso;

import java.sql.*;

public class Curso_Has_DocenteDao extends DaoBase{

    public Curso buscarCursoxIdDoc(int idDoc) {

        String sql = "SELECT * FROM lab_9.curso_has_docente chd left join curso c on (chd.idcurso = c.idcurso) where iddocente = ?;";

        int idCurso = 0;

        Curso curso = new Curso();

        try (Connection conn = this.getConection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, idDoc);

            try (ResultSet rs = pstmt.executeQuery()) {

                while (rs.next()) {
                    curso.setIdCurso(rs.getInt(1));
                    curso.setNombreCurso(rs.getString(5));

                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return curso;
    }

}
