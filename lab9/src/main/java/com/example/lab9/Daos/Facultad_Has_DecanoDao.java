package com.example.lab9.Daos;

import com.example.lab9.Beans.Curso;
import com.example.lab9.Beans.Facultad;
import com.example.lab9.Beans.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Facultad_Has_DecanoDao extends DaoBase{


    public Usuario buscarFacultadxIdDecano(int idDecano) {

        String sql = "SELECT * FROM lab_9.usuario u left join facultad_has_decano fhd on u.idusuario = fhd.iddecano left join facultad f on  fhd.idfacultad = f.idfacultad where u.idusuario = ?;";

        int idCurso = 0;

        Usuario usuario = new Usuario();

        try (Connection conn = this.getConection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, idDecano);

            try (ResultSet rs = pstmt.executeQuery()) {

                while (rs.next()) {
                    Facultad facultad = new Facultad();
                    facultad.setIdFacultad(rs.getInt("idfacultad"));
                    facultad.setNombreFacultad(rs.getString(13));

                    usuario.setFacultad(facultad);

                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return usuario;
    }

}
